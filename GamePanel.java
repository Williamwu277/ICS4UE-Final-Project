import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{ 

    private Screen currentScreen;

    public GamePanel(){ 
        setFocusable(true); 
        requestFocusInWindow(); 
    } 

    public void setScreen(Screen newScreen){
        this.currentScreen = newScreen;
    }
     
    @Override 
    public void paintComponent(Graphics g){  
        super.paintComponent(g);
        this.currentScreen.draw(g);
    }

}