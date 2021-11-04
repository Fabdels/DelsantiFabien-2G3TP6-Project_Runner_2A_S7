import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.FileNotFoundException;

//La classe GameScene est une classe fille de Scene, qui possède une caméra nommée cam

public class GameScene extends Scene {

    private Camera cam;
    private StaticThing Background_left = new StaticThing(0, 0,"D:\\Coding\\Java\\1 Runner\\img\\desert.png");
    private StaticThing Background_mid = new StaticThing(800, 0,"D:\\Coding\\Java\\1 Runner\\img\\desert.png");
    private StaticThing Background_right = new StaticThing(1600, 0,"D:\\Coding\\Java\\1 Runner\\img\\desert.png");
    private Hero hero = new Hero(200,245, "D:\\Coding\\Java\\1 Runner\\img\\heros.png");

    public static void update(long time){}


    AnimationTimer timer = new AnimationTimer() {
        public void handle(long time) {
            cam.update(time);
            cam.moveright();
            hero.update(time);
            Background_left.update(cam);
            Background_mid.update(cam);
            Background_right.update(cam);

            GameScene.update(time);

        }
    };


    public GameScene(Parent root, double width, double height, boolean depthBuffer) throws FileNotFoundException {
        super(root, width, height, depthBuffer);

        timer.start();

        cam = new Camera(0, 0);

        //Affichage du background

        Background_left.display(this);
        Background_mid.display(this);
        Background_right.display(this);
        hero.display(this);


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

    public Camera getcamera(){return cam;}
}
