import java.util.ArrayList;

// class for constant values of the base 
public class Base extends Unit{

    // the base stores gold
    private int gold;
    private int goldCooldown;
    
    // constant values 
    public Base(int teamId, int x, int y, int width, int height, int maxHealth){
        super(teamId, x, y, width, height, maxHealth, "Base");
        this.gold = Const.BASE_GOLD;
    }

    // base has a gold production function
    @Override
    public void update(ArrayList<Unit> gameObjects){
        if(this.goldCooldown == 0){
            this.gold ++;
        }
        this.goldCooldown = (this.goldCooldown + 1) % Const.BASE_GOLD_PRODUCTION_DELAY;
    }

    // getters and setters
    public void setGold(int gold){
        this.gold = gold;
    }

    public int getGold(){
        return this.gold;
    }

}
