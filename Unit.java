import java.awt.Rectangle;
import java.util.ArrayList;

// class unit template for units with lives
public abstract class Unit extends GameObject{

    // instance variables
    protected int range;
    protected int damage;
    protected int attackSpeed;
    protected int attackCooldown;
    protected HealthBar healthBar;

    // constructor
    public Unit(int teamId, int x, int y, int width, int height, int maxHealth, String sprite){
        super(teamId, x, y, width, height, sprite);
        this.healthBar = new HealthBar(this.width, Const.HEALTH_BAR_HEIGHT, maxHealth);
    }

    // function to find nearest enemy target
    public Unit findTarget(ArrayList<Unit> gameObjects){
        // find the range of this unit
        int rangeWidth = this.width + this.range * 2;
        int rangeHeight = this.height + this.range * 2;
        int rangeX = this.x + this.width/2 - rangeWidth / 2;
        int rangeY = this.y + this.height/2 - rangeHeight / 2;
        Rectangle rangeBox = new Rectangle(rangeX, rangeY, rangeWidth, rangeHeight);
        // loop through all units to consider them
        Unit target = null;
        int distance = Integer.MAX_VALUE;
        for(Unit nextUnit: gameObjects){
            // consider if the enemy unit is legal to target
            int nextDistance = (int)(Math.sqrt(Math.pow(this.x+this.width/2-nextUnit.getX()-nextUnit.getWidth(), 2) + Math.pow(this.y+this.height/2-nextUnit.getY()-nextUnit.getHeight(), 2)));
            if(nextUnit.getId() != this.teamId && nextDistance < distance && rangeBox.intersects(nextUnit.getHitBox())){
                distance = nextDistance;
                target = nextUnit;
            }
        }
        return target;
    }

    // check how much space is between this unit and the teammate ahead of it
    public int getSpaceAhead(ArrayList<Unit> gameObjects){
        int spaceAhead = Integer.MAX_VALUE;
        // loop through all units to consider
        for(Unit nextUnit: gameObjects){
            // check if unit in same team is ahead, and how much further away it is
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

    // getters and setters
    public int getHealth(){
        return this.healthBar.getHealth();
    }

    public int getMaxHealth(){
        return this.healthBar.getMaxHealth();
    }

    public void setHealth(int health){
        this.healthBar.setHealth(health);
    }

    // send instructions for how to draw the health bar to the client
    public ArrayList<String> drawHealthBar(){
        ArrayList<String> output = this.healthBar.draw(this.x, this.y);
        return output;
    }

    // send instructions for how to draw this unit to the client
    @Override
    public ArrayList<String> draw(){
        ArrayList<String> output = new ArrayList<String>();
        // if there is an image be to drawn, draw it
        if(this.sprite != null){
            String rotate;
            // check if image has to be rotated to match its team
            if(this.teamId == -1 && !(this instanceof SiegeEngine) && !(this instanceof Dwelling) && !(this instanceof BombTower) && !(this instanceof Base)){
                rotate = "N";
            }else{
                rotate = "Y";
            }
            output.add(this.sprite + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " " + rotate);
        }else{
            output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " PINK");
        }
        return output;
    }
    
}
