import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VueEncheresUtilisateur extends BorderPane {
    private ScrollPane scrollPaneEncheres;
    // private List<> lesEncheresUtilisateur;
    
    /**
     * Constructeur permettant de créer une page listant les enchères d'un utilisateur.
     */
    public VueEncheresUtilisateur() {
        Text titre = this.initTitrePrincipal();
        BorderPane partieCentrale = this.getPartieCentrale();
        this.setTop(titre);
        this.setCenter(partieCentrale);
        this.setStyle("-fx-background-color: white;");
        BorderPane.setMargin(titre, new Insets(20));
        BorderPane.setMargin(partieCentrale, new Insets(0, 18, 0, 18));
        BorderPane.setAlignment(partieCentrale, Pos.TOP_CENTER);
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
        this.scrollPaneEncheres.setPadding(new Insets(0, 20, 0, 20));
    }

    /**
     * Méthode permettant de constituer la partie centrale de la page.
     * @return BorderPane : la partie centrale de la page.
     */
    private BorderPane getPartieCentrale() {
        BorderPane leCentre = new BorderPane();
        leCentre.setPadding(new Insets(10));
        this.initScrollPaneEncheres();
        HBox boiteRecherche = this.getBoiteBarreDeRecherche();
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
        TextField barreDeRecherche = new TextField("Chercher une enchère actuelle");
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
}
