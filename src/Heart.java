import javafx.geometry.Rectangle2D;

import java.io.FileNotFoundException;

public class Heart extends AnimatedThing{


    public Heart(double x, double y, String fileName) throws FileNotFoundException {
        super(x, y, fileName);

        Imview.setViewport(new Rectangle2D(0+offset,0,80,100));
    }

    public void Empty(){

        Imview.setViewport(new Rectangle2D(160, 0, 80, 100));
    }
}
