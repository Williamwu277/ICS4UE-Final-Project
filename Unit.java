import java.awt.*;
import java.util.*;

public abstract class Unit extends GameObject{

    protected int range;
    protected int damage;
    protected int attackSpeed;
    protected int attackCooldown;
    protected HealthBar healthBar;

    public Unit(int teamId, int x, int y, int width, int height, int maxHealth){
        super(teamId, x, y, width, height);
        this.healthBar = new HealthBar(this.width, Const.HEALTH_BAR_HEIGHT, maxHealth);
    }

    public Unit findTarget(ArrayList<Unit> gameObjects){
        int rangeWidth = this.width + this.range * 2;
        int rangeHeight = this.height + this.range * 2;
        int rangeX = this.x + this.width/2 - rangeWidth / 2;
        int rangeY = this.y + this.height/2 - rangeHeight / 2;
        Rectangle rangeBox = new Rectangle(rangeX, rangeY, rangeWidth, rangeHeight);
        Unit target = null;
        int distance = Integer.MAX_VALUE;
        for(Unit nextUnit: gameObjects){
            int nextDistance = (int)(Math.sqrt(Math.pow(this.x+this.width/2-nextUnit.getX()-nextUnit.getWidth(), 2) + Math.pow(this.y+this.height/2-nextUnit.getY()-nextUnit.getHeight(), 2)));
            if(nextUnit.getId() != this.teamId && nextDistance < distance && rangeBox.intersects(nextUnit.getHitBox())){
                distance = nextDistance;
                target = nextUnit;
            }
        }
        return target;
    }

    public int getSpaceAhead(ArrayList<Unit> gameObjects){
        int spaceAhead = Integer.MAX_VALUE;
        for(Unit nextUnit: gameObjects){
            if(nextUnit != this && nextUnit.getId() == this.teamId && !(nextUnit.getX() >= this.x + this.width || nextUnit.getX() + nextUnit.getWidth() <= this.x)){
                if(teamId == 1 && nextUnit.getY() > this.y){
                    spaceAhead = Math.min(spaceAhead, nextUnit.getY() - this.y - this.height);
                }else if(teamId == -1 && nextUnit.getY() < this.y){
                    spaceAhead = Math.min(spaceAhead, this.y - nextUnit.getY() - nextUnit.getHeight());
                }
            }
        }
        return spaceAhead;
    }

    public int getHealth(){
        return this.healthBar.getHealth();
    }

    public int getMaxHealth(){
        return this.healthBar.getMaxHealth();
    }

    public void setHealth(int health){
        this.healthBar.setHealth(health);
    }

    public void drawHealthBar(Graphics g){
        this.healthBar.draw(g, this.x, this.y);
    }
    
}
