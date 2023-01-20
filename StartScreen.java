import java.awt.*;
import java.net.*;
import java.io.*;

public class StartScreen implements Screen{

    //private final String IP = "192.168.0.112";
    private final String IP = "127.0.0.1";
    private final int PORT = 6969;

    private InputListener input;
    private Button queueButton;
    private InputBox inputBox;
    private Socket clientSocket;
    private PrintWriter clientOut;
    private BufferedReader clientIn;

    public StartScreen(InputListener input){
        this.input = input;
        this.queueButton = new Button("Queue", Const.WIDTH/2-Const.BUTTON_WIDTH/2, Const.HEIGHT/2-Const.BUTTON_HEIGHT/2, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT, "StartButton");
        this.inputBox = new InputBox(input, Const.WIDTH/2-Const.BUTTON_WIDTH/2, Const.HEIGHT/2+Const.BUTTON_HEIGHT, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT, 10, "InputBox");
    }

    @Override
    public void update(){
        this.queueButton.update(this.input);
        this.inputBox.update();
    }

    @Override
    public boolean isTransitioning(){
        boolean clicked = this.queueButton.isClicked();
        if(this.inputBox.getInput().length() == 0){
            clicked = false;
        }
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
        g.drawImage(Const.IMAGES.get("Title"), 0, 0, Const.WIDTH, Const.HEIGHT, null);
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
