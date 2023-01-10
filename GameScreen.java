import java.awt.*;

public class GameScreen implements Screen{

    private InputListener input;
    private MenuBar menuBar;
    private GameMap gameMap;

    public GameScreen(InputListener input){
        this.input = input;
        int mapX = (Const.WIDTH - MenuBar.MENU_WIDTH - Const.MAP_WIDTH*Const.BLOCK_SIZE) / 2;
        int mapY = (Const.HEIGHT - Const.MAP_HEIGHT*Const.BLOCK_SIZE) / 2;
        this.gameMap = new GameMap(mapX, mapY, Const.MAP_WIDTH, Const.MAP_HEIGHT, Const.BLOCK_SIZE);
        this.menuBar = new MenuBar(this.input, this.gameMap, Const.WIDTH-MenuBar.MENU_WIDTH, 0 ,MenuBar.MENU_WIDTH, Const.HEIGHT, this.gameMap.getPlayerOneBase(), this.gameMap.getPlayerTwoBase());
    }

    @Override
    public void update(){
        this.menuBar.update();
        this.gameMap.update();
    }

    @Override
    public boolean isTransitioning(){
        return false;
    }

    @Override
    public void draw(Graphics g){
        this.menuBar.draw(g);
        this.gameMap.draw(g);
    }

    @Override
    public void close(){
    }
    
}
