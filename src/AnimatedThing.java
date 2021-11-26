import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/*La classe AnimatedThing est une classe abstraite dont vont hériter tous
les éléments animés et/ou mobiles du jeu
*/


public abstract class AnimatedThing {

    protected double x;
    protected double y;
    protected ImageView Imview;
    protected int attitude;
    protected int index=0;
    protected int duration;
    protected int max_index=6;
    protected int size;
    protected int offset=0;
    protected double speed_x;
    protected double speed_y;

    public AnimatedThing (double x, double y, String fileName) throws FileNotFoundException {

        this.x = x;

        this.y = y;

        Image Im = new Image(fileName);

        Imview = new ImageView(Im);

        Imview.setViewport(new Rectangle2D(0+offset,0,60,100));


    }


    public ImageView getImview(){return Imview;}

    public void display(GameScene scene) {

        Camera cam = scene.getcamera();

        Imview.setX(x-cam.getx());      //Changer la position de la
        Imview.setY(y-cam.gety());      //caméra décale les AnimatedThings


        Pane root = (Pane) scene.getRoot();
        root.getChildren().add(Imview);

    }


    public double get_x(){

        return x;
    }

    public void update(long time){}

    public Rectangle2D getHitBox(){
        return new Rectangle2D(x, y, 40, 70);
    }



}
