import java.util.ArrayList;

// class for ranged unit projectiles 
public class Projectile extends GameObject{

    // instance variables
    protected int rise;
    protected int run;
    protected int duration;
    protected int damage;
    protected int movementCooldown;

    // constructors
    public Projectile(int teamId, int x, int y, int size, int targetX, int targetY, int speed, int damage, String sprite){
        super(teamId, x-size/2, y-size/2, size, size, sprite);
        // calculate total x and y needed to travel
        int xMovement = targetX - x;
        int yMovement = targetY - y;
        // calculate distance and scale
        int distance = (int)Math.sqrt(Math.pow(xMovement, 2) + Math.pow(yMovement, 2));
        double scale = (double)speed / distance;
        // calculate the rise, run, and duration of projectile flight
        this.rise = (int)Math.round(scale * yMovement);
        this.run = (int)Math.round(scale * xMovement);
        this.duration = (int)(1 / scale) + 1;
        this.damage = damage;
    }

    public Projectile(int teamId, int x, int y, int size, int targetX, int targetY, int speed, int damage){
        this(teamId, x, y, size, targetX, targetY, speed, damage, null);
    }

    // update
    @Override
    public void update(ArrayList<Unit> gameObjects){
        // loop through all units in the game and check if the projectile has hit an enemy
        for(Unit nextUnit: gameObjects){
            if(nextUnit.getId() != this.teamId && nextUnit.getHitBox().intersects(this.hitBox)){
                // reduce their health
                nextUnit.setHealth(nextUnit.getHealth() - this.damage);
                this.duration = 0;
                break;
            }
        }
        // move the projectile
        if(this.movementCooldown == 0){
            this.setX(this.x + run);
            this.setY(this.y + rise);
            this.duration --;
        }
        this.movementCooldown = (this.movementCooldown + 1) % Const.PROJECTILE_STEP_COOLDOWN;
    }

    // return instructions for the client of how to draw projectiles
    @Override
    public ArrayList<String> draw(){
        ArrayList<String> output = new ArrayList<String>();
        output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " BLACK");
        return output;
    }

    // return whether or not the projectile has expired
    public boolean hasHit(){
        return this.duration <= 0;
    }
    
}
