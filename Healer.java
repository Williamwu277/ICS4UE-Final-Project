import java.util.ArrayList;
import java.awt.Rectangle;

// class for the healer unit
public class Healer extends Soldier{

    // healer passive is to heal all units in its perimeter
    private static final int HEALER_PASSIVE_CHANCE = 15;

    // healer constants
    public Healer(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE, Const.BLOCK_SIZE, 75, 60, 10, 75, 3, "Healer");
    }

    // override the attack method to heal allied units instead of damaging enemy units
    @Override
    public boolean attack(ArrayList<Unit> gameObjects){
        // find allied unit to heal directly
        Unit target = this.findTarget(gameObjects);
        if(target != null){
            if(this.attackCooldown == 0){
                // heal
                target.setHealth(Math.min(target.getMaxHealth(), target.getHealth() + this.damage));
            }
            this.attackCooldown = (this.attackCooldown + 1) % this.attackSpeed;
        }
        return false;
    }

    // override the findTarget method to find an allied unit instead of the enemy
    @Override
    public Unit findTarget(ArrayList<Unit> gameObjects){
        // create a box representing the range of the unit
        int rangeWidth = this.width + this.range * 2;
        int rangeHeight = this.height + this.range * 2;
        int rangeX = this.x + this.width/2 - rangeWidth / 2;
        int rangeY = this.y + this.height/2 - rangeHeight / 2;
        Rectangle rangeBox = new Rectangle(rangeX, rangeY, rangeWidth, rangeHeight);
        // loop through all units and check if they are in the range of the unit
        Unit target = null;
        int distance = Integer.MAX_VALUE;
        for(Unit nextUnit: gameObjects){
            int nextDistance = (int)(Math.sqrt(Math.pow(this.x+this.width/2-nextUnit.getX()-nextUnit.getWidth(), 2) + Math.pow(this.y+this.height/2-nextUnit.getY()-nextUnit.getHeight(), 2)));
            // if allied unit and in distance, and closest allied unit, set it as the target
            if(nextUnit != this && nextUnit.getId() == this.teamId && rangeBox.intersects(nextUnit.getHitBox())){
                // check if the passive is triggered by chance
                if((int)(Math.random() * HEALER_PASSIVE_CHANCE) == 0){
                    nextUnit.setHealth(Math.min(nextUnit.getMaxHealth(), nextUnit.getHealth() + 1));
                }
                if(nextDistance < distance){
                    distance = nextDistance;
                    target = nextUnit;
                }
            }
        }
        return target;
    }

    // override the method to check for enemy units ahead of this unit
    @Override
    public int getSpaceAhead(ArrayList<Unit> gameObjects){
        int spaceAhead = Integer.MAX_VALUE;
        // loop through all units and if they are not in the same team, check for the closest distance of an enemy ahead of this unit
        for(Unit nextUnit: gameObjects){
            if(nextUnit != this && !(nextUnit.getX() >= this.x + this.width || nextUnit.getX() + nextUnit.getWidth() <= this.x)){
                if(teamId == 1 && nextUnit.getY() > this.y){
                    spaceAhead = Math.min(spaceAhead, nextUnit.getY() - this.y - this.height);
                }else if(teamId == -1 && nextUnit.getY() < this.y){
                    spaceAhead = Math.min(spaceAhead, this.y - nextUnit.getY() - nextUnit.getHeight());
                }
            }
        }
        return spaceAhead;
    }
    
}
