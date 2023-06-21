import javafx.util.Duration;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.lang.annotation.Retention;
import java.util.Stack;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.shape.Rectangle;



public class FenetreDeLogin extends Application{

    private Scene scene;
    private TextField email;
    private PasswordField mdp;

    public void start(Stage stage) throws Exception{
        // Construction du graphe de scène
        BorderPane root = new BorderPane();
        BorderPane connectionBP = new BorderPane();
        stage.setTitle("Fenetre de connexion");
        stage.setFullScreen(true);
        this.ajouteConnexion(root,connectionBP);
        this.ajouteImage(root);
        this.ajouteTextField(root, connectionBP);
        stage.setFullScreenExitHint("");
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add("css.css");

        stage.setScene(scene);
        stage.show();
    }
    
// pour mettre l'image a gauche
public void ajouteImage(BorderPane root){
    ImageView imageView = new ImageView("imageLogin.png");
    //l'image est mit a gauche du borderPane root
    root.setLeft(imageView);
    imageView.setFitWidth(545);
    imageView.setFitHeight(1080);
}

// ajoute le haut de la fennetre, les boutons Se connectez et Créez un compte"
public void ajouteConnexion(BorderPane root, BorderPane connectionBP){


    GridPane GridePaneBouton = new GridPane();
    Button seConnectez = new Button("Se connectez");
    Button creezCompte = new Button("Créez un compte");
    // pour le style des boutons
    seConnectez.getStyleClass().add("button-without-background");
    creezCompte.getStyleClass().add("button-without-background");
    seConnectez.getStyleClass().add("button-custom");
    creezCompte.getStyleClass().add("button-custom");

    //petit annimations du bouton
    seConnectez.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), seConnectez);
        scaleTransition.setToX(1.2); // Facteur d'agrandissement horizontal
        scaleTransition.setToY(1.2); // Facteur d'agrandissement vertical
        scaleTransition.play();
    });
    seConnectez.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
        ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), seConnectez); 
        scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
        scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
        scaleTransitionReverse.play();
    });
    //petit annimations du bouton
    creezCompte.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), creezCompte);
        scaleTransition.setToX(1.2); // Facteur d'agrandissement horizontal
        scaleTransition.setToY(1.2); // Facteur d'agrandissement vertical
        scaleTransition.play();
    });
    creezCompte.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
        ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), creezCompte); 
        scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
        scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
        scaleTransitionReverse.play();
    });
    
    //Création du rectangle bleu en dessous de Se connectez
    Rectangle rectangleB = new Rectangle(0, 0, 130, 6);
    GridePaneBouton.add(rectangleB,0,1);
    rectangleB.setArcWidth(10); // Rayon de courbure horizontal des coins
    rectangleB.setArcHeight(10);
    rectangleB.setFill(Color.web("#4FA0FF"));
    //Création du rectangle gris en dessous de Créez un compte
    Rectangle rectangleG = new Rectangle(0, 0, 160, 6);
    GridePaneBouton.add(rectangleG,3,1);
    rectangleG.setArcWidth(10); // Rayon de courbure horizontal des coins
    rectangleG.setArcHeight(10);
    rectangleG.setFill(Color.web("#a3a3a3aa"));

    GridePaneBouton.setAlignment(Pos.CENTER);
    GridePaneBouton.setPadding(new Insets(50));
    GridePaneBouton.add(seConnectez,0,0);
    GridePaneBouton.add(creezCompte,3,0);
    GridePaneBouton.setHgap(120);
    GridePaneBouton.setVgap(1);
    connectionBP.setTop(GridePaneBouton);
    root.setCenter(connectionBP);
}

