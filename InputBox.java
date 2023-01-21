import java.awt.Graphics;
import java.awt.Color;

// custom class for a text input field
public class InputBox {

    // the only char set that is allowed
    private static final String LEGAL_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    // instance variables
    private InputListener input;
    char[] keys;
    private int x;
    private int y;
    private int width;
    private int height;
    private int keyLimit;
    private int cursor;
    private String sprite;

    // constructor
    public InputBox(InputListener input, int x, int y, int width, int height, int keyLimit, String sprite){
        this.input = input;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.keyLimit = keyLimit;
        this.keys = new char[this.keyLimit];
        this.sprite = sprite;
    }

    // update function to check for user input
    public void update(){
        // get the most current key press from the user
        char key = this.input.getKey();
        // check for the backspace key and remove the last character typed
        if(key == '\b'){
            if(this.cursor != 0){
                this.cursor --;
                this.keys[this.cursor] = Character.MIN_VALUE;
            }
            // if there is still space for another character, add it
        }else if(key != Character.MIN_VALUE && this.cursor + 1 <= this.keyLimit){
            // replace any illegal characters with an underscore
            if(!InputBox.LEGAL_CHARACTERS.contains(Character.toString(key))){
                key = '_';
            }
            this.keys[this.cursor] = key;
            this.cursor ++;
        }
    }

    // draw the input box
    public void draw(Graphics g){
        // draw the background image
        g.drawImage(ImageLoader.IMAGES.get(this.sprite), this.x, this.y, this.width, this.height, null);
        // if there is nothing in the box, prompt user for input
        if(this.cursor == 0){
            g.setColor(Color.DARK_GRAY);
            g.drawString("Nickname", this.x+5, this.y+this.height/2+5);
        }else{
            // draw the user input
            g.setColor(Color.WHITE);
            String output = this.getInput();
            g.drawString(output, this.x+5, this.y+this.height/2+5);
        }
    }

    // return the user input stored in the input box
    public String getInput(){
        return new String(keys, 0, this.cursor);
    }
    
}
