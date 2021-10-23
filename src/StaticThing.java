import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StaticThing {

    private double x;
    private double y;
    ImageView Imview;

    public StaticThing(double x, double y, String fileName) throws FileNotFoundException {

        this.x = x;
        this.y = y;

        Image Im = new Image(new FileInputStream(fileName));
        Imview = new ImageView(Im);
        Imview.setX(x);
        Imview.setY(y);

    }

    public void display(Pane root) {

        root.getChildren().add(Imview);


        // Test Github update
    }
}
