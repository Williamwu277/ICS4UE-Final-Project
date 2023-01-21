import java.util.ArrayList;

// Class for server side game updating
// Runs, stores, and controls all game units behaviors
public class GameMap {

    // function to determine whether something is on the correct side corresponding to its team
    public static boolean onCorrectSide(int currentTeam, int x, int y, int width, int height){
        if(currentTeam == 1 && y + height <= Const.MAP_HEIGHT * Const.BLOCK_SIZE / 2){
            return true;
        }else if(currentTeam == -1 && y >= Const.MAP_HEIGHT * Const.BLOCK_SIZE / 2){
            return true;
        }
        return false;
    }

    // instance variables
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
    
    // constructor
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
        // initialize player bases
        this.playerOneBase = new Base(1, this.x, this.y, Const.MAP_WIDTH*Const.BLOCK_SIZE, Const.TOWER_SIZE, Const.BASE_HEALTH);
        this.playerTwoBase = new Base(-1, this.x, this.y+Const.MAP_HEIGHT*Const.BLOCK_SIZE-Const.TOWER_SIZE , Const.MAP_WIDTH*Const.BLOCK_SIZE, Const.TOWER_SIZE, Const.BASE_HEALTH);
        this.gameObjects.add(this.playerOneBase);
        this.gameObjects.add(this.playerTwoBase);
    }

    // update the entire game state
    public int update(){
        // update all the projectiles positions
        for(Projectile nextProjectile: this.projectiles){
            nextProjectile.update(this.gameObjects);
        }
        // update all the units on the map
        for(Unit nextUnit: this.gameObjects){
            nextUnit.update(this.gameObjects);
            // if a unit is ranged, check if it shot a projectile and take it
            if(nextUnit instanceof RangedAttacker){
                Projectile projectile = ((RangedAttacker)nextUnit).getProjectile();
                if(projectile != null){
                    this.projectiles.add(projectile);
                }
            }
        }
        // remove all the units that are dead
        for(int i=this.gameObjects.size()-1; i>=0; i--){
            if(this.gameObjects.get(i).getHealth() <= 0){
                // When siege engines are dead, spawn the troops inside
                if(this.gameObjects.get(i) instanceof SiegeEngine){
                    this.gameObjects.addAll(((SiegeEngine)this.gameObjects.get(i)).onDeath());
                }
                this.gameObjects.remove(i);
            }
        }
        // remove projectiles when they run out or hit
        for(int i=this.projectiles.size()-1; i>=0; i--){
            if(this.projectiles.get(i).hasHit()){
                this.projectiles.remove(i);
            }
        }
        // return the state of the game — if a player has won or lost
        if(this.playerOneBase.getHealth() <= 0){
            return -1;
        }else if(this.playerTwoBase.getHealth() <= 0){
            return 1;
        }else{
            return 0;
        }
    }

    // draw function returning instructions to client for what to draw
    public ArrayList<String> draw(boolean purchasePending, int teamId){
        ArrayList<String> output = new ArrayList<>();
        // draw background
        output.add(Const.BOX_CODE + " " + 0 + " " + 0 + " " + Const.WIDTH + " " + Const.HEIGHT + " DARKGRAY");
        // draw checkerboard map
        for(int i=0; i<this.width; i++){
            for(int j=0; j<this.height-this.playerOneBase.getHeight()/Const.BLOCK_SIZE; j++){
                // use manhattan distance for checkerboard pattern
                // draw colours depending on whether or not user is trying to place a unit
                // if so, make the valid spaces for unit placement a different colour
                if((i + j) % 2 == 0){
                    if(purchasePending && GameMap.onCorrectSide(teamId, this.x+i*this.blockSize, this.playerOneBase.getHeight()+this.y+j*this.blockSize, this.blockSize, this.blockSize)){
                        output.add(Const.BOX_CODE + " " + (this.x+i*this.blockSize) + " " + (this.playerOneBase.getHeight()+this.y+j*this.blockSize) + " " + this.blockSize + " " + this.blockSize + " LIGHTGREEN");
                    }else{
                        output.add(Const.BOX_CODE + " " + (this.x+i*this.blockSize) + " " + (this.playerOneBase.getHeight()+this.y+j*this.blockSize) + " " + this.blockSize + " " + this.blockSize + " GREEN2");
                    }
                }else{
                    if(purchasePending && GameMap.onCorrectSide(teamId, this.x+i+this.blockSize, this.playerOneBase.getHeight()+this.y+j*this.blockSize, this.blockSize, this.blockSize)){
                        output.add(Const.BOX_CODE + " " + (this.x+i*this.blockSize) + " " + (this.playerOneBase.getHeight()+this.y+j*this.blockSize) + " " + this.blockSize + " " + this.blockSize + " LIGHTGREEN2");
                    }else{
                        output.add(Const.BOX_CODE + " " + (this.x+i*this.blockSize) + " " + (this.playerOneBase.getHeight()+this.y+j*this.blockSize) + " " + this.blockSize + " " + this.blockSize + " GREEN3");
                    }
                }
            }
        }
        // draw all units
        for(Unit nextObject: this.gameObjects){
            output.addAll(nextObject.draw());
        }
        // draw all projectiles
        for(Projectile nextProjectile: this.projectiles){
            output.addAll(nextProjectile.draw());
        }
        // draw the healthbars of units
        for(Unit nextObject: this.gameObjects){
            if(!(nextObject instanceof Base)){
                output.addAll(nextObject.drawHealthBar());
            }
        }
        //  draw the healthbars of the bases at the top and the bottom of the screen
        // add background red colour
        output.add(Const.BOX_CODE + " " + 0 + " " + 0 + " " + (Const.WIDTH*2/3) + " " + Const.BASE_HEALTH_BAR_HEIGHT + " RED");
        output.add(Const.BOX_CODE + " " + 0 + " " + (Const.HEIGHT-Const.BASE_HEALTH_BAR_HEIGHT) + " " + (Const.WIDTH*2/3) + " " + Const.BASE_HEALTH_BAR_HEIGHT + " RED");
        // find percentage of health left
        int playerOneHealthPercentage = (int)((double)this.playerOneBase.getHealth() / Const.BASE_HEALTH * Const.WIDTH * 2 / 3);
        int playerTwoHealthPercentage = (int)((double)this.playerTwoBase.getHealth() / Const.BASE_HEALTH * Const.WIDTH * 2 / 3);
        // draw the gren colour
        output.add(Const.BOX_CODE + " " + 0 + " " + 0 + " " + playerOneHealthPercentage + " " + Const.BASE_HEALTH_BAR_HEIGHT + " GREEN");
        output.add(Const.BOX_CODE + " " + 0 + " " + (Const.HEIGHT-Const.BASE_HEALTH_BAR_HEIGHT) + " " + playerTwoHealthPercentage + " " + Const.BASE_HEALTH_BAR_HEIGHT + " GREEN");
        // draw usernames on the health bars
        output.add(Const.STRING_CODE + " " + (Const.WIDTH*2/6) + " " + (this.y+5) + " BLACK LARGE " + this.firstName);
        output.add(Const.STRING_CODE + " " + (Const.WIDTH*2/6) + " " + (Const.HEIGHT-Const.BASE_HEALTH_BAR_HEIGHT/2+5) + " BLACK LARGE " + this.secondName);
        return output;
    }

    // getters and setters
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

    // add a unit to the game map; return false if the sapce is already being occupied or the position is illegal
    public boolean addUnit(Unit newUnit){
        // check for bounds
        if(newUnit.getX() + newUnit.getWidth() > this.x + this.width * Const.BLOCK_SIZE || 
        newUnit.getY() + newUnit.getHeight() > this.y + this.height * Const.BLOCK_SIZE){
            return false;
        }
        // check for intersections
        for(Unit nextUnit: this.gameObjects){
            if(nextUnit.getHitBox().intersects(newUnit.getHitBox())){
                return false;
            }
        }
        this.gameObjects.add(newUnit);
        return true;
    }

}
