import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Établir la connexion à la base de données
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDmoreira", "moreira", "moreira");

            // Créer une instance de UtilisateurBD avec la connexion établie
            UtilisateurBD utilisateurBD = new UtilisateurBD(connection);

            // Exécuter des opérations de test
            // ...

            // Fermer la connexion à la base de données
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Exemple d'utilisation : Insérer un utilisateur
        Utilisateur utilisateur = new Utilisateur("JohnDoe", "john@example.com", "password123", true);
        Roles role = new Roles("role_name");
        utilisateurBD.insererUtilisateur(utilisateur, role);

        // Exemple d'utilisation : Supprimer un utilisateur
        int idUtilisateur = 1; // ID de l'utilisateur à supprimer
        utilisateurBD.effacerJoueur(idUtilisateur);

        // Exemple d'utilisation : Obtenir le nombre maximal d'utilisateurs
        int maxId = utilisateurBD.maxIdUtilisateur();
        System.out.println("Le nombre maximal d'utilisateurs est : " + maxId);
    }
}