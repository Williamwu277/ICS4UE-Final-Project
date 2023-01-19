import javax.swing.*;
import java.awt.Dimension;

public class PlayerInterface{

    private JFrame gameFrame;
    private GamePanel gamePanel;
    private InputListener input;
    private Screen screen;
    private String currentState;

    public static void main(String[] args){
        PlayerInterface program = new PlayerInterface();
        program.setUp();
        program.runGameLoop();
    }
    
    public PlayerInterface(){
        Const.load();
        this.input = new InputListener();
        this.screen = new StartScreen(this.input);
        this.currentState = "Menu";
        this.gameFrame = new JFrame("Game");
        this.gamePanel = new GamePanel();
        this.gamePanel.setScreen(this.screen);
    }
    
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

    public void runGameLoop(){
        while(true){
            if(this.currentState.equals("Menu") && this.screen.isTransitioning()){
                StartScreen current = (StartScreen)this.screen;
                this.screen = new LobbyScreen(this.input, current.getSocket(), current.getReader(), current.getWriter());
                this.gamePanel.setScreen(this.screen);
                this.currentState = "Queue";
            }else if(this.currentState.equals("Queue") && this.screen.isTransitioning()){
                LobbyScreen current = (LobbyScreen)this.screen;
                this.screen = new GameScreen(this.input, current.getSocket(), current.getReader(), current.getWriter());
                this.gamePanel.setScreen(this.screen);
                this.currentState = "Game";
            }else if(this.currentState.equals("Game") && this.screen.isTransitioning()){
                GameScreen current = (GameScreen)this.screen;
                this.screen = new EndScreen(this.input, current.getWinner());
                this.gamePanel.setScreen(this.screen);
                this.currentState = "End";
            }else if(this.currentState.equals("End") && this.screen.isTransitioning()){
                this.screen = new StartScreen(this.input);
                this.gamePanel.setScreen(this.screen);
                this.currentState = "Menu";
            }
            this.screen.update();
            this.gameFrame.repaint();
            this.pause();
        }
    }

    public void pause(){
        try{
            Thread.sleep(Const.PAUSE);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
