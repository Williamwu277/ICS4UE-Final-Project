import java.awt.Rectangle;
import java.util.ArrayList;

// template base class for an object in the game — projectiles and units
public abstract class GameObject {

    // instance variables
    protected int teamId;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Rectangle hitBox;
    protected String sprite;

    // constructor
    public GameObject(int teamId, int x, int y, int width, int height, String sprite){
        this.teamId = teamId;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitBox = new Rectangle(x, y, this.width, this.height);
        this.sprite = sprite;
    }

    // abstract methods for updating and drawing
    public abstract void update(ArrayList<Unit> gameObjects);

    public abstract ArrayList<String> draw();

    // getters and setters
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
