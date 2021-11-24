import javafx.geometry.Rectangle2D;

import java.io.FileNotFoundException;

/*
La classe Heart ne sert qu'a fournir une
représentation de la vie restante du héros au joueur.
*/

public class Heart extends AnimatedThing{


    public Heart(double x, double y, String fileName) throws FileNotFoundException {
        super(x, y, fileName);

        Imview.setViewport(new Rectangle2D(0+offset,0,70,100));
    }

    public void Empty(){
        Imview.setViewport(new Rectangle2D(160, 0, 70, 100));
    }

    public void Fill(){
        Imview.setViewport(new Rectangle2D(0, 0, 70, 100));
    }
}
