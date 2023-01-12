import java.net.*;
import java.io.*;

public class GameThread implements Runnable{

    private PlayerConnection playerOne;
    private PlayerConnection playerTwo;
    private GameMap gameMap;
    private String firstName;
    private String secondName;
    private boolean threadAlive;

    public GameThread(Socket playerOne, Socket playerTwo) throws IOException{
        this.threadAlive = true;
        this.playerOne = new PlayerConnection(playerOne, 1);
        this.playerTwo = new PlayerConnection(playerTwo, -1);
        this.firstName = this.playerOne.readName();
        this.secondName = this.playerTwo.readName();
        int mapX = (Const.WIDTH - MenuBar.MENU_WIDTH - Const.MAP_WIDTH*Const.BLOCK_SIZE) / 2;
        int mapY = (Const.HEIGHT - Const.MAP_HEIGHT*Const.BLOCK_SIZE) / 2;
        this.gameMap = new GameMap(this.firstName, this.secondName, mapX, mapY, Const.MAP_WIDTH, Const.MAP_HEIGHT, Const.BLOCK_SIZE);
    }

    @Override
    public void run(){
        while(this.threadAlive){
            this.playerOne.update(this.gameMap);
            this.playerTwo.update(this.gameMap);
            int result = this.gameMap.update();
            if(result != 0){
                this.playerOne.endGame(result);
                this.playerTwo.endGame(result);
                this.kill();
            }
            this.pause();
        }
    }

    public void pause(){
        try{
            Thread.sleep(15);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void kill(){
        this.playerOne.kill();
        this.playerTwo.kill();
        this.threadAlive = false;
    }
}