import java.util.ArrayList;

// class for the wall unit
public class Wall extends Unit{

    // constructor
    public Wall(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE*3, Const.BLOCK_SIZE, 400, "Wall");
    }

    @Override
    public void update(ArrayList<Unit> gameObjects){}
    
}
