import java.awt.*;

public class EndScreen implements Screen{

    private InputListener input;
    private Button continueButton;
    private boolean won;

    public EndScreen(InputListener input, boolean won){
        this.input = input;
        this.won = won;
        this.continueButton = new Button("Queue", Const.WIDTH/2-Const.BUTTON_WIDTH/2, Const.HEIGHT/2-Const.BUTTON_HEIGHT/2, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT, "MenuButton");
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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Const.WIDTH, Const.HEIGHT);
        this.continueButton.draw(g);
        g.setFont(Const.EXTRA_LARGE_FONT);
        String toDraw;
        if(this.won){
            g.setColor(Color.GREEN);
            toDraw = "YOU WON";
        }else{
            g.setColor(Color.RED);
            toDraw = "YOU LOST";
        }
        int width = g.getFontMetrics().stringWidth(toDraw);
        g.drawString(toDraw, Const.WIDTH/2-width/2, Const.HEIGHT/2-Const.BUTTON_HEIGHT*2);
    }

    @Override
    public void close(){}
    
}
