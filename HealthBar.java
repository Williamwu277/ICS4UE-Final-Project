import java.awt.*;

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

    public void draw(Graphics g, int x, int y){
        g.setColor(Color.RED);
        g.fillRect(x, y-this.height, this.width, this.height);
        g.setColor(Color.GREEN);
        g.fillRect(x, y-this.height, (int)(this.width * (double)this.currentHealth / this.maxHealth), this.height);
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
