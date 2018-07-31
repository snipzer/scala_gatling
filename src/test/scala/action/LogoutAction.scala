package action

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import service.ApiService

class LogoutAction(apiService: ApiService) {
  def execute: ChainBuilder = exec(http("INITIAL_LOGOUT").get("/portail/logout").resources(http("CLEANER_LOGOUT").get("/portail/logout"))
  )
}
