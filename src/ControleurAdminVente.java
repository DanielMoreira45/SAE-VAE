import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurAdminVente implements EventHandler<ActionEvent> {
    private VueAdminGestionUtilisateurs parent;
    private ConnexionMySQL connexionMySQL;
    private Vente vente;

    public ControleurAdminVente(VueAdminGestionUtilisateurs parent, ConnexionMySQL connexionMySQL, Vente vente) {
        this.parent = parent;
        this.connexionMySQL = connexionMySQL;
        this.vente = vente;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            this.parent.getTouteLesVentes().supprimerVente(this.vente);
            this.parent.removeVente(vente);
            this.parent.majDesVentes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
