import java.util.ArrayList;

// class for ranged, attacking, and immobile towers
public abstract class Tower extends Unit implements RangedAttacker{

    // instance variables
    protected int projectileSize;
    protected int projectileSpeed;
    protected Projectile newProjectile;

    // constructor
    public Tower(int teamId, int x, int y, int size, int maxHealth, int range, int damage, int attackSpeed, int projectileSize, int projectileSpeed, String sprite){
        super(teamId, x, y, size, size, maxHealth, sprite);
        this.range = range;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.projectileSize = projectileSize;
        this.projectileSpeed = projectileSpeed;
    }

    // update function to find targets and shoot at them
    @Override
    public void update(ArrayList<Unit> gameObjects){
        Unit target = this.findTarget(gameObjects);
        // if a target is in range
        if(target != null){
            // shoot a projectile at it
            if(this.attackCooldown == 0){
                this.newProjectile = new Projectile(this.teamId, this.x+this.width/2, this.y+this.height/2, this.projectileSize, target.getX()+target.getWidth()/2, target.getY()+target.getHeight()/2, this.projectileSpeed, this.damage);
            }
            this.attackCooldown = (this.attackCooldown + 1) % this.attackSpeed;
        }
    }

    // return the projectile that the tower has shot
    @Override
    public Projectile getProjectile(){
        Projectile answer = this.newProjectile;
        this.newProjectile = null;
        return answer;
    }
    
}
