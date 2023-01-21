import java.awt.Graphics;
import java.awt.Color;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.LinkedList;

// Class for the screen that displays the game being played
public class GameScreen implements Screen{

    // instance variables — cursor input, server input, things to draw and state of the game
    private InputListener input;
    private Socket clientSocket;
    private BufferedReader clientIn;
    private PrintWriter clientOut;
    private Queue<String> toDraw;

    private boolean gameOver;
    private boolean gameWon;

    // constructor
    public GameScreen(InputListener input, Socket clientSocket, BufferedReader clientIn, PrintWriter clientOut){
        this.input = input;
        this.clientSocket = clientSocket;
        this.clientIn = clientIn;
        this.clientOut = clientOut;
        this.toDraw = new LinkedList<>();
    }

    // update function for game loop, just send data to the server and receive data from the server
    @Override
    public void update(){
        this.sendOutput();
        this.readInput();
    }

    // take in the instructions for things to be drawn from the server
    public void readInput(){
        try{
            // read the number of lines ready to be received
            String in = this.clientIn.readLine();
            //  wait until there is an actual message to receive
            while(in.equals("")){
                in = this.clientIn.readLine();
                Thread.sleep(Const.PAUSE);
            }
            // check if the message is for the conclusion of the game
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
                // read each item to be drawn and add it to the queue to be drawn
                for(int i=0; i<inputSize; i++){
                    this.toDraw.add(this.clientIn.readLine().trim());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // send data to the server — the users input
    public void sendOutput(){
        int output;
        if(this.input.isClicked()){
            output = 1;
        }else{
            output = 0;
        }
        // send mouse coordinates and if the mouse was clicked
        this.clientOut.println(this.input.getX() + " " + this.input.getY() + " " + output);
        this.clientOut.flush();
    }

    // if the game is over, transition
    @Override
    public boolean isTransitioning(){
        return this.gameOver;
    }

    public boolean getWinner(){
        return this.gameWon;
    }

    // function for carrying out the server's drawing instructions
    @Override
    public void draw(Graphics g){
        // if there is anything to draw in the queue, draw it
        while(!this.toDraw.isEmpty()){
            String nextLine = this.toDraw.poll();
            String[] separation = nextLine.split(" ", 2);
            // determine what is being drawn based on the first term
            if(separation[0].equals(Const.BOX_CODE)){
                // take the box data
                String[] messageData = separation[1].split(" ");
                int x = Integer.parseInt(messageData[0]);
                int y = Integer.parseInt(messageData[1]);
                int width = Integer.parseInt(messageData[2]);
                int height = Integer.parseInt(messageData[3]);
                Color color = Const.COLORS.get(messageData[4]);
                // draw the box
                g.setColor(color);
                g.fillRect(x, y, width, height);
            }else if(separation[0].equals(Const.STRING_CODE)){
                // take the string to be written and the data
                String[] messageData = separation[1].split(" ", 5);
                int x = Integer.parseInt(messageData[0]);
                int y = Integer.parseInt(messageData[1]);
                Color color = Const.COLORS.get(messageData[2]);
                // in case it is a multi-line message, split by the pre-determined next-line char
                String size = messageData[3];
                String[] messages = messageData[4].split(Const.SOCKET_NEXT_LINE);
                g.setColor(color);
                if(size.equals("SMALL")){
                    g.setFont(FontLoader.SMALL_FONT);
                }else{
                    g.setFont(FontLoader.LARGE_FONT);
                }
                // draw each line of the message
                for(int i=0; i<messages.length; i++){
                    int lineWidth = g.getFontMetrics().stringWidth(messages[i]);
                    g.drawString(messages[i], x - lineWidth / 2, y + Const.STRING_HEIGHT * i);
                }
            }else{
                // draw an image by taking in the input data
                String[] messageData = separation[1].split(" ");
                int x = Integer.parseInt(messageData[0]);
                int y = Integer.parseInt(messageData[1]);
                int width = Integer.parseInt(messageData[2]);
                int height = Integer.parseInt(messageData[3]);
                // rotate determines if the image needs to be rotated based on the side of its team
                String rotate = messageData[4];
                if(rotate.equals("N")){
                    height = -height;
                    y -= height;
                }
                // draw the image
                g.drawImage(ImageLoader.IMAGES.get(separation[0]), x, y, width, height, null);
            }
        }
    }

    // close the socket and the input streams
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
