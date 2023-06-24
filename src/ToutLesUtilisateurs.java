import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ToutLesUtilisateurs {
  private ConnexionMySQL laConnexionMySQL;
  private UtilisateurBD utilisateurBD;

  public ToutLesUtilisateurs(ConnexionMySQL laConnexionMySQL) {
    this.laConnexionMySQL = laConnexionMySQL;
    this.utilisateurBD = new UtilisateurBD(this.laConnexionMySQL);
  }

  /**
   * Permet d'obtenir tout les utilisateurs/administrateurs
   * @return Tout les utilisateurs/administrateurs
   * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
   */
  public List<Utilisateur> tout() throws SQLException {
    return utilisateurBD.tout();
  }

  /**
   * Permet d'obtenir tout les administrateurs
   * @return Tout les administrateurs
   * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
   */
  public List<Utilisateur> toutAdmin() throws SQLException {
    return utilisateurBD.toutAdmin();
  }

  /**
   * Permet d'obtenir tout les utilisateurs actifs
   * @return Tout les utilisateurs actifs
   * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
   */
  public List<Utilisateur> actif() throws SQLException {
    return utilisateurBD.actif();
  }

  /**
   * Permet d'obtenir tout les utilisateurs i,actifs
   * @return Tout les utilisateurs i,actifs
   * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
   */
  public List<Utilisateur> inactif() throws SQLException {
    return utilisateurBD.inactif();
  }

  /**
   * Permet d'obtenir tout les utilisateurs
   * @return Tout les utilisateurs
   * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
   */
  public List<Utilisateur> toutUtilisateurs() throws SQLException {
    return utilisateurBD.toutUtilisateurs();
  }

  /**
   * Permet de rechercher un utilisateur/administrateur
   * @param text Ce qui a été entré dans la barre de recherche
   * @return Les utilisateurs/administrateurs correspondant
   * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
   */
  public List<Utilisateur> recherche(String text) throws SQLException {
    return utilisateurBD.recherche(text);
  }

  // public List<Utilisateur> trieUtilisateursConnexion(){
  // return this.lesUtilisateurs;
  // }

  // public List<Utilisateur> trieUtilisateursConnexionDecroissant(){
  // return this.lesUtilisateurs;
  // }

  /**
   * Permet de supprimer un utilisateur
   * @param num L'id de l'utilisateur
   * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
   */
  public void supprimeUnUtilisateur(int num) throws SQLException {
    this.utilisateurBD.supprimerUtilisateur(num);
  }

  /**
   * Permet de changer le statut de l'utilisateur (actif/inactif)
   * @param utilisateur L'utilisateur dont on veut changer le statut
   * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
   */
  public void setActif(Utilisateur utilisateur) throws SQLException {
    this.utilisateurBD.setActif(utilisateur);
  }

  /**
   * Permet de changer le role d'un utilisateur
   * @param utilisateur L'utilisateur dont on veut changer le role
   * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
   */
  public void setRole(Utilisateur utilisateur) throws SQLException {
    this.utilisateurBD.setRole(utilisateur);
  }

  // public void desactiverUtilisateur(String pseudo){
  // }

  // public List<Utilisateur> getListeUtilisateurs(){
  // return this.lesUtilisateurs;
  // }

}
