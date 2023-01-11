import java.awt.*;
import java.util.*;

public class Wall extends Unit{

    public Wall(int teamId, int x, int y){
        super(teamId, x, y, Const.BLOCK_SIZE*3, Const.BLOCK_SIZE, 400);
    }

    @Override
    public void update(ArrayList<Unit> gameObjects){

    }

    @Override
    public ArrayList<String> draw(){
        ArrayList<String> output = new ArrayList<>();
        output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " LIGHTGRAY");
        return output;
    }
    
}
