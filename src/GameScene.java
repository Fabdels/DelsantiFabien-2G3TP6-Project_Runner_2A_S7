import javafx.scene.Parent;
import javafx.scene.Scene;

//La classe GameScene est une classe fille de Scene, qui possède une caméra nommée cam

public class GameScene extends Scene {

    private Camera cam;

    public GameScene(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
        cam = new Camera(400,0);
    }

    public Camera getcamera(){return cam;}
}
