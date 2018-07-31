package service

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import util.RouteMapperEnum

class HttpRequestBuilderService() {
  val TEXT_PLAIN_HEADER = Map("content-type" -> "text/plain")

  def populateUser: HttpRequestBuilder = http(RouteMapperEnum.POPULATE_USER.getName).get(RouteMapperEnum.POPULATE_USER.getUrl + "${email}")

  def populateFormeJuridique: HttpRequestBuilder = http(RouteMapperEnum.POPULATE_FORME_JURIDIQUE.getName).get(RouteMapperEnum.POPULATE_FORME_JURIDIQUE.getUrl)

  def populatePerimetre: HttpRequestBuilder = http(RouteMapperEnum.POPULATE_PERIMETRE.getName).get(RouteMapperEnum.POPULATE_PERIMETRE.getUrl)

  def populateCodeNaf: HttpRequestBuilder = http(RouteMapperEnum.POPULATE_CODE_NAF.getName).get(RouteMapperEnum.POPULATE_CODE_NAF.getUrl)

  def populateCodeCsp: HttpRequestBuilder = http(RouteMapperEnum.POPULATE_CODE_CSP.getName).get(RouteMapperEnum.POPULATE_CODE_CSP.getUrl)

  def populateEntreprises: HttpRequestBuilder = http(RouteMapperEnum.POPULATE_ENTREPRISES.getName).get(RouteMapperEnum.POPULATE_ENTREPRISES.getUrl).check(bodyString.saveAs("entreprises"))

  def populateFeedRss: HttpRequestBuilder = http(RouteMapperEnum.POPULATE_FEED_RSS.getName).get(RouteMapperEnum.POPULATE_FEED_RSS.getUrl)

  def getEntreprise: HttpRequestBuilder = http(RouteMapperEnum.GET_ENTREPRISE.getName).get(RouteMapperEnum.GET_ENTREPRISE.getUrl + "${entrepriseId}")

  def getCentre: HttpRequestBuilder = http(RouteMapperEnum.GET_CENTRE.getName).get(RouteMapperEnum.GET_CENTRE.getUrl + "${centreId}")

  def getSecretaire: HttpRequestBuilder = http(RouteMapperEnum.GET_SECRETAIRE.getName).get(RouteMapperEnum.GET_SECRETAIRE.getUrl + "${secretaireId}")

  def getMedecin: HttpRequestBuilder = http(RouteMapperEnum.GET_MEDECIN.getName).get(RouteMapperEnum.GET_MEDECIN.getUrl + "${medecinId}")

  def findDeclaration: HttpRequestBuilder = http(RouteMapperEnum.FIND_DECLARATION.getName).get(RouteMapperEnum.FIND_DECLARATION.getUrl + "${entrepriseId}")

  def goToAdherent: HttpRequestBuilder = http(RouteMapperEnum.GO_TO_ADHERENT.getName).get(RouteMapperEnum.GO_TO_ADHERENT.getUrl)

  def goToHome: HttpRequestBuilder = http(RouteMapperEnum.RETURN_HOME.getName).get(RouteMapperEnum.RETURN_HOME.getUrl + "${entrepriseId}")

  def initialLogout: HttpRequestBuilder = http(RouteMapperEnum.INITIAL_LOGOUT.getName).get(RouteMapperEnum.INITIAL_LOGOUT.getUrl)

  def cleanerLogout: HttpRequestBuilder = http(RouteMapperEnum.CLEANER_LOGOUT.getName).get(RouteMapperEnum.CLEANER_LOGOUT.getUrl)

  def login: HttpRequestBuilder = http(RouteMapperEnum.LOGIN.getName).post(RouteMapperEnum.LOGIN.getUrl()).formParam("username", "${email}").formParam("password", "${password}")

  def checkRight: HttpRequestBuilder = http(RouteMapperEnum.CHECK_RIGHT.getName).post(RouteMapperEnum.CHECK_RIGHT.getUrl()).formParam("id", "${entrepriseId}")

  def findFacture: HttpRequestBuilder = {
    http(RouteMapperEnum.FIND_FACTURE.getName).post(RouteMapperEnum.FIND_FACTURE.getUrl()).headers(TEXT_PLAIN_HEADER)
      .body(StringBody("{\"entrepriseId\": ${entrepriseId} ,\"etat\":\"A REGLER\"}"))
  }

  def findDemandeVisibilite: HttpRequestBuilder = http(RouteMapperEnum.FIND_DEMANDE_VISIBILITE.getName).post(RouteMapperEnum.FIND_DEMANDE_VISIBILITE.getUrl()).formParam("id", "${entrepriseId}")

  def findContrat: HttpRequestBuilder = {
    http(RouteMapperEnum.FIND_CONTRAT.getName).post(RouteMapperEnum.FIND_CONTRAT.getUrl()).headers(TEXT_PLAIN_HEADER)
      .body(StringBody("{\"entrepriseId\":\"" + "${entrepriseId}" + "\",\"firstResult\":0,\"maxResult\":3,\"filter\":\"ACTIF\"}"))
  }
}
