//La classe caméra n'est pour le moment composé que de deux coordonnées

public class Camera {

    private int x=0;
    private int y=0;

    public Camera(int x, int y){

        this.x=x;
        this.y=y;

    }
    public int getx(){return x;}

    public int gety(){
        return y;
    }


    @Override
    public String toString(){
        return "x : "+x+", y : "+y;
    }

    public void moveright(){
        x=(x+2)%800;
    }

    public void update(long time){}

}
