import java.sql.SQLException;
import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControleurPrixMinMax implements EventHandler<KeyEvent> {
  private PageAccueil vue;
  private TouteLesVentes ventes;

  public ControleurPrixMinMax(PageAccueil vue, TouteLesVentes touteLesVentes) {
    this.vue = vue;
    this.ventes = touteLesVentes;
  }

  @Override
  public void handle(KeyEvent event) {
    String prixMin = vue.getPrixMinTf().getText();
    String prixMax = vue.getPrixMax().getText();
    System.out.println("handle");
    if (prixMin != null && prixMax != null && prixMax.length() >= 2) {
      System.out.println("not null");
      try {
        double min = Double.valueOf(prixMin);
        double max = Double.valueOf(prixMax);
        this.vue.setLesVentes(this.vue.getTouteLesVentes().toutVente());
        this.vue.setLesVentes(this.ventes.trieVenteIntervalle(prixMin, prixMax));
        System.out.println(min + " " + max);
      } catch (NumberFormatException e) {
        System.out.println("Les valeurs saisies ne sont pas des nombres");

      } catch (SQLException | ParseException e) {
        System.out.println("Problème lors du tri des ventes.");
        e.printStackTrace();
      }
      this.vue.majAffichage();
    }
  }
}