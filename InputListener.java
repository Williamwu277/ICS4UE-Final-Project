import java.awt.event.*;

public class InputListener implements MouseMotionListener, MouseListener, KeyListener{

    private int mouseX;
    private int mouseY;
    private boolean clicked;
    private char key;

    public void mouseMoved(MouseEvent e){ 
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    } 

    public void mouseDragged(MouseEvent e){}

    public void mouseClicked(MouseEvent e){}

    public void mousePressed(MouseEvent e){ 
        this.clicked = true;
    }

    public void mouseReleased(MouseEvent e){
        this.clicked = false;
    }

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){} 

    public void keyPressed (KeyEvent e) {}    

    public void keyReleased (KeyEvent e) {}    

    public void keyTyped (KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            this.key = '\b';
        }else{
            this.key = e.getKeyChar();
        }
    }

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