package util;

public enum RouteMapperEnum {

    POPULATE_USER("/portail/api/utilisateur/getByMail?mail="),
    POPULATE_FORME_JURIDIQUE("/portail/api/entreprise/findFormeJuridique"),
    POPULATE_PERIMETRE("/portail/api/entreprise/findPerimetre"),
    POPULATE_CODE_NAF("/portail/api/entreprise/findCodeNaf"),
    POPULATE_CODE_CSP("/portail/api/poste/findCodeCSP"),
    POPULATE_ENTREPRISES("/portail/api/entreprise/findEntreprise"),
    POPULATE_FEED_RSS("/portail/api/rss/findFeedRss"),
    GET_ENTREPRISE("/portail/api/entreprise/getEntrepriseById?id="),
    GET_CENTRE("/portail/api/entreprise/getCentreById?id="),
    GET_SECRETAIRE("/portail/api/entreprise/getSecretaireById?id="),
    GET_MEDECIN("/portail/api/entreprise/getMedecinById?id="),
    FIND_DECLARATION("/portail/api/declaration/findLastDeclarations?id="),
    GO_TO_ADHERENT("/portail/api/entreprise/findEntreprise"),
    RETURN_HOME("/portail/api/entreprise/getEntrepriseById?id="),
    INITIAL_LOGOUT("/portail/logout"),
    CLEANER_LOGOUT("/portail/logout"),
    LOGIN("/portail/login"),
    CHECK_RIGHT("/portail/api/visibilite/isAdministrable"),
    FIND_FACTURE("/portail/api/facture/findFactureByQuery"),
    FIND_DEMANDE_VISIBILITE("/portail/api/visibilite/findDemande"),
    FIND_CONTRAT("/portail/api/salarie/findContratByQuery");

    private String _url;

    RouteMapperEnum(String url) {
        _url = url;
    }

    public String getUrl() {
        return _url;
    }

    public String getName() {
        return this.name();
    }
}
