import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


 
public class AppliPourTests extends Application {
    AppliVae vae;
    ConnexionMySQL connexion;
    private Scene scene;
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(AppliPourTests.class, args);
    }
    
    @Override
    public void init(){
        try{
            this.connexion = new ConnexionMySQL();
            this.connexion.connecter();
        }
        catch (Exception e) {

        }
    }
    
    @Override
    public void start(Stage stage) {
        Pane root = new VueEncheresUtilisateur(this.vae, this.connexion, 6);
        this.scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.setTitle("Test page Vente");
        stage.show();
    }
}
