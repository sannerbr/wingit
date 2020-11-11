package learn.wingit.models;

import java.awt.*;

public class PlaneImage {

    public PlaneImage() {

    }

    public PlaneImage(int image_id, int plane_id, Image image) {
        this.image_id = image_id;
        this.plane_id = plane_id;
        this.image = image;
    }

    private int image_id;
    private int plane_id;
    private Image image;

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public int getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(int plane_id) {
        this.plane_id = plane_id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
