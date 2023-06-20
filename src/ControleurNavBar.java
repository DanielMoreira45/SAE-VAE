import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurNavBar implements EventHandler<ActionEvent>{
    
    /**
     * La vue FenetreCoInsc
     */ 
    //private FenetreCreationCompte vue;

    /**
     * La vue de l'application
     */
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;

    public ControleurNavBar(AppliVae appli, ConnexionMySQL connexionMySQL){
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
    }

    /**
     * L'action consiste à changer de fenêtre pour aller à la page de connexion
     * @param actionEvent l'événement action
     */
	@Override
	public void handle(ActionEvent actionEvent) {
        Button boutonSource = (Button) actionEvent.getSource();
        if (boutonSource.getAccessibleText().equals("Messagerie")){
            //this.appli.modeMessagerie();
            System.out.println("messagerie");
        }
        else if (boutonSource.getAccessibleText().equals("Notifications")){
            //this.appli.popUpNotif();
            System.out.println("pop up notif");
        }
        else if (boutonSource.getAccessibleText().equals("Panier")){
            System.out.println("le panier");
        }
        else if (boutonSource.getAccessibleText().equals("Logo")){
            this.appli.modeAccueil();
        }
        else if (boutonSource.getAccessibleText().equals("Profil Utilisateur")){
            this.appli.modeProfilUtilisateur();
            System.out.println("profil");
        }
	}

}