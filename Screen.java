import java.awt.*;

public interface Screen {

    public void update();

    public boolean isTransitioning();

    public void draw(Graphics g);

    public void close();
    
}
