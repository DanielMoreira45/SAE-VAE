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
import javafx.scene.Cursor;

public class VueAdminGestionUtilisateurs extends BorderPane {
    private Button boutonGestionUtil;
    private Button boutonGestionVentes;
    private Button boutonRecapitulatifs;
    private ComboBox<String> comboBoxDerniereConnexion;
    private ScrollPane scrollPaneProfils;
    private List<BorderPane> listeDesProfils;

    /**
     * Construction permettant de créer une nouvelle vue de gestion des utilisateurs.
     */
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
        BorderPane.setMargin(partieCentrale, new Insets(0, 18, 0, 18));
        BorderPane.setAlignment(partieCentrale, Pos.TOP_CENTER);
        this.getStylesheets().add("stylePageUtilisateur.css");

        // this.ajouterUnProfil("test1", 0);
        // this.ajouterUnProfil("test2", 2);
        // this.ajouterUnProfil("test3", 4);
        // this.ajouterUnProfil("test4", 6);
        // this.ajouterUnProfil("test5", 8);
        // this.ajouterUnProfil("test6", 10);
        // this.ajouterUnProfil("test7", 12);
        // this.ajouterUnProfil("test8", 14);
        // this.ajouterUnProfil("test9", 16);
        // this.ajouterUnProfil("test10", 18);
        // this.ajouterUnProfil("test11", 20);
        // this.ajouterUnProfil("test12", 60);
        // this.ajouterUnProfil("test13", 1440);
        // this.ajouterUnProfil("test14", 8500);
        this.majDesProfils();
    }

    /**
     * Méthode permettant d'ajouter un nouveau profil (à modifier si besoin).
     * @param nom String : le nom de l'utilisateur à ajouter.
     * @param derniereConnexion int : sa dernière connexion.
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
            tousLesProfils.setPadding(new Insets(15, 0, 0, 0));
            tousLesProfils.getChildren().add(texteListeVide);
        }
        else {
            for (BorderPane profil : this.listeDesProfils) {
                tousLesProfils.getChildren().add(profil);
            }
        }
        this.scrollPaneProfils.setContent(tousLesProfils);
    }

    /**
     * Méthode permettant de créer les boutons "supprimer" et "désactiver" du profil.
     * @return VBox : la boite contenant les boutons "supprimer" et "désactiver".
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
     * @param nom String : le nom de l'utilisateur à créer.
     * @return Text : le nom de l'utilisateur sous forme de Text.
     */
    private Text getNomDuProfil(String nom) {
        Text nomDuProfil = new Text(nom);
        nomDuProfil.setFont(Font.font("Arial", 16));
        return nomDuProfil;
    }

    /**
     * Méthode permettant de créer la photo de profil.
     * @return ImageView : la photo de profil de l'utilisateur.
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
     * @param nomDuProfil Text : le nom de l'utilisateur.
     * @param derniereConnexion int : sa dernière connexion (en minutes).
     * @return VBox : une boite contenant les informations de profil de l'utilisateur (son pseudo et sa dernière connexion).
     */
    private VBox getInfosProfil(Text nomDuProfil, int derniereConnexion) {
        String connexion = "Dernière connexion :";
        VBox boiteInfosProfil = new VBox(10, nomDuProfil);
        if (derniereConnexion == 0) {
            boiteInfosProfil.getChildren().add(new Label(connexion + " à l'instant."));
        }
        else if (derniereConnexion < 60) {
            boiteInfosProfil.getChildren().add(new Label(connexion + " il y a " + derniereConnexion + " min."));
        }
        else if (derniereConnexion >= 60 && derniereConnexion < 1440) {
            boiteInfosProfil.getChildren().add(new Label(connexion + " il y a " + derniereConnexion/60 + " h."));
        }
        else {
            boiteInfosProfil.getChildren().add(new Label(connexion + " il y a " + derniereConnexion/1440 + " j."));
        }
        boiteInfosProfil.setPadding(new Insets(10));
        boiteInfosProfil.setStyle("-fx-background-color: #f1f1f1;");
        return boiteInfosProfil;
    }

    /**
     * Méthode permettant d'initialiser le titre principal de la page.
     * @return Text : le titre de la page.
     */
    private Text initTitrePage() {
        Text titrePrincipalPage = new Text("Administration");
        titrePrincipalPage.setFont(Font.font("Arial", 32));
        return titrePrincipalPage;
    }

    /**
     * Méthode permettant d'initialiser le bouton de gestion des utilisateurs.
     */
    private void initBoutonGestionUtil() {
        this.boutonGestionUtil = new Button("Gestion des utilisateurs   >");
        this.boutonGestionUtil.setPadding(new Insets(10));
        this.boutonGestionUtil.getStyleClass().add("buttonBleu");
    }

    /**
     * Méthode permettant d'initialiser le bouton de gestion des ventes.
     */
    private void initBoutonGestionVentes() {
        this.boutonGestionVentes = new Button("Gestion des ventes          >");
        this.boutonGestionVentes.setPadding(new Insets(10));
        this.boutonGestionVentes.getStyleClass().add("buttonBlanc");
    }

    /**
     * Méthode permettant d'initialiser le bouton des récapitulatifs.
     */
    private void initBoutonRecapitulatifs() {
        this.boutonRecapitulatifs = new Button("Récapitulatifs                   >");
        this.boutonRecapitulatifs.setPadding(new Insets(10));
        this.boutonRecapitulatifs.getStyleClass().add("buttonBlanc");
    }

    /**
     * Méthode permettant d'initialiser la ComboBox de la dernière connexion.
     */
    private void initComboBox() {
        this.comboBoxDerniereConnexion = new ComboBox<>();
        this.comboBoxDerniereConnexion.setStyle("-fx-background-color: white; -fx-background-radius : 0.8em; -fx-border-radius: 0.8em; -fx-border-color : lightgrey;");
        this.comboBoxDerniereConnexion.getItems().addAll("Dernière connexion", "Connectés", "> 5 min", "> 10 min", "> 30 min", "> 1h", "> 1j");
        this.comboBoxDerniereConnexion.getSelectionModel().selectFirst();
        this.comboBoxDerniereConnexion.setMaxHeight(40);
    }

    /**
     * Méthode permettant d'initialiser le ScrollPane situé dans la partie centrale de la page.
     */
    private void initScrollPaneProfils() {
        this.scrollPaneProfils = new ScrollPane();
        this.scrollPaneProfils.setStyle("-fx-background: #fdfdfd; -fx-border-color: #dddddd; -fx-border-radius : 0.8em; -fx-background-radius: 0.8em;");
        this.scrollPaneProfils.setFitToWidth(true);
        this.scrollPaneProfils.setPadding(new Insets(0, 20, 0, 20));
    }

    /**
     * Méthode permettant de constituer le menu de gauche.
     * @return VBox : le menu de gauche.
     */
    private VBox getMenu() {
        VBox leMenu = new VBox(5);
        this.initBoutonGestionUtil();
        this.initBoutonGestionVentes();
        this.initBoutonRecapitulatifs();
        leMenu.setPrefWidth(268);
        leMenu.setPadding(new Insets(20));
        leMenu.getChildren().addAll(this.boutonGestionUtil, this.boutonGestionVentes, this.boutonRecapitulatifs);
        return leMenu;
    }

    /**
     * Méthode permettant de constituer la partie centrale de la page.
     * @return BorderPane : la partie centrale de la page.
     */
    private BorderPane getPartieCentrale() {
        BorderPane leCentre = new BorderPane();
        leCentre.setPadding(new Insets(10));
        leCentre.setMaxHeight(900);
        this.initScrollPaneProfils();
        HBox lesElemsDeRecherche = this.getElemsRecherche();
        BorderPane.setAlignment(lesElemsDeRecherche, Pos.CENTER);
        BorderPane.setAlignment(this.scrollPaneProfils, Pos.TOP_CENTER);
        leCentre.setTop(lesElemsDeRecherche);
        leCentre.setCenter(this.scrollPaneProfils);
        BorderPane.setMargin(lesElemsDeRecherche, new Insets(0, 0, 10, 0));
        BorderPane.setMargin(this.scrollPaneProfils, new Insets(10));
        return leCentre;
    }

    /**
     * Méthode permettant de créer le bouton de recherche.
     * @return Button : le bouton de recherche.
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
     * @return TextField : le champ de recherche.
     */
    private TextField getBarreDeRecherche() {
        TextField barreDeRecherche = new TextField("Nom d'un utilisateur");
        barreDeRecherche.setAlignment(Pos.CENTER_LEFT);
        barreDeRecherche.setStyle("-fx-effect: dropshadow(gaussian, grey, 8, 0, 1, 1); -fx-background-radius: 0.8em; -fx-background-color: white;");
        barreDeRecherche.setPrefWidth(320);
        return barreDeRecherche;
    }

    /**
     * Méthode permettant de créer la boite, qui contient la barre de recherche et son bouton.
     * @return HBox : le boite contenant le bouton et la barre de recherche.
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
     * @return HBox : une boite contenant la barre de recherche et la ComboBox de temps de connexion.
     */
    private HBox getElemsRecherche() {
        HBox boiteCatRecherche = new HBox(5);
        boiteCatRecherche.setPadding(new Insets(10));
        boiteCatRecherche.setMaxWidth(600);
        this.initComboBox();
        HBox laBoiteDeRecherche = this.getBoiteBarreDeRecherche();
        laBoiteDeRecherche.setAlignment(Pos.CENTER);
        boiteCatRecherche.getChildren().addAll(laBoiteDeRecherche, this.comboBoxDerniereConnexion);
        return boiteCatRecherche;
    }
}
