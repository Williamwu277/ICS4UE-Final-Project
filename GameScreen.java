import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class GameScreen implements Screen{

    private InputListener input;
    private Socket clientSocket;
    private BufferedReader clientIn;
    private PrintWriter clientOut;
    private Queue<String> toDraw;

    private boolean gameOver;
    private boolean gameWon;

    public GameScreen(InputListener input, Socket clientSocket, BufferedReader clientIn, PrintWriter clientOut){
        this.input = input;
        this.clientSocket = clientSocket;
        this.clientIn = clientIn;
        this.clientOut = clientOut;
        this.toDraw = new LinkedList<>();
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
        while(!this.toDraw.isEmpty()){
            String nextLine = this.toDraw.poll();
            String[] separation = nextLine.split(" ", 2);
            if(separation[0].equals(Const.BOX_CODE)){
                String[] messageData = separation[1].split(" ");
                int x = Integer.parseInt(messageData[0]);
                int y = Integer.parseInt(messageData[1]);
                int width = Integer.parseInt(messageData[2]);
                int height = Integer.parseInt(messageData[3]);
                Color color = Const.COLORS.get(messageData[4]);
                g.setColor(color);
                g.fillRect(x, y, width, height);
            }else if(separation[0].equals(Const.STRING_CODE)){
                String[] messageData = separation[1].split(" ", 5);
                int x = Integer.parseInt(messageData[0]);
                int y = Integer.parseInt(messageData[1]);
                Color color = Const.COLORS.get(messageData[2]);
                String size = messageData[3];
                String[] messages = messageData[4].split(Const.SOCKET_NEXT_LINE);
                g.setColor(color);
                if(size.equals("SMALL")){
                    g.setFont(Const.SMALL_FONT);
                }else{
                    g.setFont(Const.LARGE_FONT);
                }
                for(int i=0; i<messages.length; i++){
                    int lineWidth = g.getFontMetrics().stringWidth(messages[i]);
                    g.drawString(messages[i], x - lineWidth / 2, y + Const.STRING_HEIGHT * i);
                }
            }else{
                String[] messageData = separation[1].split(" ");
                int x = Integer.parseInt(messageData[0]);
                int y = Integer.parseInt(messageData[1]);
                int width = Integer.parseInt(messageData[2]);
                int height = Integer.parseInt(messageData[3]);
                String rotate = messageData[4];
                if(rotate.equals("N")){
                    height = -height;
                    y -= height;
                }
                g.drawImage(Const.IMAGES.get(separation[0]), x, y, width, height, null);
            }
        }
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
