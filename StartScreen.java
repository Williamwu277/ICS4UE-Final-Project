import java.awt.*;
import java.net.*;
import java.io.*;

public class StartScreen implements Screen{

    private final String IP = "192.168.0.112";
    private final int PORT = 6969;

    private InputListener input;
    private Button queueButton;
    private InputBox inputBox;
    private Socket clientSocket;
    private PrintWriter clientOut;
    private BufferedReader clientIn;

    public StartScreen(InputListener input){
        this.input = input;
        this.queueButton = new Button("Queue", Const.WIDTH/2-25, Const.HEIGHT/2-25, 50, 50);
        this.inputBox = new InputBox(input, Const.WIDTH/2-50, Const.HEIGHT/2+100, 100, 50, 10);
    }

    @Override
    public void update(){
        this.queueButton.update(this.input);
        this.inputBox.update();
    }

    @Override
    public boolean isTransitioning(){
        boolean clicked = this.queueButton.isClicked();
        if(clicked){
            try{
                this.clientSocket = new Socket(IP, PORT);
                this.clientOut = new PrintWriter(clientSocket.getOutputStream());
                this.clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                this.clientOut.println(this.inputBox.getInput());
                this.clientOut.flush();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return clicked;
    }

    @Override
    public void draw(Graphics g){
        this.queueButton.draw(g);
        this.inputBox.draw(g);
    }

    @Override
    public void close(){}

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
