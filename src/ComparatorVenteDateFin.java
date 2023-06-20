import java.util.Comparator;

public class ComparatorVenteDateFin implements Comparator<Vente> {

    @Override
    public int compare(Vente arg0, Vente arg1) {
        return arg0.getFinVente().compareTo(arg1.getFinVente());
    }

}
