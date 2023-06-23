import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
// import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;


// non fonctionnel
public class PhotoBD {
    private ConnexionMySQL laConnexionMySQL;
    
    public PhotoBD(ConnexionMySQL laConnexionMySQL) {
        this.laConnexionMySQL = laConnexionMySQL;
    }

public void insertPhoto(Photo photo, Objet o) throws SQLException {
    String sql = "INSERT INTO PHOTO (idph, titreph, imgph, idob) VALUES (?, ?, ?, ?)";
    try (PreparedStatement pstmt = laConnexionMySQL.preparedStatement(sql)) {
        pstmt.setInt(1, photo.getIdph());
        pstmt.setString(2, photo.getTitreph());
        // convertir l'image de ImageView en BufferedImage
        Image image = photo.getImageView().getImage();
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        // convertir BufferedImage en tableau d'octets (byte[])
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageBytes = baos.toByteArray();
        // crée un objet Blob à partir des données de l'image
        Connection connection = (Connection) laConnexionMySQL;
        Blob imageBlob = connection.createBlob();
        imageBlob.setBytes(1, imageBytes);
        pstmt.setBlob(3, imageBlob);
        pstmt.setInt(4, o.getidObjet());
        pstmt.executeUpdate();
    } 
}
    

    int maxIdPhoto() throws SQLException {
        boolean notZero = true;
        int nb = -1;
        Statement st = laConnexionMySQL.createStatement();
        ResultSet resultats = st.executeQuery("SELECT max(idph) FROM PHOTO;");
        if(resultats.next()){
            nb = resultats.getInt(1);
            notZero = false;
        };
        if(notZero){
            return nb;
        }
        resultats.close();
        return 1;
    }

}