import java.sql.SQLException;
import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurTrierEncheresActuelles implements EventHandler<ActionEvent> {
    private VueEncheresUtilisateur vue;
    private TouteLesVentes touteLesVentes;

    public ControleurTrierEncheresActuelles(VueEncheresUtilisateur vue, TouteLesVentes toutesLesVentes) {
        this.vue = vue;
        this.touteLesVentes = toutesLesVentes;
    }

    @Override
    public void handle(ActionEvent event) {
        this.vue.reverseLesVentes();
        this.vue.majLesArticles();
    }

}
