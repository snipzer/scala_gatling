package action

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import service.ApiService

class AdherentAction(apiService: ApiService) {
  def execute: ChainBuilder = exec(apiService.goToAdherent())
}
