import javafx.util.Duration;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

        scene.getStylesheets().add("styleNavBar.css");

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

    boutonLogo.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), boutonLogo);
        scaleTransition.setToX(1.1); // Facteur d'agrandissement horizontal
        scaleTransition.setToY(1.1); // Facteur d'agrandissement vertical
        scaleTransition.play();
    });
    boutonLogo.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
        ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), boutonLogo); 
        scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
        scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
        scaleTransitionReverse.play();
    });

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

    // Bouton 
    HBox bouton= new HBox();
    // Bouton Vendre /////////////////////////////////////////////////////////////////////////////////////////////////
    Button boutonVentre = new Button("Vendre ");
    boutonVentre.setGraphic(new ImageView(new Image("argent.png")));
    boutonVentre.getStyleClass().add("bpVendre");
    boutonVentre.setPadding(new Insets(0,10,0,10));
    boutonVentre.setCursor(Cursor.HAND);

    boutonVentre.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), boutonVentre);
        scaleTransition.setToX(1.1); // Facteur d'agrandissement horizontal
        scaleTransition.setToY(1.1); // Facteur d'agrandissement vertical
        scaleTransition.play();
    });
    boutonVentre.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
        ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), boutonVentre); 
        scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
        scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
        scaleTransitionReverse.play();
    });

    Button boutonMessage = new Button();
    boutonMessage.setGraphic(new ImageView(new Image("message.png")));
    boutonMessage.setCursor(Cursor.HAND);
    boutonMessage.setPadding(new Insets(7,5,5,22));

    boutonMessage.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), boutonMessage);
        scaleTransition.setToX(1.1); // Facteur d'agrandissement horizontal
        scaleTransition.setToY(1.1); // Facteur d'agrandissement vertical
        scaleTransition.play();
    });
    boutonMessage.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
        ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), boutonMessage); 
        scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
        scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
        scaleTransitionReverse.play();
    });

    Button boutonCloche = new Button();
    boutonCloche.setGraphic(new ImageView(new Image("cloche.png")));
    boutonCloche.setCursor(Cursor.HAND);
    boutonCloche.setPadding(new Insets(5,5,5,5));

    boutonCloche.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), boutonCloche);
        scaleTransition.setToX(1.1); // Facteur d'agrandissement horizontal
        scaleTransition.setToY(1.1); // Facteur d'agrandissement vertical
        scaleTransition.play();
    });
    boutonCloche.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
        ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), boutonCloche); 
        scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
        scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
        scaleTransitionReverse.play();
    });


    Button boutonPanier = new Button();
    boutonPanier.setGraphic(new ImageView(new Image("panier.png")));
    boutonPanier.setCursor(Cursor.HAND);
    boutonPanier.setPadding(new Insets(5,5,5,5));

    boutonPanier.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), boutonPanier);
        scaleTransition.setToX(1.1); // Facteur d'agrandissement horizontal
        scaleTransition.setToY(1.1); // Facteur d'agrandissement vertical
        scaleTransition.play();
    });
    boutonPanier.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
        ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), boutonPanier); 
        scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
        scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
        scaleTransitionReverse.play();
    });


    boutonMessage.setStyle("-fx-background-color: transparent;");
    boutonCloche.setStyle("-fx-background-color: transparent;");
    boutonPanier.setStyle("-fx-background-color: transparent;");
    bouton.getChildren().addAll(boutonVentre,boutonMessage, boutonCloche, boutonPanier);
    bouton.setSpacing(10);
    haut.getChildren().addAll(navBar2, bouton);
    haut.setPadding(new Insets(10,0,10,10));
    HBox.setMargin(navBar2, new Insets(15, 550, 0, 15));
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
    stack.setPadding(new Insets(5,5,5,5));
    stack.setStyle("-fx-background-color: transparent;");
    bouton.getChildren().add(stack);
    boutonPhotoProfil.setGraphic(imagePP);
    boutonPhotoProfil.setCursor(Cursor.HAND);
    boutonPhotoProfil.setStyle("-fx-background-color: transparent;");
    haut.getChildren().add(boutonPhotoProfil);

    boutonPhotoProfil.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), boutonPhotoProfil);
        scaleTransition.setToX(1.1); // Facteur d'agrandissement horizontal
        scaleTransition.setToY(1.1); // Facteur d'agrandissement vertical
        scaleTransition.play();
    });
    boutonPhotoProfil.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
        ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), boutonPhotoProfil); 
        scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
        scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
        scaleTransitionReverse.play();
    });

    root.setTop(haut);

}


public static void main(String[] args) {
    launch(args);
}
}
