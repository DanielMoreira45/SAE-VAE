import java.sql.Statement;
import java.sql.PreparedStatement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AppliVae extends Application{
    
    /**
     * Le panel central de la vue
     */
    private Pane panelCentral;

    /**
     * La scene
     */
    private Scene scene;

    /**
     * L'utilisateur qui utilise l'appli
     */
    private Utilisateur user;

    /** 
     * La connexion à la BD pour Utilisateur
     */
    private UtilisateurBD laConnexionUtilisateur;

    /**
     * La connexion à la BD pour Vente
     */
    private VenteBD laConnexionVente;

    /**
     * La connexion à la BD pour Objet
     */
    private ObjetBD laConnexionObjet;

    /**
     * La connexion à la BD pour Encherir
     */
    private EncherirBD laConnexionEncherir;

    /**
     * Page de connexion/inscription
     */
    private FenetreCoInsc pageCoInsc;

    /**
     * Page de mise en vente
     */
    //private PageMiseEnVente pageMiseEnVente;

    /**
     * Page d'accueil
     */
    //private PageAccueil pageAccueil;

    /**
     * Page du profil utilisateur
     */
    //private PageProfilUtilisateur pageProfilUtilisateur;

    /**
     * La connexion à la BD
     */
    private ConnexionMySQL connexionMySQL;



    /**
     * Initialise tous les attributs
     * @throws ClassNotFoundException
     */
    @Override
    public void init() throws ClassNotFoundException{
        this.laScene();
        this.connexionMySQL = new ConnexionMySQL();
        this.laConnexionUtilisateur = new UtilisateurBD(this.connexionMySQL);
        this.laConnexionVente = new VenteBD(this.connexionMySQL);
        this.laConnexionObjet = new ObjetBD(this.connexionMySQL);
        this.laConnexionEncherir = new EncherirBD(this.connexionMySQL);

        this.pageCoInsc = new FenetreCoInsc(this, this.connexionMySQL);
        //this.pageMiseEnVente = new PageMiseEnVente(this.user, this.laConnexionVente);
        //this.pageAccueil = new PageAccueil(this.laConnexionVente, this);
        //this.pageProfilUtilisateur = new PageProfilUtilisateur();
        scene.getStylesheets().add("file:src/css.css");
    }

    /**
     * Crée la scène
     */
    private void laScene(){
        BorderPane fenetre = new BorderPane();
        fenetre.setCenter(this.panelCentral);
        this.scene = new Scene(fenetre, 1080, 1920);
    }

    /**
     * Permet de passer à l'affichage de la page d'inscription/connexion
     */
    public void modeCoInsc(){
        this.scene.setRoot(this.pageCoInsc);
    }

    /**
     * Permet de passer à l'affichage de la page de mise en vente
     */
    public void modeMiseEnVente(){
        //this.scene.setRoot(this.pageMiseEnVente);
    }

    /**
     * Permet de passer à l'affichage de la page d'accueil
     */
    public void modeAccueil(){
        //this.scene.setRoot(this.pageAccueil)
        System.out.println("Page d'accueil");
    }

    /**
     * Permet de passer à l'affichage de la page profil de l'utilisateur
     */
    public void modeProfilUtilisateur(){
        //this.scene.setRoot(this.pageProfilUtilisateur)
    }

    public void setUser(Utilisateur utilisateur){

    }

    public boolean isConnect(){
        return false;
    }
    
    public PreparedStatement preparedStatement(String requete){
        return null;
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("Appli VAE");
        stage.setHeight(1080);
        stage.setWidth(1920);
        stage.setFullScreen(false);
        stage.setFullScreenExitHint("");
        stage.setScene(this.scene);
        this.modeCoInsc();
        stage.show();
    }

    /**
     * Programme principal
     * @param args
     */
    public static void main(String[] args){
        launch(args);
    }
}