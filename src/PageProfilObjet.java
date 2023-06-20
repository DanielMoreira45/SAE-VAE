import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PageProfilObjet extends BorderPane{
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;

    public PageProfilObjet(AppliVae appli, ConnexionMySQL connexionMySQL){
        super();
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.setLeft(left());
        this.setCenter(center());
    }

    private VBox left(){
        VBox vbox = new VBox();
        vbox.getChildren().add(image());
        vbox.getChildren().add(image());
        vbox.getChildren().add(image());
        vbox.getChildren().add(image());

        return vbox;
    }

    private Rectangle image(){
        Rectangle rect = new Rectangle(280, 180);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        ImagePattern pattern = new ImagePattern(new Image("file:img/image.png", 280, 180, false, false)); //A modifier pour afficher les images de la BD
        rect.setFill(pattern);
        return rect;

    }

    private VBox center(){
        VBox vbox = new VBox();
        vbox.getChildren().addAll(titre(), prix(), description());
        return vbox;
    }

    private HBox titre(){
        HBox hbox = new HBox();

        Text nomObj = new Text("Nom de l'objet");

        ImageView imagePP = new ImageView(new Image("file:img/pp.jpeg")); // A modif pour mettre la pp du vedeur
        imagePP.setFitWidth(70);
        imagePP.setFitHeight(70);
        //mettre l'image de profile un cercle
        Circle clip = new Circle(30, 30, 30);
        imagePP.setClip(clip);

        Label pseudo = new Label("Pseudo"); // A modif pour mettre le pseudo du vendeur

        Button boutonContacter = new Button("Contacter");
        boutonContacter.getStyleClass().add("button-without-background");
        boutonContacter.setCursor(Cursor.HAND);

        hbox.getChildren().addAll(nomObj, imagePP, pseudo, boutonContacter);
        hbox.getStyleClass().add("title");
        

        return hbox;
    }

    private HBox prix(){
        HBox hbox = new HBox();

        HBox prixMin = new HBox();
        Text textMin = new Text("Prix minimum : ");
        Label labelMin = new Label("X");
        Text textEuro = new Text(" €");
        prixMin.getChildren().addAll(textMin, labelMin, textEuro);

        HBox prixBase = new HBox();
        Text textBase = new Text("Prix de base : ");
        Label labelBase = new Label("X");
        prixBase.getChildren().addAll(textBase, labelBase, textEuro);

        HBox prixAct = new HBox();
        Text textAct = new Text("Prix actuel : ");
        Label labelAct = new Label("X");
        prixAct.getChildren().addAll(textAct, labelAct, textEuro);

        hbox.getChildren().addAll(prixMin, prixBase, prixAct);

        return hbox;
    }

    private VBox description(){
        VBox vbox = new VBox();

        Text titre = new Text("Description");
        Text desc = new Text("IZEBFOIBEFoiUEBFOIUEBFIOUBZEFOIBIUFBIEUFIUEBIBiubfezifbuziebfizebfibeifbziefbizebfizebfibefchnbduvbszuioeqyyyvboiqrbIZEBFOIBEFoiUEBFOIUEBFIOUBZEFOIBIUFBIEUFIUEBIBiubfezifbuziebfizebfibeifbziefbizebfizebfibefchnbduvbszuioeqyyyvboiqrbIZEBFOIBEFoiUEBFOIUEBFIOUBZEFOIBIUFBIEUFIUEBIBiubfezifbuziebfizebfibeifbziefbizebfizebfibefchnbduvbszuioeqyyyvboiqrb");

        vbox.getChildren().addAll(titre, desc);
        return vbox;
    }

    private VBox encheres(){
        VBox vbox = new VBox();
        Text titre = new Text("Enchères effectuées");

        TilePane tilePane = new TilePane();
        


        return vbox;
    }
}
