import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;



public class FenetrePageChangerMdp extends GridPane{

    private ImageView profileImage;

    private String motDePasseActuelle;

    private String nouveauMotDePasse;

    private String confirmationNouveauMotDePasse;

    private AppliVae appli;

    private ConnexionMySQL connexionMySQL;


    public FenetrePageChangerMdp(AppliVae appli, ConnexionMySQL connexionMySQL){
        this.profileImage = new ImageView("pp.jpeg");
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.creerPageChangerMdp(); 
    }


    public void creerPageChangerMdp(){
        this.setStyle("-fx-border-color: #D9D9D9; -fx-border-width: 3px; -fx-border-radius: 10; -fx-padding: 20px;");
        BorderPane.setMargin(this, new Insets(130, 100, 100, 30));
        this.setHgap(30);
        this.setVgap(40);

        this.add(new Text("Charger de Mot de Passe"), 0, 0);
        this.add(new Text("Mot de Passe actuelle "), 0, 1);
        TextField textFieldMdpAc = new TextField();
        this.add(textFieldMdpAc, 1, 1);
        textFieldMdpAc.setPromptText("Entrer votre mot de passe actuelle");
        textFieldMdpAc.getStyleClass().add("text-fieldU");

        this.add(new Text("Nouveau Mot de Passe"), 0, 2);
        TextField textFieldNouveauMdp = new TextField();
        this.add(textFieldNouveauMdp, 1, 2);
        textFieldNouveauMdp.setPromptText("Enter votre nouveau mot de passe");
        textFieldNouveauMdp.getStyleClass().add("text-fieldU");

        this.add(new Text("Confirmer votre Mot de Passe"), 0, 3);
        TextField textFieldConfirMdp = new TextField();
        this.add(textFieldConfirMdp, 1, 3);
        textFieldConfirMdp.setPromptText("Confirmer le nouveau mot de passe");
        textFieldConfirMdp.getStyleClass().add("text-fieldU");

        Button boutonValider = new Button("Valider");
        boutonValider.getStyleClass().add("buttonBleu");
        this.add(boutonValider, 1, 4);

    }

}
