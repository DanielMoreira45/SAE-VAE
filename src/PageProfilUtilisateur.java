import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

public class PageProfilUtilisateur extends BorderPane {

    private ImageView profileImage;
    private String nomUtilisateur;
    private String email;
    private AppliVae appli;
    private ConnexionMySQL connexionMySQL;
    private Utilisateur utilisateur;
    private Button textInfos;
    private Button textMDP;
    private Button textPaiement;
    private Button deconnecter;
    private GridPane gridPane;
    private PasswordField tFieldMDPactuelle;
    private PasswordField tFieldnvMDP;
    private PasswordField tFieldconfinvMDP;
    private Button boutonModifNomUtilisateur;
    private Button boutonModifEmail;
    private TextField textFieldEmail;
    private TextField textFieldNomUtilisateur;

    public PageProfilUtilisateur(AppliVae appli, ConnexionMySQL connexionMySQL, Utilisateur utilisateur) {
        this.appli = appli;
        this.connexionMySQL = connexionMySQL;
        this.utilisateur = utilisateur;

        this.booutonModifier();
        this.profileImage = new ImageView("pp.jpeg");
        this.gridPane = new GridPane();
        this.deconnecter = new Button("Se déconnecter");
        this.profileImage = new ImageView("file:img/pp.jpeg");
        this.textInfos = new Button("Informations Personnelles   >");
        this.textMDP = new Button("Changer de Mot de Passe   >");
        this.textPaiement = new Button("Paiements                          >");
        this.textInfos.getStyleClass().add("buttonBlanc");
        this.textMDP.getStyleClass().add("buttonBlanc");
        this.textPaiement.getStyleClass().add("buttonBlanc");
        this.tFieldMDPactuelle = new PasswordField();
        this.tFieldnvMDP = new PasswordField();
        this.tFieldconfinvMDP = new PasswordField();
        this.textFieldEmail = new TextField();
        this.textFieldNomUtilisateur = new TextField();
        this.ajouteMenu();
        this.afficherInfoPerso();
    }

    private void booutonModifier() {
        this.boutonModifEmail = new Button("Modifier");
        this.boutonModifNomUtilisateur = new Button("Modifier");

        this.boutonModifEmail
                .setOnAction(new ControleurProfilUtilisateurChangementinfo(this, connexionMySQL, utilisateur));
        this.boutonModifNomUtilisateur
                .setOnAction(new ControleurProfilUtilisateurChangementinfo(this, connexionMySQL, utilisateur));

        this.boutonModifNomUtilisateur.getStyleClass().add("buttonBleu");
        this.boutonModifEmail.getStyleClass().add("buttonBleu");
    }

    public void afficherInfoPerso() {
        this.ajouteInfoPerso();
        this.textInfos.getStyleClass().add("buttonBleu");
        this.textMDP.getStyleClass().remove("buttonBleu");
        this.textPaiement.getStyleClass().remove("buttonBleu");
    }

    public void afficherChangerMdp() {
        this.ajoutePageChangerMdp();
        this.textInfos.getStyleClass().remove("buttonBleu");
        this.textMDP.getStyleClass().add("buttonBleu");
        this.textPaiement.getStyleClass().remove("buttonBleu");
    }

    public void afficherPaiements() {
        GridPane fenetrePaiements = new FenetrePagePaiements(this.appli, this.connexionMySQL);
        this.setCenter(fenetrePaiements);
        this.textInfos.getStyleClass().remove("buttonBleu");
        this.textMDP.getStyleClass().remove("buttonBleu");
        this.textPaiement.getStyleClass().add("buttonBleu");
    }

    public void ajouteMenu() {
        VBox menu = new VBox();
        Text pageUtilisateur = new Text("Page Utilisateur");
        pageUtilisateur.setStyle("-fx-font-size: 55px; -fx-fill: #000000; -fx-font-family: 'Verdana';");
        menu.getChildren().add(pageUtilisateur);
        this.setLeft(menu);
        menu.setPadding(new Insets(20, 20, 20, 20));
        VBox sousMenu = new VBox();

        this.textInfos.setCursor(Cursor.HAND);
        this.textInfos.setOnAction(new ControleurBoutonCompte(this));

        this.textMDP.setCursor(Cursor.HAND);
        this.textMDP.setOnAction(new ControleurBoutonCompte(this));

        this.textPaiement.setCursor(Cursor.HAND);
        this.textPaiement.setOnAction(new ControleurBoutonCompte(this));

        sousMenu.getChildren().addAll(this.textInfos, this.textMDP, this.textPaiement);
        sousMenu.setSpacing(15);
        sousMenu.setPadding(new Insets(45, 50, 0, 20));
        menu.getChildren().add(sousMenu);
    }

