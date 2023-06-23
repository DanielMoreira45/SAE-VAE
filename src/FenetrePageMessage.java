import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import javafx.geometry.Pos;



public class FenetrePageMessage extends BorderPane{

    private ImageView profileImage;

    private List<String> listeContart;

    private AppliVae appli;

    private ConnexionMySQL connexionMySQL;

    public FenetrePageMessage(AppliVae appli, ConnexionMySQL connexionMySQL){
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.profileImage = new ImageView("file:/pp.jpeg");
        this.listeContart = new ArrayList<>();
        
        ajouteContact();

        this.ajouteMenu();
        this.ajouteMilieu();
    }

    private void ajouteContact(){
        this.listeContart.add("Rémy");
        this.listeContart.add("Aymeric");
        this.listeContart.add("Alexandre");
        this.listeContart.add("Danielk");
        this.listeContart.add("Jeanlme");
        this.listeContart.add("Pierrel");
        this.listeContart.add("Paul LefebvreG");
        this.listeContart.add("Jacques Lefebvre");
        this.listeContart.add("Jeanne d'Arc");
    }

    public void ajouteMenu(){
        VBox menu = new VBox();
        this.setLeft(menu);
        menu.setPadding(new Insets(30,30,30,30));
        menu.setSpacing(30);
        menu.getStyleClass().add("gaucheMessage");
        Text Messagerie = new Text("Messagerie");
        Messagerie.setStyle("-fx-font-size: 55px; -fx-fill: #000000; -fx-font-family: 'Verdana';");

        HBox bouton = new HBox();
        Button micro = new Button();
        Button addUser = new Button();
        Button personnes = new Button();
        ImageView imageMicro = new ImageView(new Image("file:/imageMicro.png"));
        ImageView imageAddUser = new ImageView(new Image("file:/ImageAddUser.png"));
        ImageView imagePerssonne = new ImageView(new Image("file:/imagePersonnes.png"));
        micro.setGraphic(imageMicro);
        addUser.setGraphic(imageAddUser);
        personnes.setGraphic(imagePerssonne);
        micro.setStyle("-fx-background-color: transparent;");
        addUser.setStyle("-fx-background-color: transparent;");
        personnes.setStyle("-fx-background-color: transparent;");
        micro.setCursor(Cursor.HAND);
        addUser.setCursor(Cursor.HAND);
        personnes.setCursor(Cursor.HAND);
        bouton.setPadding(new Insets(30,30,30,30));
        bouton.setSpacing(50);
        bouton.getChildren().addAll(micro, addUser, personnes);
        menu.getChildren().addAll(Messagerie, bouton);
        bouton.setStyle("-fx-background-color: #45646;");

        TextField rechercheMessage = new TextField();
        rechercheMessage.setPromptText("Rechercher un message");
        rechercheMessage.getStyleClass().add("text-fieldU");
        rechercheMessage.setPrefWidth(400);
        menu.getChildren().add(rechercheMessage);


        //liste des contacts
        ScrollPane listeContartScroll = new ScrollPane();
        VBox VboxlisteContart = new VBox();
        listeContartScroll.setContent(VboxlisteContart);
        menu.getChildren().add(listeContartScroll);
        VboxlisteContart.setPadding(new Insets(30,30,30,30));
        //VboxlisteContart.setStyle("-fx-background-color: #4FA0FF;");
        VboxlisteContart.setSpacing(20);
        
        

        for(int i=0; i<listeContart.size(); i++){
            HBox contact = new HBox();
            VboxlisteContart.getChildren().add(contact);
            //contact.setStyle("-fx-background-color: #ffffff");

            // Créer un ImageView pour afficher l'image
            ImageView imagePP = new ImageView(new Image("file:/pp.jpeg"));
            imagePP.setFitWidth(80); // Largeur de l'image
            imagePP.setFitHeight(80); // Hauteur de l'image
            contact.getChildren().add(imagePP);
            contact.setPadding(new Insets(10,10,10,10));
            contact.setSpacing(20);

            //mettre l'image de profile un cercle
            Circle clipContact = new Circle(40, 40,40);
            imagePP.setClip(clipContact);
            //ajouter l'image de profile dans un StackPane
            StackPane stackContact = new StackPane();
            stackContact.getChildren().addAll(imagePP);
            stackContact.setStyle("-fx-background-color: transparent;");
            contact.getChildren().add(stackContact);
            contact.setStyle("-fx-background-color: transparent; -fx-border-color: #AEAEAE; -fx-border-radius: 15;");
    
            
            GridPane gridContarct = new GridPane();
            contact.getChildren().add(gridContarct);
            //gridContarct.setHgap(10);
            gridContarct.setVgap(10);


            Text nomContact = new Text(listeContart.get(i));
            gridContarct.add(nomContact, 0, 0);



            // Générer une heure aléatoire
            LocalTime heureAleatoire = LocalTime.of(
            (int) (Math.random() * 24),    // Heure aléatoire entre 0 et 23
            (int) (Math.random() * 60)     // Minute aléatoire entre 0 et 59
            );
            LocalDate dateAleatoire = LocalDate.of(
            (int) (2023),     
            (int) (Math.random() * 12) + 1,       // Mois aléatoire entre 1 et 12
            (int) (Math.random() * 28) + 1        // Jour aléatoire entre 1 et 28 (simplification pour cet exemple)
            );

            // Formater la date aléatoire en chaîne de caractères
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateAleatoireStr = dateAleatoire.format(formatter);

            // Convertir l'heure aléatoire en chaîne de caractères
            String heureAleatoireStr = heureAleatoire.toString();

            Text dateHeure = new Text(heureAleatoireStr + "," + dateAleatoireStr);
            gridContarct.add(dateHeure, 10, 0);


            

            Button repondre = new Button("Repondre");
            repondre.getStyleClass().add("buttonVert");
            gridContarct.add(repondre, 0, 4);
            repondre.setCursor(Cursor.HAND);

            Button pageArticle = new Button("Page de l'article");
            pageArticle.getStyleClass().add("buttonBleu");
            gridContarct.add(pageArticle, 8, 4);
            pageArticle.setCursor(Cursor.HAND);

        }



        
    }

    public void ajouteMilieu(){
        VBox disscusion = new VBox();
        this.setCenter(disscusion);

        Text messagerie = new Text("Discussion");
        messagerie.setStyle("-fx-font-size: 55px; -fx-fill: #000000; -fx-font-family: 'Verdana';");

        Text messagerie2 = new Text("Aucun Discussion");
        messagerie2.getStyleClass().add("petit-Msg");
        disscusion.setAlignment(Pos.CENTER);

        disscusion.getChildren().addAll(messagerie,messagerie2);

    }


}
