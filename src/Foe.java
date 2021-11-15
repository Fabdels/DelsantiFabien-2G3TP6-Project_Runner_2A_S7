import javafx.geometry.Rectangle2D;

import java.io.FileNotFoundException;

import static java.lang.Math.random;

public class Foe extends AnimatedThing{
    private int speed_set=0;
    public Foe(double x, double y, String fileName) throws FileNotFoundException {
        super(x, y, fileName);
        speed_x = -4;
    }


    public void update(long time){

        //Gestion de la vitesse horizontale de l'ennemi
        //Les ennemis vont de plus en plus vite
        //d'où la présence de speed_up


        if (x<1700 & x>1650 & speed_set==0) {
            double speed_up = (double) (time / 2000000000);
            speed_up=speed_up/3;
            speed_x= speed_x-speed_up;
            speed_set=1;

        }






        if (x>-130) {
            x +=speed_x;

        }
        else {
            speed_x=0;
        }

        int test = (int) (time / 120000000);
        index = test % max_index;


        Imview.setViewport(new Rectangle2D(520-(85 * index+offset), 0, 79.5, 100));


        Imview.setX(x);       //Changer la position de la
        Imview.setY(y);      //caméra ne décale pas les AnimatedThings




    }
}
