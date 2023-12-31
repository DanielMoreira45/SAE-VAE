import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Cursor;

public class VueAdminGestionUtilisateurs extends BorderPane {
    private Button boutonGestionUtil;
    private Button boutonGestionVentes;
    private Button boutonRecapitulatifs;
    private ComboBox<String> comboBoxDerniereConnexion;
    private ScrollPane scrollPaneProfils;
    private ScrollPane scrollPaneVentes;
    private List<BorderPane> listeDesProfils;
    private List<Utilisateur> listeUtilisateurs;
    private ToutLesUtilisateurs toutLesUtilisateurs;
    private List<CaseVente> listeDesCasesVentes;
    private List<Vente> listeVentes;
    private TouteLesVentes touteLesVentes;

    private Utilisateur admin;
    private ConnexionMySQL laConnexionMySQL;

    /**
     * Construction permettant de créer une nouvelle vue de gestion des utilisateurs.
     */
    public VueAdminGestionUtilisateurs(ConnexionMySQL laConnexionMySQL, Utilisateur admin) {
        super();
        this.admin = admin;
        this.laConnexionMySQL = laConnexionMySQL;
        this.toutLesUtilisateurs = new ToutLesUtilisateurs(laConnexionMySQL);
        this.touteLesVentes = new TouteLesVentes(laConnexionMySQL);


        Text titre = this.initTitrePage();
        this.setTop(titre);
        this.setLeft(this.getMenu());
        this.setStyle("-fx-background-color: white;");
        BorderPane.setMargin(titre, new Insets(20));
        this.getStylesheets().add("stylePageUtilisateur.css");

        this.setPageUtilisateur();
    }

    public void setPageVente() {
        try {
            this.listeVentes = this.touteLesVentes.toutVente();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BorderPane partieCentrale = this.getPartieCentraleVentes();
        this.setCenter(partieCentrale);
        BorderPane.setMargin(partieCentrale, new Insets(0, 18, 0, 18));
        BorderPane.setAlignment(partieCentrale, Pos.TOP_CENTER);

        // this.boutonGestionVentes.getStyleClass().add("buttonBleu");
        // this.boutonGestionUtil.getStyleClass().set(0, "buttonBlanc");
        // this.boutonRecapitulatifs.getStyleClass().add("buttonBlanc");
        this.majDesVentes();
    }

    public void setPageUtilisateur() {
        try {
            this.listeUtilisateurs = this.toutLesUtilisateurs.tout();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BorderPane partieCentrale = this.getPartieCentraleUtilisateurs();
        this.setCenter(partieCentrale);
        BorderPane.setMargin(partieCentrale, new Insets(0, 18, 0, 18));
        BorderPane.setAlignment(partieCentrale, Pos.TOP_CENTER);

        // this.boutonGestionUtil.getStyleClass().add("buttonBleu");
        // this.boutonGestionVentes.getStyleClass().add("buttonBlanc");
        // this.boutonRecapitulatifs.getStyleClass().add("buttonBlanc");
        this.majDesProfils();
    }

    /**
     * Méthode permettant d'ajouter toutes les ventes.
     */
    public void ajouterVentes() {
        this.listeDesCasesVentes = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i < this.listeVentes.size()) { 
                this.listeDesCasesVentes.add(new CaseVente(this.listeVentes.get(i), this.laConnexionMySQL, this));
            }
        }
    }

    /**
     * Méthode permettant de mettre à jour l'affichage des ventes dans le ScrollPane.
     */
    public void majDesVentes() {
        this.ajouterVentes();
        VBox tousLesProfils = new VBox(10);
        tousLesProfils.setStyle("-fx-background-color: transparent;");
        if (this.listeDesCasesVentes.isEmpty()) {
            Text texteListeVide = new Text("Il n'y a aucune vente correspondante.");
            texteListeVide.setFont(Font.font("Arial", 18));
            tousLesProfils.setPadding(new Insets(250, 0, 0, 500));
            tousLesProfils.getChildren().add(texteListeVide);
        }
        else {
            for (CaseVente vente : this.listeDesCasesVentes) {
                tousLesProfils.getChildren().add(vente);
            }
        }
        this.scrollPaneVentes.setContent(tousLesProfils);
    }


