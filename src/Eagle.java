import javafx.geometry.Rectangle2D;

import java.io.FileNotFoundException;

import static java.lang.Math.random;


//La classe Eagle est un nouvel ennemi qui hérite de Foe


public class Eagle extends Foe{
    private int speed_set=0;
    public Eagle(double x, double y, String fileName) throws FileNotFoundException {
        super(x, y, fileName);
        speed_x = -5;
        max_index=6;
    }


    @Override
    public void update(long time){

        /*
          Gestion de la vitesse horizontale de l'ennemi aigle
          Les aigles vont de plus en plus vite
          d'où la présence de speed_up
         */


        if (x<1700 & x>1650 & speed_set==0) {
            double speed_up = (double) (time / 2000000000);
            speed_up=speed_up/5;
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




        /*
          Je parcours la sprite_sheet de l'aigle en faisant des allers-retours.
         */

        if (index<4) {
            Imview.setViewport(new Rectangle2D(143 * index + offset, 0, 100, 200));
        }
        else{
            Imview.setViewport(new Rectangle2D(429-143 * (index-3) + offset, 0, 100, 200));
        }


        Imview.setX(x);       //Changer la position de la
        Imview.setY(y);      //caméra ne décale pas les AnimatedThings




    }

    public Rectangle2D getHitBox(){
        return new Rectangle2D(x, y+70, 70, 30);
    }
}

