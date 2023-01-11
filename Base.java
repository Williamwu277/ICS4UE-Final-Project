import java.awt.*;
import java.util.*;

public class Base extends Unit{

    private int gold;
    private int goldCooldown;
    
    public Base(int teamId, int x, int y, int width, int height, int maxHealth){
        super(teamId, x, y, width, height, maxHealth);
    }

    @Override
    public void update(ArrayList<Unit> gameObjects){
        if(this.goldCooldown == 0){
            this.gold ++;
        }
        this.goldCooldown = (this.goldCooldown + 1) % Const.BASE_GOLD_PRODUCTION_DELAY;
    }

    @Override
    public ArrayList<String> draw(){
        ArrayList<String> output = new ArrayList<String>();
        output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " YELLOW");
        return output;
    }

    public void setGold(int gold){
        this.gold = gold;
    }

    public int getGold(){
        return this.gold;
    }

}
