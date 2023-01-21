import javax.swing.JFrame;
import java.awt.Dimension;

// class creating the user interface and maintaining it
public class PlayerInterface{

    // instance variables
    private JFrame gameFrame;
    private GamePanel gamePanel;
    private InputListener input;
    private Screen screen;
    private String currentState;

    // constructor
    public PlayerInterface(){
        this.input = new InputListener();
        this.screen = new StartScreen(this.input);
        this.currentState = "Menu";
        this.gameFrame = new JFrame("No Name Game V2");
        this.gamePanel = new GamePanel();
        this.gamePanel.setScreen(this.screen);
    }
    
    // set up the frame
    public void setUp(){
        this.gameFrame.setSize(Const.WIDTH,Const.HEIGHT+25); 
        this.gameFrame.setMinimumSize(new Dimension(Const.WIDTH, Const.HEIGHT));
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.gameFrame.setResizable(false); 
        this.gamePanel.addMouseMotionListener(this.input);   
        this.gamePanel.addMouseListener(this.input);    
        this.gamePanel.addKeyListener(this.input);  
        this.gameFrame.add(gamePanel);  
        this.gameFrame.setVisible(true);     
    }   

    // run the game loop and switch between screens
    public void runGameLoop(){
        while(true){
            // check for screen transitions
            if(this.currentState.equals("Menu") && this.screen.isTransitioning()){
                // transition from menu to queue
                StartScreen current = (StartScreen)this.screen;
                this.screen = new LobbyScreen(current.getSocket(), current.getReader(), current.getWriter());
                this.gamePanel.setScreen(this.screen);
                this.currentState = "Queue";
            }else if(this.currentState.equals("Queue") && this.screen.isTransitioning()){
                // transition from queue to in-game
                LobbyScreen current = (LobbyScreen)this.screen;
                this.screen = new GameScreen(this.input, current.getSocket(), current.getReader(), current.getWriter());
                this.gamePanel.setScreen(this.screen);
                this.currentState = "Game";
            }else if(this.currentState.equals("Game") && this.screen.isTransitioning()){
                // transition from game to end-game screen
                GameScreen current = (GameScreen)this.screen;
                this.screen = new EndScreen(this.input, current.getWinner());
                this.gamePanel.setScreen(this.screen);
                this.currentState = "End";
            }else if(this.currentState.equals("End") && this.screen.isTransitioning()){
                // transition from end-game screen to the menu again
                this.screen = new StartScreen(this.input);
                this.gamePanel.setScreen(this.screen);
                this.currentState = "Menu";
            }
            // update the screen, repaint pictures, and pause
            this.screen.update();
            this.gameFrame.repaint();
            this.pause();
        }
    }

    // pauses for a duration of time
    public void pause(){
        try{
            Thread.sleep(Const.PAUSE);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
