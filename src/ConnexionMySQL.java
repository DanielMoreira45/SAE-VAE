
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        Class.forName("org.mariabd.jdbc.Driver");
    }
    /**
     * 
     */
    public void connecter() {
        Connection c;
        try {
            c= DriverManager.getConnection("jdbc:mysql://servinfo-mariadb:3306/DBmoreira", "moreira", "moreira");
            this.mysql = c;
        } catch (SQLException e) {
            System.out.println("Msg : "+e.getMessage() + e.getErrorCode());
        }
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