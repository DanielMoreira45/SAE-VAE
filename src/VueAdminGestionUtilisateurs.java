import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

    public VueAdminGestionUtilisateurs() {
        super();
        Text titre = this.initTitrePage();
        this.setTop(titre);
        this.setLeft(this.getMenu());
        this.setCenter(this.getPartieCentrale());
        this.setStyle("-fx-background-color: white;");
        this.setMargin(titre, new Insets(20));
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

    private VBox getMenu() {
        VBox leMenu = new VBox();
        leMenu.setPadding(new Insets(20));
        leMenu.getChildren().addAll(this.initBoutonGestionUtil(), this.initBoutonGestionVentes(), this.initBoutonRecapitulatifs());
        // leMenu.setSpacing(15);
        return leMenu;
    }

    private VBox getPartieCentrale() {
        return null;
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
