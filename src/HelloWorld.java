import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;

import static javafx.application.Application.launch;

public class HelloWorld extends Application {

        @Override
         public void start(Stage primaryStage) throws Exception{

             primaryStage.setTitle("Ohayo Sekai Good morning world !");
             Group root = new Group();
             Pane pane = new Pane(root);
             Scene theScene = new Scene(pane, 800, 400,true);
             primaryStage.setScene(theScene);




            primaryStage.show();

        }


    public static void main(String[] args) {
        launch(args);
    }


 }


