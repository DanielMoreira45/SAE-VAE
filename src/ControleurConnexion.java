import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurConnexion implements EventHandler<ActionEvent>{
    
        /**
     * La vue FenetreCoInsc
     */ 
    private FenetreDeLogin vue;

    /**
     * La vue de l'application
     */
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;

    public ControleurConnexion(FenetreDeLogin vue, AppliVae appli, ConnexionMySQL connexionMySQL){
        this.vue = vue;
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
    }

    /**
     * L'action consiste à changer de fenêtre pour aller à la page de connexion
     * @param actionEvent l'événement action
     */
	@Override
	public void handle(ActionEvent actionEvent) {
        try {
            if (this.vue.getEmail().equals("erreur")) throw new Exception();
            this.vue.setEmailErreur(false);
            this.vue.setMessageEmailErreur("");
            try {
                if (this.vue.getMdp().equals("erreur")) throw new Exception();
                // Faire la suite pour créer un compte
                this.appli.modeAccueil();
            } catch (Exception e) {
                System.out.println("b");
                this.vue.setMdpErreur();
                this.vue.setMessageErreur("   * Mot de passe incorrect");
            }
        } catch (Exception e) {
            System.out.println("a");
            this.vue.setEmailErreur(true);
            this.vue.setMessageEmailErreur("   * Cet Email n'existe pas");
        }
	}

}

