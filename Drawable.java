import java.awt.*;

public class Drawable {

    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Drawable(int x, int y, int width, int height, String color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = Const.COLORS.get(color);
    }
    
}
