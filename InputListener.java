import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

// class to store and check for user input
public class InputListener implements MouseMotionListener, MouseListener, KeyListener{

    // instance variables
    private int mouseX;
    private int mouseY;
    private boolean clicked;
    private char key;

    // constructor
    public InputListener(){}

    // if the mouse has moved, store the new coordinates
    public void mouseMoved(MouseEvent e){ 
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    } 

    public void mouseDragged(MouseEvent e){}

    public void mouseClicked(MouseEvent e){}

    // if mouse pressed, update
    public void mousePressed(MouseEvent e){ 
        this.clicked = true;
    }

    // if mouse let go, update
    public void mouseReleased(MouseEvent e){
        this.clicked = false;
    }

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){} 

    public void keyPressed (KeyEvent e) {}    

    public void keyReleased (KeyEvent e) {}    

    // if key typed, store the new key
    public void keyTyped (KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            this.key = '\b';
        }else{
            this.key = e.getKeyChar();
        }
    }

    // getters and setters
    public int getX(){
        return this.mouseX;
    }

    public int getY(){
        return this.mouseY;
    }

    public boolean isClicked(){
        return this.clicked;
    }

    public char getKey(){
        char answer = this.key;
        this.key = Character.MIN_VALUE;
        return answer;
    }

}