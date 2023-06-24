import java.sql.*;


/**
Classe représentant un objet d'une base de données.
*/
public class ObjetBD {
private ConnexionMySQL laConnexionMySQL;
Integer idLibreOb = null;
Statement st;

/**

@param laConnexionMySQL
*/
public ObjetBD(ConnexionMySQL laConnexionMySQL) {
this.laConnexionMySQL = laConnexionMySQL;
}
/**

Insère un objet dans la base de données.
@param o L'objet à insérer.
@throws SQLException 
*/
public void insereObjet(Objet o) throws SQLException {
PreparedStatement s = this.laConnexionMySQL.preparedStatement("INSERT INTO OBJET VALUES (?,?,?,?,?)");
s.setInt(1, o.getidObjet());
s.setString(2, o.getNomObjet());
s.setString(3, o.getDescription());
s.setInt(4, o.getVendeur().getId());
s.setDouble(5, o.getCategorie());
s.executeQuery();
}
/**

Supprime un objet de la base de données.
@param o L'objet à supprimer.
@throws SQLException 
*/
public void supprimeObjet(Objet o) throws SQLException {
PreparedStatement s = this.laConnexionMySQL
.preparedStatement("DELETE FROM OBJET WHERE idob = ?, nomob = ?, idut = ?, idcat = ?");
s.setInt(1, o.getidObjet());
s.setString(2, o.getNomObjet());
s.setInt(3, o.getVendeur().getId());
s.setInt(4, o.getCategorie());
s.executeQuery();
}
/**

Récupère un objet à partir de son identifiant.

@param idob L'identifiant de l'objet.
@return L'objet correspondant à l'identifiant spécifié.
@throws SQLException
*/
public Objet objetParId(int idob) throws SQLException {
UtilisateurBD utilBD = new UtilisateurBD(laConnexionMySQL);

Statement s = this.laConnexionMySQL.createStatement();
ResultSet rs = s.executeQuery("SELECT idob,nomob,descriptionob,idut,idcat FROM OBJET WHERE idob =" + idob);
rs.next();
int idut = rs.getInt(4);
String desc = rs.getString(3);
String nomOb = rs.getString(2);
int idcat = rs.getInt(5);
rs.close();

Utilisateur util = utilBD.utilisateurParId(idut);
return new Objet(idob, desc, nomOb, null, util, idcat);
}

/**

Obtient l'identifiant maximum parmi tous les objets de la base de données.
@return L'identifiant maximum des objets.
@throws SQLException 
*/
public int maxIdOb() throws SQLException {
Statement s = this.laConnexionMySQL.createStatement();
ResultSet rs = s.executeQuery("SELECT MAX(idob) FROM OBJET");
if (rs.next()){
return rs.getInt(1);
}
return 0;
}
}