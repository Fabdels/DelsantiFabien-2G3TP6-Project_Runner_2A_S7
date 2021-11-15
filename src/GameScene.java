import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.lang.Math.random;

//La classe GameScene est une classe fille de Scene, qui possède une caméra nommée cam

public class GameScene extends Scene {

    private Camera cam;
    private StaticThing Background_left = new StaticThing(0, 0,"\\desert.png");
    private StaticThing Background_mid = new StaticThing(800, 0,"\\desert.png");
    private StaticThing Background_right = new StaticThing(1600, 0,"\\desert.png");
    private Hero hero = new Hero(200,245, "\\heros.png");
    private ArrayList<Foe> List_Foes;

    public static void update(long time){}



    public GameScene(Parent root, double width, double height, boolean depthBuffer) throws FileNotFoundException {
        super(root, width, height, depthBuffer);


        List_Foes = new ArrayList<Foe>();

        for (int x = 0; x < 100; x++) {
            Foe foe = new Foe((1000-5*x) * (x + 1) + (900-5*x) * random()+1200, 245, "\\ennemi.png");
            List_Foes.add(foe);
        }


        timer.start();

        cam = new Camera(0, 0);

        //Affichage du background

        Background_left.display(this);
        Background_mid.display(this);
        Background_right.display(this);
        hero.display(this);
        for (int x = 0; x < List_Foes.size(); x++) {
            Foe foe = null;
            foe = (Foe) List_Foes.get(x);
            foe.display(this);
        }


        this.setOnMouseClicked( (event)->{
            hero.jump();
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

    AnimationTimer timer = new AnimationTimer() {

        private long lastUpdate = System.nanoTime();
        private long elapsed_nano_second;

        public void handle(long time) {


            elapsed_nano_second=time-lastUpdate;




            cam.update(elapsed_nano_second);
            cam.moveright();
            hero.update(elapsed_nano_second);
            for (int x = 0; x < List_Foes.size(); x++) {
                Foe foe = null;
                foe = (Foe) List_Foes.get(x);
                foe.update(elapsed_nano_second);



                Rectangle2D foe_hitbox = foe.getHitBox();
                Rectangle2D hero_hitbox = hero.getHitBox();



                if (hero_hitbox.intersects(foe_hitbox)) {
                    timer.stop();
                }
                Background_left.update(cam);
                Background_mid.update(cam);
                Background_right.update(cam);

                GameScene.update(time);

            }
        }
    };

    public Camera getcamera(){return cam;}
}
