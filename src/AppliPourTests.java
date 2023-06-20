import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


 
public class AppliPourTests extends Application {
    
    private Scene scene;
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(AppliPourTests.class, args);
    }
    
    @Override
    public void init(){

    }
    
    @Override
    public void start(Stage stage) {
        Pane root = new VueVente();
        this.scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.setTitle("Test page Vente");
        stage.show();
    }
}
