import java.awt.*;

public class EndScreen implements Screen{

    private InputListener input;
    private Button continueButton;
    private boolean won;

    public EndScreen(InputListener input, boolean won){
        this.input = input;
        this.won = won;
        this.continueButton = new Button("Queue", Const.WIDTH/2-25, Const.HEIGHT/2-25, 50, 50);
    }

    @Override
    public void update(){
        this.continueButton.update(this.input);
    }

    @Override
    public boolean isTransitioning(){
        return this.continueButton.isClicked();
    }

    @Override
    public void draw(Graphics g){
        this.continueButton.draw(g);
        g.setColor(Color.BLACK);
        if(this.won){
            g.drawString("You Won!", Const.WIDTH/2, Const.HEIGHT/2-100);
        }else{
            g.drawString("You Lost!", Const.WIDTH/2, Const.HEIGHT/2-100);
        }
    }

    @Override
    public void close(){}
    
}
