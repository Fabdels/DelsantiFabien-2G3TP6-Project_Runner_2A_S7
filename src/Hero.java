import javafx.geometry.Rectangle2D;

import java.io.FileNotFoundException;

public class Hero extends AnimatedThing{

    private boolean double_jump=true;

    private double anim_speed_up;
    private double new_anim_speed_up;

    public Hero(double x, double y, String fileName) throws FileNotFoundException {
        super(x, y, fileName);
    }


    public void jump()
    {
        //Le saut a lieu si le personnage est en train de courir

        if (attitude==0  || double_jump) {



            anim_speed_up=new_anim_speed_up;

            if (attitude==1){
                speed_y = -6.5-8*anim_speed_up;
                double_jump=false;
            }

            else{
                speed_y = -6.5-8*anim_speed_up;
                double_jump=true;

            }

            Imview.setViewport(new Rectangle2D(0, 165, 79.5, 100));

            attitude = 1;

        }
        else {
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
            Imview.setViewport(new Rectangle2D(85*index+offset,162,79.5,100));

            if (y <= 245) {
                speed_y = speed_y + 0.15+0.5*anim_speed_up;
            } else {

                speed_y = 0;
                attitude=0;
                y = 245;
            }
        }


        //Gestion de la vitesse horizontale du personnage

        speed_x = 3-(x/300);



        if (x<300) {
            x +=speed_x;

        }
        else {
            speed_x=0;
        }


        if (attitude==0) {

            int test=0;

            new_anim_speed_up = (double) (time / 2000000000);
            new_anim_speed_up=new_anim_speed_up/120;


            test = (int) (time / (50000000/(anim_speed_up+0.5)));


            index = test % max_index;

            Imview.setViewport(new Rectangle2D(85 * index+offset, 0, 79.5, 100));
        }

        Imview.setX(x);       //Changer la position de la
        Imview.setY(y);      //caméra ne décale pas les AnimatedThings




    }
}
