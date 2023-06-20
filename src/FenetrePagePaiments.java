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



public class FenetrePagePaiments extends GridPane{

    private AppliVae appli;

    private ConnexionMySQL connexionMySQL;



public FenetrePagePaiments(AppliVae appli, ConnexionMySQL connexionMySQL){
    this.appli = appli;
    this.connexionMySQL = connexionMySQL;
    
}



public void creerPagePaimants(){
    GridPane gridPane = new GridPane();
    gridPane.setStyle("-fx-border-color: #D9D9D9; -fx-border-width: 3px; -fx-border-radius: 10; -fx-padding: 20px;");
    BorderPane.setMargin(gridPane, new Insets(130, 100, 100, 30));
    gridPane.setVgap(300);

    gridPane.add(new Text("Moyen de paiements enregistrer :"), 0, 0);
    VBox vboxBouton = new VBox();
    Text text = new Text("Enregistrer un nouveau moyen de paiement :");

    ImageView imageVisa = new ImageView("visa.png");
    imageVisa.setPreserveRatio(true);
    imageVisa.setFitWidth(150);
    Button boutonVisa = new Button();
    boutonVisa.setGraphic(imageVisa);
    boutonVisa.setCursor(Cursor.HAND);  
    boutonVisa.setStyle("-fx-background-color: transparent;");
    
    ImageView imagePaypal = new ImageView("paypal.png");
    imagePaypal.setPreserveRatio(true);
    imagePaypal.setFitWidth(160);
    Button boutonPaypal = new Button();
    boutonPaypal.setGraphic(imagePaypal);
    boutonPaypal.setCursor(Cursor.HAND);  
    boutonPaypal.setStyle("-fx-background-color: transparent;");

    ImageView imageMasterCard = new ImageView("masterCard.png");
    imageMasterCard.setPreserveRatio(true);
    imageMasterCard.setFitWidth(100);
    Button boutonMasterCard = new Button();
    boutonMasterCard.setGraphic(imageMasterCard);
    boutonMasterCard.setCursor(Cursor.HAND);  
    boutonMasterCard.setStyle("-fx-background-color: transparent;");

    vboxBouton.getChildren().addAll(text,boutonVisa,boutonPaypal,boutonMasterCard);
    vboxBouton.setSpacing(30);
    gridPane.add(vboxBouton,0,1);
    }
}
