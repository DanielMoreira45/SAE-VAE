import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VueAdminGestionUtilisateurs extends BorderPane {
    private Button boutonGestionUtil;
    private Button boutonGestionVentes;
    private Button boutonRecapitulatifs;
    private ComboBox<String> comboBoxDerniereConnexion;
    private ScrollPane scrollPaneProfils;
    private List<BorderPane> listeDesProfils;

    public VueAdminGestionUtilisateurs() {
        super();
        this.listeDesProfils = new ArrayList<>();
        Text titre = this.initTitrePage();
        BorderPane partieCentrale = this.getPartieCentrale();
        this.setTop(titre);
        this.setLeft(this.getMenu());
        this.setCenter(partieCentrale);
        this.setStyle("-fx-background-color: white;");
        BorderPane.setMargin(titre, new Insets(20));
        BorderPane.setMargin(partieCentrale, new Insets(10));
        BorderPane.setAlignment(partieCentrale, Pos.TOP_CENTER);


        // this.ajouterUnProfil("test", 5);
        // this.ajouterUnProfil("test", 5);
        // this.ajouterUnProfil("test", 5);
        // this.ajouterUnProfil("test", 5);

        // this.ajouterUnProfil("test", 5);
        // this.ajouterUnProfil("test", 5);
        // this.ajouterUnProfil("test", 5);
        // this.ajouterUnProfil("test", 5);
        // this.ajouterUnProfil("test", 5);
        // this.ajouterUnProfil("test", 5);
        // this.ajouterUnProfil("test", 5);
        // this.ajouterUnProfil("test", 5);
        // this.ajouterUnProfil("test", 5);
        // this.ajouterUnProfil("test", 5);
        this.majDesProfils();
    }

    /**
     * Méthode permettant d'ajouter un nouveau profil. À modifier si besoin.
     * @param nom
     * @param derniereConnexion
     */
    public void ajouterUnProfil(String nom, int derniereConnexion) {
        BorderPane tempProfil = new BorderPane();
        tempProfil.setPrefWidth(this.scrollPaneProfils.getWidth());
        tempProfil.setStyle("-fx-background-color: #fdfdfd;");
        tempProfil.setPadding(new Insets(5));

        VBox boiteBoutonsGestion = this.getLesBoutonsProfil();
        Text nomDuProfil = this.getNomDuProfil(nom);
        ImageView photoProfil = this.getImageProfil();
        VBox boiteInfosProfil = this.getInfosProfil(nomDuProfil, derniereConnexion);

        tempProfil.setLeft(photoProfil);
        tempProfil.setCenter(boiteInfosProfil);
        tempProfil.setRight(boiteBoutonsGestion);

        BorderPane.setMargin(photoProfil, new Insets(5));
        BorderPane.setMargin(boiteInfosProfil, new Insets(5, 0, 5, 5));
        BorderPane.setMargin(boiteBoutonsGestion, new Insets(5, 5, 5, 0));
        BorderPane.setAlignment(boiteBoutonsGestion, Pos.CENTER);

        this.listeDesProfils.add(tempProfil);
    }

    /**
     * Méthode permettant de mettre à jour l'affichage des profils dans le ScrollPane.
     */
    public void majDesProfils() {
        VBox tousLesProfils = new VBox(10);
        tousLesProfils.setStyle("-fx-background-color: transparent;");
        if (this.listeDesProfils.isEmpty()) {
            Text texteListeVide = new Text("Il n'y a aucun utilisateur existant.");
            texteListeVide.setFont(Font.font("Arial", 18));
            this.scrollPaneProfils.setContent(texteListeVide);
        }
        else {
            for (BorderPane profil : this.listeDesProfils) {
                tousLesProfils.getChildren().add(profil);
            }
            this.scrollPaneProfils.setContent(tousLesProfils);
        }
    }

    /**
     * Méthode permettant de créer les boutons "supprimer" et "désactiver" du profil.
     * @return
     */
    private VBox getLesBoutonsProfil() {
        Button supprimer = new Button("Supprimer");
        Button desactiver = new Button("Désactiver");
        supprimer.setStyle("-fx-background-color : #ff9292; -fx-border-radius: 0.8em; -fx-background-radius : 0.8em; -fx-effect: dropshadow(gaussian, grey, 8, 0, 1, 1);");
        desactiver.setStyle("-fx-background-color : #6a6a6a; -fx-border-radius: 0.8em; -fx-background-radius : 0.8em; -fx-effect: dropshadow(gaussian, grey, 8, 0, 1, 1);");
        VBox boiteBoutonsGestion = new VBox(10, supprimer, desactiver);
        boiteBoutonsGestion.setPadding(new Insets(10));
        boiteBoutonsGestion.setStyle("-fx-background-color: #f1f1f1;");
        return boiteBoutonsGestion;
    }

    /**
     * Méthode permettant de créer le nom d'utilisateur.
     * @param nom
     * @return
     */
    private Text getNomDuProfil(String nom) {
        Text nomDuProfil = new Text(nom);
        nomDuProfil.setFont(Font.font("Arial", 16));
        return nomDuProfil;
    }

    /**
     * Méthode permettant de créer la photo de profil.
     * @return
     */
    private ImageView getImageProfil() {
        Circle cercleImage = new Circle(40, 40, 40);
        ImageView photoProfil = new ImageView(new Image("file:./img/photoParDefaut.png"));
        photoProfil.setStyle("-fx-border-radius: 1em; -fx-background-radius: 1em;");
        photoProfil.setFitWidth(80);
        photoProfil.setFitHeight(80);
        photoProfil.setClip(cercleImage);
        return photoProfil;
    }

    /**
     * Méthode permettant de créer une boite contentant le nom de l'utilisateur et la dernière connexion.
     * @param nomDuProfil
     * @param derniereConnexion
     * @return
     */
    private VBox getInfosProfil(Text nomDuProfil, int derniereConnexion) {
        VBox boiteInfosProfil = new VBox(10, nomDuProfil, new Label("Dernière connexion : il y a " + derniereConnexion + " (min/h/j)"));
        boiteInfosProfil.setPadding(new Insets(10));
        boiteInfosProfil.setStyle("-fx-background-color: #f1f1f1;");
        return boiteInfosProfil;
    }

    /**
     * Méthode permettant d'initialiser le titre principal de la page.
     * @return
     */
    private Text initTitrePage() {
        Text titrePrincipalPage = new Text("Administration");
        titrePrincipalPage.setFont(Font.font("Arial", 32));
        return titrePrincipalPage;
    }

    /**
     * Méthode permettant d'initialiser le bouton de gestion des utilisateurs.
     * @return
     */
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

    /**
     * Méthode permettant d'initialiser le bouton de gestion des ventes.
     * @return
     */
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

    /**
     * Méthode permettant d'initialiser le bouton des récapitulatifs.
     * @return
     */
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

    /**
     * Méthode permettant d'initialiser la ComboBox de la dernière connexion.
     */
    private void initComboBox() {
        this.comboBoxDerniereConnexion = new ComboBox<>();
        this.comboBoxDerniereConnexion.setStyle("-fx-background-color: white; -fx-background-radius : 0.8em; -fx-border-radius: 0.8em; -fx-border-color : lightgrey;");
        this.comboBoxDerniereConnexion.getItems().addAll("< 5 mins", "> 10 mins");
        this.comboBoxDerniereConnexion.setMaxHeight(40);
    }

    /**
     * Méthode permettant d'initialiser le ScrollPane situé dans la partie centrale de la page.
     */
    private void initScrollPaneProfils() {
        this.scrollPaneProfils = new ScrollPane();
        this.scrollPaneProfils.setStyle("-fx-background: #fdfdfd; -fx-border-color: #dddddd; -fx-border-radius : 0.8em; -fx-background-radius: 0.8em;");
        this.scrollPaneProfils.setFitToWidth(true);
        this.scrollPaneProfils.setPadding(new Insets(25));
    }

    /**
     * Méthode permettant de constituer le menu de gauche.
     * @return
     */
    private VBox getMenu() {
        VBox leMenu = new VBox(10);
        leMenu.setPadding(new Insets(20));
        leMenu.getChildren().addAll(this.initBoutonGestionUtil(), this.initBoutonGestionVentes(), this.initBoutonRecapitulatifs());
        return leMenu;
    }

    /**
     * Méthode permettant de constituer la partie centrale de la page.
     * @return
     */
    private BorderPane getPartieCentrale() {
        BorderPane leCentre = new BorderPane();
        leCentre.setPadding(new Insets(10));
        leCentre.setMaxHeight(800);
        this.initScrollPaneProfils();
        HBox lesElemsDeRecherche = this.getElemsRecherche();
        BorderPane.setAlignment(lesElemsDeRecherche, Pos.CENTER);
        BorderPane.setAlignment(this.scrollPaneProfils, Pos.TOP_CENTER);
        leCentre.setTop(lesElemsDeRecherche);
        leCentre.setCenter(this.scrollPaneProfils);
        BorderPane.setMargin(lesElemsDeRecherche, new Insets(10));
        BorderPane.setMargin(this.scrollPaneProfils, new Insets(10));
        return leCentre;
    }

    /**
     * Méthode permettant de créer le bouton de recherche.
     * @return
     */
    private Button getBoutonRecherche() {
        ImageView imageRecherche = new ImageView(new Image("file:./img/recherche.png"));
        imageRecherche.setFitWidth(18);
        imageRecherche.setPreserveRatio(true);
        Button boutonRecherche = new Button("", imageRecherche);
        boutonRecherche.setStyle("-fx-background-color: #b5d6fd;");
        boutonRecherche.setAlignment(Pos.CENTER_LEFT);
        return boutonRecherche;
    }

    /**
     * Méthode permettant de créer la barre de recherche en elle-même.
     * @return
     */
    private TextField getBarreDeRecherche() {
        TextField barreDeRecherche = new TextField("Nom d'un utilisateur");
        barreDeRecherche.setAlignment(Pos.CENTER_LEFT);
        barreDeRecherche.setStyle("-fx-effect: dropshadow(gaussian, grey, 8, 0, 1, 1); -fx-background-radius: 0.8em;");
        barreDeRecherche.setPrefWidth(320);
        return barreDeRecherche;
    }

    /**
     * Méthode permettant de créer la boite, qui contient la barre de recherche et son bouton.
     * @return
     */
    private HBox getBoiteBarreDeRecherche() {
        TextField barreDeRech = this.getBarreDeRecherche();
        Button leBouton = this.getBoutonRecherche();
        HBox boiteRecherche = new HBox(5);
        boiteRecherche.setMaxWidth(400);
        boiteRecherche.setMaxHeight(40);
        boiteRecherche.setPadding(new Insets(5));
        boiteRecherche.getChildren().addAll(leBouton, barreDeRech);
        boiteRecherche.setStyle("-fx-background-color: #b5d6fd; -fx-background-radius: 0.8em; -fx-border-color : black; -fx-border-radius: 0.8em;");
        return boiteRecherche;
    }

    /**
     * Méthode permettant de constituer une boite, contenant la boite de la barre de recherche, avec la ComboBox de la dernière connexion.
     * @return
     */
    private HBox getElemsRecherche() {
        HBox boiteCatRecherche = new HBox(5);
        boiteCatRecherche.setPadding(new Insets(10));
        boiteCatRecherche.setMaxWidth(520);
        this.initComboBox();
        HBox laBoiteDeRecherche = this.getBoiteBarreDeRecherche();
        laBoiteDeRecherche.setAlignment(Pos.CENTER);
        boiteCatRecherche.getChildren().addAll(laBoiteDeRecherche, this.comboBoxDerniereConnexion);
        return boiteCatRecherche;
    }
}
