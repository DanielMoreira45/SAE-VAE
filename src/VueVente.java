import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VueVente extends BorderPane {
    TextField tfTitreVente;
    TextArea descriptionVente;
    ComboBox<Categorie> choixCategorie;
    ComboBox<String> choixMarque;
    ComboBox<String> choixEtat;
    TextField prixMin;
    TextField prixMax;
    // Gestion dates
    Button ajoutVente;

    public VueVente() {
        super();
        this.setTop(new BarreDeNav());
        this.setCenter(this.boiteCentrale());
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
    private VBox boiteCentrale() {
        VBox laBoite = new VBox(10);
        laBoite.setPadding(new Insets(20));
        VBox boiteTitre = this.boiteTextFieldTitreVente();
        VBox boiteDesc = this.boiteTextAreaDescriptionVente();
        HBox hboxBoiteCatMarqueEtat = this.boiteCatMarqueEtat();
        HBox hboxPrixDureeAjout = this.boitePrixDureeAjout();
        laBoite.getChildren().addAll(this.titrePrincipal(), boiteTitre, boiteDesc, hboxBoiteCatMarqueEtat, hboxPrixDureeAjout);
        VBox.setMargin(boiteTitre, new Insets(0, 50, 0, 50));
        VBox.setMargin(boiteDesc, new Insets(0, 50, 0, 50));
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
        this.choixCategorie.getItems().addAll(Categorie.values());
        this.choixCategorie.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser le ComboBox pour la marque de la vente.
     */
    private void initComboBoxMarque() {
        this.choixMarque = new ComboBox<>();
        this.choixMarque.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant d'initialiser le ComboBox pour l'état de la vente.
     */
    private void initComboBoxEtat() {
        this.choixEtat = new ComboBox<>();
        this.choixEtat.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    private void initTfPrix() {
        this.prixMin = new TextField();
        this.prixMax = new TextField();
        this.prixMin.setMaxWidth(50);
        this.prixMax.setMaxWidth(50);
        this.prixMin.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
        this.prixMax.setStyle("-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: white;");
    }

    /**
     * Méthode permettant de créer la boite contenant la zone de saisie du titre de la vente.
     * @return VBox : une boite contenant le titre de la zone et le textField associé.
     */
    private VBox boiteTextFieldTitreVente() {
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
    private VBox boiteTextAreaDescriptionVente() {
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
    private VBox boiteCategorie(int largeur) {
        VBox vboxCategorie = new VBox(5);
        vboxCategorie.setPrefWidth(largeur);
        this.initComboBoxCategorie();
        vboxCategorie.setStyle("-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxCategorie.getChildren().addAll(this.titreDesSection("Catégorie"), this.choixCategorie);
        VBox.setMargin(this.choixCategorie, new Insets(5, 50, 15, 50));
        return vboxCategorie;
    }

    /**
     * Méthode permettant de créer la boite contenant le ComboBox de la marque.
     * @param largeur int : la largeur de la boite à créer.
     * @return VBox : la boite contenant le titre de la section et la ComboBox concernée.
     */
    private VBox boiteMarque(int largeur) {
        VBox vboxMarque = new VBox(5);
        vboxMarque.setPrefWidth(largeur);
        this.initComboBoxMarque();
        vboxMarque.setStyle("-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxMarque.getChildren().addAll(this.titreDesSection("Marque"), this.choixMarque);
        VBox.setMargin(this.choixMarque, new Insets(5, 50, 15, 50));
        return vboxMarque;
    }

    /**
     * Méthode permettant de créer la boite contenant le ComboBox de l'état.
     * @param largeur int : la largeur de la boite à créer.
     * @return VBox : la boite contenant le titre de la section et la ComboBox concernée.
     */
    private VBox boiteEtat(int largeur) {
        VBox vboxEtat = new VBox(5);
        vboxEtat.setPrefWidth(largeur);
        this.initComboBoxEtat();
        vboxEtat.setStyle("-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxEtat.getChildren().addAll(this.titreDesSection("État"), this.choixEtat);
        VBox.setMargin(this.choixEtat, new Insets(5, 50, 15, 50));
        return vboxEtat;
    }

    /**
     * Méthode permettant de créer une boite contenant les boites de la catégorie, de la marque et de l'état.
     * @return HBox : la boite contenant les autres boites concernées.
     */
    private HBox boiteCatMarqueEtat() {
        HBox laBoite = new HBox(30);
        int largeur = 268;
        laBoite.getChildren().addAll(this.boiteCategorie(largeur), this.boiteMarque(largeur), this.boiteEtat(largeur));
        return laBoite;
    }

    /**
     * Méthode permettant de créer la boite contenant les labels et les TextFields des prix.
     * @param largeur double : la largeur de la boite à créer.
     * @return HBox : la boite contenant les labels et les TextFields des prix.
     */
    private HBox boiteTfPrix(double largeur) {
        HBox laBoite = new HBox(20);
        laBoite.setPrefWidth((int)largeur);
        laBoite.setPadding(new Insets(10));
        VBox prixMin = new VBox(new Label("minimum"), this.prixMin);
        VBox prixVise = new VBox(new Label("visé"), this.prixMax);
        laBoite.getChildren().addAll(prixMin, prixVise);
        return laBoite;
    }

    /**
     * Méthode permettant de créer la section des prix.
     * @param largeur int : la largeur de la boite à créer.
     * @return VBox : la boite contenant le titre de la section et les éléments concernés.
     */
    private VBox boiteDesPrix(int largeur) {
        VBox vboxDesPrix = new VBox(5);
        this.initTfPrix();
        HBox laBoiteDestfPrix = this.boiteTfPrix(vboxDesPrix.getWidth());
        vboxDesPrix.setPrefWidth(largeur);
        vboxDesPrix.setStyle("-fx-background-color : #e1edfb; -fx-background-radius : 0.8em; -fx-border-color: lightgrey; -fx-border-radius : 0.8em;");
        vboxDesPrix.getChildren().addAll(this.titreDesSection("Prix minimun & prix visé"), laBoiteDestfPrix);
        laBoiteDestfPrix.setAlignment(Pos.CENTER);
        return vboxDesPrix;
    }

    /**
     * Méthode permettant de créer une boite contenant les boites des prix, de la durée et le bouton de validation.
     * @return HBox : la boite contenant les autres boites concernées.
     */
    private HBox boitePrixDureeAjout() {
        HBox laBoite = new HBox(10);
        int largeur = 268;
        laBoite.getChildren().addAll(this.boiteDesPrix(largeur));
        return laBoite;
    }
}
