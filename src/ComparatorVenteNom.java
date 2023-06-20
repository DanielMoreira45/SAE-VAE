import java.util.Comparator;

public class ComparatorVenteNom implements Comparator<Vente> {

    @Override
    public int compare(Vente arg0, Vente arg1) {
        return arg0.getObjet().getNomObjet().compareTo(arg1.getObjet().getNomObjet());
    }

}
