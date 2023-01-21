import javax.swing.JPanel;
import java.awt.Graphics;

// Game panel displays the GUI
public class GamePanel extends JPanel{ 

    // the current game-state that is being displayed
    private Screen currentScreen;

    public GamePanel(){ 
        this.setFocusable(true); 
        this.requestFocusInWindow(); 
    } 
    
    // change the state of the game being displayed
    public void setScreen(Screen newScreen){
        this.currentScreen = newScreen;
    }
     
    // draw the screen
    @Override 
    public void paintComponent(Graphics g){  
        super.paintComponent(g);
        this.currentScreen.draw(g);
    }

}