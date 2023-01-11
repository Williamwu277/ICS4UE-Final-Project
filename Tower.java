import java.util.*;
import java.awt.*;

public abstract class Tower extends Unit implements RangedAttacker{

    protected int projectileSize;
    protected int projectileSpeed;
    protected Projectile newProjectile;

    public Tower(int teamId, int x, int y, int size, int maxHealth, int range, int damage, int attackSpeed, int projectileSize, int projectileSpeed){
        super(teamId, x, y, size, size, maxHealth);
        this.range = range;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.projectileSize = projectileSize;
        this.projectileSpeed = projectileSpeed;
    }

    @Override
    public void update(ArrayList<Unit> gameObjects){
        Unit target = this.findTarget(gameObjects);
        if(target != null){
            if(this.attackCooldown == 0){
                this.newProjectile = new Projectile(this.teamId, this.x+this.width/2, this.y+this.height/2, this.projectileSize, target.getX()+target.getWidth()/2, target.getY()+target.getHeight()/2, this.projectileSpeed, this.damage);
            }
            this.attackCooldown = (this.attackCooldown + 1) % this.attackSpeed;
        }
    }

    @Override
    public ArrayList<String> draw(){
        ArrayList<String> output = new ArrayList<String>();
        output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " PINK");
        return output;
    }

    @Override
    public Projectile getProjectile(){
        Projectile answer = this.newProjectile;
        this.newProjectile = null;
        return answer;
    }
    
}
