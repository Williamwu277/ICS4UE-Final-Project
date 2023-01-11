import java.awt.*;
import java.util.*;

public class HealthBar {

    private int width;
    private int height;
    private int currentHealth;
    private int maxHealth;

    public HealthBar(int width, int height, int maxHealth){
        this.width = width;
        this.height = height;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public ArrayList<String> draw(int x, int y){
        ArrayList<String> output = new ArrayList<String>();
        output.add(Const.BOX_CODE + " " + x + " " + (y-this.height) + " " + this.width + " " + this.height + " RED");
        output.add(Const.BOX_CODE + " " + x + " " + (y-this.height) + " " + ((int)(this.width * (double)this.currentHealth / this.maxHealth))+ " " + this.height + " GREEN");
        return output;
    }

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
