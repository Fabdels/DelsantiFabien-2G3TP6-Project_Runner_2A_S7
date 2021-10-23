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

        primaryStage.setTitle("Runner");
        Group root = new Group();
        Pane pane = new Pane(root);
        GameScene theScene = new GameScene(pane, 1600, 400,true);
        primaryStage.setScene(theScene);





        StaticThing Background_left = new StaticThing(0, 0,"D:\\Coding\\Java\\1 Runner\\img\\desert.png");
        StaticThing Background_right = new StaticThing(800, 0,"D:\\Coding\\Java\\1 Runner\\img\\desert.png");

        Background_left.display(pane);
        Background_right.display(pane);

        StaticThing cam_im = new StaticThing(200, 0,"D:\\Coding\\Java\\1 Runner\\img\\cam.png");
        cam_im.display(pane);


        Image spriteSheet = new Image(new FileInputStream("D:\\Coding\\Java\\1 Runner\\img\\heros.png"));
        ImageView sprite = new ImageView(spriteSheet);
        sprite.setViewport(new Rectangle2D(20,0,60,100));
        sprite.setX(250);
        sprite.setY(250);
        pane.getChildren().add(sprite);

        Camera camera = theScene.getcamera();
        Image rectangle = new Image(new FileInputStream("D:\\Coding\\Java\\1 Runner\\img\\Red_rectangle.png"));
        ImageView rect = new ImageView(rectangle);
        rect.setViewport(new Rectangle2D(0,0,1000,1000));
        rect.setX(camera.getx());
        rect.setY(camera.gety());
        pane.getChildren().add(rect);

        primaryStage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }


}

