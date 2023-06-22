import java.util.*;

/**
 * Classe Utilisateur permetant de modeliser un utilisateur
 */
public class Utilisateur {
    /**
     * Id de l'utilisateur
     */
    private int id;

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

    /**
     * private double id;
     * Le role de l'utilisateur
     */
    private int role;

    /**
     * Ensemble des enchères créées par l'utilisateur
     */
    private Set<Enchere> lesEncheres;

    /**
     * constructeur pour avoir un utilisateur
     */
    public Utilisateur(int id, String pseudo, String email, String mdp, int role) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
        this.active = true;
        this.role = role;
    }

    public Utilisateur(int id, String pseudo, String email, String mdp,boolean active, int role) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
        this.active = active;
        this.role = role;
    }

    /**
     * Getter id
     * 
     * @return (double) id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pseudo
     * 
     * @return (String) pseudo
     */
    public String getPseudo() {
        return this.pseudo;
    }

    /**
     * Setter pseudo
     * 
     * @param nouveauPseudo
     */
    public void setPseudo(String nouveauPseudo) {
        this.pseudo = nouveauPseudo;
    }

    /**
     * Getter email
     * 
     * @return (String) email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter email
     * 
     * @param nouvelleEmail
     */
    public void setEmail(String nouvelleEmail) {
        this.email = nouvelleEmail;
    }

    /**
     * Indique si l'utilisateur est actif
     * 
     * @return (boolean) true si l'utilisateur est actif, false sinon
     */
    public Boolean estActive() {
        return this.active;
    }

    /**
     * Setter active
     * 
     * @param active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Getter mdp
     * 
     * @return (String) mdp
     */
    public String getMotDePasse() {
        return mdp;
    }

    /**
     * Setter mdp
     * 
     * @param nouveauMDP
     */
    public void setMotDePasse(String nouveauMDP) {
        this.mdp = nouveauMDP;
    }

    /**
     * Getter role
     * 
     * @return (int) role
     */
    public int getRole() {
        return this.role;
    }

    /**
     * Setter role
     * 
     * @param nouveauRole
     */
    public void ChangeRole(int nouveauRole) {
        this.role = nouveauRole;
    }

    /**
     * Permet d'optenir les encheres le l'utilisateur
     * 
     * @return un ensemble d'encheres
     */
    public Set<Enchere> getLesEncheres() {
        return lesEncheres;
    }

    /**
     * Permet d'ajouter une enchere a l'utilisateur
     * 
     * @param enchere l'enchere de l'utilisateur
     */
    public void ajouteEnchere(Enchere enchere) {
        this.lesEncheres.add(enchere);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof Utilisateur)) return false;

        Utilisateur u = (Utilisateur) obj;
        return this.getId() == u.getId();
    }
}
