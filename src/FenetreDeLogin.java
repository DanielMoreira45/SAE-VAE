import javafx.util.Duration;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.HPos;


public class FenetreDeLogin extends GridPane{

    private TextField email;
    private PasswordField mdp;
    private Text erreurMdpMsg;
    private Text erreurEmail;
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;

    public FenetreDeLogin(AppliVae appli, ConnexionMySQL connexionMySQL){
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.email = new TextField();
        this.mdp = new PasswordField();
        this.erreurMdpMsg = new Text("");
        this.erreurEmail = new Text("");
        this.ajouteTextField();
    }

    private void ajouteTextField(){
        //espace entre les lignes et colonnes
        this.setHgap(10);
        this.setVgap(10);
        //création de textField
        this.email.setPromptText("Entrez l'email");
        this.mdp.setPromptText("Entrer le mot de passe");
        //on les place dans le gridPane
        this.add(this.email,50,30);
        this.add(this.erreurEmail,50,31);
        this.add(this.mdp,50,32);
        this.add(this.erreurMdpMsg,50,33);
        
        this.email.setPrefWidth(400); // Largeur préférée de 350 pixels
        this.email.setPrefHeight(48); // Hauteur préférée de 40 pixels
        this.erreurEmail.setFont(Font.font("Varela", FontWeight.THIN, 10));
        this.erreurEmail.setFill(Color.RED);
        this.mdp.setPrefWidth(400); // Largeur préférée de 350 pixels
        this.mdp.setPrefHeight(48); // Hauteur préférée de 40 pixels
        this.erreurMdpMsg.setFont(Font.font("Varela", FontWeight.THIN, 10));
        this.erreurMdpMsg.setFill(Color.RED);

        //modification du style en css
        this.mdp.getStyleClass().add("text-field");
        this.email.getStyleClass().add("text-field");

        // Création du bouton SE CONNECTER
        Button seConnecter = new Button("SE CONNECTER");
        seConnecter.setOnAction(new ControleurConnexion(this, this.appli, this.connexionMySQL));
        this.add(seConnecter,50,40);
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
        this.add(motDePasseOublie, 50, 41);
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

    public String getMdp() {
        return this.mdp.getText();
    }

    public String getEmail() {
        return this.email.getText();
    }

    public void setMdpErreur() {
        this.mdp.setStyle("-fx-border-color: red");
    }

    public void setEmailErreur(boolean hasErreur) {
        if (hasErreur) this.email.setStyle("-fx-border-color: red");
        else this.email.setStyle("-fx-border-color: #a3a3a3aa");
    }

    public void setMessageErreur(String msg) {
        this.erreurMdpMsg.setText(msg);
    }

    public void setMessageEmailErreur(String msg) {
        this.erreurEmail.setText(msg);
    }
}
