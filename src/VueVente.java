import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VueVente extends BorderPane {
    Button ajoutPhotos;
    List<ImageView> listeDesPhotos;
    TextField tfTitreVente;
    TextArea descriptionVente;
    ComboBox<String> choixCategorie;
    ComboBox<String> choixMarque;
    ComboBox<String> choixEtat;
    TextField prixMin;
    TextField prixMax;
    DatePicker dateDebut;
    DatePicker dateFin;
    Button ajoutVente;

    public VueVente() {
        super();
        // this.setTop(new BarreDeNav());
        this.setCenter(this.partieCentrale());
    }

    private void initDesPhotos() {
        this.listeDesPhotos = new ArrayList<>();
        ImageView imageParDefaut = new ImageView(new Image("file:./img/defaultImage.png"));
        imageParDefaut.setFitWidth(200);
        imageParDefaut.setPreserveRatio(true);
        imageParDefaut.setStyle("-fx-background-radius : 0.8em;");
        this.listeDesPhotos.add(imageParDefaut);
    }

    public void ajoutImage(String lien) {
        if (this.listeDesPhotos.size() < 4) {
            ImageView nouvImg = new ImageView(new Image(lien));
            nouvImg.setFitWidth(200);
            nouvImg.setPreserveRatio(true);
            this.listeDesPhotos.add(nouvImg);
        }
    }

    /**
     * Méthode permettant de créer le titre principal de la page.
     * @return Text : le titre principal.
     */
    private Text titrePrincipal() {
        Text titreDeLaPage = new Text("Mise en vente");
        titreDeLaPage.setFont(Font.font("Arial", 32));
        return titreDeLaPage;
    }

    /**
     * Méthode permettant de créer la boite centrale de la page.
     * @return VBox : la boite contenant toutes les sections de la page.
     */
    private VBox partieCentrale() {
        VBox laBoite = new VBox(10);
        laBoite.setPadding(new Insets(20));
        VBox sectionPhotos = this.sectionDesPhotos();
        VBox sectionTitre = this.sectionTitreVente();
        VBox sectionDesc = this.sectionDescriptionVente();
        HBox hboxBoiteCatMarqueEtat = this.regroupementSectionCategorieMarqueEtat();
        HBox hboxPrixDureeAjout = this.regroupementPrixDureeAjout();
        laBoite.getChildren().addAll(this.titrePrincipal(), sectionPhotos, sectionTitre, sectionDesc, hboxBoiteCatMarqueEtat, hboxPrixDureeAjout);
        VBox.setMargin(sectionPhotos, new Insets(0, 50, 0, 50));
        VBox.setMargin(sectionTitre, new Insets(0, 50, 0, 50));
        VBox.setMargin(sectionDesc, new Insets(0, 50, 0, 50));
        VBox.setMargin(hboxBoiteCatMarqueEtat, new Insets(0, 50, 0, 50));
        VBox.setMargin(hboxPrixDureeAjout, new Insets(0, 50, 0, 50));
        return laBoite;
    }

    /**
     * Méthode permettant de créer un titre pour une section.
     * @param nomSection String : le titre à mettre.
     * @return HBox : la boite contenant le titre de la section.
     */
    private HBox titreDesSection(String nomSection) {
        HBox titreDeLaBoite = new HBox(5, new Text(nomSection));
        titreDeLaBoite.setPadding(new Insets(5));
        titreDeLaBoite.setStyle("-fx-effect: dropshadow(gaussian, grey, 12, 0, 2, 3); -fx-background-color : white; -fx-background-radius : 0.8em;");
        titreDeLaBoite.setMaxWidth(titreDeLaBoite.getWidth());
        return titreDeLaBoite;
    }

    // Initialisation des attributs de la classe

    private void initBoutonAjoutPhotos() {
        ImageView imageAjoutPhoto = new ImageView(new Image("file:./img/ajoutPhoto.png"));
        imageAjoutPhoto.setFitWidth(50);
        imageAjoutPhoto.setPreserveRatio(true);
        this.ajoutPhotos = new Button("Ajoutez des photos", imageAjoutPhoto);
        this.ajoutPhotos.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: grey;");
    }

    /**
     * Méthode permettant d'initialiser le TextField pour le titre de la vente.
     */
    private void initTextFieldTitre() {
        this.tfTitreVente = new TextField();
        this.tfTitreVente.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser le TextArea pour la description de la vente.
     */
    private void initTextAreaDesc() {
        this.descriptionVente = new TextArea();
        this.descriptionVente.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser la ComboBox pour la catégorie de la vente.
     */
    private void initComboBoxCategorie() {
        this.choixCategorie = new ComboBox<>();
        this.choixCategorie.getItems().addAll(Categorie.ACCESSOIRE, Categorie.CHAUSSURE, 
                                              Categorie.ELECTROMENAGER, Categorie.INFORMATIQUE,
                                              Categorie.JEUX, Categorie.LIVRE,
                                              Categorie.MEUBLE, Categorie.MUSIQUE,
                                              Categorie.OUTIL, Categorie.SPORT,
                                              Categorie.USTENSILECUISINE, Categorie.VEHICULE,
                                              Categorie.VETEMENT);
        this.choixCategorie.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser le ComboBox pour la marque de la vente.
     */
    private void initComboBoxMarque() {
        this.choixMarque = new ComboBox<>();
        this.choixMarque.getItems().add("(À venir...)");
        this.choixMarque.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser le ComboBox pour l'état de la vente.
     */
    private void initComboBoxEtat() {
        this.choixEtat = new ComboBox<>();
        this.choixEtat.getItems().addAll("À venir", "En cours",
                                              "À valider", "Validée",
                                              "Non conclue");
        this.choixEtat.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser les TextFields pour les prix.
     */
    private void initTfPrix() {
        this.prixMin = new TextField();
        this.prixMax = new TextField();
        this.prixMin.setMaxWidth(70);
        this.prixMax.setMaxWidth(70);
        this.prixMin.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
        this.prixMax.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser les champs de sélection de dates.
     */
    private void initDates() {
        this.dateDebut = new DatePicker();
        this.dateDebut.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
        this.dateFin = new DatePicker();
        this.dateFin.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    private void initBoutonValidation() {
        this.ajoutVente = new Button("Ajouter le produit > ");
        this.ajoutVente.setStyle("-fx-background-color : white; -fx-background-radius : 0.8em; -fx-border-color : black; -fx-border-radius : 0.8em;");
    }

    // Création des différentes boites.

    private StackPane boitePhotos() {
        HBox boiteDesPhotos = new HBox(5);
        StackPane boutonEtPhotos = new StackPane();
        boiteDesPhotos.setPadding(new Insets(10));
        for(ImageView img : this.listeDesPhotos) {
            boiteDesPhotos.getChildren().add(img);
        }
        boutonEtPhotos.getChildren().add(boiteDesPhotos);
        if (this.listeDesPhotos.size() < 4) {
            boutonEtPhotos.getChildren().add(this.ajoutPhotos);
        }
        return boutonEtPhotos;
    }

    private VBox sectionDesPhotos() {
        VBox vboxDesPhotos = new VBox(5);
        this.initBoutonAjoutPhotos();
        this.initDesPhotos();
        vboxDesPhotos.setStyle("-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxDesPhotos.getChildren().addAll(new HBox(5, this.titreDesSection("Ajoute jusqu'à 4 photos"), this.titreDesSection(this.listeDesPhotos.size()-1 + "/4")), this.boitePhotos());
        return vboxDesPhotos;
    }

    /**
     * Méthode permettant de créer la boite contenant la zone de saisie du titre de la vente.
     * @return VBox : une boite contenant le titre de la zone et le textField associé.
     */
    private VBox sectionTitreVente() {
        VBox vboxTitreVente = new VBox(5);
        this.initTextFieldTitre();
        vboxTitreVente.setStyle("-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxTitreVente.getChildren().addAll(this.titreDesSection("Titre"), this.tfTitreVente);
        VBox.setMargin(this.tfTitreVente, new Insets(5, 50, 15, 50));
        return vboxTitreVente;
    }

    /**
     * Méthode permettant de créer la boite contenant la zone de saisie de la description de la vente.
     * @return VBox : une boite contenant le titre de la zone et le textArea associé.
     */
    private VBox sectionDescriptionVente() {
        VBox vboxDescVente = new VBox(5);
        this.initTextAreaDesc();
        vboxDescVente.setStyle("-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxDescVente.getChildren().addAll(this.titreDesSection("Décris ton article"), this.descriptionVente);
        VBox.setMargin(this.descriptionVente, new Insets(5, 50, 15, 50));
        return vboxDescVente;
    }

    /**
     * Méthode permettant de créer la boite contenant le ComboBox de la catégorie.
     * @param largeur int : la largeur de la boite à créer.
     * @return VBox : la boite contenant le titre de la section et la ComboBox concernée.
     */
    private VBox sectionCategorie(int largeur) {
        VBox vboxCategorie = new VBox(5);
        vboxCategorie.setPrefWidth(largeur);
        this.initComboBoxCategorie();
        vboxCategorie.setStyle("-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxCategorie.getChildren().addAll(this.titreDesSection("Catégorie"), this.choixCategorie);
        VBox.setMargin(this.choixCategorie, new Insets(5, 50, 15, 50));
        return vboxCategorie;
    }

    /**
     * Méthode permettant de créer la section contenant le ComboBox de la marque.
     * @param largeur int : la largeur de la boite à créer.
     * @return VBox : la boite contenant le titre de la section et la ComboBox concernée.
     */
    private VBox sectionMarque(int largeur) {
        VBox vboxMarque = new VBox(5);
        vboxMarque.setPrefWidth(largeur);
        this.initComboBoxMarque();
        vboxMarque.setStyle("-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxMarque.getChildren().addAll(this.titreDesSection("Marque"), this.choixMarque);
        VBox.setMargin(this.choixMarque, new Insets(5, 0, 15, 70));
        return vboxMarque;
    }

    /**
     * Méthode permettant de créer la section contenant le ComboBox de l'état.
     * @param largeur int : la largeur de la boite à créer.
     * @return VBox : la boite contenant le titre de la section et la ComboBox concernée.
     */
    private VBox sectionEtat(int largeur) {
        VBox vboxEtat = new VBox(5);
        vboxEtat.setPrefWidth(largeur);
        this.initComboBoxEtat();
        vboxEtat.setStyle("-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxEtat.getChildren().addAll(this.titreDesSection("État"), this.choixEtat);
        VBox.setMargin(this.choixEtat, new Insets(5, 0, 15, 68));
        return vboxEtat;
    }

    /**
     * Méthode permettant de regrouper dans une boite, les sections de la catégorie, de la marque et de l'état.
     * @return HBox : la boite contenant les autres boites concernées.
     */
    private HBox regroupementSectionCategorieMarqueEtat() {
        HBox laBoite = new HBox(30);
        int largeur = 268;
        laBoite.getChildren().addAll(this.sectionCategorie(largeur), this.sectionMarque(largeur), this.sectionEtat(largeur));
        return laBoite;
    }

    /**
     * Méthode permettant de créer une boite contenant les labels et les TextFields des prix.
     * Cette boite est située dans la section des prix.
     * @param largeur int : la largeur de la boite à créer.
     * @return HBox : la boite contenant les labels et les TextFields des prix.
     */
    private HBox boiteTfPrix(int largeur) {
        HBox laBoite = new HBox(150);
        laBoite.setPrefWidth((int)largeur);
        laBoite.setPadding(new Insets(25));
        Label euroMin = new Label("€");
        Label euroMax = new Label("€");
        StackPane champMin = new StackPane(this.prixMin, euroMin);
        StackPane champVise = new StackPane(this.prixMax, euroMax);
        StackPane.setAlignment(euroMin, Pos.CENTER_RIGHT);
        StackPane.setAlignment(euroMax, Pos.CENTER_RIGHT);
        StackPane.setMargin(euroMax, new Insets(10));
        StackPane.setMargin(euroMin, new Insets(10));
        VBox boitePrixMin = new VBox(new Label("minimum"), champMin);
        VBox boitePrixVise = new VBox(new Label("visé"), champVise);
        boitePrixMin.setAlignment(Pos.CENTER);
        boitePrixVise.setAlignment(Pos.CENTER);
        laBoite.getChildren().addAll(boitePrixMin, boitePrixVise);
        return laBoite;
    }

    /**
     * Méthode permettant de créer la section des prix.
     * @param largeur int : la largeur de la boite à créer.
     * @return VBox : la boite contenant le titre de la section et les éléments concernés.
     */
    private VBox sectionDesPrix(int largeur) {
        VBox vboxDesPrix = new VBox(5);
        this.initTfPrix();
        HBox laBoiteDestfPrix = this.boiteTfPrix((int)vboxDesPrix.getWidth());
        vboxDesPrix.setPrefWidth(largeur);
        vboxDesPrix.setStyle("-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxDesPrix.getChildren().addAll(this.titreDesSection("Prix minimun & prix visé"), laBoiteDestfPrix);
        laBoiteDestfPrix.setAlignment(Pos.BASELINE_CENTER);
        return vboxDesPrix;
    }

    /**
     * Méthode permettant de créer une boite pour regrouper les champs des dates.
     * @return VBox : une boite contenant les champs des dates.
     */
    private VBox boiteChampsDates() {
        VBox vboxDesDates = new VBox(5);
        this.initDates();
        vboxDesDates.getChildren().addAll(new VBox(5, new Label("début"), this.dateDebut), new VBox(5, new Label("fin"), this.dateFin));
        return vboxDesDates;
    }

    /**
     * Méthode permettant de créer la section des dates.
     * @return VBox : la boite contenant le titre de la section avec les champs pour gérer les dates.
     */
    private VBox sectionDates() {
        VBox vboxPourLesDates = new VBox(5);
        vboxPourLesDates.setStyle("-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        VBox lesChampsDates = this.boiteChampsDates();
        vboxPourLesDates.getChildren().addAll(this.titreDesSection("Durée et date"), lesChampsDates);
        VBox.setMargin(lesChampsDates, new Insets(5, 50, 15, 50));
        return vboxPourLesDates;
    }

    /**
     * Méthode permettant de regrouper les sections prix, durée, ainsi que le bouton de validation.
     * @return HBox : la boite contenant les autres boites concernées.
     */
    private HBox regroupementPrixDureeAjout() {
        HBox laBoite = new HBox(10);
        int largeur = 350;
        this.initBoutonValidation();
        VBox sectionPrix = this.sectionDesPrix(largeur);
        sectionPrix.setFillWidth(true);
        VBox sectionDatesVbox = this.sectionDates();
        sectionDatesVbox.setFillWidth(true);
        laBoite.getChildren().addAll(sectionPrix, sectionDatesVbox, this.ajoutVente);
        HBox.setMargin(this.ajoutVente, new Insets(60, 0, 0, 20));
        this.ajoutVente.setAlignment(Pos.CENTER);
        return laBoite;
    }
}
