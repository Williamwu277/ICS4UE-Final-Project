import javax.swing.*;

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
        this.input = new InputListener();
        this.screen = new StartScreen(this.input);
        this.currentState = "Menu";
        this.gameFrame = new JFrame("Game");
        this.gamePanel = new GamePanel();
        this.gamePanel.setScreen(this.screen);
    }
    
    public void setUp(){
        this.gameFrame.setSize(Const.WIDTH,Const.HEIGHT+25); 
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.gameFrame.setResizable(false); 
        this.gamePanel.addMouseMotionListener(this.input);   
        this.gamePanel.addMouseListener(this.input);      
        this.gameFrame.add(gamePanel);  
        this.gameFrame.setVisible(true);     
    }   

    public void runGameLoop(){
        while(true){
            if(this.currentState.equals("Menu") && this.screen.isTransitioning()){
                this.screen = new GameScreen(this.input);
                this.gamePanel.setScreen(this.screen);
                this.currentState = "Game";
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
