
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Une enchere
 */
public class Enchere {

    /**
     * Permet de crée un nouvelle enchere
     * 
     * @param u       L'utilisateur qui a crée une nouvelle enchere
     * @param v       La vente sur la quelle l'enchere a été mise
     * @param montant Le montant de l'enchere
     * @param date    La date de l'enchere sous la forme (dd/MM/yy:hh/mm/ss)
     * @throws ParseException Si la date n'est pas sous la bonne forme, la bonne
     *                        forme est : dd/MM/yyyy:hh/mm/ss
     */
    public Enchere(Utilisateur u, Vente v, Double montant, String date) throws ParseException {
        SimpleDateFormat lecteur = new SimpleDateFormat("dd/MM/yyyy:hh/mm/ss");
        this.utilisateur = u;
        this.vente = v;
        this.montant = montant;
        this.dateHeure = lecteur.parse(date);
    }

    /**
     * L'utilisateur qui a fait l'enchere
     */
    private Utilisateur utilisateur;
    /**
     * La vente qui est consernée par l'enchere
     */
    private Vente vente;
    /**
     * Le montant de l'enchere
     */
    private Double montant;

    /**
     * la date de l'enchere
     */
    private Date dateHeure;

    /**
     * Permet d'optenir le montant de l'enchere
     * 
     * @return double le montant
     */
    public Double getMontant() {
        return montant;
    }

    /**
     * Permet de redefinir le montant de l'enchere
     * 
     * @param montant le nouveau montant
     */
    public void setMontant(Double montant) {
        this.montant = montant;
    }

    /**
     * Permet d'optenir la date de l'enchere
     * 
     * @return le Long de la date
     */
    public Long getDateHeure() {
        return dateHeure.getTime();
    }

    /**
     * Permet de redefinir la date de l'enchere
     * 
     * @param nouvelleDate la nouvelle date sous la forme (dd/MM/yy:hh/mm/ss)
     * @throws ParseException Si la forme de la date n'est pas respecter
     */
    public void setDateHeure(String nouvelleDate) throws ParseException {
        SimpleDateFormat lecteur = new SimpleDateFormat("dd/MM/yyyy:hh/mm/ss");
        this.dateHeure = lecteur.parse(nouvelleDate);
    }

    /**
     * Permet d'optenir l'utilisateur qui a fait l'enchere
     * 
     * @return l'utilisateur qui a fait l'enchere
     */
    public Utilisateur getEncherisseur() {
        return utilisateur;
    }

    /**
     * Permet de redefinir l'utilisateur qui a fait l'enchere
     * 
     * @param u le nouvelle utilisateur
     */
    public void setEncherisseur(Utilisateur u) {
        this.utilisateur = u;
    }

    /**
     * Permet de savoir sur quel vente l'enchere se porte
     * 
     * @return la vente
     */
    public Vente getVente() {
        return this.vente;
    }

    /**
     * Permet de redefinir la vente sur laquel l'enchere se porte
     * 
     * @param v la nouvelle vente
     */
    public void setVente(Vente v) {
        this.vente = v;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (obj instanceof Enchere) {
            Enchere enchere = (Enchere) obj;
            return utilisateur.equals(enchere.getEncherisseur()) && vente.equals(enchere.getVente())
                    && montant.equals(enchere.getMontant()) && this.getDateHeure().equals(enchere.getDateHeure());
        }
        return false;

    }
}