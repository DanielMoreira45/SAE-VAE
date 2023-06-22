import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Object> laMap;

    public ControleurConnexion(FenetreDeLogin vue, AppliVae appli, ConnexionMySQL connexionMySQL){
        this.vue = vue;
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        laMap = new HashMap<>();
    }

    /**
     * L'action consiste à changer de fenêtre pour aller à la page de connexion
     * @param actionEvent l'événement action
     */
	@Override
	public void handle(ActionEvent actionEvent) {
        System.out.println("avant");
        try{
            UtilisateurBD userBd = new UtilisateurBD(connexionMySQL);
            String mail = vue.getEmail();
            this.laMap = userBd.rechercherJoueurParMail(mail);
            if (this.laMap == null){
                String pseudo = vue.getEmail();
                this.laMap = userBd.rechercherJoueurParPseudo(pseudo);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try {
            if (this.vue.getEmail().equals("erreur")) throw new Exception();
            this.vue.setEmailErreur(false);
            this.vue.setMessageEmailErreur("");
            if(this.laMap == null){
                throw new Exception();
            }
            try {
                if (this.vue.getMdp().equals("erreur")){
                    if (this.vue.getMdpClair().equals("erreur")){
                        throw new Exception();
                    }
                }
                String mdpBon;
                if (this.vue.getMdp().equals(laMap.get("mdput"))){
                    mdpBon = this.vue.getMdp();
                }
                else if(this.vue.getMdpClair().equals(laMap.get("mdput"))){
                    mdpBon = this.vue.getMdpClair();
                }
                else{
                    throw new Exception();
                }
                /*if (!vue.getMdp().equals(laMap.get("mdput"))) {
                    if (!vue.getMdpClair().equals(laMap.get("mdput"))) {
                        throw new Exception();
                    }
                    String mdpBon = this.vue.getMdpClair();
                }*/
                Utilisateur userCo = new Utilisateur((Integer) laMap.get("idut"), mdpBon, (String) laMap.get("emailut"), (String) laMap.get("mdput"),true, (Integer) laMap.get("idrole"));
                appli.setUtilisateurActuel(userCo);
                vue.popUpCompteConnecte((String) laMap.get("pseudout"));
                //System.out.println("Role = "+ userCo.getRole()+"");
                if (userCo.getRole() == (Roles.ADMINISTRATEUR)){
                    this.appli.modeAdministrateur();
                }
                else{
                    this.appli.modeAccueil();
                }
                
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
