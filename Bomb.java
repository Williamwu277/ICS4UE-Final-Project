import java.awt.Rectangle;
import java.util.ArrayList;

// Class for the bomb projectile
public class Bomb extends Projectile{

    // statistics for explosions
    private boolean explosion;
    private int explosionRadius;
    private int explosionDamage;

    public Bomb(int teamId, int x, int y, int size, int targetX, int targetY, int speed, int damage, int explosionRadius, int explosionDamage){
        super(teamId, x-size/2, y-size/2, size, targetX, targetY, speed, damage);
        this.explosionRadius = explosionRadius;
        this.explosionDamage = explosionDamage;
    }

    // override the projectile update method to include an explosion once enemy hit
    @Override
    public void update(ArrayList<Unit> gameObjects){
        if(!this.explosion){
            // update the bomb as a normal projectile if it hasn't exploded yet
            super.update(gameObjects);
            // if it has hit an enemy, or the travelling duration has ended, create an explosion
            if(this.duration == 0){
                this.explosion = true;
                this.duration = Const.BOMB_EXPLOSION_DURATION;
                // damage all enemies within the explosion radius
                int explosionSize = this.explosionRadius * 2;
                Rectangle explosionBox = new Rectangle(this.x - explosionSize / 2, this.y - explosionSize / 2, explosionSize, explosionSize);
                for(Unit nextUnit: gameObjects){
                    if(nextUnit.getId() != this.teamId && explosionBox.intersects(nextUnit.getHitBox())){
                        nextUnit.setHealth(nextUnit.getHealth() - this.explosionDamage);
                    }
                }
            }
        }else{
            // let the explosion animation play out
            this.duration --;
        }
    }

    // Override to include different drawings depending on the state of the bomb
    @Override
    public ArrayList<String> draw(){
        ArrayList<String> output = new ArrayList<String>();
        if(!this.explosion){
            output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " BLACK");
        }else{
            int explosionSize = this.explosionRadius * 2;
            output.add("Explosion " + (this.x - explosionSize / 2) + " " + (this.y - explosionSize / 2) + " " + explosionSize + " " + explosionSize + " Y");
        }
        return output;
    }
    
}
