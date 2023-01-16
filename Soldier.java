import java.util.*;

public abstract class Soldier extends Unit{

    protected int movementSpeed;
    protected int movementCooldown;

    public Soldier(int teamId, int x, int y, int width, int height, int maxHealth, int range, int damage, int attackSpeed, int movementSpeed){
        super(teamId, x, y, width, height, maxHealth);
        this.range = range;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void update(ArrayList<Unit> gameObjects){
        if(!this.attack(gameObjects)){
            this.move(gameObjects);
        }
    }

    public boolean attack(ArrayList<Unit> gameObjects){
        Unit target = this.findTarget(gameObjects);
        if(target != null){
            if(this.attackCooldown == 0){
                target.setHealth(target.getHealth() - this.damage);
            }
            this.attackCooldown = (this.attackCooldown + 1) % this.attackSpeed;
            return true;
        }
        return false;
    }

    public void move(ArrayList<Unit> gameObjects){
        int spaceAhead = this.getSpaceAhead(gameObjects);
        if(this.movementCooldown == 0){
            this.y += this.teamId * Math.min(spaceAhead, Const.MOVEMENT_STEP_SIZE);
            this.hitBox.setLocation(this.x, this.y);
        }
        this.movementCooldown = (this.movementCooldown + 1) % this.movementSpeed;
    }

    @Override
    public ArrayList<String> draw(){
        ArrayList<String> output = new ArrayList<String>();
        output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " PINK");
        return output;
    }
    
}
