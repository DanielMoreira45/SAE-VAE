import java.sql.SQLException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * ControleurProfilUtilisateurChangementMDP
 */
public class ControleurProfilUtilisateurChangementMDP implements EventHandler<ActionEvent> {

    private PageProfilUtilisateur vue;
    private ConnexionMySQL connexion;
    private Utilisateur utilisateur;

    public ControleurProfilUtilisateurChangementMDP(PageProfilUtilisateur pageProfilUtilisateur,
            ConnexionMySQL connexionMySQL, Utilisateur utilisateur) {
        this.vue = pageProfilUtilisateur;
        this.connexion = connexionMySQL;
        this.utilisateur = utilisateur;
    }

    @Override
    public void handle(ActionEvent ev) {
        if (vue.getMDPactuelle().getText().isEmpty() || vue.getConfinvMDP().getText().isEmpty()
                || vue.getNvMDP().getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.show();
        } else {
            String ancienMotDePasse = vue.getMDPactuelle().getText();
            String nouveauMotDePasse = vue.getNvMDP().getText();
            String nouveauConfMotDePasse = vue.getConfinvMDP().getText();
            if (nouveauMotDePasse.equals(nouveauConfMotDePasse)) {
                UtilisateurBD utilBD = new UtilisateurBD(connexion);
                if (VerificateurMDP.validar(nouveauConfMotDePasse)) {
                    try {
                        Alert alert = new Alert(AlertType.CONFIRMATION, "Vous ete sur de vouloir changer ?",
                                ButtonType.NO, ButtonType.YES);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.YES) {
                            utilisateur.setMotDePasse(nouveauMotDePasse);
                            utilBD.updateUtilisateur(utilisateur);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.show();
                }

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.show();
            }
        }
    }

}