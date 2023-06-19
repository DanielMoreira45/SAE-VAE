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
     * 
     */
    private UtilisateurBD laConnexionUtilisateur;

    /**
     * 
     */
    private VenteBD laConnexionVente;

    /**
     * 
     */
    private ObjetBD laConnexionObjet;

    /**
     * 
     */
    private EncherirBD laConnexionEncherir;

    /**
     * 
     */
    private FenetreCoInsc pageCoInsc;

    /**
     * 
     */
    //private PageMiseEnVente pageMiseEnVente;

    /**
     * 
     */
    //private PageAccueil pageAccueil;

    /**
     * 
     */
    //private PageProfilUtilisateur pageProfilUtilisateur;

    /**
     * 
     */
    private ConnexionMySQL connexionMySQL;




    @Override
    public void init() throws ClassNotFoundException{
        this.laScene();
        //this.connexionMySQL = new ConnexionMySQL();
        this.laConnexionUtilisateur = new UtilisateurBD(this.connexionMySQL);
        this.laConnexionVente = new VenteBD(this.connexionMySQL);
        this.laConnexionObjet = new ObjetBD(this.connexionMySQL);
        this.laConnexionEncherir = new EncherirBD(this.connexionMySQL);

        this.pageCoInsc = new FenetreCoInsc();
        //this.pageMiseEnVente = new PageMiseEnVente(this.user, this.laConnexionVente);
        //this.pageAccueil = new PageAccueil(this.laConnexionVente, this);
        //this.pageProfilUtilisateur = new PageProfilUtilisateur();
        scene.getStylesheets().add("css.css");
    }

    private void laScene(){
        BorderPane fenetre = new BorderPane();
        //fenetre.setTop(this.titre());
        fenetre.setCenter(this.panelCentral);
        this.scene = new Scene(fenetre, 1080, 1920);
    }

    /*private Pane titre(){

    }*/

    public void modeCoInsc(){
        this.scene.setRoot(pageCoInsc);
    }

    public void modeMiseEnVente(){

    }

    public void modeAccueil(){

    }

    public void modeVoirVente(){

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