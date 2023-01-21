import java.awt.Graphics;

// interface for different game state screens
public interface Screen {

    public void update();

    public boolean isTransitioning();

    public void draw(Graphics g);

    public void close();
    
}
