import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurProfilUtilisateur implements EventHandler<ActionEvent> {

    /**
     * La vue de l'application
     */
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;

    public ControleurProfilUtilisateur(AppliVae appli, ConnexionMySQL connexionMySQL) {
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
    }

    /**
     * L'action consiste à changer de fenêtre pour aller à la page de connexion
     * 
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        this.appli.modeCoInsc();
    }

}
