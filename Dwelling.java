import java.awt.*;
import java.util.*;

public class Dwelling extends Unit{

    private int goldCooldown;

    public Dwelling(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE*2, Const.BLOCK_SIZE*2, 100);
    }

    @Override
    public void update(ArrayList<Unit> gameObjects){
        Base teamBase = null;
        for(Unit nextUnit: gameObjects){
            if(nextUnit.getId() == this.teamId && nextUnit instanceof Base){
                teamBase = (Base)nextUnit;
            }
        }
        if(this.goldCooldown == 0){
            teamBase.setGold(teamBase.getGold() + 1);
        }
        this.goldCooldown = (this.goldCooldown + 1) % Const.DWELLING_GOLD_PRODUCTION_DELAY;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
    
}