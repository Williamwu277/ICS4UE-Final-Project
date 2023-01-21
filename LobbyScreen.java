import java.awt.Graphics;
import java.awt.Color;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;

// the screen displayed while player is in queue searching for an opponent
public class LobbyScreen implements Screen{

    // instance variables
    private Socket clientSocket;
    private BufferedReader clientIn;
    private PrintWriter clientOut;
    private boolean opponentFound;

    // constructor
    public LobbyScreen(Socket clientSocket, BufferedReader clientIn, PrintWriter clientOut){
        this.clientSocket = clientSocket;
        this.clientIn = clientIn;
        this.clientOut = clientOut;
    }

    // update method to check periodically to see whether or not another user has been matched to this one
    @Override
    public void update(){
        try{
            // if there is communication, matchmaking has finished
            if(this.clientIn.ready()){
                String input = this.clientIn.readLine().trim();
                if(input.equals("Opponent Found.")){
                    this.opponentFound = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // if an opponent has been found, transition to the actual game
    @Override
    public boolean isTransitioning(){
        return this.opponentFound;
    }

    // draw function to tell the user to wait
    @Override
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        String waitingMessage = "Finding Opponent ...";
        int stringWidth = g.getFontMetrics().stringWidth(waitingMessage);
        g.drawString("Finding Opponent ...", Const.WIDTH/2-stringWidth/2, Const.HEIGHT/2);
    }

    @Override
    public void close(){
    }

    // getters and setters
    public Socket getSocket(){
        return this.clientSocket;
    }

    public BufferedReader getReader(){
        return this.clientIn;
    }

    public PrintWriter getWriter(){
        return this.clientOut;
    }
    
}
