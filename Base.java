import java.util.*;

public class Base extends Unit{

    private int gold;
    private int goldCooldown;
    
    public Base(int teamId, int x, int y, int width, int height, int maxHealth){
        super(teamId, x, y, width, height, maxHealth, "Base");
        this.gold = Const.BASE_GOLD;
    }

    @Override
    public void update(ArrayList<Unit> gameObjects){
        if(this.goldCooldown == 0){
            this.gold ++;
        }
        this.goldCooldown = (this.goldCooldown + 1) % Const.BASE_GOLD_PRODUCTION_DELAY;
    }

    public void setGold(int gold){
        this.gold = gold;
    }

    public int getGold(){
        return this.gold;
    }

}
