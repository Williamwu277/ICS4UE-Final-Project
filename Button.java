import java.awt.*;

public class Button extends Thread{
    
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean threadAlive;
    private boolean buttonClicked;
    private boolean buttonHeld;
    private InputListener input;

    public Button(InputListener input, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.input = input;
        this.threadAlive = true;
        this.start();
    }

    @Override
    public void run(){
        while(this.threadAlive){
            if(x <= input.getX() && input.getX() <= x+width && y <= input.getY() && input.getY() <= y+height && input.isClicked()){
                if(!this.buttonClicked && !this.buttonHeld){
                    this.buttonClicked = true;
                }
                this.buttonHeld = true;
            }else{
                this.buttonHeld = false;
            }
            this.pause();
        }
    }

    public void kill(){
        this.threadAlive = false;
    }

    public void pause(){
        try{
            Thread.sleep(Const.PAUSE);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isClicked(){
        boolean answer = this.buttonClicked;
        this.buttonClicked = false;
        return answer;
    }

    public void draw(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(x, y, width, height);
    }

}
