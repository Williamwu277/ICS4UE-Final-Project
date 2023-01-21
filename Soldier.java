import java.util.ArrayList;

// class for template of moving units
public abstract class Soldier extends Unit{

    // units have the ability to move
    protected int movementSpeed;
    protected int movementCooldown;

    // constructor
    public Soldier(int teamId, int x, int y, int width, int height, int maxHealth, int range, int damage, int attackSpeed, int movementSpeed, String sprite){
        super(teamId, x, y, width, height, maxHealth, sprite);
        this.range = range;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.movementSpeed = movementSpeed;
    }

    // if the unit is attacking, move, and vice versa
    @Override
    public void update(ArrayList<Unit> gameObjects){
        if(!this.attack(gameObjects)){
            this.move(gameObjects);
        }
    }

    // method to send an attack to an enemy if there is one that is close enough
    public boolean attack(ArrayList<Unit> gameObjects){
        Unit target = this.findTarget(gameObjects);
        // if there is a target and cooldown is over
        if(target != null){
            if(this.attackCooldown == 0){
                // send an attack
                target.setHealth(target.getHealth() - this.damage);
            }
            this.attackCooldown = (this.attackCooldown + 1) % this.attackSpeed;
            return true;
        }
        return false;
    }

    // method for unit to move
    public void move(ArrayList<Unit> gameObjects){
        // if teammate isn't blocking the path
        int spaceAhead = this.getSpaceAhead(gameObjects);
        // move forwards
        if(this.movementCooldown == 0){
            this.y += this.teamId * Math.min(spaceAhead, Const.MOVEMENT_STEP_SIZE);
            this.hitBox.setLocation(this.x, this.y);
        }
        this.movementCooldown = (this.movementCooldown + 1) % this.movementSpeed;
    }
    
}
