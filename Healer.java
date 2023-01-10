import java.util.*;
import java.awt.*;

public class Healer extends Soldier{

    private static final int HEALER_PASSIVE_CHANCE = 15;

    public Healer(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE, Const.BLOCK_SIZE, 75, 60, 10, 75, 3);
    }

    @Override
    public boolean attack(ArrayList<Unit> gameObjects){
        Unit target = this.findTarget(gameObjects);
        if(target != null){
            if(this.attackCooldown == 0){
                target.setHealth(Math.min(target.getMaxHealth(), target.getHealth() + this.damage));
            }
            this.attackCooldown = (this.attackCooldown + 1) % this.attackSpeed;
        }
        return false;
    }

    @Override
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
            if(nextUnit != this && nextUnit.getId() == this.teamId && rangeBox.intersects(nextUnit.getHitBox())){
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

    @Override
    public int getSpaceAhead(ArrayList<Unit> gameObjects){
        int spaceAhead = Integer.MAX_VALUE;
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
