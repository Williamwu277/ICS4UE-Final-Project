import java.util.*;

public class SiegeEngine extends Soldier{

    public SiegeEngine(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE * 3, Const.BLOCK_SIZE * 3, 500, 10, 40, 150, 5, "SiegeEngine");
    }

    public ArrayList<Unit> onDeath(){
        ArrayList<Unit> spawns = new ArrayList<>();
        if(this.teamId == 1){
            spawns.add(new Tanker(this.teamId, this.x, this.y+Const.BLOCK_SIZE));
            spawns.add(new FootSoldier(this.teamId, this.x+Const.BLOCK_SIZE*2, this.y+Const.BLOCK_SIZE*2));
            spawns.add(new Healer(this.teamId, this.x+Const.BLOCK_SIZE*2, this.y+Const.BLOCK_SIZE));
            for(int i=0; i<3; i++){
                spawns.add(new Archer(this.teamId, this.x+Const.BLOCK_SIZE*i, this.y));
            }
        }else{
            spawns.add(new Tanker(this.teamId, this.x+Const.BLOCK_SIZE, this.y));
            spawns.add(new FootSoldier(this.teamId, this.x, this.y));
            spawns.add(new Healer(this.teamId, this.x, this.y+Const.BLOCK_SIZE));
            for(int i=0; i<3; i++){
                spawns.add(new Archer(this.teamId, this.x+Const.BLOCK_SIZE*i, this.y+Const.BLOCK_SIZE*2));
            }
        }
        return spawns;
    }
    
}
 