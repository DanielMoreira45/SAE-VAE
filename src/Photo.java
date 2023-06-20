import javafx.scene.image.ImageView;

public class Photo {
    private Integer idph;
    private String titreph;
    private ImageView imageView;

    public Photo(Integer idph, String titreph, ImageView imageView) {
        this.idph = idph;
        this.titreph = titreph;
        this.imageView = imageView;
    }

    public Integer getIdph() {
        return idph;
    }

    public void setIdph(Integer idph) {
        this.idph = idph;
    }

    public String getTitreph() {
        return titreph;
    }

    public void setTitreph(String titreph) {
        this.titreph = titreph;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}