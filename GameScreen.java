import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class GameScreen implements Screen{

    private InputListener input;
    private Socket clientSocket;
    private BufferedReader clientIn;
    private PrintWriter clientOut;
    private ArrayList<String> toDraw;

    private boolean gameOver;
    private boolean gameWon;

    public GameScreen(InputListener input, Socket clientSocket, BufferedReader clientIn, PrintWriter clientOut){
        this.input = input;
        this.clientSocket = clientSocket;
        this.clientIn = clientIn;
        this.clientOut = clientOut;
        this.toDraw = new ArrayList<>();
    }

    @Override
    public void update(){
        this.sendOutput();
        this.readInput();
    }

    public void readInput(){
        try{
            String in = this.clientIn.readLine();
            while(in.equals("")){
                in = this.clientIn.readLine();
            }
            if(in.trim().equals("WIN")){
                this.gameOver = true;
                this.gameWon = true;
                this.close();
            }else if(in.trim().equals("LOSE")){
                this.gameOver = true;
                this.gameWon = false;
                this.close();
            }else{
                int inputSize = Integer.parseInt(in);
                for(int i=0; i<inputSize; i++){
                    this.toDraw.add(this.clientIn.readLine().strip());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendOutput(){
        int output;
        if(this.input.isClicked()){
            output = 1;
        }else{
            output = 0;
        }
        this.clientOut.println(this.input.getX() + " " + this.input.getY() + " " + output);
        this.clientOut.flush();
    }

    @Override
    public boolean isTransitioning(){
        return this.gameOver;
    }

    public boolean getWinner(){
        return this.gameWon;
    }

    @Override
    public void draw(Graphics g){
        for(String nextLine: this.toDraw){
            String[] separation = nextLine.split(" ");
            if(Integer.parseInt(separation[0]) == Const.BOX_CODE){
                int x = Integer.parseInt(separation[1]);
                int y = Integer.parseInt(separation[2]);
                int width = Integer.parseInt(separation[3]);
                int height = Integer.parseInt(separation[4]);
                Color color = Const.COLORS.get(separation[5]);
                g.setColor(color);
                g.fillRect(x, y, width, height);
            }else{
                int x = Integer.parseInt(separation[1]);
                int y = Integer.parseInt(separation[2]);
                Color color = Const.COLORS.get(separation[3]);
                String message = separation[4];
                g.setColor(color);
                g.drawString(message, x, y);
            }
        }
        this.toDraw.clear();
    }

    @Override
    public void close(){
        try{
            this.clientIn.close();
            this.clientOut.close();
            this.clientSocket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
