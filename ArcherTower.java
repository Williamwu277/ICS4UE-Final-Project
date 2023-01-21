// Class for constant values of the ArcherTower
public class ArcherTower extends Tower{

    public ArcherTower(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE*2, 200, 180, 10, 40, 5, 15, "ArcherTower");
    }
    
}