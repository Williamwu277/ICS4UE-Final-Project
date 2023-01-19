import java.awt.*;

public class InputBox {
    
    private static final String LEGAL_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private InputListener input;
    char[] keys;
    private int x;
    private int y;
    private int width;
    private int height;
    private int keyLimit;
    private int cursor;
    private String sprite;

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

    public void update(){
        char key = this.input.getKey();
        if(key == '\b'){
            if(this.cursor != 0){
                this.cursor --;
                this.keys[this.cursor] = Character.MIN_VALUE;
            }
        }else if(key != Character.MIN_VALUE && this.cursor + 1 <= this.keyLimit){
            if(!InputBox.LEGAL_CHARACTERS.contains(Character.toString(key))){
                key = '_';
            }
            this.keys[this.cursor] = key;
            this.cursor ++;
        }
    }

    public void draw(Graphics g){
        g.drawImage(Const.IMAGES.get(this.sprite), this.x, this.y, this.width, this.height, null);
        if(this.cursor == 0){
            g.setColor(Color.DARK_GRAY);
            g.drawString("Nickname", this.x+5, this.y+this.height/2+5);
        }else{
            g.setColor(Color.WHITE);
            String output = this.getInput();
            g.drawString(output, this.x+5, this.y+this.height/2+5);
        }
    }

    public String getInput(){
        return new String(keys, 0, this.cursor);
    }
    
}
