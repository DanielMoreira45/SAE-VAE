import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;



public class FenetrePageUtilisateur extends Application{

    private ImageView profileImage;

    private String nomUtilisateur;

    private String nom;

    private String prenom;

    private String dateNaissance;

    private BorderPane root;


    @Override
    public void init() {
        this.profileImage = new ImageView("pp.jpeg");
        this.nomUtilisateur = "Captain_Ayhos";
        this.nom= "Rémi";
        this.prenom= "Boulay";
        this.dateNaissance= "08/08/2004";
    }

    public void start(Stage stage) throws Exception{
        // Construction du graphe de scène
        this.root = new BorderPane();
        Scene scene = new Scene(root);
        stage.setTitle("Fenetre Acceuil");
        stage.setFullScreen(true);
        this.ajouteMenu(root);
        this.ajouteMilieu(root);
        stage.setFullScreenExitHint("");

        scene.getStylesheets().add("styleNavBar.css");
        scene.getStylesheets().add("stylePageUtilisateur.css");

        stage.setScene(scene);
        stage.show();
    }

    public void afficherInfoPerso(){
        this.ajouteMilieu(this.root);
    }

    public void afficherChangerMdp(){
        GridPane fenetreChangerMdp = new FenetrePageChangerMdp(this.appli, this.connexionMySQL);
        this.root.setCenter(fenetreChangerMdp);
    } 

    public void afficherPaiments(){
        GridPane fenetrePaiments = new FenetrePagePaiments(this.appli, this.connexionMySQL);
        this.root.setCenter(fenetrePaiments);
    } 

public void ajouteMenu(BorderPane root){
    VBox menu = new VBox();
    Text pageUtilisateur = new Text("Page Utilisateur");
    pageUtilisateur.setStyle("-fx-font-size: 55px; -fx-fill: #000000; -fx-font-family: 'Verdana';");
    menu.getChildren().add(pageUtilisateur);
    root.setLeft(menu);
    menu.setPadding(new Insets(20,20,20,20));
    VBox sousMenu = new VBox();
    Button infosPerso = new Button("Informations Personnelles");
    infosPerso.setCursor(Cursor.HAND);
    infosPerso.setOnAction(new ControleurBoutonCompte(this));

    Button changerMdp = new Button("Changer de Mot de Passe   >");
    changerMdp.setCursor(Cursor.HAND);
    changerMdp.setOnAction(new ControleurBoutonCompte(this));

    Button paiment = new Button("Paiments                          >");
    paiment.setCursor(Cursor.HAND);
    paiment.setOnAction(new ControleurBoutonCompte(this));

    infosPerso.getStyleClass().add("buttonBleu");
    changerMdp.getStyleClass().add("buttonBlanc");
    paiment.getStyleClass().add("buttonBlanc");
    sousMenu.getChildren().addAll(infosPerso, changerMdp, paiment);
    sousMenu.setSpacing(15);
    sousMenu.setPadding(new Insets(45,50,0,20));
    menu.getChildren().add(sousMenu);
}

public void ajouteMilieu(BorderPane root){
    GridPane gridPane = new GridPane();
gridPane.setPadding(new Insets(300, 0, 0, 30));
    gridPane.setStyle("-fx-border-color: #D9D9D9; -fx-border-width: 3px; -fx-border-radius: 10; -fx-padding: 20px;");
    BorderPane.setMargin(gridPane, new Insets(130, 100, 100, 30));
    gridPane.setHgap(30);
    gridPane.setVgap(50);

    gridPane.add(new Text("Nom d'utilisateur "), 0, 0);
    TextField textFieldNomUtilisateur = new TextField();
    gridPane.add(textFieldNomUtilisateur, 1, 0);
    textFieldNomUtilisateur.setPromptText(this.nomUtilisateur);

    Button boutonModifNomUtilisateur = new Button("Modifier");
    boutonModifNomUtilisateur.getStyleClass().add("buttonBleu");
    gridPane.add(boutonModifNomUtilisateur, 2, 0);
    textFieldNomUtilisateur.getStyleClass().add("text-fieldU");

    gridPane.add(new Text("Nom"), 0, 1);
    TextField textFieldNom = new TextField();
    gridPane.add(textFieldNom, 1, 1);
    textFieldNom.setPromptText(this.nom);
    Button boutonModifNom = new Button("Modifier");
    boutonModifNom.getStyleClass().add("buttonBleu");
    gridPane.add(boutonModifNom, 2, 1);
    textFieldNom.getStyleClass().add("text-fieldU");

    gridPane.add(new Text("Prénom"), 0, 2);
    TextField textFieldPrenom = new TextField();
    gridPane.add(textFieldPrenom, 1, 2);
    textFieldPrenom.setPromptText(this.prenom);
    Button boutonModifPrenom = new Button("Modifier");
    boutonModifPrenom.getStyleClass().add("buttonBleu");
    gridPane.add(boutonModifPrenom, 2, 2);
    textFieldPrenom.getStyleClass().add("text-fieldU");

    gridPane.add(new Text("Date de naissance"), 0, 3);
    DatePicker datePicker = new DatePicker();
    gridPane.add(datePicker, 1, 3);
    datePicker.setPromptText(this.dateNaissance);
    Button boutonModifDateNaissance = new Button("Modifier");
    boutonModifDateNaissance.getStyleClass().add("buttonBleu");
    gridPane.add(boutonModifDateNaissance, 2, 3);
    datePicker.getStyleClass().add("datePiker");

    gridPane.add(new Text("Photo de Profil"), 0, 4);
    gridPane.add(this.profileImage, 1, 4);
    Button boutonModifPP = new Button("Modifier");
    boutonModifPP.getStyleClass().add("buttonBleu");
    gridPane.add(boutonModifPP, 2, 4);
    profileImage.setPreserveRatio(true);
    profileImage.setFitWidth(200); // Spécifiez la largeur souhaitée ici

    root.setCenter(gridPane);
}


public static void main(String[] args) {
    launch(args);
}
}
