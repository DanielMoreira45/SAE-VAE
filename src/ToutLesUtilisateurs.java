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

  public List<Utilisateur> tout() throws SQLException {
    return utilisateurBD.tout();
  }

  public List<Utilisateur> toutAdmin() throws SQLException {
    return utilisateurBD.toutAdmin();
  }

  public List<Utilisateur> actif() throws SQLException {
    return utilisateurBD.actif();
  }

  public List<Utilisateur> inactif() throws SQLException {
    return utilisateurBD.inactif();
  }

  public List<Utilisateur> toutUtilisateurs() throws SQLException {
    return utilisateurBD.toutUtilisateurs();
  }

  public List<Utilisateur> recherche(String text) throws SQLException {
    return utilisateurBD.recherche(text);
  }

  // public List<Utilisateur> trieUtilisateursConnexion(){
  // return this.lesUtilisateurs;
  // }

  // public List<Utilisateur> trieUtilisateursConnexionDecroissant(){
  // return this.lesUtilisateurs;
  // }

  public void supprimeUnUtilisateur(int num) throws SQLException {
    this.utilisateurBD.supprimerUtilisateur(num);
  }

  public void setActif(Utilisateur utilisateur) throws SQLException {
    this.utilisateurBD.setActif(utilisateur);
  }

  public void setRole(Utilisateur utilisateur) throws SQLException {
    this.utilisateurBD.setRole(utilisateur);
  }

  // public void desactiverUtilisateur(String pseudo){
  // }

  // public List<Utilisateur> getListeUtilisateurs(){
  // return this.lesUtilisateurs;
  // }

}
