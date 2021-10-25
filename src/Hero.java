import java.io.FileNotFoundException;

public class Hero extends AnimatedThing{
    public Hero(double x, double y, String fileName) throws FileNotFoundException {
        super(x, y, fileName);
    }
}
