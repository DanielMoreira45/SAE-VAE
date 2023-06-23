import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurConnexion implements EventHandler<ActionEvent> {

    /**
     * La vue FenetreCoInsc
     */
    private FenetreDeLogin vue;

    /**
     */
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;
    private List<Utilisateur> utilisateurTrouve;
    private Utilisateur uti;

    public ControleurConnexion(FenetreDeLogin vue, AppliVae appli, ConnexionMySQL connexionMySQL) {
        this.vue = vue;
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.utilisateurTrouve = new ArrayList<>();
    }

    /**
     * L'action consiste à changer de fenêtre pour aller à la page de connexion
     * 
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            UtilisateurBD userBd = new UtilisateurBD(connexionMySQL);
            String mail = vue.getEmail();
            this.utilisateurTrouve = userBd.recherche(mail);
            if (!(utilisateurTrouve.isEmpty())) {
                this.uti = utilisateurTrouve.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (this.vue.getEmail().equals("erreur"))
                throw new Exception();
            this.vue.setEmailErreur(false);
            this.vue.setMessageEmailErreur("");
            if (utilisateurTrouve == null) {
                throw new Exception();
            }
            try {
                if (this.vue.getMdp().equals("erreur")){
                    throw new Exception();
                }
                this.vue.setMdpErreur(false);
                this.vue.setMessageErreur("");
                if (!vue.getMdp().equals(this.uti.getMotDePasse()) && !vue.getMdpClair().equals(this.uti.getMotDePasse())) {
                    throw new Exception();
                }
                if (uti.estActive()) {
                    appli.setUtilisateurActuel(uti);
                    vue.popUpCompteConnecte(this.uti.getPseudo());
                    //System.out.println("Role = " + this.uti.getRole() + "");
                    if (this.uti.getRole() == (Roles.ADMINISTRATEUR)) {
                        this.appli.modeAdministrateur();
                    } else {
                        this.appli.modeAccueil();
                    }
                } else {
                    this.vue.popUpCompteDesactive(uti.getPseudo());
                }
            } catch (Exception e) {
                if (!vue.getEmail().equals(this.uti.getEmail()) && !vue.getEmail().equals(this.uti.getPseudo())){
                    System.out.println("is");
                    this.vue.setEmailErreur(true);
                    this.vue.setMessageEmailErreur("   * Cet Email/Pseudo n'existe pas");
                }
                this.vue.setMdpErreur(true);
                this.vue.setMessageErreur("   * Ce mot de passe n'est pas bon");
                
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
