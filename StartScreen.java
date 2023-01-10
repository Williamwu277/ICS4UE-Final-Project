import java.awt.*;

public class StartScreen implements Screen{

    private InputListener input;
    private Button queueButton;

    public StartScreen(InputListener input){
        this.queueButton = new Button(input, Const.WIDTH/2-25, Const.HEIGHT/2-25, 50, 50);
    }

    @Override
    public void update(){
    }

    @Override
    public boolean isTransitioning(){
        return this.queueButton.isClicked();
    }

    @Override
    public void draw(Graphics g){
        this.queueButton.draw(g);
    }

    @Override
    public void close(){
        this.queueButton.kill();
    }
    
}
