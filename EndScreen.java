import java.awt.*;

// class for the game end screen when a player wins or loses
// transitions back to the start scree
public class EndScreen implements Screen{

    private InputListener input;
    private Button continueButton;
    private boolean won;

    public EndScreen(InputListener input, boolean won){
        this.input = input;
        this.won = won;
        this.continueButton = new Button("Queue", Const.WIDTH/2-Const.BUTTON_WIDTH/2, Const.HEIGHT/2-Const.BUTTON_HEIGHT/2, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT, "MenuButton");
    }

    // check for clicks on the menu button
    @Override
    public void update(){
        this.continueButton.update(this.input);
    }

    // return if player is transitioning to the start screen again
    @Override
    public boolean isTransitioning(){
        return this.continueButton.isClicked();
    }

    @Override
    public void draw(Graphics g){
        // fill the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Const.WIDTH, Const.HEIGHT);
        // draw the menu button
        this.continueButton.draw(g);
        // determine if this player won or lost and which message to display
        g.setFont(FontLoader.EXTRA_LARGE_FONT);
        String toDraw;
        if(this.won){
            g.setColor(Color.GREEN);
            toDraw = "YOU WON";
        }else{
            g.setColor(Color.RED);
            toDraw = "YOU LOST";
        }
        // draw the win or lose message
        int width = g.getFontMetrics().stringWidth(toDraw);
        g.drawString(toDraw, Const.WIDTH/2-width/2, Const.HEIGHT/2-Const.BUTTON_HEIGHT*2);
    }

    @Override
    public void close(){}
    
}
