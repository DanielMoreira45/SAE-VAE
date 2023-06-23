import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import java.time.LocalTime;

public class ControleurMiseEnVente implements EventHandler<ActionEvent> {

    // La vue de l'application
    private VueVente vue;
    private ConnexionMySQL laConnexionMySQL;
    Integer idLibre = null;
    PhotoBD photoBd;
    Objet obj = null;
    String dateDeBut;
    String dateFin;
    private AppliVae appli;

    public ControleurMiseEnVente(VueVente vue, ConnexionMySQL laConnexionMySQL, AppliVae appli) {
        this.vue = vue;
        this.laConnexionMySQL = laConnexionMySQL;
        this.appli = appli;
    }

    /**
     * L'action consiste à changer de fenêtre pour aller à la page de connexion
     * 
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        LocalTime heureActuelle = LocalTime.now();
        LocalDate date = LocalDate.now();
        String heureActuelleAc = heureActuelle.toString().substring(0, 8).replace(":", "/");
        String cat = vue.getCategorie();
        Double prixMin = vue.getPrixMin();
        Double prixBase = vue.getprixBase();
        this.dateDeBut = vue.dateDebutToString();
        this.dateFin = vue.dateFinToString();
        if (this.dateDeBut != null & this.dateFin != null) {
            this.dateFin = this.dateFin.replace("-", "/");
            this.dateDeBut = this.dateDeBut.replace("-", "/");
        }
        String desc = vue.getDesc();
        List<Photo> lesPhotos = vue.getPhotos();
        String titrePh = vue.getTitre();
        String titreOb = vue.titreVente();
        ObjetBD objetBD = new ObjetBD(laConnexionMySQL);
        VenteBD venteDeOb = new VenteBD(laConnexionMySQL);
        try {
            this.idLibre = objetBD.maxIdOb() + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Button bouton = (Button) actionEvent.getTarget();
        if (bouton.getText().equals("Ajoutez des photos")) {
            this.photoBd = new PhotoBD(laConnexionMySQL);
                        try {
                vue.ajoutImage();
                Photo photo = new Photo(photoBd.maxIdPhoto() + 1, vue.getTitre(), vue.getImageView());
                System.out.println("Creation instance photo");
                vue.ajouteUnePhoto(photo);
                System.out.println("photo ajout");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (bouton.getText().equals("Ajouter le produit > ")) {
            if (prixMin != null && prixBase != null && cat != null && dateFin != null && dateDeBut != null) {
                if (!(vue.valideDate())) {
                    vue.getDateDebut().setStyle(
                            "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: red;");
                    vue.getDateFin().setStyle(
                            "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: red;");

                    vue.setMessageErreurDate();

                }
                if (prixMin >= prixBase) {
                    vue.getPrixMinTf().setStyle(
                            "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: red;");
                    vue.getprixBaseTf().setStyle(
                            "-fx-background-color : white; -fx-background-radius: 0.8em; -fx-border-radius : 0.8em; -fx-border-color: red;");
                }
                if (vue.valideDate() && prixMin < prixBase && titreOb.length() > 0) {
                    try {
                        this.obj = new Objet(objetBD.maxIdOb() + 1, desc, titreOb, lesPhotos, vue.getVendeur(),
                                Categorie.getIntCategorie(cat));
                        objetBD.insereObjet(obj);
                        vue.popUpObjetCo(titreOb);
                        // for (Photo photos : lesPhotos) {
                        // photoBd.insertPhoto(photos, obj);
                        // }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        Vente vente = new Vente(venteDeOb.maxIdVe() + 1, prixBase, prixMin,
                                dateDeBut + ":00/00/00", dateFin + ":00/00/00",
                                Status.calculStatutInsertion(date, vue.getDateDebut().getValue()), this.obj);

                        vue.popUpVenteInserer(titreOb, prixBase);
                        venteDeOb.insereVente(vente);
                        appli.modeAccueil();
                    } catch (SQLException e) {
                        System.out.println("un blème");
                    } catch (ParseException e) {
                        System.out.println("problème de parse");
                    }
                }
            } else {
                this.vue.popUpRemplirChamp();
            }
        }
    }
}