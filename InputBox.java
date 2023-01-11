import java.awt.*;

public class InputBox {
    
    private InputListener input;
    char[] keys;
    private int x;
    private int y;
    private int width;
    private int height;
    private int keyLimit;
    private int cursor;

    public InputBox(InputListener input, int x, int y, int width, int height, int keyLimit){
        this.input = input;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.keyLimit = keyLimit;
        this.keys = new char[this.keyLimit];
    }

    public void update(){
        char key = this.input.getKey();
        if(key == '\b'){
            if(this.cursor != 0){
                this.cursor --;
                this.keys[this.cursor] = Character.MIN_VALUE;
            }
        }else if(key != Character.MIN_VALUE && this.cursor + 1 <= this.keyLimit){
            if(key == ' '){
                key = '-';
            }
            this.keys[this.cursor] = key;
            this.cursor ++;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(this.x, this.y, this.width, this.height);
        g.setColor(Color.WHITE);
        String output = this.getInput();
        int stringWidth = g.getFontMetrics().stringWidth(output);
        g.drawString(output, this.x + this.width/2 - stringWidth/2, this.y + this.height/2);
    }

    public String getInput(){
        return new String(keys, 0, this.cursor);
    }
    
}
