import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurCaseVente implements EventHandler<ActionEvent> {

    /**
     * La vue de l'application
     */
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;
    private Vente vente;

    public ControleurCaseVente(AppliVae appli, ConnexionMySQL connexionMySQL, Vente vente) {
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.vente = vente;
    }

    /**
     * L'action consiste à changer de fenêtre pour aller à la page de connexion
     * 
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        this.appli.modeProfilObjet(this.vente);
    }

}
