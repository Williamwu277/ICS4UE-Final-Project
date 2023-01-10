import java.net.*;
import java.io.*;

public class GameThread implements Runnable{
    private PlayerConnection playerOne;
    private PlayerConnection playerTwo;
    public GameThread(Socket playerOne, Socket playerTwo) throws IOException{
        this.playerOne = new PlayerConnection(playerOne);
        this.playerTwo = new PlayerConnection(playerTwo);
    }
    public void run(){
        
    }
}