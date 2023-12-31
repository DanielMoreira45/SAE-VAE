
import java.sql.*;

/**
 * 
 */
public class ConnexionMySQL {
    /**
     * 
     */
    private Connection mysql = null;
    /**
     * 
     */
    private boolean connecte = false;
    /**
     * Default constructor
     */
    public ConnexionMySQL() throws ClassNotFoundException{
        Class.forName("org.mariadb.jdbc.Driver");
    }
    /**
     * 
     */
    public void connecter() {
        // si tout c'est bien passé la connexion n'est plus nulle
		Connection c;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"vae","root","root");
            // jdbc:mysql://localhost:3306/nomBD
			// c = DriverManager.getConnection("jdbc:mysql://servinfo-mariadb:3306/"+"DBmoreira","moreira","moreira");
			this.mysql=c;
		} catch ( SQLException ex ) {
			System.out.println("Msg : " + ex.getMessage() + ex.getErrorCode());
		}
		this.connecte=this.mysql!=null;
    }
    public boolean isConnecte() {
        return this.connecte;
    }
    public Statement createStatement() throws SQLException {
        return this.mysql.createStatement();
    }
    public PreparedStatement preparedStatement(String requete) throws SQLException{
        return this.mysql.prepareStatement(requete);
    }

}