import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class AnimatedThing {

    private double x;
    private double y;
    private ImageView Imview;
    private int attitude;
    private int index;
    private int duration;
    private int max_index;
    private int window_x;
    private int window_y;
    private int offset;

    public AnimatedThing (double x, double y, String fileName) throws FileNotFoundException {

        this.x = x;

        this.y = y;

        Image Im = new Image(new FileInputStream(fileName));

        Imview = new ImageView(Im);

        Imview.setViewport(new Rectangle2D(20,0,60,100));


    }


    public ImageView getImview(){return Imview;}

    public void display(GameScene scene) {

        Camera cam = scene.getcamera();

        Imview.setX(x-cam.getx());      //Changer la position de la
        Imview.setY(y-cam.gety());      //caméra décale les AnimatedThings


        Pane root = (Pane) scene.getRoot();
        root.getChildren().add(Imview);

    }



}
