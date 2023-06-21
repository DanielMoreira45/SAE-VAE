import java.sql.Date;
import java.time.LocalDate;

/**
 * Permet d'avoir le status de la vente
 */
public class Status {
    private Status(){}
    public static int AVENIR = 1;
    public static int ENCOURS = 2;
    public static int AVALIDER = 3;
    public static int VALIDEE = 4;
    public static int NONCONCLUE = 5;


    public static int calculStatutInsertion(LocalDate ajd, LocalDate dateDebut){   
        if(ajd.isBefore(dateDebut)){
            return 1;
        }else{
            return 2;
        }
    }

}