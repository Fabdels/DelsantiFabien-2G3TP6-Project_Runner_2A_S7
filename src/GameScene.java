import javafx.scene.Parent;
import javafx.scene.Scene;

public class GameScene extends Scene {

    private Camera cam;

    public GameScene(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
        cam = new Camera(15,90);
    }

    public Camera getcamera(){return cam;}
}
