import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CaseProfil extends BorderPane{
    private Utilisateur utilisateur;
    private VueAdminGestionUtilisateurs parent;

    public CaseProfil(Utilisateur utilisateur, VueAdminGestionUtilisateurs parent) {
        this.utilisateur = utilisateur;
        this.parent = parent;

        this.setContenu();
        this.setStyle(this.parent.getScrollPaneProfils());
    }

    /**
     * Permet d'initialiser le contenu de la case
     */
    private void setContenu() {
        VBox boiteBoutonsGestion = this.getLesBoutonsProfil();
        ImageView photoProfil = this.getImageProfil();
        VBox boiteInfosProfil = this.getInfosProfil();

        this.setLeft(photoProfil);
        this.setCenter(boiteInfosProfil);
        this.setRight(boiteBoutonsGestion);

        BorderPane.setMargin(photoProfil, new Insets(5));
        BorderPane.setMargin(boiteInfosProfil, new Insets(5, 0, 5, 5));
        BorderPane.setMargin(boiteBoutonsGestion, new Insets(5, 5, 5, 0));
        BorderPane.setAlignment(boiteBoutonsGestion, Pos.CENTER);
    }

    /**
     * Permet d'appliquer le style de la case, avec la largeur du ScrollPane parent
     * @param scrollPaneProfils Le ScrollPane parent
     */
    private void setStyle(ScrollPane scrollPaneProfils) {
        this.setPrefWidth(scrollPaneProfils.getWidth());
        this.setStyle("-fx-background-color: #fdfdfd;");
        this.setPadding(new Insets(5));
    }

    /**
     * Méthode permettant de créer les boutons "supprimer" et "désactiver" du profil.
     * @return VBox : la boite contenant les boutons "supprimer" et "désactiver".
     */
    private VBox getLesBoutonsProfil() {
        Button supprimer = new Button("Supprimer");
        Button desactiver = this.utilisateur.estActive() ? new Button("Désactiver") : new Button("Activer");
        Button boutonChangerRole = new Button("Changer de rôles");
        desactiver.setCursor(Cursor.HAND);
        supprimer.setCursor(Cursor.HAND);
        boutonChangerRole.setCursor(Cursor.HAND);
        supprimer.setStyle("-fx-background-color : #ff9292; -fx-border-radius: 0.8em; -fx-background-radius : 0.8em; -fx-effect: dropshadow(gaussian, grey, 8, 0, 1, 1);");
        desactiver.setStyle("-fx-background-color : #6a6a6a; -fx-border-radius: 0.8em; -fx-background-radius : 0.8em; -fx-effect: dropshadow(gaussian, grey, 8, 0, 1, 1);");
        boutonChangerRole.setStyle("-fx-background-color : #b5d6fd; -fx-border-radius: 0.8em; -fx-background-radius : 0.8em; -fx-effect: dropshadow(gaussian, grey, 8, 0, 1, 1);");
        VBox boiteBoutonsGestion = new VBox(10);
        if (this.utilisateur.getRole() != 1)  boiteBoutonsGestion.getChildren().addAll(supprimer, desactiver, boutonChangerRole);
        else boiteBoutonsGestion.getChildren().addAll(boutonChangerRole);
        boiteBoutonsGestion.setPadding(new Insets(10));
        boiteBoutonsGestion.setAlignment(Pos.BOTTOM_RIGHT);
        boiteBoutonsGestion.setStyle("-fx-background-color: #f1f1f1;");

        
        supprimer.setOnAction(new ControleurAdminUtilisateur(this, this.parent));
        desactiver.setOnAction(new ControleurAdminUtilisateur(this, this.parent));
        boutonChangerRole.setOnAction(new ControleurAdminUtilisateur(this, this.parent));

        return boiteBoutonsGestion;
    }

    /**
     * Méthode permettant de créer le nom d'utilisateur.
     * @param nom String : le nom de l'utilisateur à créer.
     * @return Text : le nom de l'utilisateur sous forme de Text.
     */
    private Text getNomEmailDuProfil(String nom, String email) {
        Text nomEmailDuProfil = new Text(nom + "/" + email);
        nomEmailDuProfil.setFont(Font.font("Arial", 16));
        return nomEmailDuProfil;
    }

    /**
     * Méthode permettant de créer la photo de profil.
     * @return ImageView : la photo de profil de l'utilisateur.
     */
    private ImageView getImageProfil() {
        Circle cercleImage = new Circle(40, 40, 40);
        ImageView photoProfil = new ImageView(new Image("file:./img/photoParDefaut.png"));
        photoProfil.setStyle("-fx-border-radius: 1em; -fx-background-radius: 1em;");
        photoProfil.setFitWidth(80);
        photoProfil.setFitHeight(80);
        photoProfil.setClip(cercleImage);
        return photoProfil;
    }

    /**
     * Méthode permettant de créer une boite contentant le nom de l'utilisateur et la dernière connexion.
     * @param nomDuProfil Text : le nom de l'utilisateur.
     * @param derniereConnexion int : sa dernière connexion (en minutes).
     * @return VBox : une boite contenant les informations de profil de l'utilisateur (son pseudo et sa dernière connexion).
     */
    private VBox getInfosProfil() {
        VBox boiteInfosProfil = new VBox(this.getNomEmailDuProfil(this.utilisateur.getPseudo(), this.utilisateur.getEmail()));

        boiteInfosProfil.getChildren().add(this.utilisateur.estActive() ? new Label("Statut : Actif") : new Label("Statut : Inactif"));
        boiteInfosProfil.getChildren().add(this.utilisateur.getRole() == 1 ? new Label("Role : Admin") : new Label("Role : Utilisateur"));

        boiteInfosProfil.setPadding(new Insets(10));
        boiteInfosProfil.setSpacing(30);
        boiteInfosProfil.setStyle("-fx-background-color: #f1f1f1;");
        return boiteInfosProfil;
    }

    /**
     * Permet d'obtenir l'utilisateur de la case
     * @return L'utilisateur de la case
     */
    public Utilisateur getUtilisateur() { return this.utilisateur; }
}
