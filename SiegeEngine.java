import java.util.ArrayList;

// class for siege engine unit
public class SiegeEngine extends Soldier{

    // constructor
    public SiegeEngine(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE * 3, Const.BLOCK_SIZE * 3, 500, 10, 40, 150, 5, "SiegeEngine");
    }

    // spawn units on death
    public ArrayList<Unit> onDeath(){
        ArrayList<Unit> spawns = new ArrayList<>();
        // depending on which side, the formation is different
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
 