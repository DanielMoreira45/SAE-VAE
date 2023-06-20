import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PhotoBD {
    private ConnexionMySQL laConnexionMySQL;
    /**
     * Default constructor
     */
    public PhotoBD(ConnexionMySQL laConnexionMySQL) {
        this.laConnexionMySQL = laConnexionMySQL;
    }

public void insertPhoto(Photo photo, Objet o) throws SQLException {
    String sql = "INSERT INTO PHOTO (idph, titreph, imgph, idob) VALUES (?, ?, ?, ?)";

    try (PreparedStatement pstmt = laConnexionMySQL.preparedStatement(sql)) {
        pstmt.setBigDecimal(1, o.getIdph()); // Remplacez o.getIdph() par la valeur réelle de l'ID de la photo
        pstmt.setString(2, o.getTitreph()); // Remplacez o.getTitreph() par la valeur réelle du titre de la photo
        // Convertir l'image de ImageView en BufferedImage
        Image image = imageView.getImage();
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        // Convertir BufferedImage en tableau d'octets (byte[])
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageBytes = baos.toByteArray();
        // Créer un objet Blob à partir des données de l'image
        Blob imageBlob = connection.createBlob();
        imageBlob.setBytes(1, imageBytes);
        pstmt.setBlob(3, imageBlob); // Utilise l'objet Blob créé pour l'image
        pstmt.setInt(4, o.getidObjet()); // Remplacez o.getIdob() par la valeur réelle de l'ID de l'objet associé à la photo

        pstmt.executeUpdate();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    

