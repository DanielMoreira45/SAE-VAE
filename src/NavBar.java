import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;



public class NavBar extends Application{

    private Image profileImage;


    @Override
    public void init() {
        Image profileImage = new Image("pp.jpeg");
        ImageView imagePP = new ImageView(profileImage);
    }

    public void start(Stage stage) throws Exception{
        // Construction du graphe de scène
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        stage.setTitle("Fenetre Acceuil");
        stage.setFullScreen(true);
        this.ajouteNavBar(root);
        stage.setFullScreenExitHint("");

        scene.getStylesheets().add("styleAcceuil.css");

        stage.setScene(scene);
        stage.show();
    }

public void ajouteNavBar(BorderPane root){
    // Barre de recherche
    HBox haut = new HBox();
    HBox navBar = new HBox();
    HBox navBar2 = new HBox();

    haut.getStyleClass().add("bottom-border");

    
    Button boutonLogo = new Button();
    ImageView imageView = new ImageView(new Image("logo.png"));
    imageView.setFitHeight(70);
    imageView.setFitWidth(70);
    boutonLogo.setGraphic(imageView);
    boutonLogo.setCursor(Cursor.HAND);
    boutonLogo.setStyle("-fx-background-color: transparent;");

    haut.getChildren().add(boutonLogo);

    // Rectangle Bleu
    HBox rectangleB = new HBox(10);
    rectangleB.setStyle("-fx-background-color: #B5D6FD; -fx-border-color:black; -fx-border-radius: 0.8em; -fx-background-radius: 0.8em;");
    rectangleB.setPadding(new Insets(5,5,5,5));
    rectangleB.setMaxHeight(45);
    rectangleB.setPrefWidth(400);

    ImageView imageRecherche = new ImageView("imageRecherche.png");
    imageRecherche.setFitHeight(30);
    imageRecherche.setFitWidth(30);

    TextField textFieldRecherche = new TextField();
    textFieldRecherche.setPromptText("Nom d'un produit");
    textFieldRecherche.getStyleClass().add("text-field");
    textFieldRecherche.setPrefWidth(400);


    GridPane gridPane = new GridPane();
    gridPane.add(textFieldRecherche, 1,0 );
    gridPane.add(imageRecherche, 0, 0);
    rectangleB.getChildren().addAll(imageRecherche, textFieldRecherche);
    haut.getChildren().add(rectangleB);

    //ComboBox Categories
    ComboBox<String> comboBox = new ComboBox<>();
    comboBox.setCursor(Cursor.HAND);
    comboBox.getItems().addAll("Tout", "Vêtement", "Chaussure", "Accessoire", "Electromenager", "Informatique", "Jeux", "Livre", "Musique", "Sport", "Vehicule","Ustensile Cuisine","Meuble","Outil");
    comboBox.setPromptText("Categories Populaires");
    comboBox.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 15px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-color: black; -fx-border-width: 1;");
    comboBox.setPrefHeight(40);
    navBar2.getChildren().addAll(navBar,comboBox);

    // Bouton deconnexion
    HBox bouton= new HBox();
    Button boutonMessage = new Button();
    boutonMessage.setGraphic(new ImageView(new Image("message.png")));
    boutonMessage.setCursor(Cursor.HAND);



    Button boutonCloche = new Button();
    boutonCloche.setGraphic(new ImageView(new Image("cloche.png")));
    boutonCloche.setCursor(Cursor.HAND);

    Button boutonPanier = new Button();
    boutonPanier.setGraphic(new ImageView(new Image("panier.png")));
    boutonPanier.setCursor(Cursor.HAND);

    boutonMessage.setStyle("-fx-background-color: transparent;");
    boutonCloche.setStyle("-fx-background-color: transparent;");
    boutonPanier.setStyle("-fx-background-color: transparent;");
    bouton.getChildren().addAll(boutonMessage, boutonCloche, boutonPanier);
    bouton.setSpacing(7);
    haut.getChildren().addAll(navBar2, bouton);
    haut.setPadding(new Insets(10,0,10,10));
    HBox.setMargin(navBar2, new Insets(15, 700, 0, 15));
    HBox.setMargin(rectangleB, new Insets(15, 0, 10, 200));
    HBox.setMargin(bouton, new Insets(15,0,0,0));

    Button boutonPhotoProfil = new Button();
    
    // Image de profile a modifier un fois les page relié entre elle
    Image profileImage = new Image("pp.jpeg");
    // Créer un ImageView pour afficher l'image
    ImageView imagePP = new ImageView(profileImage);
    imagePP.setFitWidth(70); // Largeur de l'image
    imagePP.setFitHeight(70); // Hauteur de l'image

    //mettre l'image de profile un cercle
    Circle clip = new Circle(30, 30, 30);
    imagePP.setClip(clip);
    //ajouter l'image de profile dans un StackPane
    StackPane stack = new StackPane();
    stack.getChildren().addAll(imagePP);
    stack.setPadding(new Insets(0,0,0,0));
    stack.setStyle("-fx-background-color: transparent;");
    bouton.getChildren().add(stack);
    boutonPhotoProfil.setGraphic(imagePP);
    boutonPhotoProfil.setCursor(Cursor.HAND);
    boutonPhotoProfil.setStyle("-fx-background-color: transparent;");
    haut.getChildren().add(boutonPhotoProfil);

    root.setTop(haut);

}


public static void main(String[] args) {
    launch(args);
}
}
