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
        this.listeContart.add("Rémftyftyftyfyft");
        this.listeContart.add("Aymericjjefebvre");
        this.listeContart.add("Alexandrekkefebv");
        this.listeContart.add("DanielkkefebvreG");
        this.listeContart.add("JeanlmefebvreFGH");
        this.listeContart.add("PierrelmefebvreG");
        this.listeContart.add("Paul LefebvreGGG");
        this.listeContart.add("Jacques Lefebvre");
        this.listeContart.add("Jeanne LefebvrTe");
    }

    /*
    @Override
    public void init() {
        this.profileImage = new ImageView("pp.jpeg");
        this.listeContart = new ArrayList<>();
        this.listeContart.add("Rémftyftyftyfyft");
        this.listeContart.add("Aymericjjefebvre");
        this.listeContart.add("Alexandrekkefebv");
        this.listeContart.add("DanielkkefebvreG");
        this.listeContart.add("JeanlmefebvreFGH");
        this.listeContart.add("PierrelmefebvreG");
        this.listeContart.add("Paul LefebvreGGG");
        this.listeContart.add("Jacques Lefebvre");
        this.listeContart.add("Jeanne LefebvrTe");
    }

    public void start(Stage stage) throws Exception{
        // Construction du graphe de scène
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        stage.setTitle("Fenetre Acceuil");
        stage.setFullScreen(true);
        this.ajouteNavBar(root);
        this.ajouteMenu(root);
        this.ajouteMilieu(root);
        stage.setFullScreenExitHint("");

        scene.getStylesheets().add("styleNavBar.css");
        scene.getStylesheets().add("stylePageMessagerie.css");

        stage.setScene(scene);
        stage.show();
    }*/

    /*
    public void ajouteNavBar(){
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

        Image profileImageBarNav = new Image("pp.jpeg");
        // Créer un ImageView pour afficher l'image
        ImageView imagePP = new ImageView(profileImageBarNav);
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
        this.setTop(haut);

    }*/

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
