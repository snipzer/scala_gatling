package sim

import java.io.StringReader

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import action._
import entity.Entreprise
import io.gatling.core.scenario.Scenario
import io.gatling.core.structure.ScenarioBuilder
import service.{ApiService, EntrepriseJsonReader}

class RecordedSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("https://monportail.sstrn.fr")
    .inferHtmlResources()
    .acceptHeader("application/json, text/plain, */*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0")

  val TEXT_PLAIN_HEADER = Map("content-type" -> "text/plain")

  val uri1 = "https://monportail.sstrn.fr:443"

  val apiService = new ApiService()
  val loginAction: LoginAction = new LoginAction(apiService)
  val adherentAction: AdherentAction = new AdherentAction(apiService)
  //val homeAction: HomeAction = new HomeAction(apiService)
  val logoutAction: LogoutAction = new LogoutAction(apiService)


  var entrepriseId = ""
  var centreId = ""
  var medecinId = ""
  var secretaireId = ""

  val setupScenario: ScenarioBuilder = scenario("SetupScenario")
    .exec(apiService.login("loic.arif@sstrn.fr", "azertyazerty").resources(apiService.populateEntreprises()))
    .exec(session => {
      val entreprise: Entreprise = EntrepriseJsonReader.read(new StringReader(session("entreprises").as[String])).head
      entrepriseId = entreprise.id
      medecinId = entreprise.medecinId
      centreId = entreprise.centreId
      secretaireId = entreprise.secretaireId
      session
    }).pause(10)

  val testerScenario: ScenarioBuilder = scenario("TestScenario")
    .repeat(10) {
      exec(loginAction.execute(entrepriseId, centreId, medecinId, secretaireId)).pause(3)
        .exec(adherentAction.execute).pause(3)
  //    .exec(HomeAction.execute).pause(3)
        .exec(logoutAction.execute).pause(3)
  }

  val scn = List(setupScenario.inject(atOnceUsers(1)), testerScenario.inject(nothingFor(10), atOnceUsers(1)))

  setUp(scn).protocols(httpProtocol)
}