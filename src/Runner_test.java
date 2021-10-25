import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;

import static javafx.application.Application.launch;

public class Runner_test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        //Création de la fenêtre

        primaryStage.setTitle("Runner");
        Group root = new Group();
        Pane pane = new Pane(root);
        GameScene theScene = new GameScene(pane, 800, 400,true);
        primaryStage.setScene(theScene);



        //Instanciation du background

        StaticThing Background_left = new StaticThing(0, 0,"D:\\Coding\\Java\\1 Runner\\img\\desert.png");
        StaticThing Background_right = new StaticThing(800, 0,"D:\\Coding\\Java\\1 Runner\\img\\desert.png");

        //Affichage du background

        Background_left.display(theScene);
        Background_right.display(theScene);




        //Affichage du héros (mis en commentaire avant de passer à AnimatedThing et Hero)

        /*  Image spriteSheet = new Image(new FileInputStream("D:\\Coding\\Java\\1 Runner\\img\\heros.png"));
        ImageView sprite = new ImageView(spriteSheet);
        sprite.setViewport(new Rectangle2D(20,0,60,100));
        sprite.setX(250);
        sprite.setY(250);
        pane.getChildren().add(sprite);
        */

        //Récupération de la caméra associée à theScene

        Camera camera = theScene.getcamera();


        //Affichage du primaryStage

        primaryStage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }


}

