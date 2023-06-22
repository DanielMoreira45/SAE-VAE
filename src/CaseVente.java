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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CaseVente extends HBox {
    private Vente vente;
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;

    public CaseVente(Vente vente, AppliVae appli, ConnexionMySQL connexionMySQL) {
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.vente = vente;
        this.setStyle();
        this.setImage();
        this.setContenu();
    }

    private void setImage() {
        Rectangle rect = new Rectangle(280, 180);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        ImagePattern pattern = new ImagePattern(new Image("file:img/image.png", 280, 180, false, false));
        rect.setFill(pattern);
        this.getChildren().add(rect);
    }

    private void setContenu() {
        BorderPane contenu = new BorderPane();
        contenu.setPadding(new Insets(10, 10, 10, 20));

        contenu.setTop(this.setHaut());
        contenu.setCenter(this.setDescription());
        contenu.setBottom(this.setBas());

        this.getChildren().add(contenu);
    }

    private HBox setHaut() {
        Text nomArticle = new Text(this.getTxtMinLongueur(this.vente.getObjet().getNomObjet(), 20));
        HBox nomArtBox = new HBox(nomArticle);
        nomArtBox.setAlignment(Pos.BASELINE_LEFT);
        Text dateFin = new Text(
                "Fin : " + new Timestamp(this.vente.getFinVente()).toString().substring(0, 10));
        HBox dateFinBox = new HBox(dateFin);
        dateFinBox.setAlignment(Pos.BASELINE_RIGHT);
        nomArticle.setFont(Font.font("Valera", FontWeight.MEDIUM, 20));
        nomArticle.setTextAlignment(TextAlignment.LEFT);
        dateFin.setFont(Font.font("Valera", FontWeight.MEDIUM, 20));
        dateFin.setTextAlignment(TextAlignment.RIGHT);
        HBox haut = new HBox(nomArtBox, dateFinBox);
        haut.setSpacing((780 - 280 - 30 - nomArticle.getText().length() * 10 - dateFin.getText().length() * 10));
        haut.setPrefWidth(780 - 280 - 30);
        return haut;
    }

    private Text setDescription() {
        Text description = new Text(
                this.setDescription(this.getTxtMinLongueur(this.vente.getObjet().getDescription(), 200)));
        description.setTextAlignment(TextAlignment.LEFT);
        return description;
    }

    private HBox setBas() {
        Text prix = new Text("Prix : " + (this.vente.getPrixBase()) + "€");
        prix.setFont(Font.font("Valera", FontWeight.MEDIUM, 16));
        prix.setTextAlignment(TextAlignment.CENTER);
        HBox prixPane = new HBox(prix);
        prixPane.setPadding(new Insets(8, 0, 0, 0));

        HBox bouton = new HBox(this.setBoutonEncherir(), prixPane);
        bouton.setSpacing(20);

        HBox cercleBox = new HBox(this.setCercle());
        cercleBox.setPadding(new Insets(5, 0, 0, 0));

        HBox bas = new HBox(bouton, cercleBox);
        bas.setSpacing(140);
        return bas;
    }

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

    private Circle setCercle() {
        Circle cercle = new Circle(15);
        cercle.setFill(Color.web("#72FF91"));
        return cercle;
    }

    private void setStyle() {
        this.setPrefWidth(780);
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(12), null)));
        this.setBorder(new Border(new BorderStroke(Color.web("#D9D9D9"), BorderStrokeStyle.SOLID, new CornerRadii(12),
                new BorderWidths(4))));
    }

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
