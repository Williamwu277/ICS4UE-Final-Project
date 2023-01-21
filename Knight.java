import java.util.ArrayList;

// class for the knight unit
public class Knight extends Soldier{

    // acceleration field for how far the knight has travelled
    private int acceleration;

    // knight constant variables
    public Knight(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE, Const.BLOCK_SIZE*2, 150, 15, 15, 75, 1, "Knight");
    }

    // override the attack method to account for the extra damage from a knights acceleration
    @Override
    public boolean attack(ArrayList<Unit> gameObjects){
        Unit target = this.findTarget(gameObjects);
        if(target != null){
            if(this.attackCooldown == 0){
                // deal extra damage from the acceleration
                target.setHealth(target.getHealth() - this.damage - (int)Math.sqrt(this.acceleration) * 2);
                this.acceleration = 0;
            }
            this.attackCooldown = (this.attackCooldown + 1) % this.attackSpeed;
            return true;
        }
        return false;
    }

    // override the move function to record the distance that the knight has moved without being obstructed
    @Override
    public void move(ArrayList<Unit> gameObjects){
        int spaceAhead = this.getSpaceAhead(gameObjects);
        // if unit is allowed to move
        if(this.movementCooldown == 0){
            // if there is nothing ahead, move, and increase accleration. Otherwise set acceleration to 0
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
