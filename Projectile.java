import java.util.*;

public class Projectile extends GameObject{

    protected int rise;
    protected int run;
    protected int duration;
    protected int damage;
    protected int movementCooldown;

    public Projectile(int teamId, int x, int y, int size, int targetX, int targetY, int speed, int damage, String sprite){
        super(teamId, x-size/2, y-size/2, size, size, sprite);
        int xMovement = targetX - x;
        int yMovement = targetY - y;
        int distance = (int)Math.sqrt(Math.pow(xMovement, 2) + Math.pow(yMovement, 2));
        double scale = (double)speed / distance;
        this.rise = (int)Math.round(scale * yMovement);
        this.run = (int)Math.round(scale * xMovement);
        this.duration = (int)(1 / scale) + 1;
        this.damage = damage;
    }

    public Projectile(int teamId, int x, int y, int size, int targetX, int targetY, int speed, int damage){
        this(teamId, x, y, size, targetX, targetY, speed, damage, null);
    }

    @Override
    public void update(ArrayList<Unit> gameObjects){
        for(Unit nextUnit: gameObjects){
            if(nextUnit.getId() != this.teamId && nextUnit.getHitBox().intersects(this.hitBox)){
                nextUnit.setHealth(nextUnit.getHealth() - this.damage);
                this.duration = 0;
                break;
            }
        }
        if(this.movementCooldown == 0){
            this.setX(this.x + run);
            this.setY(this.y + rise);
            this.duration --;
        }
        this.movementCooldown = (this.movementCooldown + 1) % Const.PROJECTILE_STEP_COOLDOWN;
    }

    @Override
    public ArrayList<String> draw(){
        ArrayList<String> output = new ArrayList<String>();
        output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " BLACK");
        return output;
    }

    public boolean hasHit(){
        return this.duration <= 0;
    }
    
}
