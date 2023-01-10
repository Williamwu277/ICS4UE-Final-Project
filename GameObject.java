import java.awt.*;
import java.util.*;

public abstract class GameObject {

    protected int teamId;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Rectangle hitBox;
    
    public GameObject(int teamId, int x, int y, int width, int height){
        this.teamId = teamId;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitBox = new Rectangle(x, y, this.width, this.height);
    }

    public abstract void update(ArrayList<Unit> gameObjects);

    public abstract void draw(Graphics g);

    public int getId(){
        return this.teamId;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public void setX(int x){
        this.x = x;
        this.hitBox.setLocation(this.x, this.y);
    }

    public void setY(int y){
        this.y = y;
        this.hitBox.setLocation(this.x, this.y);
    }

    public Rectangle getHitBox(){
        return this.hitBox;
    }

}
