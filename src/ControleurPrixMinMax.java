import java.sql.SQLException;
import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControleurPrixMinMax implements EventHandler<KeyEvent>{
  private PageAccueil vue;
  private TouteLesVentes ventes;

  public ControleurPrixMinMax(PageAccueil vue, TouteLesVentes touteLesVentes) {
    this.vue = vue;
    this.ventes = touteLesVentes;
  }

  @Override
    public void handle(KeyEvent event) {
        TextField recherche = (TextField) event.getTarget();
        String prixMin = vue.getPrixMinTf().getText();
        String prixMax = vue.getPrixMax().getText();
        if(prixMin != null && prixMax != null){
          try {
            this.vue.setLesVentes(this.ventes.trieVenteIntervalle(prixMin, prixMax));
          } catch (SQLException | ParseException e) {
            System.out.println("blème");
            e.printStackTrace();
          }
          this.vue.majAffichage();
        }

      }
    }
