import java.util.ArrayList;

// class for Archer unit
public class Archer extends Soldier implements RangedAttacker{

    // stores projectile data
    protected int projectileSize;
    protected int projectileSpeed;
    protected Projectile newProjectile;

    // constant values for the archer unit in constructor
    public Archer(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE, Const.BLOCK_SIZE, 45, 90, 10, 75, 3, "Archer");
        this.projectileSize = 5;
        this.projectileSpeed = 15;
    }

    // overriden attack function to use projectiles instead of melee attacks
    @Override
    public boolean attack(ArrayList<Unit> gameObjects){
        Unit target = this.findTarget(gameObjects);
        if(target != null){
            if(this.attackCooldown == 0){
                this.newProjectile = new Projectile(this.teamId, this.x+this.width/2, this.y+this.height/2, this.projectileSize, target.getX()+target.getWidth()/2, target.getY()+target.getHeight()/2, this.projectileSpeed, this.damage);
            }
            this.attackCooldown = (this.attackCooldown + 1) % this.attackSpeed;
            return true;
        }
        return false;
    }
    
    // function to allow the game loop program to get the projectile
    @Override
    public Projectile getProjectile(){
        Projectile answer = this.newProjectile;
        this.newProjectile = null;
        return answer;
    }
    
}
