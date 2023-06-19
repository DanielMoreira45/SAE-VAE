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
    private BorderPane panelCentral;

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
    private PageCoInsc pageCoInsc;

    /**
     * 
     */
    private PageMiseEnVente pageMiseEnVente;

    /**
     * 
     */
    private PageAccueil pageAccueil;

    /**
     * 
     */
    private PageProfilUtilisateur pageProfilUtilisateur;




    @Override
    public void init(){
        this.scene = this.laScene();
        this.laConnexionUtilisateur = new UtilisateurBD();
        this.laConnexionVente = new VenteBD();
        this.laConnexionObjet = new ObjetBD();
        this.laConnexionEncherir = new EncherirBD();

        this.pageCoInsc = new PageCoInsc(this.scene, this.laConnexionUtilisateur, this);
        this.pageMiseEnVente = new PageMiseEnVente(this.user, this.laConnexionVente);
        this.pageAccueil = new PageAccueil(this.laConnexionVente, this);
        this.pageProfilUtilisateur = new PageProfilUtilisateur();
    }

    private Scene laScene(){
        BorderPane fenetre = new BorderPane();
        fenetre.setTop(this.titre());
        fenetre.setCenter(this.panelCentral);
    }

    private Pane titre(){

    }

    public void modeConnexion(){
        this.pageCoInsc.afficherPageConnexion();
    }

    public void modeInscription(){
        this.pageCoInsc.afficherPageInscription();
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

    }
    
    public PreparedStatement preparedStatement(String requete){
        
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("Appli VAE");
        stage.setScene(this.laScene());
        this.modeConnexion();
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
