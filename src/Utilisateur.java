import java.util.*;

/**
 * 
 */
public class Utilisateur {
    /**
     * Id de l'utilisateur
     */
    private Integer id;

    /**
     * Pseudo de l'utilisateur
     */
    private String pseudo;

    /**
     * Email de l'utilisateur
     */
    private String email;

    /**
     * Mot de passe de l'utilisateur
     */
    private String mdp;

    /**
     * Indique si l'utilisateur est actif ou non (true s'il est actif, false sinon)
     */
    private boolean active;

    /**private double id;
     * Le role de l'utilisateur
     */
    private int role;

    /**
     * Ensemble des enchères créées par l'utilisateur
     */
    private Set<Enchere> lesEncheres; 


    /**
     * Default constructor
     */
    public Utilisateur(Integer id,String pseudo, String email, String mdp, int role) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
        this.active = true;
        this.role = role;
    }

    /**
     * Getter id
     * @return (double) id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pseudo
     * @return (String) pseudo
     */
    public String getPseudo() {
        return this.pseudo;
    }

    /**
     * Setter pseudo
     * @param nouveauPseudo 
     */
    public void setPseudo(String nouveauPseudo) {
        this.pseudo = nouveauPseudo;
    }

    /**
     * Getter email
     * @return (String) email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter email
     * @param nouvelleEmail 
     */
    public void setEmail(String nouvelleEmail) {
        this.email = nouvelleEmail;
    }

    /**
     * Indique si l'utilisateur est actif
     * @return (boolean) true si l'utilisateur est actif, false sinon
     */
    public Boolean estActive() {
        return this.active;
    }

    /**
     * Setter active
     * @param active 
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Getter mdp
     * @return (String) mdp
     */
    public String getMotDePasse() {
        return mdp;
    }

    /**
     * Setter mdp
     * @param nouveauMDP 
     */
    public void setMotDePasse(String nouveauMDP) {
        this.mdp = nouveauMDP;
    }

    /**
     * Getter role
     * @return (Roles) role
     */
    public int getRole() {
        return this.role;
    }

    /**
     * Setter role
     * @param nouveauRole 
     */
    public void ChangeRole(int nouveauRole) {
        this.role = nouveauRole;
    }
}
