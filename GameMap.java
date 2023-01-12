import java.util.*;

public class GameMap {

    private int x;
    private int y;
    private int width;
    private int height;
    private int blockSize;
    private ArrayList<Unit> gameObjects;
    private ArrayList<Projectile> projectiles;
    private Base playerOneBase;
    private Base playerTwoBase;
    private String firstName;
    private String secondName;

    //private boolean purchasePending;
    
    public GameMap(String firstName, String secondName, int x, int y, int width, int height, int blockSize){
        this.firstName = firstName;
        this.secondName = secondName;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.blockSize = blockSize;
        this.gameObjects = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.playerOneBase = new Base(1, this.x, this.y, Const.MAP_WIDTH*Const.BLOCK_SIZE, Const.TOWER_SIZE, Const.BASE_HEALTH);
        this.playerTwoBase = new Base(-1, this.x, this.y+Const.MAP_HEIGHT*Const.BLOCK_SIZE-Const.TOWER_SIZE , Const.MAP_WIDTH*Const.BLOCK_SIZE, Const.TOWER_SIZE, Const.BASE_HEALTH);
        this.gameObjects.add(this.playerOneBase);
        this.gameObjects.add(this.playerTwoBase);
    }

    public int update(){
        for(Projectile nextProjectile: this.projectiles){
            nextProjectile.update(this.gameObjects);
        }
        for(Unit nextUnit: this.gameObjects){
            nextUnit.update(this.gameObjects);
            if(nextUnit instanceof RangedAttacker){
                Projectile projectile = ((RangedAttacker)nextUnit).getProjectile();
                if(projectile != null){
                    this.projectiles.add(projectile);
                }
            }
        }
        for(int i=this.gameObjects.size()-1; i>=0; i--){
            if(this.gameObjects.get(i).getHealth() <= 0){
                if(this.gameObjects.get(i) instanceof SiegeEngine){
                    this.gameObjects.addAll(((SiegeEngine)this.gameObjects.get(i)).onDeath());
                }
                this.gameObjects.remove(i);
            }
        }
        for(int i=this.projectiles.size()-1; i>=0; i--){
            if(this.projectiles.get(i).hasHit()){
                this.projectiles.remove(i);
            }
        }
        if(this.playerOneBase.getHealth() <= 0){
            return -1;
        }else if(this.playerTwoBase.getHealth() <= 0){
            return 1;
        }else{
            return 0;
        }
    }

    public ArrayList<String> draw(boolean purchasePending){
        ArrayList<String> output = new ArrayList<>();
        output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + (Const.MAP_WIDTH * this.blockSize) + " " + (Const.MAP_HEIGHT * this.blockSize) + " GRAY");
        if(purchasePending){
            for(int i=0; i<this.width; i++){
                for(int j=0; j<this.height-this.playerOneBase.getHeight()/Const.BLOCK_SIZE; j++){
                    if((i+j)%2==0){
                        output.add(Const.BOX_CODE + " " + (this.x+i*this.blockSize) + " " + (this.playerOneBase.getHeight()+this.y+j*this.blockSize) + " " + this.blockSize + " " + this.blockSize + " BLUE");
                    }
                }
            }
        }
        for(Unit nextObject: this.gameObjects){
            output.addAll(nextObject.draw());
        }
        for(Projectile nextProjectile: this.projectiles){
            output.addAll(nextProjectile.draw());
        }
        for(Unit nextObject: this.gameObjects){
            if(!(nextObject instanceof Base)){
                output.addAll(nextObject.drawHealthBar());
            }
        }
        output.add(Const.BOX_CODE + " " + 0 + " " + 0 + " " + (Const.WIDTH*2/3) + " " + Const.BASE_HEALTH_BAR_HEIGHT + " RED");
        output.add(Const.BOX_CODE + " " + 0 + " " + (Const.HEIGHT-Const.BASE_HEALTH_BAR_HEIGHT) + " " + (Const.WIDTH*2/3) + " " + Const.BASE_HEALTH_BAR_HEIGHT + " RED");
        int playerOneHealthPercentage = (int)((double)this.playerOneBase.getHealth() / Const.BASE_HEALTH * Const.WIDTH * 2 / 3);
        int playerTwoHealthPercentage = (int)((double)this.playerTwoBase.getHealth() / Const.BASE_HEALTH * Const.WIDTH * 2 / 3);
        output.add(Const.BOX_CODE + " " + 0 + " " + 0 + " " + playerOneHealthPercentage + " " + Const.BASE_HEALTH_BAR_HEIGHT + " GREEN");
        output.add(Const.BOX_CODE + " " + 0 + " " + (Const.HEIGHT-Const.BASE_HEALTH_BAR_HEIGHT) + " " + playerTwoHealthPercentage + " " + Const.BASE_HEALTH_BAR_HEIGHT + " GREEN");
        // draw names
        output.add(Const.STRING_CODE + " " + (Const.WIDTH*2/6) + " " + 15 + " BLACK " + this.firstName);
        output.add(Const.STRING_CODE + " " + (Const.WIDTH*2/6) + " " + (Const.HEIGHT-Const.BASE_HEALTH_BAR_HEIGHT) + " BLACK " + this.secondName);
        return output;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Base getPlayerOneBase(){
        return this.playerOneBase;
    }

    public Base getPlayerTwoBase(){
        return this.playerTwoBase;
    }

    //public void setPurchasePending(boolean purchasePending){
    //    this.purchasePending = purchasePending;
    //}

    public boolean addUnit(Unit newUnit){
        if(newUnit.getX() + newUnit.getWidth() > this.x + this.width * Const.BLOCK_SIZE || 
        newUnit.getY() + newUnit.getHeight() > this.y + this.height * Const.BLOCK_SIZE){
            return false;
        }
        for(Unit nextUnit: this.gameObjects){
            if(nextUnit.getHitBox().intersects(newUnit.getHitBox())){
                return false;
            }
        }
        this.gameObjects.add(newUnit);
        return true;
    }

}
