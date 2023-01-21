import java.util.ArrayList;

// class for dwelling tower
public class Dwelling extends Unit{

    private int goldCooldown;

    public Dwelling(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE*2, Const.BLOCK_SIZE*2, 100, "Dwelling");
    }

    // overide update function to produce money every time cooldown reaches 0
    @Override
    public void update(ArrayList<Unit> gameObjects){
        // find the base to increase the gold storage variable
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
    
}
