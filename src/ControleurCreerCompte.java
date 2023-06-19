import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurCreerCompte implements EventHandler<ActionEvent>{
    
    /**
     * La vue FenetreCoInsc
     */ 
    private FenetreCreationCompte vue;

    /**
     * La vue de l'application
     */
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;

    public ControleurCreerCompte(FenetreCreationCompte vue, AppliVae appli, ConnexionMySQL connexionMySQL){
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
        if (VerificateurMDP.mdpConfirmationValide(this.vue.getMdp(), this.vue.getMdpConfirmation())) {
            this.vue.setMessageMdpConfirmationErreur("");
            this.vue.setMdpConfimationErreur(false);
        } else {
            this.vue.setMessageMdpConfirmationErreur("  * Les mots de passe saisis ne correspondent pas");
            this.vue.setMdpConfimationErreur(true);
        }
        try {
            VerificateurMDP.estValide(this.vue.getMdp());
            // Faire la suite pour créer un compte
            this.appli.modeAccueil();
        } catch (FormatMotDePasseException e) {
            this.vue.setMdpErreur();
            this.vue.setMessageErreur(e.getMessage());
        }
	}

}