
import java.util.Arrays;

import javafx.animation.ScaleTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class PageProfilObjet extends BorderPane {
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;
    private Vente vente;
    private Utilisateur utilisateur;
    private TextField TFEncherir;
    private Label labelErreur;

    public PageProfilObjet(AppliVae appli, ConnexionMySQL connexionMySQL, Vente vente, Utilisateur utilisateur) {
        super();
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.vente = vente;
        this.utilisateur = utilisateur;
        this.TFEncherir = new TextField();
        this.labelErreur = new Label("");
        VBox left = left();
        this.setLeft(left);
        VBox.setMargin(left, new Insets(80, 80, 80, 80));
        GridPane center = center();
        this.setCenter(center);
        BorderPane.setMargin(center, new Insets(80, 80, 80, 80));
    }

    private VBox left() {
        VBox vbox = new VBox();
        vbox.getChildren().add(image());
        vbox.getChildren().add(image());
        vbox.getChildren().add(image());
        vbox.getChildren().add(image());

        vbox.setPadding(new Insets(30, 30, 30, 30));

        return vbox;
    }

    private Rectangle image() {
        Rectangle rect = new Rectangle(380, 180);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        ImagePattern pattern = new ImagePattern(new Image("file:img/image.png", 280, 180, false, false)); // A modifier
                                                                                                          // pour
                                                                                                          // afficher
                                                                                                          // les images
                                                                                                          // de la BD
        rect.setFill(pattern);
        VBox.setMargin(rect, new Insets(10, 10, 10, 10));
        return rect;

    }

    private GridPane center() {
        GridPane gridPane = new GridPane();

        gridPane.add(titre(), 0, 0);
        gridPane.add(prix(), 0, 1);
        VBox description = description();
        gridPane.add(description, 0, 2);
        VBox encheres = encheres();
        gridPane.add(encheres, 0, 3);
        GridPane.setMargin(description, new Insets(50, 20, 20, 25));
        GridPane.setMargin(encheres, new Insets(0, 20, 20, 25));
        HBox encherir = encherir();
        encherir.setAlignment(Pos.CENTER);
        gridPane.add(encherir, 0, 4);

        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.getStyleClass().add("center-gridPane");

        HBox hBox = new HBox(this.labelErreur);
        gridPane.add(hBox, 0, 5);
        GridPane.setMargin(hBox, new Insets(10, 10, 10, 10));
        hBox.setAlignment(Pos.CENTER);
        this.labelErreur.setStyle("-fx-text-fill: red");
        return gridPane;
    }

    private GridPane titre() {
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(1350, 50);

        Text nomObj = new Text("Nom de l'objet");

        ImageView imagePP = new ImageView(new Image("file:img/pp.jpeg")); // A modif pour mettre la pp du vedeur
        imagePP.setFitWidth(70);
        imagePP.setFitHeight(70);
        // mettre l'image de profile un cercle
        Circle clip = new Circle(30, 30, 30);
        imagePP.setClip(clip);

        Label pseudo = new Label("Pseudo"); // A modif pour mettre le pseudo du vendeur

        Button boutonContacter = new Button("Contacter >");
        boutonContacter.setFont(new Font("Verdana", 20));
        boutonContacter.getStyleClass().add("button-without-background");
        boutonContacter.setCursor(Cursor.HAND);
        boutonContacter.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> { // Animation du bouton qui grandit quand on
                                                                             // passe la souris dessus
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), boutonContacter);
            scaleTransition.setToX(1.1); // Facteur d'agrandissement horizontal
            scaleTransition.setToY(1.1); // Facteur d'agrandissement vertical
            scaleTransition.play();
        });
        boutonContacter.addEventHandler(MouseEvent.MOUSE_EXITED, event -> { // Animation du bouton qui reprends sa
                                                                            // taille normale quand on enlève la souris
            ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), boutonContacter);
            scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
            scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
            scaleTransitionReverse.play();
        });

        gridPane.add(nomObj, 1, 1);
        nomObj.setFont(new Font("Verdana", 20));
        gridPane.add(pseudo, 2, 1);
        pseudo.setFont(new Font("Verdana", 20));
        gridPane.add(imagePP, 3, 1);
        gridPane.add(boutonContacter, 4, 1);
        boutonContacter.setFont(new Font("Verdana", 20));

        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPercentWidth(2);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(38);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(38);
        col2.setHalignment(HPos.RIGHT);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(7);
        col3.setHalignment(HPos.RIGHT);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(15);
        col4.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(col0, col1, col2, col3, col4);
        RowConstraints row0 = new RowConstraints(5);
        gridPane.getRowConstraints().add(row0);
        gridPane.getStyleClass().add("title");

        return gridPane;
    }

    private GridPane prix() {
        GridPane gridPane = new GridPane();

        HBox prixMin = new HBox();
        Text textMin = new Text("Prix minimum : ");
        textMin.setFont(new Font("Verdana", 20));
        Label labelMin = new Label("X");
        labelMin.setFont(new Font("Verdana", 20));
        Text textEuro = new Text(" €");
        textEuro.setFont(new Font("Verdana", 20));
        prixMin.getChildren().addAll(textMin, labelMin, textEuro);

        HBox prixBase = new HBox();
        Text textBase = new Text("Prix de base : ");
        textBase.setFont(new Font("Verdana", 20));
        Label labelBase = new Label("X");
        labelBase.setFont(new Font("Verdana", 20));
        textEuro = new Text(" €");
        textEuro.setFont(new Font("Verdana", 20));
        prixBase.getChildren().addAll(textBase, labelBase, textEuro);

        HBox prixAct = new HBox();
        Text textAct = new Text("Prix actuel : ");
        textAct.setFont(new Font("Verdana", 20));
        Label labelAct = new Label("X");
        labelAct.setFont(new Font("Verdana", 20));
        textEuro = new Text(" €");
        textEuro.setFont(new Font("Verdana", 20));
        prixAct.getChildren().addAll(textAct, labelAct, textEuro);

        gridPane.add(prixMin, 1, 1);
        gridPane.add(prixBase, 2, 1);
        gridPane.add(prixAct, 3, 1);

        ColumnConstraints c0 = new ColumnConstraints();
        c0.setPercentWidth(10);
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(30);
        c1.setHalignment(HPos.CENTER);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(30);
        c2.setHalignment(HPos.CENTER);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(30);
        c3.setHalignment(HPos.CENTER);
        gridPane.getColumnConstraints().addAll(c0, c1, c2, c3);

        RowConstraints r0 = new RowConstraints(50);
        gridPane.getRowConstraints().add(r0);

        return gridPane;
    }

    private VBox description() {
        VBox vbox = new VBox();

        Text titre = new Text("Description");
        titre.setFont(new Font("Verdana", 20));

        Text desc = new Text(
                "IZEBFOIBEFoiUEBFOIUEBFIOUBZEFOIBIUFB IEUFIUEBIBiubfezifbuziebfi zebfibeifbziefbizebfizebfibefchnbduvbsz uioeqyyyvboiqrb IZEBFO IBEFoiU EBFOIUE BFIOUBZE FOIBIUFBIEUFIUE BIBiub fezifbuz iebfizebfibeifbziefbizeb fizebfibefchnbduvbszui oeqyyyvboiq rbIZEBFOIBEFoiUEBFOIUEBFIOUBZEFOIBI UFBIEUFIUEBIB iubfezifbuzi ebfiz ebfibeifbziefbizebfizebfibefchnbduvbszuioeq yyyvboiqrb");
        desc.setFont(new Font("Verdana", 16));
        desc.setWrappingWidth(1000);
        desc.setTextAlignment(TextAlignment.JUSTIFY);

        vbox.getChildren().addAll(titre, desc);
        VBox.setMargin(desc, new Insets(30, 20, 20, 20));
        return vbox;
    }

    private VBox encheres() {
        VBox vbox = new VBox();
        Text titre = new Text("Enchères effectuées");
        titre.setFont(new Font("Verdana", 20));
        VBox.setMargin(titre, new Insets(0, 0, 20, 0));

        TilePane tilePane = new TilePane();
        for (int i = 0; i < 8; i++) {
            HBox laCase = new HBox();
            laCase.setStyle(
                    "-fx-background-color: #DDDDDD; -fx-border-color:transparent; -fx-border-radius: 0.8em; -fx-background-radius: 0.8em;");
            laCase.setPadding(new Insets(5, 5, 5, 5));

            ImageView imagePP = new ImageView(new Image("file:img/pp.jpeg")); // A modif pour mettre la pp du vedeur
            imagePP.setFitWidth(70);
            imagePP.setFitHeight(70);
            // mettre l'image de profile un cercle
            Circle clip = new Circle(15, 15, 15);
            imagePP.setClip(clip);

            VBox vboxCase = new VBox();
            Label pseudo = new Label("Pseudo"); // A modif pour mettre le pseudo de l'enchérisseur
            VBox.setMargin(pseudo, new Insets(5));

            Label date = new Label("Date"); // A modif pour mettre la date de l'enchère
            VBox.setMargin(date, new Insets(5));

            Label prix = new Label("Prix"); // A modif pour mettre le prix de l'enchère
            VBox.setMargin(prix, new Insets(5));
            vboxCase.getChildren().addAll(pseudo, date, prix);
            vboxCase.setPrefWidth(180);

            laCase.getChildren().addAll(imagePP, vboxCase);
            TilePane.setMargin(laCase, new Insets(10, 10, 10, 10));
            tilePane.getChildren().add(laCase);
            laCase.setPrefWidth(200);
        }
        vbox.getChildren().addAll(titre, tilePane);
        return vbox;
    }

    private HBox encherir() {
        HBox hbox = new HBox();
        
        this.TFEncherir.setPromptText("Votre montant en €");
        this.TFEncherir.getStyleClass().add("text-field-montant");

        Button bouton = new Button("ENCHERIR >");

        bouton.setOnAction(new ControleurEncherir(this, this.appli, this.connexionMySQL, this.vente, this.utilisateur));
        bouton.setCursor(Cursor.HAND);
        bouton.getStyleClass().add("button-encherir");
        bouton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> { // Animation du bouton qui grandit quand on passe la
                                                                    // souris dessus
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), bouton);
            scaleTransition.setToX(1.1); // Facteur d'agrandissement horizontal
            scaleTransition.setToY(1.1); // Facteur d'agrandissement vertical
            scaleTransition.play();
        });
        bouton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> { // Animation du bouton qui reprends sa taille normale
                                                                   // quand on enlève la souris
            ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), bouton);
            scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
            scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
            scaleTransitionReverse.play();
        });

        hbox.getChildren().addAll(this.TFEncherir, bouton);
        
        return hbox;
    }

    public String getTFEncherir(){
        return this.TFEncherir.getText();
    }

    public void erreurEncherir(boolean erreur){
        if (erreur){
            this.TFEncherir.setStyle("-fx-border-color: red");
        }
        else{
            this.TFEncherir.setStyle("-fx-border-color: #DDDDDD");
        }
    }

    public void messageErreurEncherir(String erreur){
        if (erreur.equals("lettre")){
            this.labelErreur.setText("  * Le montant doit être un nombre");
        }
        else {
            this.labelErreur.setText("  * Le montant doit être supérieur à la dernière enchère");
        }
    }

    public void popUpEnchereAjoutee(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Enchère ajoutée");
        alert.setHeaderText("Votre enchère a bien été ajoutée");
        alert.showAndWait();
    }
}
