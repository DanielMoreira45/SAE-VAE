import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VueEncheresUtilisateur extends BorderPane {
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;
    private ScrollPane scrollPaneEncheres;
    private TouteLesVentes toutesLesVentes;
    private List<Vente> lesVentes;
    
    /**
     * Constructeur permettant de créer une page listant les enchères d'un utilisateur.
     */
    public VueEncheresUtilisateur(AppliVae appli, ConnexionMySQL connexionMySQL, int idUtil) {
        super();

        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.toutesLesVentes = new TouteLesVentes(this.connexionMySQL);
        try {
            this.lesVentes = this.toutesLesVentes.ventesPourUnAcheteur(idUtil);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        Text titre = this.initTitrePrincipal();
        BorderPane partieCentrale = this.getPartieCentrale();
        this.setTop(titre);
        this.setCenter(partieCentrale);
        this.setStyle("-fx-background-color: white;");
        BorderPane.setMargin(titre, new Insets(20));
        BorderPane.setMargin(partieCentrale, new Insets(0, 18, 0, 18));
        BorderPane.setAlignment(partieCentrale, Pos.TOP_CENTER);
        this.majLesArticles();
    }

    /**
     * Méthode permettant d'initialiser le titre principal de la page.
     * @return Text : le titre de la page.
     */
    private Text initTitrePrincipal() {
        Text titrePrincipal = new Text("Vos enchères actuelles");
        titrePrincipal.setFont(Font.font("Arial", 32));
        return titrePrincipal;
    }

    /**
     * Méthode permettant d'initialiser le ScrollPane situé dans la partie centrale de la page.
     * Il contiendra toutes les enchères auquel l'utilisateur participe.
     */
    private void initScrollPaneEncheres() {
        this.scrollPaneEncheres = new ScrollPane();
        this.scrollPaneEncheres.setStyle("-fx-background: #fdfdfd; -fx-border-color: #dddddd; -fx-border-radius : 0.8em; -fx-background-radius: 0.8em;");
        this.scrollPaneEncheres.setFitToWidth(true);
        this.scrollPaneEncheres.setPadding(new Insets(5, 10, 5, 10));
    }

    /**
     * Méthode permettant de constituer la partie centrale de la page.
     * @return BorderPane : la partie centrale de la page.
     */
    private BorderPane getPartieCentrale() {
        BorderPane leCentre = new BorderPane();
        leCentre.setPadding(new Insets(5, 30, 30, 30));
        this.initScrollPaneEncheres();
        HBox boiteRecherche = this.getElemsRecherche();
        BorderPane.setAlignment(boiteRecherche, Pos.CENTER);
        BorderPane.setAlignment(this.scrollPaneEncheres, Pos.TOP_CENTER);
        leCentre.setTop(boiteRecherche);
        leCentre.setCenter(this.scrollPaneEncheres);
        BorderPane.setMargin(boiteRecherche, new Insets(0, 0, 10, 0));
        BorderPane.setMargin(this.scrollPaneEncheres, new Insets(15, 20, 40, 20));
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
        TextField barreDeRecherche = new TextField();
        barreDeRecherche.setPromptText("Chercher une enchère actuelle");
        barreDeRecherche.setAlignment(Pos.CENTER_LEFT);
        barreDeRecherche.setStyle("-fx-effect: dropshadow(gaussian, grey, 8, 0, 1, 1); -fx-background-radius: 0.8em; -fx-background-color: white;");
        barreDeRecherche.setPrefWidth(320);
        // barreDeRecherche.setOnAction(new ControleurRechercheEnchereUtil(this, barreDeRecherche));
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
     * Méthode permettant de créer le bouton permettant d'inverser l'ordre des enchères.
     * @return Button : Le bouton d'inversement.
     */
    private Button setBoutonInverse() {
        Button inverse = new Button("Inverse");
        inverse.setMaxHeight(40);
        inverse.setFont(Font.font("Valera", 12));
        inverse.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        inverse.setBorder(new Border(new BorderStroke(Color.valueOf("black"), BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(1))));
        inverse.setOnAction(new ControleurTrierEncheresActuelles(this, this.toutesLesVentes));
        return inverse;
    }

    /**
     * Méthode permettant de génerer une boite contenant la barre de recherche avec le bouton d'inversement.
     * @return HBox : la boite contenant les éléments concernés.
     */
    private HBox getElemsRecherche() {
        HBox lesElems = new HBox(10);
        lesElems.setMaxWidth(465);
        lesElems.setPadding(new Insets(10));
        lesElems.getChildren().addAll(this.getBoiteBarreDeRecherche(), this.setBoutonInverse());
        return lesElems;
    }

    /**
     * Méthode permettant de mettre à jour le ScrollPane contenant les ventes.
     */
    public void majLesArticles() {
        VBox vboxToutesLesVentes = new VBox(10);
        vboxToutesLesVentes.setPadding(new Insets(10, 0, 10, 0));
        vboxToutesLesVentes.setStyle("-fx-background-color: transparent;");
        if (this.lesVentes.isEmpty()) {
            Text texteListeVide = new Text("Vous avez participé à aucune enchère.");
            texteListeVide.setFont(Font.font("Arial", 18));
            vboxToutesLesVentes.setPadding(new Insets(15));
            vboxToutesLesVentes.getChildren().add(texteListeVide);
        }
        else {
            for (int i = 0; i < this.lesVentes.size(); i++) {
                CaseVente venteTemp = new CaseVente(this.lesVentes.get(i), this.appli, this.connexionMySQL);
                venteTemp.setMinWidth(200);
                venteTemp.setPadding(new Insets(5));
                vboxToutesLesVentes.getChildren().add(venteTemp);
            }
        }
        this.scrollPaneEncheres.setContent(vboxToutesLesVentes);
    }

    /**
     * Méthode permettant d'initialiser/modifier l'attribut "this.lesVentes"
     * @param lesVentes
     */
    public void setLesVentes(List<Vente> lesVentes) {
        this.lesVentes = lesVentes;
    }

    /**
     * Méthode permettan d'obtenir toutes les ventes.
     * @return TouteLesVentes : la classe TouteLesVentes.
     */
    public TouteLesVentes getToutesLesVentes() {
        return this.toutesLesVentes;
    }

    /**
     * Méthode permettant d'obtenir la liste des ventes.
     * @return List : une liste contenant des objets de type Vente.
     */
    public List<Vente> getLesVentes() {
        return this.lesVentes;
    }

    /**
     * Méthode permettant d'inverser la liste des ventes.
     */
    public void reverseLesVentes() {
        Collections.reverse(this.lesVentes);
    }
}
