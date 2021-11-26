import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.lang.Math.random;

//La classe GameScene est une classe fille de Scene et possède une caméra nommée cam

public class GameScene extends Scene {

    private Camera cam;
    private StaticThing Background_left = new StaticThing(0, 0,"\\desert.png");
    private StaticThing Background_mid = new StaticThing(800, 0,"\\desert.png");
    private StaticThing Background_right = new StaticThing(1600, 0,"\\desert.png");

    private StaticThing Win_message = new StaticThing(3000, 400,"\\you_win.png");
    private StaticThing Lost_message = new StaticThing(3000, 400,"\\game_over.png");

    private Hero hero = new Hero(200,245, "\\heros.png");
    private ArrayList<Foe> List_Foes;

    private ArrayList<Bonus> List_Bonuses;

    private ArrayList<Heart> List_Hearts;

    public static void update(long time){}



    public GameScene(Parent root, double width, double height, boolean depthBuffer) throws FileNotFoundException {
        super(root, width, height, depthBuffer);


        //Instanciation de la caméra:

        cam = new Camera(0, 0);


        //Création des listes d'ennemis et de bonus :


        List_Foes = new ArrayList<Foe>();

        for (int x = 0; x < 80; x++) {
            Foe foe = new Foe((1000-4*x) * (x + 1) + (900-4*x) * random()+1200, 245, "\\ennemi.png");
            List_Foes.add(foe);
        }

        for (int x = 0; x < 90; x++) {
            Eagle eagle = new Eagle((1200-5*x) * (x + 1) + (1000-5*x) * random()+1500, -30+x/10+50*random(), "\\eagle.png");
            List_Foes.add(eagle);
        }

        List_Bonuses = new ArrayList<Bonus>();

        for (int x = 0; x < 10; x++) {
            Bonus bonus = new Bonus(7000* (x + 1) + 5000 * random()+5000, 200*random()+40, "\\bonus_heart.png");
            List_Bonuses.add(bonus);
        }

        List_Hearts = new ArrayList<Heart>();

        for (int i = 0; i < 4; i++) {
            Heart heart = new Heart(70*i, 0, "\\heart.png");
            List_Hearts.add(heart);
        }






    //Lancement du timer

        timer.start();












        //Affichage du background et des messages de victoire et de défaite

        Background_left.display(this);
        Background_mid.display(this);
        Background_right.display(this);


        Win_message.display(this);

        Lost_message.display(this);

        //Affichage des AnimatedThings :


        hero.display(this);
        for (int x = 0; x < List_Foes.size(); x++) {
            List_Foes.get(x).display(this);
        }

        for (int x = 0; x < List_Hearts.size(); x++) {
            List_Hearts.get(x).display(this);
        }

        for (int x = 0; x < List_Bonuses.size(); x++) {
            List_Bonuses.get(x).display(this);
        }


        //Gestion du saut si le joueur clique

        this.setOnMouseClicked( (event)->{
            hero.jump();
        });



        /*
        Gestion de la vitesse horizontale du personnage
        si le joeur appuie sur une des touches directionnelles (gauche ou droite)
        du clavier.
         */

        this.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.RIGHT)) {
                hero.set_acceleration(1);
            }
            if (ke.getCode().equals(KeyCode.LEFT)) {
                hero.set_acceleration(-1);
            }
        });

        this.setOnKeyReleased((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.RIGHT)) {
                hero.set_acceleration(2);
            }
            if (ke.getCode().equals(KeyCode.LEFT)) {
                hero.set_acceleration(-2);
            }
        });




        /*Test de déplacement de la caméra
        (qui ici, consiste seulement en un déplacement inverse
        du background)

        this.setOnMouseMoved( (event)->{


            cam.moveright();
            Background_left.update(cam);
            Background_mid.update(cam);
            Background_right.update(cam);

            System.out.println("Camera : "+cam.toString());



        });

*/


    }


    //All updates :

    AnimationTimer timer = new AnimationTimer() {

        private long lastUpdate = System.nanoTime();
        private long elapsed_nano_second;

        public void handle(long time) {


            elapsed_nano_second=time-lastUpdate;




            cam.update(elapsed_nano_second);
            cam.moveright();
            hero.update(elapsed_nano_second);

            /*
            Gestion des collisions avec les ennemis
             */

            for (int x = 0; x < List_Foes.size(); x++) {
                Foe foe = null;
                foe = (Foe) List_Foes.get(x);
                foe.update(elapsed_nano_second);





                if (hero.isInvincible(elapsed_nano_second)==0) {
                    Rectangle2D foe_hitbox = foe.getHitBox();
                    Rectangle2D hero_hitbox = hero.getHitBox();


                    if (hero_hitbox.intersects(foe_hitbox)) {

                        int Life =hero.hit(elapsed_nano_second);
                        List_Hearts.get(Life).Empty();
                        if (Life == 0) {
                            timer.stop();
                            Lost_message.center();
                        }
                    }

                }

                if (foe.get_x()<-120){

                    List_Foes.remove(x);

                }

                if (List_Foes.isEmpty()){

                    timer.stop();

                    Win_message.center();

                }

            }


            /*
            Gestion des collisions avec les bonus
             */

            for (int x = 0; x < List_Bonuses.size(); x++) {
                Bonus bonus = null;
                bonus = (Bonus) List_Bonuses.get(x);
                bonus.update(elapsed_nano_second);


                Rectangle2D foe_hitbox = bonus.getHitBox();
                Rectangle2D hero_hitbox = hero.getHitBox();


                if (hero_hitbox.intersects(foe_hitbox)) {

                    int Life =hero.lifeup();
                    List_Hearts.get(Life-1).Fill();
                    bonus.disappear();
                    List_Bonuses.remove(x);

                }


                if (bonus.get_x()<-120){

                    List_Bonuses.remove(x);

                }


            }

            Background_left.update(cam);
            Background_mid.update(cam);
            Background_right.update(cam);

            GameScene.update(time);
        }
    };

    public Camera getcamera(){return cam;}
}
