import java.awt.*;
import java.util.*;

public class Bomb extends Projectile{

    private boolean explosion;
    private int explosionRadius;
    private int explosionDamage;

    public Bomb(int teamId, int x, int y, int size, int targetX, int targetY, int speed, int damage, int explosionRadius, int explosionDamage){
        super(teamId, x-size/2, y-size/2, size, targetX, targetY, speed, damage);
        this.explosionRadius = explosionRadius;
        this.explosionDamage = explosionDamage;
    }

    @Override
    public void update(ArrayList<Unit> gameObjects){
        if(!this.explosion){
            super.update(gameObjects);
            if(this.duration == 0){
                this.explosion = true;
                this.duration = Const.BOMB_EXPLOSION_DURATION;
                int explosionSize = this.explosionRadius * 2;
                Rectangle explosionBox = new Rectangle(this.x - explosionSize / 2, this.y - explosionSize / 2, explosionSize, explosionSize);
                for(Unit nextUnit: gameObjects){
                    if(nextUnit.getId() != this.teamId && explosionBox.intersects(nextUnit.getHitBox())){
                        nextUnit.setHealth(nextUnit.getHealth() - this.explosionDamage);
                    }
                }
            }
        }else{
            this.duration --;
        }
    }

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
