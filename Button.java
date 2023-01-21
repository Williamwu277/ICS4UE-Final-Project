import java.awt.Graphics;
import java.util.ArrayList;

// Class for button. Can operate on both server side or client side
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

    // update function to check whether or not user is clicking button
    public void update(int mouseX, int mouseY, boolean mouseClicked){
        if(this.x <= mouseX && mouseX <= x+width && y <= mouseY && mouseY <= y+height && mouseClicked){
            // a new click has only been performed if the button hasn't been held
            if(!this.buttonClicked && !this.buttonHeld){
                this.buttonClicked = true;
            }
            this.buttonHeld = true;
        }else{
            this.buttonHeld = false;
        }
    }

    // if there is access to the input listener, easier way to call update
    public void update(InputListener input){
        this.update(input.getX(), input.getY(), input.isClicked());
    }

    // tell the user if button has been clicked, and reset 
    public boolean isClicked(){
        boolean answer = this.buttonClicked;
        this.buttonClicked = false;
        return answer;
    }

    // draw function for the client side, actually draws an image
    public void draw(Graphics g){
        g.drawImage(ImageLoader.IMAGES.get(this.sprite), this.x, this.y, this.width, this.height, null);
    }

    // draw function for the server side, returns the instructions on how to draw this button for the client 
    public ArrayList<String> draw(){
        ArrayList<String> output = new ArrayList<>();
        output.add(Const.BOX_CODE + " " + this.x + " " + this.y + " " + this.width + " " + this.height + " PINK");
        if(this.sprite != null){
            output.add(this.sprite + " " + (this.x+5) + " " + (this.y+5) + " " + (this.width-10) + " " + (this.height-10) + " Y");
        }
        return output;
    }

    // getters and setters
    public String getId(){
        return this.id;
    }

}
