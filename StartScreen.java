import java.awt.Graphics;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;

// class for the screen that displays the menu
public class StartScreen implements Screen{

    // ip of the server and the port
    private final String IP = "127.0.0.1";
    private final int PORT = 6969;

    // instance variables
    private InputListener input;
    private Button queueButton;
    private InputBox inputBox;
    private Socket clientSocket;
    private PrintWriter clientOut;
    private BufferedReader clientIn;

    // constructor
    public StartScreen(InputListener input){
        this.input = input;
        this.queueButton = new Button("Queue", Const.WIDTH/2-Const.BUTTON_WIDTH/2, Const.HEIGHT/2-Const.BUTTON_HEIGHT/2, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT, "StartButton");
        this.inputBox = new InputBox(input, Const.WIDTH/2-Const.BUTTON_WIDTH/2, Const.HEIGHT/2+Const.BUTTON_HEIGHT, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT, 10, "InputBox");
    }

    // update the screen and check for any inputs to textbox or button
    @Override
    public void update(){
        this.queueButton.update(this.input);
        this.inputBox.update();
    }

    // if the start button has been clicked and the input field is not empty, join the queue and transition
    @Override
    public boolean isTransitioning(){
        boolean clicked = this.queueButton.isClicked();
        // if name field is not completed, disallow the transition
        if(this.inputBox.getInput().length() == 0){
            clicked = false;
        }   
        // if name is created and start button clicked, join the server
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

    // draw the title, the start button and the input field
    @Override
    public void draw(Graphics g){
        g.drawImage(ImageLoader.IMAGES.get("Title"), 0, 0, Const.WIDTH, Const.HEIGHT, null);
        this.queueButton.draw(g);
        this.inputBox.draw(g);
    }

    @Override
    public void close(){}

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
