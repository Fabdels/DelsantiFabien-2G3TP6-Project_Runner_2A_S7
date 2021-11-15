//La classe caméra n'est pour le moment composé que de deux coordonnées

public class Camera {

    private double x=0;
    private double y=0;
    private double cam_speed=3;
    private double speed_up=0;

    public Camera(int x, int y){

        this.x=x;
        this.y=y;

    }
    public double getx(){return x;}

    public double gety(){
        return y;
    }


    @Override
    public String toString(){
        return "x : "+x+", y : "+y;
    }

    public void moveright(){


        x=(x+cam_speed+speed_up)%800;
    }

    public void update(long time){

        speed_up = (double) (time / 2000000000);
        speed_up=speed_up/5;
    }

}
