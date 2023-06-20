import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VueAdminGestionUtilisateurs extends BorderPane {
    private Button boutonGestionUtil;
    private Button boutonGestionVentes;
    private Button boutonRecapitulatifs;
    private ComboBox<String> derniereConnexion;

    public VueAdminGestionUtilisateurs() {
        super();
        Text titre = this.initTitrePage();
        BorderPane partieCentrale = this.getPartieCentrale();
        this.setTop(titre);
        this.setLeft(this.getMenu());
        this.setCenter(partieCentrale);
        this.setStyle("-fx-background-color: white;");
        this.setMargin(titre, new Insets(20));
        this.setMargin(partieCentrale, new Insets(30));
    }

    private Text initTitrePage() {
        Text titrePrincipalPage = new Text("Administration");
        titrePrincipalPage.setFont(Font.font("Arial", 32));
        return titrePrincipalPage;
    }

    private StackPane initBoutonGestionUtil() {
        this.boutonGestionUtil = new Button("Gestion des utilisateurs");
        this.boutonGestionUtil.setPadding(new Insets(5));
        StackPane boiteBoutonGestionUtil = new StackPane(this.boutonGestionUtil);
        boiteBoutonGestionUtil.setPrefWidth(190);
        boiteBoutonGestionUtil.setPadding(new Insets(5));
        this.boutonGestionUtil.setStyle("-fx-background-color: #b5d6fd; -fx-background-radius: 0.8em; -fx-border-radius: 0.8em;");
        StackPane.setAlignment(this.boutonGestionUtil, Pos.CENTER_LEFT);
        return boiteBoutonGestionUtil;
    }

    private StackPane initBoutonGestionVentes() {
        this.boutonGestionVentes = new Button("Gestion des ventes");
        this.boutonGestionVentes.setPadding(new Insets(5));
        Label fleche = new Label(">");
        StackPane boiteBoutonGestionVentes = new StackPane(this.boutonGestionVentes, fleche);
        boiteBoutonGestionVentes.setPadding(new Insets(5));
        boiteBoutonGestionVentes.setStyle("-fx-background-color: white; -fx-background-radius: 0.8em; -fx-border-radius: 0.8em;");
        this.boutonGestionVentes.setStyle("-fx-background-color: transparent;");
        StackPane.setAlignment(fleche, Pos.CENTER_RIGHT);
        StackPane.setAlignment(this.boutonGestionVentes, Pos.CENTER_LEFT);
        return boiteBoutonGestionVentes;
    }

    private StackPane initBoutonRecapitulatifs() {
        this.boutonRecapitulatifs = new Button("Récapitulatifs");
        this.boutonRecapitulatifs.setPadding(new Insets(5));
        Label fleche = new Label(">");
        StackPane boiteBoutonRecap = new StackPane(this.boutonRecapitulatifs, fleche);
        boiteBoutonRecap.setPadding(new Insets(5));
        boiteBoutonRecap.setStyle("-fx-background-color: white; -fx-background-radius: 0.8em; -fx-border-radius: 0.8em;");
        this.boutonRecapitulatifs.setStyle("-fx-background-color: transparent;");
        StackPane.setAlignment(fleche, Pos.CENTER_RIGHT);
        StackPane.setAlignment(this.boutonRecapitulatifs, Pos.CENTER_LEFT);
        return boiteBoutonRecap;
    }

    private void initComboBox() {
        this.derniereConnexion = new ComboBox<>();
        this.derniereConnexion.getItems().addAll("< 5 mins", "> 10 mins");
    }

    private VBox getMenu() {
        VBox leMenu = new VBox(10);
        leMenu.setPadding(new Insets(20));
        leMenu.getChildren().addAll(this.initBoutonGestionUtil(), this.initBoutonGestionVentes(), this.initBoutonRecapitulatifs());
        return leMenu;
    }

    private BorderPane getPartieCentrale() {
        BorderPane leCentre = new BorderPane();
        VBox lesProfils = new VBox(10);
        lesProfils.setStyle("-fx-background-color: #fdfdfd; -fx-border-color: #dddddd; -fx-border-radius : 0.8em; -fx-background-radius: 0.8em;");
        leCentre.setCenter(lesProfils);
        return leCentre;
    }

    public HBox boiteRechercheCategorie() {
        HBox boiteCatRecherche = new HBox(5);
        boiteCatRecherche.setPadding(new Insets(10));
        this.creerLesCategories();
        boiteCatRecherche.getChildren().addAll(this.boiteBarreDeRecherche(), this.lesCategories);
        return boiteCatRecherche;
    }

    public HBox boiteBarreDeRecherche() {
        HBox boiteRecherche = new HBox(5);
        boiteRecherche.setMaxHeight(40);
        // boiteRecherche.setMaxWidth(100);
        boiteRecherche.setPadding(new Insets(5));
        ImageView imageRecherche = new ImageView(new Image("file:./img/recherche.png"));
        imageRecherche.setFitWidth(18);
        imageRecherche.setPreserveRatio(true);
        this.boutonRecherche = new Button("", imageRecherche);
        TextField barreDeRecherche = new TextField("Nom d'un produit");
        boiteRecherche.getChildren().addAll(this.boutonRecherche, barreDeRecherche);
        boiteRecherche.setStyle("-fx-background-color: #b5d6fd; -fx-background-radius: 0.8em; -fx-border-color : black; -fx-border-radius: 0.8em;");
        this.boutonRecherche.setStyle("-fx-background-color: #b5d6fd;");
        barreDeRecherche.setAlignment(Pos.CENTER_LEFT);
        barreDeRecherche.setStyle("-fx-effect: dropshadow(gaussian, black, 8, 0, 1, 1); -fx-background-radius: 0.8em;");
        this.boutonRecherche.setAlignment(Pos.CENTER_LEFT);
        return boiteRecherche;
    }

    // public void ajouteMenu(BorderPane root){
    //     VBox menu = new VBox();
    //     Text pageUtilisateur = new Text("Page Utilisateur");
    //     pageUtilisateur.setStyle("-fx-font-size: 55px; -fx-fill: #000000; -fx-font-family: 'Verdana';");
    //     menu.getChildren().add(pageUtilisateur);
    //     root.setLeft(menu);
    //     menu.setPadding(new Insets(20,20,20,20));
    //     VBox sousMenu = new VBox();
    //     Button infosPerso = new Button("Informations Personnelles");
    //     infosPerso.setCursor(Cursor.HAND);
    //     Button changerMdp = new Button("Changer de Mot de Passe   >");
    //     changerMdp.setCursor(Cursor.HAND);
    //     Button paiment = new Button("Paiments                          >");
    //     paiment.setCursor(Cursor.HAND);
    //     infosPerso.getStyleClass().add("buttonBleu");
    //     changerMdp.getStyleClass().add("buttonBlanc");
    //     paiment.getStyleClass().add("buttonBlanc");
    //     sousMenu.getChildren().addAll(infosPerso, changerMdp, paiment);
    //     sousMenu.setSpacing(15);
    //     sousMenu.setPadding(new Insets(45,50,0,20));
    //     menu.getChildren().add(sousMenu);
    // }
    
    // public void ajouteMilieu(BorderPane root){
    //     GridPane gridPane = new GridPane();
    //     gridPane.setPadding(new Insets (100));
    //     gridPane.setStyle("-fx-border-color: #D9D9D9; -fx-border-width: 3px; -fx-border-radius: 10; -fx-padding: 20px;");
    //     BorderPane.setMargin(gridPane, new Insets(130, 100, 100, 30));
    //     gridPane.setHgap(10);
    //     gridPane.setVgap(10);
    
    //     gridPane.add(new Text("Nom d'utilisateur "), 0, 0);
    //     TextField textFieldNomUtilisateur = new TextField();
    //     gridPane.add(textFieldNomUtilisateur, 1, 0);
    //     Button boutonModifNomUtilisateur = new Button("Modifier");
    //     boutonModifNomUtilisateur.getStyleClass().add("buttonBleu");
    //     gridPane.add(boutonModifNomUtilisateur, 2, 0);
    //     textFieldNomUtilisateur.getStyleClass().add("text-fieldU");
    
    //     gridPane.add(new Text("Nom"), 0, 1);
    //     TextField textFieldNom = new TextField();
    //     gridPane.add(textFieldNom, 1, 1);
    //     Button boutonModifNom = new Button("Modifier");
    //     boutonModifNom.getStyleClass().add("buttonBleu");
    //     gridPane.add(boutonModifNom, 2, 1);
    //     textFieldNom.getStyleClass().add("text-fieldU");
    
    //     gridPane.add(new Text("Prénom"), 0, 2);
    //     TextField textFieldPrenom = new TextField();
    //     gridPane.add(textFieldPrenom, 1, 2);
    //     Button boutonModifPrenom = new Button("Modifier");
    //     boutonModifPrenom.getStyleClass().add("buttonBleu");
    //     gridPane.add(boutonModifPrenom, 2, 2);
    //     textFieldPrenom.getStyleClass().add("text-fieldU");
    
    //     root.setCenter(gridPane);
    // }
}
