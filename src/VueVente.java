import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class VueVente extends VBox {
    Button ajoutPhotos;
    List<ImageView> listeDesPhotos;
    List<Photo> lesPhotos;
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
    AppliVae appli;
    ConnexionMySQL connexionMySQL;
    String titre = null;
    ImageView imageActu = null;
    private Utilisateur utilisateur;

    public VueVente(AppliVae appli, ConnexionMySQL connexionMySQL, Utilisateur utilisateur) {
        super(10);
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        Insets insetsParDefaut = new Insets(2, 70, 2, 70);
        this.setPadding(new Insets(20));
        VBox sectionPhotos = this.sectionDesPhotos();
        VBox sectionTitre = this.sectionTitreVente();
        VBox sectionDesc = this.sectionDescriptionVente();
        GridPane gpBoiteCatMarqueEtat = this.regroupementSectionCategorieMarqueEtat();
        GridPane gpPrixDureeAjout = this.regroupementPrixDureeAjout();
        this.getChildren().addAll(this.titrePrincipal(), sectionPhotos, sectionTitre, sectionDesc, gpBoiteCatMarqueEtat,
                gpPrixDureeAjout);
        VBox.setMargin(sectionPhotos, insetsParDefaut);
        VBox.setMargin(sectionTitre, insetsParDefaut);
        VBox.setMargin(sectionDesc, insetsParDefaut);
        VBox.setMargin(gpBoiteCatMarqueEtat, insetsParDefaut);
        VBox.setMargin(gpPrixDureeAjout, insetsParDefaut);
        ControleurMiseEnVente cme = new ControleurMiseEnVente(this, connexionMySQL);
        ajoutPhotos.setOnAction(cme);
        ajoutVente.setOnAction(cme);
        this.utilisateur = utilisateur; // vendeur
    }
    public String getCategorie(){
        return this.choixCategorie.getValue();
    }

    public String getMarque(){
        return this.choixMarque.getValue();
    }

    public String getEtat(){
        return this.choixEtat.getValue();
    }

    public String getDesc(){
        return this.descriptionVente.getText();
    }

    public Integer getPrixMin(){
        return Integer.valueOf(prixMin.getText());
    }

    public LocalDate dateDebut(){
        return dateDebut.getValue();
    }

    public LocalDate dateFin(){
        return dateFin.getValue();
    }

    public Integer getPrixMax(){
        return Integer.valueOf(prixMin.getText());
    }

    public List<Photo> getPhotos(){
        return this.lesPhotos;
    }

    public Utilisateur getVendeur(){
        return this.utilisateur;
    }


    public void ajouteUnePhoto(Photo photo){
        this.lesPhotos.add(photo);
    }

    /**
     * Méthode permettant d'initialiser l'image par défaut.
     */
    private void initDesPhotos() {
        this.listeDesPhotos = new ArrayList<>();
        ImageView imageParDefaut = new ImageView(new Image("file:./img/defaultImage.png"));
        imageParDefaut.setFitWidth(200);
        imageParDefaut.setPreserveRatio(true);
        this.listeDesPhotos.add(imageParDefaut);
    }

    /**
     * Méthode permettant d'ajouter une nouvelle photo.
     * 
     * @param lien String : le lien vers la photo.
     */
    public void ajoutImage(String lien) {
        if (this.listeDesPhotos.size() < 4) {
            if (this.listeDesPhotos.size() == 1) {
                this.listeDesPhotos.remove(0);
            }
            ImageView nouvImg = new ImageView(new Image("file:./img/" + lien));
            nouvImg.setFitWidth(200);
            nouvImg.setPreserveRatio(true);
            this.listeDesPhotos.add(nouvImg);
        }
    }

    /**
     * Méthode permettant d'ajouter une nouvelle photo avec un filechooser.
     */
    public void ajoutImage() {
        FileChooser imageChoisi = new FileChooser();
        imageChoisi.setTitle("Ajoutez une image");
        imageChoisi.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.png"));
        
        File fichierImage = imageChoisi.showOpenDialog(null);
        if (fichierImage != null) {
            this.titre = fichierImage.getName();
            Image image1 = new Image(fichierImage.toURI().toString());
            this.imageActu = new ImageView(image1);
            this.listeDesPhotos.add(imageActu);
        }
    }

    public DatePicker getDateDebut(){
        return this.dateDebut;
    }

    public DatePicker getDateFin(){
        return this.dateFin;
    }

    public String getTitre(){
        return this.titre;
    }


    public ImageView getImageView(){
        return this.imageActu;
    }

    /**
     * Méthode permettant de créer le titre principal de la page.
     * 
     * @return Text : le titre principal.
     */
    private Text titrePrincipal() {
        Text titreDeLaPage = new Text("Mise en vente");
        titreDeLaPage.setFont(Font.font("Arial", 32));
        return titreDeLaPage;
    }

    /**
     * Méthode permettant de créer un titre pour une section.
     * 
     * @param nomSection String : le titre à mettre.
     * @return HBox : la boite contenant le titre de la section.
     */
    private HBox titreDesSection(String nomSection) {
        HBox titreDeLaBoite = new HBox(5, new Text(nomSection));
        titreDeLaBoite.setPadding(new Insets(5));
        titreDeLaBoite.setStyle(
                "-fx-effect: dropshadow(gaussian, grey, 12, 0, 2, 3); -fx-background-color : white; -fx-background-radius : 0.8em;");
        titreDeLaBoite.setMaxWidth(titreDeLaBoite.getWidth());
        return titreDeLaBoite;
    }

    // Initialisation des attributs de la classe

    private void initBoutonAjoutPhotos() {
        ImageView imageAjoutPhoto = new ImageView(new Image("file:./img/ajoutPhoto.png"));
        imageAjoutPhoto.setFitWidth(50);
        imageAjoutPhoto.setPreserveRatio(true);
        this.ajoutPhotos = new Button("Ajoutez des photos", imageAjoutPhoto);
        this.ajoutPhotos.setStyle(
                "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: grey;");
    }

    /**
     * Méthode permettant d'initialiser le TextField pour le titre de la vente.
     */
    private void initTextFieldTitre() {
        this.tfTitreVente = new TextField();
        this.tfTitreVente.setStyle(
                "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser le TextArea pour la description de la vente.
     */
    private void initTextAreaDesc() {
        this.descriptionVente = new TextArea();
        this.descriptionVente.setStyle(
                "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
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
        this.choixCategorie.setStyle(
                "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser le ComboBox pour la marque de la vente.
     */
    private void initComboBoxMarque() {
        this.choixMarque = new ComboBox<>();
        this.choixMarque.getItems().add("(À venir...)");
        this.choixMarque.setStyle(
                "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser le ComboBox pour l'état de la vente.
     */
    private void initComboBoxEtat() {
        this.choixEtat = new ComboBox<>();
        this.choixEtat.getItems().addAll("À venir", "En cours",
                "À valider", "Validée",
                "Non conclue");
        this.choixEtat.setStyle(
                "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser les TextFields pour les prix.
     */
    private void initTfPrix() {
        this.prixMin = new TextField();
        this.prixMax = new TextField();
        this.prixMin.setMaxWidth(70);
        this.prixMax.setMaxWidth(70);
        this.prixMin.setStyle(
                "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
        this.prixMax.setStyle(
                "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser les champs de sélection de dates.
     */
    private void initDates() {
        this.dateDebut = new DatePicker();
        this.dateDebut.setStyle(
                "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
        this.dateFin = new DatePicker();
        this.dateFin.setStyle(
                "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    private void initBoutonValidation() {
        this.ajoutVente = new Button("Ajouter le produit > ");
        this.ajoutVente.setStyle(
                "-fx-background-color : white; -fx-background-radius : 0.8em; -fx-border-color : black; -fx-border-radius : 0.8em;");
    }

    // Création des différentes boites.

    /**
     * Méthode permettant de créer la boite contenant toutes les photos importées.
     * 
     * @return StackPane : la boite contenant les photos avec le bouton pour en
     *         ajouter.
     */
    private StackPane boitePhotos() {
        HBox boiteDesPhotos = new HBox(5);
        StackPane boutonEtPhotos = new StackPane();
        boiteDesPhotos.setPadding(new Insets(10));
        for (ImageView img : this.listeDesPhotos) {
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
        vboxDesPhotos.setStyle(
                "-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxDesPhotos.getChildren().addAll(new HBox(5, this.titreDesSection("Ajoute jusqu'à 4 photos"),
                this.titreDesSection(this.listeDesPhotos.size() - 1 + "/4")), this.boitePhotos());
        return vboxDesPhotos;
    }

    /**
     * Méthode permettant de créer la boite contenant la zone de saisie du titre de
     * la vente.
     * 
     * @return VBox : une boite contenant le titre de la zone et le textField
     *         associé.
     */
    private VBox sectionTitreVente() {
        VBox vboxTitreVente = new VBox(5);
        this.initTextFieldTitre();
        vboxTitreVente.setStyle(
                "-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxTitreVente.getChildren().addAll(this.titreDesSection("Titre"), this.tfTitreVente);
        VBox.setMargin(this.tfTitreVente, new Insets(5, 50, 15, 50));
        return vboxTitreVente;
    }

    /**
     * Méthode permettant de créer la boite contenant la zone de saisie de la
     * description de la vente.
     * 
     * @return VBox : une boite contenant le titre de la zone et le textArea
     *         associé.
     */
    private VBox sectionDescriptionVente() {
        VBox vboxDescVente = new VBox(5);
        this.initTextAreaDesc();
        vboxDescVente.setStyle(
                "-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxDescVente.getChildren().addAll(this.titreDesSection("Décris ton article"), this.descriptionVente);
        VBox.setMargin(this.descriptionVente, new Insets(5, 50, 15, 50));
        return vboxDescVente;
    }

    /**
     * Méthode permettant de créer la boite contenant le ComboBox de la catégorie.
     * 
     * @param largeur int : la largeur de la boite à créer.
     * @return VBox : la boite contenant le titre de la section et la ComboBox
     *         concernée.
     */
    private VBox sectionCategorie() {
        VBox vboxCategorie = new VBox(5);
        this.initComboBoxCategorie();
        VBox vboxChoixCat = new VBox(this.choixCategorie);
        vboxCategorie.setStyle(
                "-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxCategorie.getChildren().addAll(this.titreDesSection("Catégorie"), vboxChoixCat);
        VBox.setMargin(vboxChoixCat, new Insets(20, 20, 40, 20));
        vboxChoixCat.setAlignment(Pos.CENTER);
        return vboxCategorie;
    }

    /**
     * Méthode permettant de créer la section contenant le ComboBox de la marque.
     * 
     * @param largeur int : la largeur de la boite à créer.
     * @return VBox : la boite contenant le titre de la section et la ComboBox
     *         concernée.
     */
    private VBox sectionMarque() {
        VBox vboxMarque = new VBox(5);
        this.initComboBoxMarque();
        VBox vboxChoixMarque = new VBox(this.choixMarque);
        vboxMarque.setStyle(
                "-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxMarque.getChildren().addAll(this.titreDesSection("Marque"), vboxChoixMarque);
        VBox.setMargin(vboxChoixMarque, new Insets(20, 20, 40, 20));
        vboxChoixMarque.setAlignment(Pos.CENTER);
        return vboxMarque;
    }

    /**
     * Méthode permettant de créer la section contenant le ComboBox de l'état.
     * 
     * @param largeur int : la largeur de la boite à créer.
     * @return VBox : la boite contenant le titre de la section et la ComboBox
     *         concernée.
     */
    private VBox sectionEtat() {
        VBox vboxEtat = new VBox(5);
        this.initComboBoxEtat();
        VBox vboxChoixEtat = new VBox(this.choixEtat);
        vboxEtat.setStyle(
                "-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxEtat.getChildren().addAll(this.titreDesSection("État"), vboxChoixEtat);
        VBox.setMargin(vboxChoixEtat, new Insets(20, 20, 40, 20));
        vboxChoixEtat.setAlignment(Pos.CENTER);
        return vboxEtat;
    }

    /**
     * Méthode permettant de regrouper dans une boite, les sections de la catégorie,
     * de la marque et de l'état.
     * 
     * @return GridPane : la boite contenant les autres boites concernées.
     */
    private GridPane regroupementSectionCategorieMarqueEtat() {
        GridPane laBoite = new GridPane();
        laBoite.setHgap(15);
        VBox sectionCatVbox = this.sectionCategorie();
        VBox sectionMarqueVbox = this.sectionMarque();
        VBox sectionEtatVbox = this.sectionEtat();
        laBoite.add(sectionCatVbox, 0, 0);
        laBoite.add(sectionMarqueVbox, 1, 0);
        laBoite.add(sectionEtatVbox, 2, 0);
        GridPane.setHgrow(sectionCatVbox, Priority.ALWAYS);
        GridPane.setHgrow(sectionMarqueVbox, Priority.ALWAYS);
        GridPane.setHgrow(sectionEtatVbox, Priority.ALWAYS);
        return laBoite;
    }

    /**
     * Méthode permettant de créer une boite contenant les labels et les TextFields
     * des prix.
     * Cette boite est située dans la section des prix.
     * 
     * @param largeur int : la largeur de la boite à créer.
     * @return HBox : la boite contenant les labels et les TextFields des prix.
     */
    private HBox boiteTfPrix(int largeur) {
        HBox laBoite = new HBox(150);
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
     * 
     * @param largeur int : la largeur de la boite à créer.
     * @return VBox : la boite contenant le titre de la section et les éléments
     *         concernés.
     */
    private VBox sectionDesPrix() {
        VBox vboxDesPrix = new VBox(5);
        this.initTfPrix();
        HBox laBoiteDestfPrix = this.boiteTfPrix((int) vboxDesPrix.getWidth());
        vboxDesPrix.setStyle(
                "-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxDesPrix.getChildren().addAll(this.titreDesSection("Prix minimun & prix visé"), laBoiteDestfPrix);
        laBoiteDestfPrix.setAlignment(Pos.BASELINE_CENTER);
        VBox.setMargin(laBoiteDestfPrix, new Insets(20));
        return vboxDesPrix;
    }

    /**
     * Méthode permettant de créer une boite pour regrouper les champs des dates.
     * 
     * @return VBox : une boite contenant les champs des dates.
     */
    private VBox boiteChampsDates() {
        this.initDates();
        VBox vboxDesDates = new VBox(5);
        VBox boiteDebut = new VBox(5, new Label("début"), this.dateDebut);
        VBox boiteFin = new VBox(5, new Label("fin"), this.dateFin);
        boiteDebut.setAlignment(Pos.CENTER);
        boiteFin.setAlignment(Pos.CENTER);
        vboxDesDates.getChildren().addAll(boiteDebut, boiteFin);
        return vboxDesDates;
    }

    /**
     * Méthode permettant de créer la section des dates.
     * 
     * @return VBox : la boite contenant le titre de la section avec les champs pour
     *         gérer les dates.
     */
    private VBox sectionDates() {
        VBox vboxPourLesDates = new VBox(5);
        vboxPourLesDates.setStyle(
                "-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        VBox lesChampsDates = this.boiteChampsDates();
        vboxPourLesDates.getChildren().addAll(this.titreDesSection("Durée et date"), lesChampsDates);
        VBox.setMargin(lesChampsDates, new Insets(20, 20, 30, 20));
        lesChampsDates.setAlignment(Pos.CENTER);
        return vboxPourLesDates;
    }

    
    /**
     * Méthode permettant de regrouper les sections prix, durée, ainsi que le bouton
     * de validation.
     * 
     * @return GridPane : la boite contenant les autres boites concernées.
     */
    private GridPane regroupementPrixDureeAjout() {
        GridPane laBoite = new GridPane();
        laBoite.setHgap(15);
        this.initBoutonValidation();
        VBox sectionPrix = this.sectionDesPrix();
        VBox sectionDates = this.sectionDates();
        laBoite.add(sectionPrix, 0, 0);
        laBoite.add(sectionDates, 1, 0);
        GridPane.setHgrow(sectionPrix, Priority.ALWAYS);
        GridPane.setHgrow(sectionDates, Priority.ALWAYS);
        laBoite.add(this.ajoutVente, 2, 0);
        this.ajoutVente.setAlignment(Pos.CENTER);
        return laBoite;
    }
    public void popUpCompteConnecte(String titre) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("L'objet à bien était mit en vente");
        alert.setHeaderText("Bravo ! ");
        alert.showAndWait();
    }

}
