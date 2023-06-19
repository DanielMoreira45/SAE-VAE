import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class FenetreCoInsc extends HBox {
    private BorderPane panelCentral;
    private Button seConnecter;
    private Button creerCompte;

    private Rectangle rectangleC;
    private Rectangle rectangleI;

    public FenetreCoInsc(){
        this.panelCentral = new BorderPane();

        /*stage.setTitle("Fenetre de connexion");
        stage.setHeight(1080);
        stage.setWidth(1920);
        stage.setFullScreen(false);
        stage.setFullScreenExitHint("");*/

        this.ajouteConnexion();
        this.gridButton();
        this.modeLogin();
        this.getChildren().addAll(this.ajouteImage(), this.panelCentral);
        
        
        

        //stage.setScene(scene);
        //stage.show();
    }
    /*
    @Override
    public void init() {
        this.root = new HBox();
        this.panelCentral = new BorderPane();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Fenetre de connexion");
        stage.setHeight(1080);
        stage.setWidth(1920);
        stage.setFullScreen(false);
        stage.setFullScreenExitHint("");

        this.ajouteConnexion();
        this.gridButton();
        this.modeLogin();
        this.root.getChildren().addAll(this.ajouteImage(), this.panelCentral);
        Scene scene = new Scene(this.root);
        
        scene.getStylesheets().add("css.css");

        stage.setScene(scene);
        stage.show();
    }*/

    public void modeLogin() {
        this.seConnecter.setDisable(true);
        this.creerCompte.setDisable(false);
        this.rectangleC.setFill(Color.web("#4FA0FF"));
        this.rectangleI.setFill(Color.web("#a3a3a3aa"));
        this.panelCentral.setCenter(new FenetreDeLogin());
    }

    public void modeCreationCompte() {
        this.seConnecter.setDisable(false);
        this.creerCompte.setDisable(true);
        this.rectangleC.setFill(Color.web("#a3a3a3aa"));
        this.rectangleI.setFill(Color.web("#4FA0FF"));
        this.panelCentral.setCenter(new FenetreCreationCompte());
    }
    
    // pour mettre l'image a gauche
    private ImageView ajouteImage(){
        ImageView imageView = new ImageView("file:img/imageLogin.png");
        imageView.setFitWidth(545);
        imageView.setFitHeight(1080);
        return imageView;
    }

    private void gridButton() {
        GridPane gridePaneBouton = new GridPane();//Création du rectangle bleu en dessous de Se connectez
        this.rectangleC = new Rectangle(0, 0, 115, 6);
        gridePaneBouton.add(rectangleC,0,1);
        this.rectangleC.setArcWidth(10); // Rayon de courbure horizontal des coins
        this.rectangleC.setArcHeight(10);
        this.rectangleC.setFill(Color.web("#4FA0FF"));
        //Création du rectangle gris en dessous de Créez un compte
        this.rectangleI = new Rectangle(0, 0, 140, 6);
        gridePaneBouton.add(rectangleI,3,1);
        this.rectangleI.setArcWidth(10); // Rayon de courbure horizontal des coins
        this.rectangleI.setArcHeight(10);
        this.rectangleI.setFill(Color.web("#a3a3a3aa"));

        gridePaneBouton.setAlignment(Pos.CENTER_RIGHT);
        gridePaneBouton.setPadding(new Insets(50, 0, 0, 400));
        gridePaneBouton.add(this.seConnecter,0,0);
        gridePaneBouton.add(this.creerCompte,3,0);
        gridePaneBouton.setHgap(120);
        gridePaneBouton.setVgap(1);
        this.panelCentral.setTop(gridePaneBouton);
    }

    // ajoute le haut de la fennetre, les boutons Se connectez et Créez un compte"
    private void ajouteConnexion(){
        this.seConnecter = new Button("Se connectez");
        this.creerCompte = new Button("Créez un compte");

        this.seConnecter.setOnAction(new ControleurBoutonCoInsc(this));
        this.creerCompte.setOnAction(new ControleurBoutonCoInsc(this));

        // pour le style des boutons
        this.seConnecter.getStyleClass().add("button-without-background");
        this.creerCompte.getStyleClass().add("button-without-background");
        this.seConnecter.getStyleClass().add("button-custom");
        this.creerCompte.getStyleClass().add("button-custom");

        //petit annimations du bouton
        this.seConnecter.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), this.seConnecter);
            scaleTransition.setToX(1.2); // Facteur d'agrandissement horizontal
            scaleTransition.setToY(1.2); // Facteur d'agrandissement vertical
            scaleTransition.play();
        });
        this.seConnecter.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), this.seConnecter); 
            scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
            scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
            scaleTransitionReverse.play();
        });
        //petit annimations du bouton
        this.creerCompte.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), this.creerCompte);
            scaleTransition.setToX(1.2); // Facteur d'agrandissement horizontal
            scaleTransition.setToY(1.2); // Facteur d'agrandissement vertical
            scaleTransition.play();
        });
        this.creerCompte.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), this.creerCompte); 
            scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
            scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
            scaleTransitionReverse.play();
        });
    }
/*
    public static void main(String[] args) {
        launch(args);
    }*/
}
