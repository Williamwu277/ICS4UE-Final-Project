import java.util.*;

public class Knight extends Soldier{

    private int acceleration;

    public Knight(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE, Const.BLOCK_SIZE*2, 150, 15, 15, 75, 1, "Knight");
    }

    @Override
    public boolean attack(ArrayList<Unit> gameObjects){
        Unit target = this.findTarget(gameObjects);
        if(target != null){
            if(this.attackCooldown == 0){
                target.setHealth(target.getHealth() - this.damage - (int)Math.sqrt(this.acceleration) * 2);
                this.acceleration = 0;
            }
            this.attackCooldown = (this.attackCooldown + 1) % this.attackSpeed;
            return true;
        }
        return false;
    }

    @Override
    public void move(ArrayList<Unit> gameObjects){
        int spaceAhead = this.getSpaceAhead(gameObjects);
        if(this.movementCooldown == 0){
            if(spaceAhead < Const.MOVEMENT_STEP_SIZE){
                this.y += this.teamId * spaceAhead;
                this.acceleration = 0;
            }else{
                this.y += this.teamId * Const.MOVEMENT_STEP_SIZE;
                this.acceleration ++;
            }
            this.hitBox.setLocation(this.x, this.y);
        }
        this.movementCooldown = (this.movementCooldown + 1) % this.movementSpeed;
    }
    
}
