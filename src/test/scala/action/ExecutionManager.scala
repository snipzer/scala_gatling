package action

import java.io.StringReader

import entity.Entreprise
import io.gatling.core.Predef._
import service._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.request.builder.HttpRequestBuilder

class ExecutionManager(httpRequestBuilderService: HttpRequestBuilderService) {

  val TEXT_PLAIN_HEADER = Map("content-type" -> "text/plain")

  def executeLogin: ChainBuilder = exec(login).pause(3)
    .exec(session => populateSession(session)).pause(3)
    .exec(home).pause(3)

  def executeAdherent: ChainBuilder = exec(adherent).pause(3)

  def executeHome: ChainBuilder = exec(httpRequestBuilderService.goToHome).pause(3)
    .exec(home).pause(3)

  def executeLogout: ChainBuilder = exec(httpRequestBuilderService.initialLogout).pause(3)
    .exec(httpRequestBuilderService.cleanerLogout).pause(3)

  private def login: HttpRequestBuilder = {
    httpRequestBuilderService.login.resources(
      httpRequestBuilderService.populateUser,
      httpRequestBuilderService.populateFormeJuridique,
      httpRequestBuilderService.populatePerimetre,
      httpRequestBuilderService.populateCodeNaf,
      httpRequestBuilderService.populateCodeCsp,
      httpRequestBuilderService.populateEntreprises
    )
  }

  private def populateSession(session: Session): Session = {
      val entreprise: Entreprise = EntrepriseJsonReader.read(new StringReader(session("entreprises").as[String])).head
      session.set("entrepriseId", entreprise.id)
        .set("medecinId", entreprise.medecinId)
        .set("centreId", entreprise.centreId)
        .set("secretaireId", entreprise.secretaireId)
  }

  private def home: HttpRequestBuilder = {
    httpRequestBuilderService.populateEntreprises.resources(
      httpRequestBuilderService.getEntreprise,
      httpRequestBuilderService.populateFeedRss,
      httpRequestBuilderService.getCentre,
      httpRequestBuilderService.checkRight,
      httpRequestBuilderService.getSecretaire,
      httpRequestBuilderService.getMedecin,
      httpRequestBuilderService.findFacture,
      httpRequestBuilderService.findDeclaration,
      httpRequestBuilderService.findDemandeVisibilite,
      httpRequestBuilderService.findContrat
    )
  }

  private def adherent: HttpRequestBuilder = httpRequestBuilderService.goToAdherent.resources(httpRequestBuilderService.populateEntreprises)
}