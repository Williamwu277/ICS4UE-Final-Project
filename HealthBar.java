import java.util.ArrayList;

// class to store the health of a unit
public class HealthBar {

    // instance variables
    private int width;
    private int height;
    private int currentHealth;
    private int maxHealth;

    // constructor
    public HealthBar(int width, int height, int maxHealth){
        this.width = width;
        this.height = height;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    // return the instructions for the client to draw the healthbar
    public ArrayList<String> draw(int x, int y){
        ArrayList<String> output = new ArrayList<String>();
        output.add(Const.BOX_CODE + " " + x + " " + (y-this.height) + " " + this.width + " " + this.height + " RED");
        output.add(Const.BOX_CODE + " " + x + " " + (y-this.height) + " " + ((int)(this.width * (double)this.currentHealth / this.maxHealth))+ " " + this.height + " GREEN");
        return output;
    }

    // getters and setters
    public void setHealth(int health){
        this.currentHealth = health;
    }

    public int getHealth(){
        return this.currentHealth;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }
    
}
