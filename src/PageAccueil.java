import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PageAccueil extends VBox {
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;
    private TouteLesVentes toutesLesVentes;
    private List<Vente> lesVentes;
    private Text titreCat;
    private Text sousTitreCat;
    private String cat;
    private String sousTitre;
    private TextField prixMin;
    private TextField prixMax;

    /*
     * @Override
     * public void init() {
     * this = new BorderPane();
     * }
     * 
     * @Override
     * public void start(Stage stage) {
     * this.setCenter(this.setPage());
     * stage.setTitle("Page d'accueil");
     * stage.setHeight(1080);
     * stage.setWidth(1920);
     * stage.setFullScreen(false);
     * stage.setFullScreenExitHint("");
     * 
     * Scene scene = new Scene(this.root);
     * 
     * scene.getStylesheets().add("css.css");
     * 
     * stage.setScene(scene);
     * stage.show();
     * }
     */

    public PageAccueil(AppliVae appli, ConnexionMySQL connexionMySQL) {
        super();
        this.cat = "Tout type de produit";
        this.sousTitre = "Aucun filtre";
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.toutesLesVentes = new TouteLesVentes(this.connexionMySQL);
        try {
            this.lesVentes = this.toutesLesVentes.toutVente();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        this.prixMin = new TextField();
        this.prixMax = new TextField();
        prixMax.setOnKeyPressed(new ControleurPrixMinMax(this, this.toutesLesVentes));
        prixMin.setOnKeyPressed(new ControleurPrixMinMax(this, this.toutesLesVentes));
        this.getChildren().addAll(this.setTitre(), this.setSousTitre(), this.setBoutonTrier(), this.setVentes());
        this.setPadding(new Insets(20, 20, 50, 20));
    }

    private HBox setPrixMinLabel() {
    Label prixMinLabel = new Label("Prix Min:");
    prixMinLabel.setFont(Font.font("Valera", FontWeight.NORMAL, 12));
    HBox hbox = new HBox(prixMinLabel);
    hbox.setAlignment(Pos.CENTER_LEFT);
    return hbox;

}

public TextField getPrixMinTf(){
    return this.prixMin;
}

public TextField getPrixMax(){
    return this.prixMax;
}

private HBox setPrixMaxLabel() {
    Label prixMaxLabel = new Label("Prix Max:");
    prixMaxLabel.setFont(Font.font("Valera", FontWeight.NORMAL, 12));
    HBox hbox = new HBox(prixMaxLabel);
    hbox.setAlignment(Pos.CENTER_LEFT);
    return hbox;
}

    public void setCategorie(String cat) {
        this.cat = cat;
    }

    public void majAffichage() {
        this.getChildren().setAll(this.setTitre(this.cat), this.setSousTitre(this.sousTitre), this.setBoutonTrier(),
                this.setVentes());
    }

    private Text setTitre() {
        this.titreCat = new Text(sousTitre);
        titreCat.setFont(Font.font("Valera", FontWeight.NORMAL, 58));
        return titreCat;
    }

    private Text setTitre(String cat) {
        this.titreCat = new Text(cat);
        titreCat.setFont(Font.font("Valera", FontWeight.NORMAL, 58));
        return titreCat;
    }

    private Text setSousTitre() {
        this.sousTitreCat = new Text("-> " + this.sousTitre);
        this.sousTitreCat.setFont(Font.font("Valera", FontWeight.NORMAL, 24));
        return sousTitreCat;
    }

    public Text setSousTitre(String sousCat) {
        this.sousTitre = sousCat;
        this.sousTitreCat = new Text("-> " + sousCat);
        this.sousTitreCat.setFont(Font.font("Valera", FontWeight.NORMAL, 24));
        return sousTitreCat;
    }

    private HBox setBoutonTrier() {
        HBox trierVente = new HBox();
        try {
            trierVente.getChildren().addAll(this.setBoutonTout(), this.setBoutonTrierParDate(),
                    this.setBoutonTrierParNom(), this.setBoutonEnchereEnCours(), this.setBoutonAVenir(),
                    this.setBoutonInverse(), this.setBoutonTrierParNom(),this.setPrixMinLabel(),this.prixMin,this.setPrixMaxLabel(), this.prixMax);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        trierVente.setPadding(new Insets(50, 0, 20, 0));
        trierVente.setSpacing(20);

        return trierVente;
    }

    private Button setBoutonTout() throws SQLException, ParseException {
        Circle cercleBleu = new Circle(12);
        cercleBleu.setFill(Color.web("#00F801"));
        Text nbTout = new Text(this.toutesLesVentes.toutVente().size() + "");
        nbTout.setFont(Font.font("Valera", FontWeight.BOLD, 10));
        StackPane stackTout = new StackPane(cercleBleu, nbTout);
        Button tout = new Button("Tout", stackTout);
        tout.setPadding(new Insets(10));
        tout.setFont(Font.font("Valera", 12));
        tout.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        tout.setBorder(new Border(new BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID, new CornerRadii(8),
                new BorderWidths(1))));

        tout.setOnAction(new ControleurTrier(this, this.toutesLesVentes));

        return tout;
    }

    private Button setBoutonTrierParDate() throws SQLException, ParseException {
        Button parDate = new Button("Trier par date");
        parDate.setPadding(new Insets(14));
        parDate.setFont(Font.font("Valera", 12));
        parDate.setBackground(
                new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        parDate.setBorder(new Border(new BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID,
                new CornerRadii(8), new BorderWidths(1))));

        parDate.setOnAction(new ControleurTrier(this, this.toutesLesVentes));

        return parDate;
    }

    private Button setBoutonTrierParNom() throws SQLException, ParseException {
        Button parNom = new Button("Trier par nom");
        parNom.setPadding(new Insets(14));
        parNom.setFont(Font.font("Valera", 12));
        parNom.setBackground(
                new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        parNom.setBorder(new Border(new BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID,
                new CornerRadii(8), new BorderWidths(1))));

        parNom.setOnAction(new ControleurTrier(this, this.toutesLesVentes));

        return parNom;
    }

    private Button setBoutonEnchereEnCours() throws SQLException, ParseException {
        Circle cercleVert = new Circle(12);
        cercleVert.setFill(Color.web("#72FF91"));
        Text nbEnchereEnCours = new Text(this.toutesLesVentes.getNombreVenteParStatus(Status.ENCOURS) + "");
        nbEnchereEnCours.setFont(Font.font("Valera", FontWeight.BOLD, 12));
        StackPane stackEnchereEnCours = new StackPane(cercleVert, nbEnchereEnCours);
        Button enchereEnCours = new Button("Enchères en cours", stackEnchereEnCours);
        enchereEnCours.setPadding(new Insets(10));
        enchereEnCours.setFont(Font.font("Valera", 12));
        enchereEnCours.setBackground(
                new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        enchereEnCours.setBorder(new Border(new BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID,
                new CornerRadii(8), new BorderWidths(1))));

        enchereEnCours.setOnAction(new ControleurTrier(this, this.toutesLesVentes));

        return enchereEnCours;
    }

    // private Button setBoutonNonEncherier() {
    // Circle cercleJaune = new Circle(12);
    // cercleJaune.setFill(Color.web("#FFED4E"));
    // Text nbNonEncherier = new Text("7");
    // nbNonEncherier.setFont(Font.font("Valera", FontWeight.BOLD, 12));
    // StackPane stackNonEncherier = new StackPane(cercleJaune, nbNonEncherier);
    // Button objetNonEncherier = new Button("Objets non-enchèries",
    // stackNonEncherier);
    // objetNonEncherier.setPadding(new Insets(10));
    // objetNonEncherier.setFont(Font.font("Valera", 12));
    // objetNonEncherier.setBackground(new Background(new
    // BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
    // objetNonEncherier.setBorder(new Border(new
    // BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID, new
    // CornerRadii(8), new BorderWidths(1))));
    // return objetNonEncherier;
    // }

    // private Button setBoutonBientotFini() {
    // Circle cercleRouge = new Circle(12);
    // cercleRouge.setFill(Color.web("#FF9292"));
    // Text nbBientotFini = new Text("10");
    // nbBientotFini.setFont(Font.font("Valera", FontWeight.BOLD, 12));
    // StackPane stackBientotFini = new StackPane(cercleRouge, nbBientotFini);
    // Button enchereBientotFini = new Button("Enchères bientôt finies",
    // stackBientotFini);
    // enchereBientotFini.setPadding(new Insets(10));
    // enchereBientotFini.setFont(Font.font("Valera", 12));
    // enchereBientotFini.setBackground(new Background(new
    // BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
    // enchereBientotFini.setBorder(new Border(new
    // BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID, new
    // CornerRadii(8), new BorderWidths(1))));
    // return enchereBientotFini;
    // }

    private Button setBoutonAVenir() throws SQLException, ParseException {
        Circle cercleBleu = new Circle(12);
        cercleBleu.setFill(Color.valueOf("blue"));
        Text nbValide = new Text(this.toutesLesVentes.getNombreVenteParStatus(Status.AVENIR) + "");
        nbValide.setFont(Font.font("Valera", FontWeight.BOLD, 12));
        StackPane stackBientotFini = new StackPane(cercleBleu, nbValide);
        Button enchereAvenir = new Button("Enchères à venir", stackBientotFini);
        enchereAvenir.setPadding(new Insets(10));
        enchereAvenir.setFont(Font.font("Valera", 12));
        enchereAvenir.setBackground(
                new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        enchereAvenir.setBorder(new Border(new BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID,
                new CornerRadii(8), new BorderWidths(1))));

        enchereAvenir.setOnAction(new ControleurTrier(this, this.toutesLesVentes));

        return enchereAvenir;
    }

    private Button setBoutonInverse() {
        Button inverse = new Button("Inverse");
        inverse.setPadding(new Insets(14));
        inverse.setFont(Font.font("Valera", 12));
        inverse.setBackground(
                new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        inverse.setBorder(new Border(new BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID,
                new CornerRadii(8), new BorderWidths(1))));

        inverse.setOnAction(new ControleurTrier(this, this.toutesLesVentes));

        return inverse;
    }

    private HBox setVentes() {
        ScrollPane scrollVentes = this.setScrollVente();
        HBox ventes = new HBox();
        ventes.setPrefWidth(1550);
        if (this.lesVentes.size() > 4)
            ventes.getChildren().addAll(scrollVentes, this.setScrollBar(scrollVentes));
        else if (this.lesVentes.size() == 0) {
            Text pasVentesCorrespondante = new Text("Pas d'article correspondant");
            pasVentesCorrespondante.setFont(Font.font("Valera", 18));
            ventes.setAlignment(Pos.CENTER);
            ventes.setPadding(new Insets(280, 0, 0, 0));
            ventes.getChildren().add(pasVentesCorrespondante);
        } else
            ventes.getChildren().add(scrollVentes);
        return ventes;
    }

    private GridPane setGridVente() {
        GridPane gridVentes = new GridPane();
        gridVentes.setPadding(new Insets(50, 50, 0, 50));
        gridVentes.setHgap(100);
        gridVentes.setVgap(40);
        for (int i = 0; i < 20; i += 2) {
            if (this.lesVentes.size() > i + 1) {
                gridVentes.add(new CaseVente(this.lesVentes.get(i), this.appli, this.connexionMySQL), 0, i);
                gridVentes.add(new CaseVente(this.lesVentes.get(i + 1), this.appli, this.connexionMySQL), 1, i);
            }
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
        bar.setPrefHeight(ventes.getHeight() - 10);
        bar.setMinWidth(20);
        bar.setPrefWidth(20);
        bar.setOrientation(Orientation.VERTICAL);
        bar.setMin(ventes.getVmin());
        bar.setMax(ventes.getVmax());
        bar.valueProperty().bindBidirectional(ventes.vvalueProperty());
        bar.setBackground(new Background(new BackgroundFill(Color.web("#B5D6FD"), new CornerRadii(20), Insets.EMPTY)));
        return bar;
    }

    public void setLesVentes(List<Vente> lesVentes) {
        this.lesVentes = lesVentes;
    }

    public void reverseLesVentes() {
        Collections.reverse(this.lesVentes);
    }

    public TouteLesVentes getTouteLesVentes() {
        return this.toutesLesVentes;
    }

    public List<Vente> getLesVentes() {
        return this.lesVentes;
    }
}