    public void ajouteInfoPerso() {
        this.gridPane = new GridPane();
        if (utilisateur != null) {
            this.nomUtilisateur = utilisateur.getPseudo();
            this.email = utilisateur.getEmail();
        }
        gridPane.setPadding(new Insets(300, 0, 0, 30));
        gridPane.setStyle(
                "-fx-border-color: #D9D9D9; -fx-border-width: 3px; -fx-border-radius: 10; -fx-padding: 20px;");
        BorderPane.setMargin(gridPane, new Insets(130, 100, 100, 30));
        gridPane.setHgap(30);
        gridPane.setVgap(50);

        gridPane.add(new Text("Nom d'utilisateur "), 0, 0);
        gridPane.add(textFieldNomUtilisateur, 1, 0);
        textFieldNomUtilisateur.setPromptText(this.nomUtilisateur);
        gridPane.add(this.boutonModifNomUtilisateur, 2, 0);
        textFieldNomUtilisateur.getStyleClass().add("text-fieldU");

        gridPane.add(new Text("Email"), 0, 1);
        gridPane.add(textFieldEmail, 1, 1);
        textFieldEmail.setPromptText(this.email);
        gridPane.add(this.boutonModifEmail, 2, 1);
        textFieldEmail.getStyleClass().add("text-fieldU");
        gridPane.add(new Text("Photo de Profil"), 0, 4);
        gridPane.add(this.profileImage, 1, 4);
        Button boutonModifPP = new Button("Modifier");
        boutonModifPP.getStyleClass().add("buttonBleu");
        gridPane.add(boutonModifPP, 2, 4);
        profileImage.setPreserveRatio(true);
        profileImage.setFitWidth(200);

        this.deconnecter.getStyleClass().add("buttonRouge");
        gridPane.add(deconnecter, 0, 7);
        this.deconnecter.setOnAction(new ControleurProfilUtilisateur(this.appli, this.connexionMySQL));

        this.setCenter(gridPane);
    }

    public void ajoutePageChangerMdp() {
        this.gridPane = new GridPane();
        gridPane.setStyle(
                "-fx-border-color: #D9D9D9; -fx-border-width: 3px; -fx-border-radius: 10; -fx-padding: 20px;");
        BorderPane.setMargin(gridPane, new Insets(130, 100, 100, 30));
        gridPane.setHgap(30);
        gridPane.setVgap(40);

        gridPane.add(new Text("Charger de Mot de Passe"), 0, 0);
        gridPane.add(new Text("Mot de Passe actuelle "), 0, 1);
        gridPane.add(new Text("Nouveau Mot de Passe"), 0, 2);
        gridPane.add(new Text("Confirmer votre Mot de Passe"), 0, 3);

        gridPane.add(tFieldMDPactuelle, 1, 1);
        tFieldMDPactuelle.setPromptText("Entrer votre mot de passe actuelle");
        tFieldMDPactuelle.getStyleClass().add("text-fieldU");

        gridPane.add(tFieldnvMDP, 1, 2);
        tFieldnvMDP.setPromptText("Enter votre nouveau mot de passe");
        tFieldnvMDP.getStyleClass().add("text-fieldU");

        gridPane.add(tFieldconfinvMDP, 1, 3);
        tFieldconfinvMDP.setPromptText("Confirmer le nouveau mot de passe");
        tFieldconfinvMDP.getStyleClass().add("text-fieldU");

        Button boutonValider = new Button("Valider");
        boutonValider.getStyleClass().add("buttonBleu");
        boutonValider.setOnAction(new ControleurProfilUtilisateurChangementMDP(this, this.connexionMySQL, utilisateur));
        gridPane.add(boutonValider, 1, 4);

        this.setCenter(this.gridPane);
    }

    public PasswordField getMDPactuelle() {
        return tFieldMDPactuelle;
    }

    public PasswordField getNvMDP() {
        return tFieldnvMDP;
    }

    public PasswordField getConfinvMDP() {
        return tFieldconfinvMDP;
    }

    public Button getBoutonModifNomUtilisateur() {
        return boutonModifNomUtilisateur;
    }

    public Button getBoutonModifEmail() {
        return boutonModifEmail;
    }

    public TextField getTextFieldEmail() {
        return textFieldEmail;
    }

    public TextField getTextFieldNomUtilisateur() {
        return textFieldNomUtilisateur;
    }

}