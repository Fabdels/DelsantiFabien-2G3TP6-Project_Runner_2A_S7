import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.FileNotFoundException;

//La classe GameScene est une classe fille de Scene, qui possède une caméra nommée cam

public class GameScene extends Scene {

    private Camera cam;
    private StaticThing Background_left = new StaticThing(0, 0,"D:\\Coding\\Java\\1 Runner\\img\\desert.png");
    private StaticThing Background_right = new StaticThing(800, 0,"D:\\Coding\\Java\\1 Runner\\img\\desert.png");
    private Hero hero = new Hero(0,250, "D:\\Coding\\Java\\1 Runner\\img\\heros.png");




    public GameScene(Parent root, double width, double height, boolean depthBuffer) throws FileNotFoundException {
        super(root, width, height, depthBuffer);

        cam = new Camera(0,0);

        //Affichage du background

        Background_left.display(this);
        Background_right.display(this);

        hero.display(this);


        /*Test de déplacement de la caméra
        (qui ici, consiste seulement en un déplacement inverse
        du background)*/

        this.setOnMouseMoved( (event)->{


            cam.moveright();
            Background_left.update(this);
            Background_right.update(this);
            System.out.println("Move camera : "+cam.toString());



        });


    }

    public Camera getcamera(){return cam;}
}
