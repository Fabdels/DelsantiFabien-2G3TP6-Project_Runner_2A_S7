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
    private int index=0;
    private int duration;
    private int max_index;
    private int window_x;
    private int window_y;
    private int offset;
    private double speed_x;
    private double speed_y;

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



    public void jump()
    {
        //Le saut a lieu si le personnage est en train de courir
        if (attitude==0) {

            speed_y = -5.5;
            Imview.setViewport(new Rectangle2D(0, 165, 79.5, 100));
            attitude = 1;
        }
        else{
        }

    }


    public void update(long time){


        //Gestion des vitesses, composante verticale d'abord :

        y +=speed_y;



        //Si le personnage est en plein saut
        if (attitude==1) {

            //S'il en train de monter (vitesse verticale négative)
            if (speed_y<0) {

                index=0;

            }

            //S'il est en train de descendre
            else{
                index=1;
            }
            Imview.setViewport(new Rectangle2D(85*index,162,79.5,100));

            if (y <= 245) {
                speed_y = speed_y + 0.08;
            } else {

                speed_y = 0;
                attitude=0;
            }
        }


        //Gestion de la vitesse horizontale du personnage

        speed_x = 3-(x/600);



        if (x<600) {
            x +=speed_x;

        }


        if (attitude==0) {
            int test = (int) (time / 120000000);
            index = test % 6;

            Imview.setViewport(new Rectangle2D(85 * index, 0, 79.5, 100));
        }

        Imview.setX(x);       //Changer la position de la
        Imview.setY(y);      //caméra ne décale pas les AnimatedThings

    }




}
