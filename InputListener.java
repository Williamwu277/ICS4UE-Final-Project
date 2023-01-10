import java.awt.event.*;

public class InputListener implements MouseMotionListener, MouseListener{

    private int mouseX;
    private int mouseY;
    private boolean clicked;

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

    public int getX(){
        return this.mouseX;
    }

    public int getY(){
        return this.mouseY;
    }

    public boolean isClicked(){
        return this.clicked;
    }

}