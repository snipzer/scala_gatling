package action

import java.io.StringReader

import io.gatling.core.Predef._
import service._
import io.gatling.core.structure.ChainBuilder

class LoginAction(apiService: ApiService) {



  def execute(entrepriseId: String, centreId: String, medecinId: String, secretaireId: String): ChainBuilder = exec(apiService.login("loic.arif@sstrn.fr", "azertyazerty").resources(
    apiService.populateUser("loic.arif@sstrn.fr"),
    apiService.populateFormeJuridique(),
    apiService.populatePerimetre(),
    apiService.populateCodeNaf(),
    apiService.populateCodeCsp(),
    apiService.populateEntreprises()
  )).exec(apiService.getEntreprise(s"$entrepriseId").resources(
      apiService.populateFeedRss(),
      apiService.getCentre(s"$centreId"),
      apiService.checkRight(s"$entrepriseId"),
      apiService.getSecretaire(s"$secretaireId"),
      apiService.getMedecin(s"$medecinId"),
      apiService.findFacture(s"$entrepriseId", "A REGLER"),
      apiService.findDeclaration(s"$entrepriseId"),
      apiService.findDemandeVisibilite(s"$entrepriseId"),
      apiService.findContrat(s"$entrepriseId", "ACTIF")
    ))
}



//  val execute: ChainBuilder = exec(ApiService.login("loic.arif@sstrn.fr", "azertyazerty").resources(
//    ApiService.populateUser("loic.arif@sstrn.fr"),
//    ApiService.populateFormeJuridique(),
//    ApiService.populatePerimetre(),
//    ApiService.populateCodeNaf(),
//    ApiService.populateCodeCsp(),
//    ApiService.populateEntreprises())
//  ).exec({ session =>
//    val listEntreprise: List[Entreprise] = EntrepriseJsonReader.read(new StringReader(session("entreprises").as[String]))
//    val entreprise: Entreprise = listEntreprise.head
//    exec(ApiService.getEntreprise(entreprise.id).resources(
//      ApiService.getEntreprise(entreprise.id),
//      ApiService.populateFeedRss(),
//      ApiService.getCentre(entreprise.centreId),
//      ApiService.checkRight(entreprise.id),
//      ApiService.getSecretaire(entreprise.secretaireId),
//      ApiService.getMedecin(entreprise.medecinId),
//      ApiService.findFacture(entreprise.id, "A REGLER"),
//      ApiService.findDeclaration(entreprise.id),
//      ApiService.findDemandeVisibilite(entreprise.id),
//      ApiService.findContrat(entreprise.id, "ACTIF"))
//    )
//    session
//  })