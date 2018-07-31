package action

import java.io.StringReader

import entity.Entreprise
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import service.{ApiService, EntrepriseJsonReader}

class HomeAction(apiService: ApiService) {
//  def execute: ChainBuilder = exec(apiService.goToHome(previousEntreprise.id).resources(
//    apiService.populateEntreprises(),
//    apiService.getEntreprise(entreprise.id),
//    apiService.populateFeedRss(),
//    apiService.getCentre(entreprise.centreId),
//    apiService.checkRight(entreprise.id),
//    apiService.getSecretaire(entreprise.secretaireId),
//    apiService.getMedecin(entreprise.medecinId),
//    apiService.findFacture(entreprise.id, "A REGLER"),
//    apiService.findDeclaration(entreprise.id),
//    apiService.findDemandeVisibilite(entreprise.id),
//    apiService.findContrat(entreprise.id, "ACTIF")))

}
