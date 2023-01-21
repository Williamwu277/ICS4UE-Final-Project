import java.util.ArrayList;

// class for the bomb tower
public class BombTower extends Tower{

    // stores explosion metrics
    private int explosionRadius;
    private int explosionDamage;

    // bomb tower constant values
    public BombTower(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE*3, 275, 120, 10, 150, 10, 10, "BombTower");
        this.explosionRadius = 30;
        this.explosionDamage = 20;
    }

    // override the update function of a tower to use bombs instead of normal projectiles
    @Override
    public void update(ArrayList<Unit> gameObjects){
        Unit target = this.findTarget(gameObjects);
        if(target != null){
            if(this.attackCooldown == 0){
                this.newProjectile = new Bomb(this.teamId, this.x+this.width/2, this.y+this.height/2, this.projectileSize, target.getX()+target.getWidth()/2, target.getY()+target.getHeight()/2, this.projectileSpeed, this.damage, this.explosionRadius, this.explosionDamage);
            }
            this.attackCooldown = (this.attackCooldown + 1) % this.attackSpeed;
        }
    }
    
}