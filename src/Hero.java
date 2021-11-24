import javafx.geometry.Rectangle2D;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.lang.Math.random;


//La classe Hero est notre personnage contrôlable


public class Hero extends AnimatedThing{

    private boolean double_jump=true;

    private int life=4;


    private boolean free=false;

    private double invincibility;
    private double invincibility_time;

    private float acceleration_direction=0;

    private double speed_x_variation=0;

    private double anim_speed_up;
    private double new_anim_speed_up;

    public Hero(double x, double y, String fileName) throws FileNotFoundException {
        super(x, y, fileName);

        speed_x=0;

    }

    /*
    La fonction set_acceleration permet le contrôle de la vitesse horizontale du héros.
    J'ai fait en sorte que les variations de vitesse soient progressives.
    La variable free permet de revenir à une vitesse nulle lorsque le joeur relâche
    les touches directionnelles.
    */
    public void set_acceleration(float acc){

        free=false;

        if (acc==-2 | acc==2){


            if ((acc==-2 & acceleration_direction==-1) |(acc==2 & acceleration_direction==1)) {

                speed_x_variation=0;

                free=true;

                acceleration_direction = 0;
            }
        }
        else {
            acceleration_direction = acc;
        }

    }


    public void jump()
    {
        /*
        Le saut a lieu si le personnage est en train de courir ou s'il
        a encore son double_jump de disponible.
        */

        if (attitude==0  || double_jump) {


            /*
            Je mets ici l'anim_speed_up à jour. C'est la variable qui
            provoque l'accélération de l'animation du héros.
            Je le fais pendant un saut pour éviter qu'il y ait des décalages dans
            l'animation.
             */

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



        //Si le personnage est en plein saut :

        if (attitude==1) {

            //S'il en train de monter (vitesse verticale négative) :

            if (speed_y<0) {

                index=0;

            }



            //S'il est en train de descendre :

            else{
                index=1;
            }

            //On affiche l'image correspondante :

            Imview.setViewport(new Rectangle2D(85*index+offset,162,79.5,100));






            //S'il n'a pas encore atteint le sol :
            if (y <= 245) {
                speed_y = speed_y + 0.15+0.5*anim_speed_up;
            }

            //S'il l'a atteint :
            else {

                speed_y = 0;
                attitude=0;
                y = 245;
            }
        }



        //Gestion de la vitesse horizontale du personnage


        x +=speed_x;


        /*
        Si le perso est au bord gauche de l'écran
        et que le joueur essaye d'aller à gauche,
        rien ne se passe
         */

        if (x<20 & (speed_x<0|acceleration_direction<0)){

            speed_x=0;
            speed_x_variation=0;
            acceleration_direction=0;

        }

        /*
        De même si le perso est au bord droit de l'écran
        et que le joueur essaye d'aller à droit,
        rien ne se passe

        Pourquoi aller se coller à droite de toute façon ?
        Il devient impossible de voir les ennemis arriver !
        */

        if (x>1400 & (speed_x>0|acceleration_direction>0)){

            speed_x=0;
            speed_x_variation=0;
            acceleration_direction=0;
        }



        /*
        La condition if qui suit empêche la variable speed_x_variation de diverger. (verger)

        Il faut vérifier la valeur de acceleration_direction.

        En effet, si celle-ci est positive, c'est que le joueur demande à aller à droite,
        et dans ce cas, on majore speed_x_variation pour éviter qu'elle tende vers + infini

        Si elle est négative, c'est que le joueur demande à aller à gauche,
        et dans ce cas, on minore speed_x_variation pour éviter qu'elle tende vers - infini
        */

        if ((speed_x_variation<2 & acceleration_direction>0)|(-2<speed_x_variation & acceleration_direction<0)) {


            speed_x_variation = speed_x_variation + 0.1*acceleration_direction;

            /*
            Il faut également imposer une vitesse speed_x maximale et minimale,
            ici, 2 et -1.
            */

            if (speed_x_variation > 0 & speed_x < 2) {
                speed_x += speed_x_variation;
            }
            if (speed_x_variation < 0 & -1 < speed_x) {
                speed_x += speed_x_variation;
            }
        }

        /*
         Si free==true, c'est que les 2 touches directionnelles gauche et droite
         du clavier ne sont pas enfoncées. Dans ce cas, le héros revient progressivement à une vitesse nulle.
         */


        if (free)
        {
            speed_x-=speed_x/10;

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





        /*

            L'invincibilité s'accompagne d'un clignotement
            du héros pour que le joeur sache :

            -Qu'il a été touché (s'il ne l'a pas déjà remarqué)

            -Son état (dès que le clignotement s'arrête, il n'est plus invincible)
         */


        if (invincibility_time>=0) {

            invincibility_time = invincibility - time / 100000;


            int test_2 = (int) (time / (50000000 / (anim_speed_up + 0.5)));


            int blip = test_2 % 3;

            if (blip==1){

                Imview.setViewport(new Rectangle2D(250, 162, 79.5, 100));

            }


        }


    }

    public Rectangle2D getHitBox(){
        return new Rectangle2D(x+10, y, 40, 70);
    }

    public int hit(long time){

        life--;
        invincibility=25000+time/100000;
        invincibility_time=0;
        return life;


    }

    public int lifeup(){

        if (life<4) {
            life++;
        }
        return life;
    }

    public int isInvincible(long time){



        if (invincibility_time<0)
        {
            return 0;

        }
        else{
            return 1;
        }


    }
}
