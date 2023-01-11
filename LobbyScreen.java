import java.awt.*;
import java.net.*;
import java.io.*;

public class LobbyScreen implements Screen{

    private InputListener input;
    private Socket clientSocket;
    private BufferedReader clientIn;
    private PrintWriter clientOut;
    private boolean opponentFound;

    public LobbyScreen(InputListener input, Socket clientSocket, BufferedReader clientIn, PrintWriter clientOut){
        this.input = input;
        this.clientSocket = clientSocket;
        this.clientIn = clientIn;
        this.clientOut = clientOut;
    }

    @Override
    public void update(){
        try{
            if(this.clientIn.ready()){
                String input = this.clientIn.readLine().strip();
                if(input.equals("Opponent Found.")){
                    this.opponentFound = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean isTransitioning(){
        return this.opponentFound;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.drawString("Finding Opponent ...", Const.WIDTH/2, Const.HEIGHT/2);
    }

    @Override
    public void close(){
    }

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
