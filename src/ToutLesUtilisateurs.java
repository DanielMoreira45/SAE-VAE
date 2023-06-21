import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ToutLesUtilisateurs{
  private ConnexionMySQL laConnexionMySQL;
  private UtilisateurBD utilisateurBD;

  public ToutLesUtilisateurs(ConnexionMySQL laConnexionMySQL) {
    this.laConnexionMySQL = laConnexionMySQL;
    this.utilisateurBD = new UtilisateurBD(this.laConnexionMySQL);
  }

  public List<Utilisateur> toutUtilisateurs() throws SQLException, ParseException {
    return utilisateurBD.toutUtilisateurs();
  }

  public List<Utilisateur> recherche(String text) throws SQLException {
    return utilisateurBD.recherche(text);
  }

  // public List<Utilisateur> trieUtilisateursConnexion(){
  //   return this.lesUtilisateurs;
  // }


  // public List<Utilisateur> trieUtilisateursConnexionDecroissant(){
  //   return this.lesUtilisateurs;
  // }

  public void supprimeUnUtilisateur(int num) throws SQLException{
    this.utilisateurBD.supprimerUtilisateur(num);
  }

  public void setActif(Utilisateur user) throws SQLException {
    this.utilisateurBD.setActif(user);
  }


  // public void desactiverUtilisateur(String pseudo){
  // }


  // public List<Utilisateur> getListeUtilisateurs(){
  //   return this.lesUtilisateurs;
  // }

}