public void ajouteTextField(BorderPane root, BorderPane connectionBP){
    //création d'un gridPane que l'on met au center du borderPane Connection
    GridPane gridPaneTF = new GridPane();
    connectionBP.setCenter(gridPaneTF);
    //espace entre les lignes et colonnes
    gridPaneTF.setHgap(10);
    gridPaneTF.setVgap(10);
    //création de textField
    TextField mail = new TextField();
    PasswordField mdp = new PasswordField();

    mail.setPromptText("Entrez l'email");
    mdp.setPromptText("Entrer le mot de passe");
    //on les place dans le gridPane
    gridPaneTF.add(mail,50,30);
    
    mail.setPrefWidth(400); // Largeur préférée de 350 pixels
    mail.setPrefHeight(48); // Hauteur préférée de 40 pixels
    mdp.setPrefWidth(400); // Largeur préférée de 350 pixels
    mdp.setPrefHeight(48); // Hauteur préférée de 40 pixels

    //modification du style en css
    mdp.getStyleClass().add("text-field");
    mail.getStyleClass().add("text-field");

    //création du bouton POUR voir mot de passe

    ToggleButton voirMdp = new ToggleButton();
    voirMdp.setStyle("-fx-background-color: transparent;");
    ImageView oeil = new ImageView("oeil.png");
    oeil.setFitWidth(20);
    oeil.setFitHeight(20);
    ImageView oeilBarre = new ImageView("oeilBarre.png");
    oeilBarre.setFitWidth(20);
    oeilBarre.setFitHeight(20);
    voirMdp.setGraphic(oeil);
    voirMdp.setPrefWidth(20);
    voirMdp.setPrefHeight(20);

    TextField mdpClair = new TextField();
    mdpClair.setPromptText("Entrer le mot de passe");
    mdpClair.getStyleClass().add("text-field");
    mdpClair.setPrefWidth(400); // Largeur préférée de 350 pixels
    mdpClair.setPrefHeight(48); // Hauteur préférée de 40 pixels

    StackPane stackPane = new StackPane();
    stackPane.getChildren().add(mdp);
    stackPane.getChildren().add(voirMdp);
    StackPane.setAlignment(voirMdp, Pos.CENTER_RIGHT);
    gridPaneTF.add(stackPane,50,32);


    voirMdp.setOnAction(event -> {
        if (voirMdp.isSelected()) {
            stackPane.getChildren().remove(mdp);
            stackPane.getChildren().add(mdpClair);
            stackPane.getChildren().remove(voirMdp);
            stackPane.getChildren().add(voirMdp);
            mdpClair.setText(mdp.getText());
            voirMdp.setGraphic(oeilBarre);
        }
        else {
            stackPane.getChildren().remove(mdpClair);
            stackPane.getChildren().add(mdp);
            stackPane.getChildren().remove(voirMdp);
            stackPane.getChildren().add(voirMdp);
            mdp.setText(mdpClair.getText());
            voirMdp.setGraphic(oeil);

        }
    });


    // Création du bouton SE CONNECTER
    Button seConnecter = new Button("SE CONNECTER");
    gridPaneTF.add(seConnecter,50,40);
    seConnecter.getStyleClass().add("button-connection");

    seConnecter.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), seConnecter);
        scaleTransition.setToX(1.1); // Facteur d'agrandissement horizontal
        scaleTransition.setToY(1.1); // Facteur d'agrandissement vertical
        scaleTransition.play();
    });
    seConnecter.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
        ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), seConnecter); 
        scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
        scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
        scaleTransitionReverse.play();
    });

    Button motDePasseOublie = new Button("Mot de passe oublié");
    motDePasseOublie.getStyleClass().add("button-without-background");
    motDePasseOublie.getStyleClass().add("button-custom");
    gridPaneTF.add(motDePasseOublie, 50, 41);
    GridPane.setHalignment(motDePasseOublie, HPos.RIGHT);

    motDePasseOublie.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), motDePasseOublie);
        scaleTransition.setToX(1.1); // Facteur d'agrandissement horizontal
        scaleTransition.setToY(1.1); // Facteur d'agrandissement vertical
        scaleTransition.play();
    });
    motDePasseOublie.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
        ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), motDePasseOublie); 
        scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
        scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
        scaleTransitionReverse.play();
    });
}


public static void main(String[] args) {
    launch(args);
}
}
