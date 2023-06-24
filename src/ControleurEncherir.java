import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurEncherir implements EventHandler<ActionEvent> {

    /**
     * La vue de l'application
     */
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;
    private Vente vente;
    private Utilisateur utilisateur;
    private PageProfilObjet vue;

    public ControleurEncherir(PageProfilObjet vue, AppliVae appli, ConnexionMySQL connexionMySQL, Vente vente,
            Utilisateur utilisateur) {
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.vente = vente;
        this.utilisateur = utilisateur;
        this.vue = vue;
    }

    /**
     * L'action consiste à changer de fenêtre pour aller à la page de connexion
     * 
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        // System.out.println(this.vente.getEncheres().get(0).getMontant());
        // System.out.println(this.vente.getEncheres().get(this.vente.getEncheres().size()-1).getMontant());
        String prixString = this.vue.getTFEncherir();
        

        try {
            Double prix = Double.valueOf(prixString);

            System.out.println(this.vente.getEncheres().size());

            EncherirBD encherirBD = new EncherirBD(this.connexionMySQL);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:hh/mm/ss");
            LocalDateTime dateTimeDateHeure2 = LocalDateTime.now();
            String dateHeureString2 = dateTimeDateHeure2.format(outputFormatter);          

            Enchere enchere = new Enchere(this.utilisateur, this.vente, prix, dateHeureString2);
            System.out.println("test0");
            encherirBD.insereEnchere(enchere);
            System.out.println("test1");
            this.vue.popUpEnchereAjoutee();
            System.out.println("test2");

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("sql");
            e.printStackTrace();
        } catch (NumberFormatException e){
            this.vue.erreurEncherir(true);
            this.vue.messageErreurEncherir("lettre");
        }


    }

}

/*
 * idut
 * idve
 * dateheure
 * montant
 */

