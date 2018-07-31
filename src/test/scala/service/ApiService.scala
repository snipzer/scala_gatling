package service

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

class ApiService() {
  val TEXT_PLAIN_HEADER = Map("content-type" -> "text/plain")

  def populateUser(email: String): HttpRequestBuilder = http("POPULATE_USER").get("/portail/api/utilisateur/getByMail?mail=" + email)

  def populateFormeJuridique(): HttpRequestBuilder = http("POPULATE_FORME_JURIDIQUE").get("/portail/api/entreprise/findFormeJuridique")

  def populatePerimetre(): HttpRequestBuilder = http("POPULATE_PERIMETRE").get("/portail/api/entreprise/findPerimetre")

  def populateCodeNaf(): HttpRequestBuilder = http("POPULATE_CODE_NAF").get("/portail/api/entreprise/findCodeNaf")

  def populateCodeCsp(): HttpRequestBuilder = http("POPULATE_CODE_CSP").get("/portail/api/poste/findCodeCSP")

  def populateEntreprises(): HttpRequestBuilder = http("POPULATE_ENTREPRISES").get("/portail/api/entreprise/findEntreprise").check(bodyString.saveAs("entreprises"))

  def populateFeedRss(): HttpRequestBuilder = http("POPULATE_FEED_RSS").get("/portail/api/rss/findFeedRss")

  def getEntreprise(entrepriseId: String): HttpRequestBuilder = {
    println("/portail/api/entreprise/getEntrepriseById?id=" + s"${entrepriseId}")
    http("GET_ENTREPRISE").get("/portail/api/entreprise/getEntrepriseById?id=" + s"${entrepriseId}")
  }

  def getCentre(centreId: String): HttpRequestBuilder = http("GET_CENTRE").get("/portail/api/entreprise/getCentreById?id=" + centreId)

  def getSecretaire(secretaireId: String): HttpRequestBuilder = http("GET_SECRETAIRE").get("/portail/api/entreprise/getSecretaireById?id=" + secretaireId)

  def getMedecin(medecinId: String): HttpRequestBuilder = http("GET_MEDECIN").get("/portail/api/entreprise/getMedecinById?id=" + medecinId)

  def findDeclaration(entrepriseId: String): HttpRequestBuilder = http("FIND_DECLARATION").get("/portail/api/declaration/findLastDeclarations?id=" + entrepriseId)

  def goToAdherent(): HttpRequestBuilder = http("GO_TO_ADHERENT").get("/portail/api/entreprise/findEntreprise")

  def goToHome(entrepriseId: String): HttpRequestBuilder = http("RETURN_HOME").get("/portail/api/entreprise/getEntrepriseById?id=" + entrepriseId)

  def logout(): HttpRequestBuilder = http("LOGOUT").get("/portail/logout")

  def login(userName: String, password: String): HttpRequestBuilder = {
    http("LOGIN").post("/portail/login")
      .formParam("username", userName)
      .formParam("password", password)
  }

  def checkRight(entrepriseId: String): HttpRequestBuilder = {
    http("CHECK_RIGHT").post("/portail/api/visibilite/isAdministrable")
      .formParam("id", entrepriseId)
  }

  def findFacture(entrepriseId: String, etat: String): HttpRequestBuilder = {
    http("FIND_FACTURE").post("/portail/api/facture/findFactureByQuery")
      .headers(TEXT_PLAIN_HEADER)
      .body(StringBody("{\"entrepriseId\":" + entrepriseId + ",\"etat\":\"" + etat + "\"}"))
  }

  def findDemandeVisibilite(entrepriseId: String): HttpRequestBuilder = {
    http("FIND_DEMANDE_VISIBILITE").post("/portail/api/visibilite/findDemande")
      .formParam("id", entrepriseId)
  }

  def findContrat(entrepriseId: String, filter: String): HttpRequestBuilder = {
    http("FIND_CONTRAT").post("/portail/api/salarie/findContratByQuery")
      .headers(TEXT_PLAIN_HEADER)
      .body(StringBody("{\"entrepriseId\":\"" + entrepriseId + "\",\"firstResult\":0,\"maxResult\":3,\"filter\":\"" + filter + "\"}"))
  }
}
