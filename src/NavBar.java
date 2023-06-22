import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;

public class NavBar extends HBox {

    // private Image profileImage;

    /*
     * @Override
     * public void init() {
     * Image profileImage = new Image("pp.jpeg");
     * ImageView imagePP = new ImageView(profileImage);
     * }
     * 
     * public void start(Stage stage) throws Exception{
     * // Construction du graphe de scène
     * BorderPane root = new BorderPane();
     * Scene scene = new Scene(root);
     * stage.setTitle("Fenetre Acceuil");
     * stage.setFullScreen(true);
     * this.ajouteNavBar(root);
     * stage.setFullScreenExitHint("");
     * 
     * scene.getStylesheets().add("styleAcceuil.css");
     * 
     * stage.setScene(scene);
     * stage.show();
     * }
     */

    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;
    private TextField textFieldRecherche;

    public NavBar(AppliVae appli, ConnexionMySQL connexionMySQL) {
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        ajouteNavBar();
    }

    public void ajouteNavBar() {
        // Image profileImage = new Image("message.png");
        // ImageView imagePP = new ImageView(profileImage);

        // Barre de recherche
        HBox navBar = new HBox();
        HBox navBar2 = new HBox();

        this.getStyleClass().add("bottom-border");

        Button boutonLogo = new Button();
        ImageView imageView = new ImageView(new Image("file:img/logo.png"));
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        boutonLogo.setGraphic(imageView);
        boutonLogo.setCursor(Cursor.HAND);
        boutonLogo.setStyle("-fx-background-color: transparent;");
        boutonLogo.setAccessibleText("Logo");
        boutonLogo.setOnAction(new ControleurNavBar(this.appli, this.connexionMySQL));

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
    

        this.getChildren().add(boutonLogo);

        // Rectangle Bleu
        HBox rectangleB = new HBox(10);
        rectangleB.setStyle(
                "-fx-background-color: #B5D6FD; -fx-border-color:black; -fx-border-radius: 0.8em; -fx-background-radius: 0.8em;");
        rectangleB.setPadding(new Insets(5, 5, 5, 5));
        rectangleB.setMaxHeight(45);
        rectangleB.setPrefWidth(400);

        ImageView imageRecherche = new ImageView("file:img/imageRecherche.png");
        imageRecherche.setFitHeight(30);
        imageRecherche.setFitWidth(30);
        imageRecherche.setOnMouseClicked(new ControleurRechercheClick(this.appli, this));

        this.textFieldRecherche = new TextField();
        textFieldRecherche.setPromptText("Nom d'un produit");
        textFieldRecherche.getStyleClass().add("text-field");
        textFieldRecherche.setPrefWidth(400);
        textFieldRecherche.setOnKeyReleased(new ControleurRechercheEntrer(this.appli, this));

        GridPane gridPane = new GridPane();
        gridPane.add(textFieldRecherche, 1, 0);
        gridPane.add(imageRecherche, 0, 0);
        rectangleB.getChildren().addAll(imageRecherche, textFieldRecherche);
        this.getChildren().add(rectangleB);

        // ComboBox Categories
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setCursor(Cursor.HAND);
        comboBox.getItems().addAll("Tout", "Vêtement", "Chaussure", "Accessoire", "Electromenager", "Informatique",
                "Jeux", "Livre", "Musique", "Sport", "Vehicule", "Ustensile Cuisine", "Meuble", "Outil");
        comboBox.setPromptText("Tout");
        comboBox.setStyle(
                "-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 15px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-color: black; -fx-border-width: 1;");
        comboBox.setPrefHeight(45);
        comboBox.setOnAction(new ControleurCategorie(this.appli));
        navBar2.getChildren().addAll(navBar, comboBox);

        // Boutons
        HBox bouton = new HBox();
        Button boutonMessage = new Button();
        boutonMessage.setGraphic(new ImageView(new Image("file:img/message.png")));
        boutonMessage.setCursor(Cursor.HAND);
        boutonMessage.setAccessibleText("Messagerie");
        boutonMessage.setOnAction(new ControleurNavBar(this.appli, this.connexionMySQL));

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
        boutonCloche.setGraphic(new ImageView(new Image("file:img/cloche.png")));
        boutonCloche.setCursor(Cursor.HAND);
        boutonCloche.setAccessibleText("Notifications");
        boutonCloche.setOnAction(new ControleurNavBar(this.appli, this.connexionMySQL));

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
        boutonPanier.setGraphic(new ImageView(new Image("file:img/panier.png")));
        boutonPanier.setCursor(Cursor.HAND);
        boutonPanier.setAccessibleText("Panier");
        boutonPanier.setOnAction(new ControleurNavBar(this.appli, this.connexionMySQL));

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
        bouton.setSpacing(7);
        this.setPadding(new Insets(10, 0, 10, 10));
        HBox.setMargin(navBar2, new Insets(15, 550, 0, 15));
        HBox.setMargin(rectangleB, new Insets(15, 0, 10, 200));
        HBox.setMargin(bouton, new Insets(15, 0, 0, 20));

        Button boutonVendre = new Button("Vendre ");
        boutonVendre.setGraphic(new ImageView(new Image("file:img/argent.png")));
        boutonVendre.getStyleClass().add("bpVendre");
        boutonVendre.setPadding(new Insets(0,10,0,10));
        boutonVendre.setCursor(Cursor.HAND);
        boutonVendre.setAccessibleText("Vendre un objet");
        boutonVendre.setOnAction(new ControleurNavBar(this.appli, this.connexionMySQL));
        HBox.setMargin(boutonVendre, new Insets(0, 20, 0, 0));
        bouton.getChildren().addAll(boutonVendre, boutonMessage, boutonCloche, boutonPanier);

        this.getChildren().addAll(navBar2, bouton);
        
    
        boutonVendre.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), boutonVendre);
            scaleTransition.setToX(1.1); // Facteur d'agrandissement horizontal
            scaleTransition.setToY(1.1); // Facteur d'agrandissement vertical
            scaleTransition.play();
        });
        boutonVendre.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), boutonVendre); 
            scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
            scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
            scaleTransitionReverse.play();
        });
    

        Button boutonPhotoProfil = new Button();


        // Image de profile a modifier un fois les page relié entre elle
        Image profileImage = new Image("file:img/pp.jpeg");
        // Créer un ImageView pour afficher l'image
        ImageView imagePP = new ImageView(profileImage);
        imagePP.setFitWidth(70); // Largeur de l'image
        imagePP.setFitHeight(70); // Hauteur de l'image

        // mettre l'image de profile un cercle
        Circle clip = new Circle(30, 30, 30);
        imagePP.setClip(clip);
        // ajouter l'image de profile dans un StackPane
        StackPane stack = new StackPane();
        stack.getChildren().addAll(imagePP);
        stack.setPadding(new Insets(0, 0, 0, 0));
        stack.setStyle("-fx-background-color: transparent;");
        bouton.getChildren().add(stack);
        boutonPhotoProfil.setGraphic(imagePP);
        boutonPhotoProfil.setCursor(Cursor.HAND);
        boutonPhotoProfil.setStyle("-fx-background-color: transparent;");
        boutonPhotoProfil.setAccessibleText("Profil Utilisateur");
        boutonPhotoProfil.setOnAction(new ControleurNavBar(this.appli, this.connexionMySQL));
        this.getChildren().add(boutonPhotoProfil);

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
    

    }

    public TextField getTextFieldRecherche() {
        return this.textFieldRecherche;
    }

}
