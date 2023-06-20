import java.security.cert.CertPath;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

import javax.swing.text.html.ImageView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.skin.ScrollBarSkin;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PageAccueil extends VBox {
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;
    private TouteLesVentes lesVentes;

    /*
    @Override
    public void init() {
        this = new BorderPane();
    }

    @Override
    public void start(Stage stage) {
        this.setCenter(this.setPage());
        stage.setTitle("Page d'accueil");
        stage.setHeight(1080);
        stage.setWidth(1920);
        stage.setFullScreen(false);
        stage.setFullScreenExitHint("");

        Scene scene = new Scene(this.root);
        
        scene.getStylesheets().add("css.css");

        stage.setScene(scene);
        stage.show();
    }*/

    public PageAccueil(AppliVae appli, ConnexionMySQL connexionMySQL){
        super();
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.lesVentes = new TouteLesVentes(this.connexionMySQL);

        Text titre = new Text("Catégorie");
        titre.setFont(Font.font("Valera", FontWeight.NORMAL, 58));

        Text sousTitre = new Text("-> Sous-catégorie");
        sousTitre.setFont(Font.font("Valera", FontWeight.NORMAL, 24));
        
        ScrollPane ventes = this.setScrollVente();
        HBox test = new HBox(ventes, this.setScrollBar(ventes));

        this.getChildren().addAll(titre, sousTitre, this.setBoutonTrier(), test);
        this.setPadding(new Insets(20, 20, 50, 20));
    }
        

    private HBox setBoutonTrier() {
        HBox trierVente = new HBox();
        trierVente.getChildren().addAll(this.setBoutonEnchereEnCours(), this.setBoutonNonEncherier(), this.setBoutonBientotFini(), this.setPlusTrie(), this.setBoutonInverse());
        trierVente.setPadding(new Insets(50, 0, 20, 0));
        trierVente.setSpacing(20);
        
        return trierVente;
    }

    private Button setBoutonEnchereEnCours() {
        Circle cercleVert = new Circle(12);
        cercleVert.setFill(Color.web("#72FF91"));
        Text nbEnchereEnCours = new Text("3");
        nbEnchereEnCours.setFont(Font.font("Valera", FontWeight.BOLD, 12));
        StackPane stackEnchereEnCours = new StackPane(cercleVert, nbEnchereEnCours);
        Button enchereEnCours = new Button("Enchères en cours", stackEnchereEnCours);
        enchereEnCours.setPadding(new Insets(10));
        enchereEnCours.setFont(Font.font("Valera", 12));
        enchereEnCours.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        enchereEnCours.setBorder(new Border(new BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(1))));
        return enchereEnCours;
    }

    private Button setBoutonNonEncherier() {
        Circle cercleJaune = new Circle(12);
        cercleJaune.setFill(Color.web("#FFED4E"));
        Text nbNonEncherier = new Text("7");
        nbNonEncherier.setFont(Font.font("Valera", FontWeight.BOLD, 12));
        StackPane stackNonEncherier = new StackPane(cercleJaune, nbNonEncherier);
        Button objetNonEncherier = new Button("Objets non-enchèries", stackNonEncherier);
        objetNonEncherier.setPadding(new Insets(10));
        objetNonEncherier.setFont(Font.font("Valera", 12));
        objetNonEncherier.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        objetNonEncherier.setBorder(new Border(new BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(1))));
        return objetNonEncherier;
    }

    private Button setBoutonBientotFini() {
        Circle cercleRouge = new Circle(12);
        cercleRouge.setFill(Color.web("#FF9292"));
        Text nbBientotFini = new Text("10");
        nbBientotFini.setFont(Font.font("Valera", FontWeight.BOLD, 12));
        StackPane stackBientotFini = new StackPane(cercleRouge, nbBientotFini);
        Button enchereBientotFini = new Button("Enchères bientôt finies", stackBientotFini);
        enchereBientotFini.setPadding(new Insets(10));
        enchereBientotFini.setFont(Font.font("Valera", 12));
        enchereBientotFini.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        enchereBientotFini.setBorder(new Border(new BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(1))));
        return enchereBientotFini;
    }

    private Button setBoutonTrierValide() {
        Circle cercleBleu = new Circle(12);
        cercleBleu.setFill(Color.valueOf("blue"));
        Text nbValide = new Text("10");
        nbValide.setFont(Font.font("Valera", FontWeight.BOLD, 12));
        StackPane stackBientotFini = new StackPane(cercleBleu, nbValide);
        Button enchereValide = new Button("Enchères validés", stackBientotFini);
        enchereValide.setPadding(new Insets(0, 2, 0, 2));
        enchereValide.setFont(Font.font("Valera", 12));
        enchereValide.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        enchereValide.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.EMPTY)));
        return enchereValide;
    }

    private ComboBox<Button> setPlusTrie() {
        ComboBox<Button> plus = new ComboBox<>();
        plus.getItems().addAll(this.setBoutonTrierValide(), new Button("b"));
        plus.getSelectionModel().selectFirst();
        plus.setPadding(new Insets(5));
        plus.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        plus.setBorder(new Border(new BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(1))));
        return plus;
    }

    private Button setBoutonInverse() {
        Button inverse = new Button("Inverse");
        inverse.setPadding(new Insets(10));
        inverse.setFont(Font.font("Valera", 12));
        inverse.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        inverse.setBorder(new Border(new BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(1))));
        return inverse;
    }

    private GridPane setGridVente() {
        GridPane gridVentes = new GridPane();
        gridVentes.setPadding(new Insets(50, 50, 0, 50));
        gridVentes.setHgap(100);
        gridVentes.setVgap(40);
        try {
            for (int i = 0; i < 10; i+=2) {
                gridVentes.add(new CaseVente(this.lesVentes.toutVente().get(i)), 0, i);
                gridVentes.add(new CaseVente(this.lesVentes.toutVente().get(i+1)), 1, i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return gridVentes;
    }

    private ScrollPane setScrollVente() {
        ScrollPane ventes = new ScrollPane(this.setGridVente());
        ventes.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        ventes.setHbarPolicy(ScrollBarPolicy.NEVER);
        ventes.setVbarPolicy(ScrollBarPolicy.NEVER);
        return ventes;
    }

    private ScrollBar setScrollBar(ScrollPane ventes) {
        ScrollBar bar = new ScrollBar();
        bar.setPrefHeight(ventes.getHeight()-10);
        bar.setMinWidth(0);
        bar.setPrefWidth(20);
        bar.setOrientation(Orientation.VERTICAL);
        bar.setMin(ventes.getVmin());
        bar.setMax(ventes.getVmax());
        bar.valueProperty().bindBidirectional(ventes.vvalueProperty());
        bar.setBackground(new Background(new BackgroundFill(Color.web("#B5D6FD"), new CornerRadii(20), Insets.EMPTY)));
        return bar;
    }
}