import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CaseVente extends HBox {
    private Vente vente;
    
    public CaseVente(Vente vente) {
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
        // Text nomArticle = new Text(this.vente.getObjet().getNomObjet());
        // Text prix = new Text(this.vente.getEncheres().get(this.vente.getEncheres().size()-1).getMontant() + "€");
        // Text dateFin = new Text(this.vente.getFinVente());
        Text nomArticle = new Text(this.getTxtMinLongueur("Text", 12));
        Text prix = new Text("8.99 €");
        Text dateFin = new Text("20/06/2023");
        nomArticle.setFont(Font.font("Valera", FontWeight.MEDIUM, 20));
        nomArticle.setTextAlignment(TextAlignment.LEFT);
        prix.setFont(Font.font("Valera", FontWeight.MEDIUM, 20));
        prix.setTextAlignment(TextAlignment.CENTER);
        dateFin.setFont(Font.font("Valera", FontWeight.MEDIUM, 20));
        dateFin.setTextAlignment(TextAlignment.RIGHT);
        HBox haut = new HBox(nomArticle, prix, dateFin);
        haut.setSpacing((780-280-30-nomArticle.getText().length()*10-prix.getText().length()*10-dateFin.getText().length()*10)/3);
        return haut;
    }

    private Text setDescription() {
        // Text description = new Text(this.vente.getObjet().getDescription());
        Text description = new Text(this.setDescription(this.getTxtMinLongueur("ezfoie zjfezoij fezofj oe zfjoe zfjo ezjf ezfoie zjfezoij fezofj oe zfjoe zfjo ezjf ezfoie zjfezoij fezofj oe zfjoe zfjo ezjf ezfoie zjfezoij fezofj oe zfjoe zfjo ezjf ezfoie zjfezoij fezofj oe zfjoe zfjo ezjf ", 200)));
        description.setTextAlignment(TextAlignment.LEFT);
        return description;
    }

    private HBox setBas() {
        HBox bouton = new HBox(this.setBoutonVoirAnnonce(), this.setBoutonEncherir());
        bouton.setSpacing(20);

        HBox cercleBox = new HBox(this.setCercle());
        cercleBox.setPadding(new Insets(5, 0, 0, 0));

        HBox bas = new HBox(bouton, cercleBox);
        bas.setSpacing(200);
        return bas;
    }

    private Button setBoutonVoirAnnonce() {
        Button voirAnnonce = new Button("Voir l'annonce");
        voirAnnonce.setPadding(new Insets(10));
        voirAnnonce.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        voirAnnonce.setBorder(new Border(new BorderStroke(Color.web("#D9D9D9"), BorderStrokeStyle.SOLID, new CornerRadii(16), new BorderWidths(2))));
        return voirAnnonce;
    }

    private Button setBoutonEncherir() {
        Button encherir = new Button("Enchérir");
        encherir.setPadding(new Insets(10));
        encherir.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        encherir.setBorder(new Border(new BorderStroke(Color.web("#D9D9D9"), BorderStrokeStyle.SOLID, new CornerRadii(16), new BorderWidths(2))));
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
        this.setBorder(new Border(new BorderStroke(Color.web("#D9D9D9"), BorderStrokeStyle.SOLID, new CornerRadii(12), new BorderWidths(4))));
    }

    private String getTxtMinLongueur(String txt, int nbLettres) {
        String nvTxt = "";
        for (int i = 0; i < nbLettres; i++) {
            if (txt.length() > i) nvTxt += txt.charAt(i);
        }
        if (nvTxt.length() < txt.length()) nvTxt += "...";
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
            description += mot;
            cpt++;
        }
        return description;
    }
}