    /**
     * Méthode permettant d'ajouter tout les profils.
     */
    public void ajouterProfils() {
        this.listeDesProfils = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i < this.listeUtilisateurs.size()) { 
                if (!this.listeUtilisateurs.get(i).equals(this.admin)) this.listeDesProfils.add(new CaseProfil(this.listeUtilisateurs.get(i), this));
            }
        }
    }

    /**
     * Méthode permettant de mettre à jour l'affichage des profils dans le ScrollPane.
     */
    public void majDesProfils() {
        this.ajouterProfils();
        VBox tousLesProfils = new VBox(10);
        tousLesProfils.setStyle("-fx-background-color: transparent;");
        if (this.listeDesProfils.isEmpty()) {
            Text texteListeVide = new Text("Il n'y a aucun utilisateur correspondant.");
            texteListeVide.setFont(Font.font("Arial", 18));
            tousLesProfils.setPadding(new Insets(250, 0, 0, 500));
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
        this.boutonGestionUtil.getStyleClass().add("buttonBlanc");
        // this.boutonGestionUtil.getStyleClass().add("buttonBleu");
        this.boutonGestionUtil.setCursor(Cursor.HAND);
        this.boutonGestionUtil.setOnAction(new ControleurAdminSwitchPage(this));
    }

    /**
     * Méthode permettant d'initialiser le bouton de gestion des ventes.
     */
    private void initBoutonGestionVentes() {
        this.boutonGestionVentes = new Button("Gestion des ventes          >");
        this.boutonGestionVentes.setPadding(new Insets(10));
        this.boutonGestionVentes.getStyleClass().add("buttonBlanc");
        this.boutonGestionVentes.setCursor(Cursor.HAND);
        this.boutonGestionVentes.setOnAction(new ControleurAdminSwitchPage(this));
    }

    /**
     * Méthode permettant d'initialiser le bouton des récapitulatifs.
     */
    private void initBoutonRecapitulatifs() {
        this.boutonRecapitulatifs = new Button("Récapitulatifs                   >");
        this.boutonRecapitulatifs.setPadding(new Insets(10));
        this.boutonRecapitulatifs.getStyleClass().add("buttonBlanc");
        this.boutonRecapitulatifs.setCursor(Cursor.HAND);
        this.boutonRecapitulatifs.setOnAction(new ControleurAdminSwitchPage(this));
    }

    /**
     * Méthode permettant d'initialiser la ComboBox de la dernière connexion.
     */
    private void initComboBox() {
        this.comboBoxDerniereConnexion = new ComboBox<>();
        this.comboBoxDerniereConnexion.setStyle("-fx-background-color: white; -fx-background-radius : 0.8em; -fx-border-radius: 0.8em; -fx-border-color : lightgrey;");
        this.comboBoxDerniereConnexion.getItems().addAll("Tout", "Admin", "Utilisateurs", "Actif", "Inactif");
        this.comboBoxDerniereConnexion.getSelectionModel().selectFirst();
        this.comboBoxDerniereConnexion.setMaxHeight(40);
        this.comboBoxDerniereConnexion.setOnAction(new ControleurAdminTrieUtilisateur(this));
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
     * Méthode permettant d'initialiser le ScrollPane situé dans la partie centrale de la page.
     */
    private void initscrollPaneVentes() {
        this.scrollPaneVentes = new ScrollPane();
        this.scrollPaneVentes.setStyle("-fx-background: #fdfdfd; -fx-border-color: #dddddd; -fx-border-radius : 0.8em; -fx-background-radius: 0.8em;");
        this.scrollPaneVentes.setFitToWidth(true);
        this.scrollPaneVentes.setPadding(new Insets(0, 20, 0, 20));
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
    private BorderPane getPartieCentraleUtilisateurs() {
        BorderPane leCentre = new BorderPane();
        leCentre.setPadding(new Insets(10));
        leCentre.setMaxHeight(900);
        this.initScrollPaneProfils();
        HBox lesElemsDeRecherche = this.getElemsRechercheUtilisateurs();
        BorderPane.setAlignment(lesElemsDeRecherche, Pos.CENTER);
        BorderPane.setAlignment(this.scrollPaneProfils, Pos.TOP_CENTER);
        leCentre.setTop(lesElemsDeRecherche);
        leCentre.setCenter(this.scrollPaneProfils);
        BorderPane.setMargin(lesElemsDeRecherche, new Insets(0, 0, 10, 0));
        BorderPane.setMargin(this.scrollPaneProfils, new Insets(10));
        return leCentre;
    }

    /**
     * Méthode permettant de constituer la partie centrale de la page.
     * @return BorderPane : la partie centrale de la page.
     */
    private BorderPane getPartieCentraleVentes() {
        BorderPane leCentre = new BorderPane();
        leCentre.setPadding(new Insets(10));
        leCentre.setMaxHeight(900);
        this.initscrollPaneVentes();
        HBox lesElemsDeRecherche = this.getElemsRechercheVentes();
        BorderPane.setAlignment(lesElemsDeRecherche, Pos.CENTER);
        BorderPane.setAlignment(this.scrollPaneVentes, Pos.TOP_CENTER);
        leCentre.setTop(lesElemsDeRecherche);
        leCentre.setCenter(this.scrollPaneVentes);
        BorderPane.setMargin(lesElemsDeRecherche, new Insets(0, 0, 10, 0));
        BorderPane.setMargin(this.scrollPaneVentes, new Insets(10));
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

    private TextField setBarreDeRechercheVentes() {
        TextField barreDeRecherche = new TextField();
        barreDeRecherche.setPromptText("Nom d'une vente");
        barreDeRecherche.setAlignment(Pos.CENTER_LEFT);
        barreDeRecherche.setStyle("-fx-effect: dropshadow(gaussian, grey, 8, 0, 1, 1); -fx-background-radius: 0.8em; -fx-background-color: white;");
        barreDeRecherche.setPrefWidth(320);
        
        barreDeRecherche.setOnKeyPressed(new ControleurRechercheVenteClavier(this));

        return barreDeRecherche;
    }

    /**
     * Méthode permettant de créer la barre de recherche en elle-même.
     * @return TextField : le champ de recherche.
     */
    private TextField setBarreDeRechercheUtilisateurs() {
        TextField barreDeRecherche = new TextField();
        barreDeRecherche.setPromptText("Nom d'un utilisateur");
        barreDeRecherche.setAlignment(Pos.CENTER_LEFT);
        barreDeRecherche.setStyle("-fx-effect: dropshadow(gaussian, grey, 8, 0, 1, 1); -fx-background-radius: 0.8em; -fx-background-color: white;");
        barreDeRecherche.setPrefWidth(320);
        
        barreDeRecherche.setOnKeyPressed(new ControleurRechercheUtilisateursClavier(this));

        return barreDeRecherche;
    }

    /**
     * Méthode permettant de créer la boite, qui contient la barre de recherche et son bouton.
     * @return HBox : le boite contenant le bouton et la barre de recherche.
     */
    private HBox getBoiteBarreDeRechercheUtilisateurs() {
        TextField barreDeRech = this.setBarreDeRechercheUtilisateurs();
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
     * Méthode permettant de créer la boite, qui contient la barre de recherche et son bouton.
     * @return HBox : le boite contenant le bouton et la barre de recherche.
     */
    private HBox getBoiteBarreDeRechercheVentes() {
        TextField barreDeRech = this.setBarreDeRechercheVentes();
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
    private HBox getElemsRechercheUtilisateurs() {
        HBox boiteCatRecherche = new HBox(5);
        boiteCatRecherche.setPadding(new Insets(10));
        boiteCatRecherche.setMaxWidth(600);
        this.initComboBox();
        HBox laBoiteDeRecherche = this.getBoiteBarreDeRechercheUtilisateurs();
        laBoiteDeRecherche.setAlignment(Pos.CENTER);
        boiteCatRecherche.getChildren().addAll(laBoiteDeRecherche, this.comboBoxDerniereConnexion);
        return boiteCatRecherche;
    }

    /**
     * Méthode permettant de constituer une boite, contenant la boite de la barre de recherche, avec la ComboBox de la dernière connexion.
     * @return HBox : une boite contenant la barre de recherche et la ComboBox de temps de connexion.
     */
    private HBox getElemsRechercheVentes() {
        HBox boiteCatRecherche = new HBox(5);
        boiteCatRecherche.setPadding(new Insets(10));
        boiteCatRecherche.setMaxWidth(600);
        HBox laBoiteDeRecherche = this.getBoiteBarreDeRechercheVentes();
        laBoiteDeRecherche.setAlignment(Pos.CENTER);
        boiteCatRecherche.getChildren().addAll(laBoiteDeRecherche, this.comboBoxDerniereConnexion);
        return boiteCatRecherche;
    }

    public ToutLesUtilisateurs getToutLesUtilisateurs() { return this.toutLesUtilisateurs; }

    public void setListeUtilisateurs(List<Utilisateur> liste) {
        this.listeUtilisateurs = liste;
    }

    public ScrollPane getScrollPaneProfils() { return this.scrollPaneProfils; }

    public void removeUtilisateur(Utilisateur utilisateur) {
        this.listeUtilisateurs.remove(utilisateur);
    }

    public TouteLesVentes getTouteLesVentes() { return this.touteLesVentes; }

    public void setListeVentes(List<Vente> liste) {
        this.listeVentes = liste;
    }

    public ScrollPane getscrollPaneVentes() { return this.scrollPaneVentes; }

    public void removeVente(Vente vente) {
        this.listeVentes.remove(vente);
    }
}
