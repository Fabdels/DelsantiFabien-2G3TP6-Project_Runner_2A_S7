import javafx.geometry.Rectangle2D;

import java.io.FileNotFoundException;

import static java.lang.Math.random;

public class Bonus extends AnimatedThing{
    private int speed_set=0;
    public Bonus(double x, double y, String fileName) throws FileNotFoundException {
        super(x, y, fileName);
        speed_x = -4;
    }


    public void update(long time){

        //Gestion de la vitesse horizontale du bonus


        if (x<1700 & x>1650 & speed_set==0) {
            double speed_up = (double) (time / 2000000000);
            speed_up=speed_up/8;
            speed_x= speed_x-speed_up;
            speed_set=1;

        }






        if (x>-130) {
            x +=speed_x;

        }
        else {
            speed_x=0;
        }



        Imview.setX(x);       //Changer la position de la
        Imview.setY(y);      //caméra ne décale pas les AnimatedThings




    }

    public void disappear(){

        x=-50;
        Imview.setX(x);

    }

    public Rectangle2D getHitBox(){
        return new Rectangle2D(x, y, 40, 40);
    }
}

