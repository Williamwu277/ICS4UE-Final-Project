import java.net.Socket;
import java.io.IOException;

// The class facilitating a single game of two players playing against each other
// Each separate game being played is a different thread
public class GameThread implements Runnable{

    // communication between each player and the socket
    private PlayerConnection playerOne;
    private PlayerConnection playerTwo;
    // the game map and unit
    private GameMap gameMap;
    // miscellaneous data
    private String firstName;
    private String secondName;
    private boolean threadAlive;

    // constructor
    public GameThread(Socket playerOne, Socket playerTwo) throws IOException{
        this.threadAlive = true;
        // the first team is designated by 1 and the other team is designated as -1
        this.playerOne = new PlayerConnection(playerOne, 1);
        this.playerTwo = new PlayerConnection(playerTwo, -1);
        // read player nicknames
        this.firstName = this.playerOne.readName();
        this.secondName = this.playerTwo.readName();
        // create the game map
        int mapX = (Const.WIDTH - MenuBar.MENU_WIDTH - Const.MAP_WIDTH*Const.BLOCK_SIZE) / 2;
        int mapY = (Const.HEIGHT - Const.MAP_HEIGHT*Const.BLOCK_SIZE) / 2;
        this.gameMap = new GameMap(this.firstName, this.secondName, mapX, mapY, Const.MAP_WIDTH, Const.MAP_HEIGHT, Const.BLOCK_SIZE);
    }

    // run the game loop
    @Override
    public void run(){
        while(this.threadAlive){
            // update each player
            this.playerOne.update(this.gameMap);
            this.playerTwo.update(this.gameMap);
            int result = this.gameMap.update();
            // if a player's base is destroyed, game is over
            if(result != 0){
                this.playerOne.endGame(result);
                this.playerTwo.endGame(result);
                this.kill();
            }
            this.pause();
        }
    }

    // the pause between each loop in the game
    public void pause(){
        try{
            Thread.sleep(Const.PAUSE);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // kill the thread and close all sockets or input streams
    public void kill(){
        this.playerOne.kill();
        this.playerTwo.kill();
        this.threadAlive = false;
    }
}