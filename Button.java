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
    private String sprite;

    public Button(String id, int x, int y, int width, int height, String sprite){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.sprite = sprite;
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
        g.drawImage(Const.IMAGES.get(this.sprite), this.x, this.y, this.width, this.height, null);
    }

    public ArrayList<String> draw(){
        ArrayList<String> output = new ArrayList<>();
        output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " PINK");
        if(this.sprite != null){
            output.add(this.sprite + " " + (this.x+5) + " " + (this.y+5) + " " + (this.width-10) + " " + (this.height-10) + " Y");
        }
        return output;
    }

    public String getId(){
        return this.id;
    }

}
