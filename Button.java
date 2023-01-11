import java.awt.*;
import java.util.*;

public class Button{
    
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean buttonClicked;
    private boolean buttonHeld;
    private String id;

    public Button(String id, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
    }

    public void update(int mouseX, int mouseY, boolean mouseClicked){
        if(this.x <= mouseX && mouseX <= x+width && y <= mouseY && mouseY <= y+height && mouseClicked){
            if(!this.buttonClicked && !this.buttonHeld){
                this.buttonClicked = true;
            }
            this.buttonHeld = true;
        }else{
            this.buttonHeld = false;
        }
    }

    public void update(InputListener input){
        this.update(input.getX(), input.getY(), input.isClicked());
    }

    public boolean isClicked(){
        boolean answer = this.buttonClicked;
        this.buttonClicked = false;
        return answer;
    }

    public void draw(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(this.x, this.y, this.width, this.height);
    }

    public ArrayList<String> getData(){
        ArrayList<String> output = new ArrayList<>();
        output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " PINK");
        return output;
    }

    public String getId(){
        return this.id;
    }

}
