import javafx.util.Duration;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class FenetreCreationCompte extends GridPane{
    private TextField pseudo;
    private TextField mail;
    private PasswordField mdp;
    private PasswordField mdpConfirme;

    private Text erreurMdpMsg;
    private Text erreurMdpConfirmationMsg;
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;


    public FenetreCreationCompte(AppliVae appli, ConnexionMySQL connexionMySQL) {
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.pseudo = new TextField();
        this.mail = new TextField();
        this.mdp = new PasswordField();
        this.mdpConfirme = new PasswordField();
        this.erreurMdpMsg = new Text("");
        this.erreurMdpConfirmationMsg = new Text("");
        this.ajouteTextField();
        this.ajouteBoutonOeil();
    }

        
        public void ajouteTextField(){
            //espace entre les lignes et colonnes
        this.setHgap(10);
        this.setVgap(10);
        
        this.pseudo.setPromptText("Nom d'utilisateur");
        this.mail.setPromptText("Entrez l'email");
        this.mdp.setPromptText("Entrez le mot de passe");
        this.mdp.setTooltip(new Tooltip("Votre mot de passe doit avoir :\n - au moins 8 caractères\n - aucune répétition successive de lettre\n - au moins une lettre majuscule"));
        this.erreurMdpMsg.setFont(Font.font("Varela", FontWeight.THIN, 10));
        this.erreurMdpMsg.setFill(Color.RED);
        this.mdpConfirme.setPromptText("Confirmer le mot de passe");
        this.erreurMdpConfirmationMsg.setFont(Font.font("Varela", FontWeight.THIN, 10));
        this.erreurMdpConfirmationMsg.setFill(Color.RED);
        
        this.pseudo.setPrefWidth(400); // Largeur préférée de 350 pixels
        this.pseudo.setPrefHeight(48); // Hauteur préférée de 40 pixels
        
        this.mail.setPrefWidth(400); // Largeur préférée de 350 pixels
        this.mail.setPrefHeight(48); // Hauteur préférée de 40 pixels

        this.mdp.setPrefWidth(400); // Largeur préférée de 350 pixels
        this.mdp.setPrefHeight(48); // Hauteur préférée de 40 pixels
        
        this.mdpConfirme.setPrefWidth(400); // Largeur préférée de 350 pixels
        this.mdpConfirme.setPrefHeight(48); // Hauteur préférée de 40 pixels

        //on les place dans le gridPane
        this.add(this.pseudo,50,26);
        this.add(this.mail,50,28);
        this.add(this.mdp,50,32);
        this.add(this.erreurMdpMsg,50,33);
        this.add(this.mdpConfirme,50,34);
        this.add(this.erreurMdpConfirmationMsg,50,35);

        //modification du style en css
        this.pseudo.getStyleClass().add("text-field");
        this.mail.getStyleClass().add("text-field");
        this.mdp.getStyleClass().add("text-field");
        this.mdpConfirme.getStyleClass().add("text-field");
        
        
        // Création du bouton SE CONNECTER
        Button creerCompte = new Button("CRÉÉR UN COMPTE");
        creerCompte.setOnAction(new ControleurCreerCompte(this, this.appli, this.connexionMySQL));
        this.add(creerCompte,50,40);
        creerCompte.getStyleClass().add("button-connection");
        
        creerCompte.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), creerCompte);
            scaleTransition.setToX(1.1); // Facteur d'agrandissement horizontal
            scaleTransition.setToY(1.1); // Facteur d'agrandissement vertical
            scaleTransition.play();
        });
        creerCompte.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            ScaleTransition scaleTransitionReverse = new ScaleTransition(Duration.millis(200), creerCompte); 
            scaleTransitionReverse.setToX(1); // Retour à la taille d'origine pour l'axe X
            scaleTransitionReverse.setToY(1); // Retour à la taille d'origine pour l'axe Y
            scaleTransitionReverse.play();
        });
    }

    private void ajouteBoutonOeil(){
        ToggleButton voirMdp = new ToggleButton();
        voirMdp.toFront();
        voirMdp.setStyle("-fx-background-color: transparent;");
        ImageView oeil = new ImageView("file:img/oeil.png");
        oeil.setFitWidth(20);
        oeil.setFitHeight(20);

        ImageView oeilBarre = new ImageView("file:img/oeilBarre.png");
        oeilBarre.setFitWidth(20);
        oeilBarre.setFitHeight(20);

        voirMdp.setGraphic(oeil);
        voirMdp.setPrefWidth(20);
        voirMdp.setPrefHeight(20);
        voirMdp.setCursor(Cursor.HAND);

        TextField mdpClair = new TextField();
        mdpClair.setPromptText("Entrez le mot de passe (oeil)");
        mdpClair.getStyleClass().add("text-field");
        mdpClair.setPrefWidth(400); // Largeur préférée de 350 pixels
        mdpClair.setPrefHeight(48); // Hauteur préférée de 40 pixels

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(this.mdp);
        stackPane.getChildren().add(voirMdp);
        StackPane.setAlignment(voirMdp, Pos.CENTER_RIGHT);
        this.add(stackPane,50,32);

        TextField mdpConfirmeClair = new TextField();
        mdpConfirmeClair.setPromptText("Confirmer le mot de passe (oeil)");
        mdpConfirmeClair.getStyleClass().add("text-field");
        mdpConfirmeClair.setPrefWidth(400); // Largeur préférée de 350 pixels
        mdpConfirmeClair.setPrefHeight(48); // Hauteur préférée de 40 pixels
        
        voirMdp.setOnAction(event -> {
            if (voirMdp.isSelected()) {
                stackPane.getChildren().remove(this.mdp);
                stackPane.getChildren().add(mdpClair);
                stackPane.getChildren().remove(voirMdp);
                stackPane.getChildren().add(voirMdp);
                mdpClair.setText(this.mdp.getText());
                this.add(mdpConfirmeClair,50,34);
                mdpConfirmeClair.setText(mdpConfirme.getText());
                voirMdp.setGraphic(oeilBarre);
            }
            else {
                stackPane.getChildren().remove(mdpClair);
                stackPane.getChildren().add(this.mdp);
                stackPane.getChildren().remove(voirMdp);
                stackPane.getChildren().add(voirMdp);
                this.mdp.setText(mdpClair.getText());
                this.getChildren().remove(mdpConfirmeClair);
                mdpConfirme.setText(mdpConfirmeClair.getText());
                voirMdp.setGraphic(oeil);

            }
        });
    }
    
    public void setMessageErreur(String msg) {
        this.erreurMdpMsg.setText("    * "+msg);
    }
    
    public void setMessageMdpConfirmationErreur(String msg) {
        this.erreurMdpConfirmationMsg.setText(msg);
    }
    
    public String getMdp() {
        return this.mdp.getText();
    }

    public String getMdpConfirmation() {
        return this.mdpConfirme.getText();
    }
    
    public String getPseudo(){
        return this.pseudo.getText();
    }

    public String getMail(){
        return this.mail.getText();
    }

    // public ConnexionMySQL getConnexionMySQL(){
    //     System.out.println("vue "+ this.connexionMySQL);
    //     return this.connexionMySQL;
    // }


    public void setMdpErreur() {
        this.mdp.setStyle("-fx-border-color: red");
    }

    public void setMdpConfimationErreur(boolean hasErreur) {
        if (hasErreur) this.mdpConfirme.setStyle("-fx-border-color: red");
        else this.mdpConfirme.setStyle("-fx-border-color: #a3a3a3aa");
    }
    
    public void popUpErreurSQL(Exception e){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Une erreur s'est produite");
    alert.setContentText("Description de l'erreur : " + e.getMessage());
    alert.showAndWait();
}
public void popUpCompteValide(Exception e){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Une erreur s'est produite");
    alert.setContentText("Description de l'erreur : " + e.getMessage());
    alert.showAndWait();
}
public void popUpCompteValide(String nomCompte) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Confirmation de création de compte");
    alert.setHeaderText("Compte créé avec succès");
    alert.setContentText("Le compte '" + nomCompte + "' a été créé avec succès.");
    alert.showAndWait();
}
}