import java.sql.Timestamp;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Case contenant toutes les informations sur les ventes
 */
public class CaseVente extends HBox {
    private Vente vente;
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;
    private Double prixMaxEnchere;
    private VueAdminGestionUtilisateurs parent;

    /**
     * Constructeur de CaseVente permettant de créer une case dans la partie utilisateur.
     * @param vente La vente correspondante à la case
     * @param prixMaxEnchere Le prix maximum des enchères
     * @param appli L'application VAE
     * @param connexionMySQL La connexion MySQL
     */
    public CaseVente(Vente vente, Double prixMaxEnchere, AppliVae appli, ConnexionMySQL connexionMySQL) {
        this.appli = appli;
        this.parent = null;
        this.connexionMySQL = connexionMySQL;
        this.vente = vente;
        this.prixMaxEnchere = prixMaxEnchere;
        this.setStyle();
        this.setImage();
        this.setContenu();

        this.setFillHeight(true);
    }

    /**
     * Constructeur de CaseVente permettant de créer une case dans la partie administrateur
     * @param vente
     * @param connexionMySQL
     * @param parent
     */
    public CaseVente(Vente vente, ConnexionMySQL connexionMySQL, VueAdminGestionUtilisateurs parent) {
        this.connexionMySQL = connexionMySQL;
        this.vente = vente;
        this.prixMaxEnchere = 0.0;
        this.appli = null;
        this.parent = parent;
        this.setStyle();
        this.setImage();
        this.setContenu();

        this.setFillHeight(true);
    }

    /**
     * Permet d'initialiser l'image
     */
    private void setImage() {
        Rectangle rect = new Rectangle(280, 180);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        ImagePattern pattern = new ImagePattern(new Image("file:img/image.png", 280, 180, false, false));
        rect.setFill(pattern);
        this.getChildren().add(rect);
    }

    /**
     * Permet d'initialiser le contenu de la classe (les informations sur la vente)
     */
    private void setContenu() {
        BorderPane contenu = new BorderPane();
        contenu.setPadding(new Insets(10, 10, 10, 20));

        contenu.setTop(this.setHaut());
        contenu.setCenter(this.setDescription());
        contenu.setBottom(this.setBas());

        this.getChildren().add(contenu);
        VBox.setVgrow(contenu, Priority.ALWAYS);
        HBox.setHgrow(contenu, Priority.ALWAYS);
    }

    /**
     * Permet de créer la partie haute du contenu
     * @return La partie haute du contenu (BorderPane)
     */
    private BorderPane setHaut() {
        Text nomArticle = new Text(this.getTxtMinLongueur(this.vente.getObjet().getNomObjet(), 20));
        Text dateFin = new Text(
                "Fin : " + new Timestamp(this.vente.getFinVente()).toString().substring(0, 10));
        HBox dateFinBox = new HBox(dateFin);
        dateFinBox.setAlignment(Pos.CENTER_RIGHT);
        nomArticle.setFont(Font.font("Valera", FontWeight.MEDIUM, 20));
        nomArticle.setTextAlignment(TextAlignment.LEFT);
        dateFin.setFont(Font.font("Valera", FontWeight.MEDIUM, 20));
        dateFin.setTextAlignment(TextAlignment.RIGHT);
        BorderPane haut = new BorderPane();
        haut.setLeft(nomArticle);
        haut.setRight(dateFinBox);
        return haut;
    }

    /**
     * Permet de créer la description de la vente (au milieu)
     * @return La description de la vente (Text)
     */
    private Text setDescription() {
        Text description = new Text(
                this.setDescription(this.getTxtMinLongueur(this.vente.getObjet().getDescription(), 200)));
        description.setTextAlignment(TextAlignment.LEFT);
        return description;
    }

