package sim

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import action._
import io.gatling.core.structure.ScenarioBuilder
import service.HttpRequestBuilderService

class RecordedSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("https://monportail.sstrn.fr")
    .inferHtmlResources()
    .acceptHeader("application/json, text/plain, */*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0")

  val TEXT_PLAIN_HEADER = Map("content-type" -> "text/plain")

  val actionManager: ExecutionManager = new ExecutionManager(new HttpRequestBuilderService())

  val testerScenario: ScenarioBuilder = scenario("TestScenario")
    .exec(feed(csv("../resources/data/user_credentials.csv")))
    .repeat(10) {
      exec(actionManager.execut&eLogin)
        .exec(actionManager.executeAdherent)
        .exec(actionManager.executeHome)
        .exec(actionManager.executeLogout)
  }

  setUp(testerScenario.inject(atOnceUsers(4))).protocols(httpProtocol)
}