    /**
     * Permet de créer la partie basse du contenu
     * @return La partie basse du contenu (BorderPane)
     */
    private BorderPane setBas() {
        Text prix = new Text("Prix de base : " + this.vente.getPrixBase() + (this.prixMaxEnchere == 0.0 ? "" : " Prix dernière enchère : " + this.prixMaxEnchere + "€"));
        prix.setFont(Font.font("Valera", FontWeight.MEDIUM, 14));
        prix.setTextAlignment(TextAlignment.CENTER);
        HBox prixPane = new HBox(prix);
        prixPane.setPadding(new Insets(12, 0, 0, 0));
        HBox bouton = new HBox();
        if (this.appli == null) bouton.getChildren().addAll(this.setBoutonSupprimer(), prixPane);
        else bouton.getChildren().addAll(this.setBoutonEncherir(), prixPane);
        bouton.setSpacing(20);
        HBox cercleBox = new HBox(this.setCercle());
        cercleBox.setPadding(new Insets(5, 0, 0, 0));
        BorderPane bas = new BorderPane();
        bas.setLeft(bouton);
        bas.setRight(cercleBox);
        // bas.setSpacing(140);
        return bas;
    }

    /**
     * Permet de créer le bouton "supprimer"
     * @return Le bouton "supprimer" (Button)
     */
    private Button setBoutonSupprimer() {
        Button supprimer = new Button("Supprimer");
        supprimer.setPadding(new Insets(10));
        supprimer.setBackground(new Background(new BackgroundFill(Color.web("#ff9292"), CornerRadii.EMPTY, null)));
        supprimer.setBorder(new Border(new BorderStroke(Color.web("#ff9292"), BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(2))));
        supprimer.setOnAction(new ControleurAdminVente(this.parent, this.connexionMySQL, this.vente));
        supprimer.setCursor(Cursor.HAND);
        return supprimer;
    }

    /**
     * Permet de créer le bouton "enchérir"
     * @return Le bouton "enchérir" (Button)
     */
    private Button setBoutonEncherir() {
        Button encherir = new Button("Enchérir");
        encherir.setPadding(new Insets(10));
        encherir.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        encherir.setBorder(new Border(new BorderStroke(Color.web("#D9D9D9"), BorderStrokeStyle.SOLID,
                new CornerRadii(16), new BorderWidths(2))));
        encherir.setOnAction(new ControleurCaseVente(this.appli, this.connexionMySQL));
        encherir.setCursor(Cursor.HAND);
        return encherir;
    }

    /**
     * Permet de créer le cercle de couleur en fonction du statut de la vente
     * @return Le cercle de couleur (Circle)
     */
    private Circle setCercle() {
        Circle cercle = new Circle(15);
        int statut = this.vente.getStatus();
        switch (statut) {
            case 1:
                cercle.setFill(Color.BLUE);
                break;
            case 2:
                cercle.setFill(Color.web("#72FF91"));
                break;
            case 3:
                cercle.setFill(Color.web("#FFFF00"));
                break;
            case 4:
                cercle.setFill(Color.web("#DCDCDC"));
                break;
            default:
                cercle.setFill(Color.BLACK); // non conclue
                break;
        }

        return cercle;
    }

    /**
     * Permet d'appliquer le style de case
     */
    private void setStyle() {
        this.setPrefWidth(780);
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(12), null)));
        this.setBorder(new Border(new BorderStroke(Color.web("#D9D9D9"), BorderStrokeStyle.SOLID, new CornerRadii(12),
                new BorderWidths(4))));
    }

    /**
     * Permet de trouver le texte de longueur nbLettres avec "..." si le texte dépasse
     * @param txt Le texte original
     * @param nbLettres Le nombre de lettre que l'on veut
     * @return Le texte de longueur nbLettres avec "..." si le texte dépasse (String)
     */
    private String getTxtMinLongueur(String txt, int nbLettres) {
        String nvTxt = "";
        for (int i = 0; i < nbLettres; i++) {
            if (txt.length() > i)
                nvTxt += txt.charAt(i);
        }
        if (nvTxt.length() < txt.length())
            nvTxt += "...";
        return nvTxt;
    }

    /**
     * Permet de créer la description en permettant d'aller à la ligne
     * @param desc La description original
     * @return La description transformée
     */
    private String setDescription(String desc) {
        String description = "";
        int cpt = 0;
        for (String mot : desc.split(" ")) {
            if (cpt == 10) {
                description += "\n";
                cpt = 0;
            }
            description += mot + " ";
            cpt++;
        }
        return description;
    }
}
