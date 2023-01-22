import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

// class for the server's game threads to communicate with each player separately
public class PlayerConnection {

    // instance variables
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private MenuBar menu;
    private int currentTeam;

    // constructor
    public PlayerConnection(Socket socket, int currentTeam){
        this.socket = socket;
        this.currentTeam = currentTeam;
        this.menu = new MenuBar(this.currentTeam, Const.WIDTH-MenuBar.MENU_WIDTH, 0 ,MenuBar.MENU_WIDTH, Const.HEIGHT);
        try{
            // tell the user on the client side that an opponent has been found and that the game can be started
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.output = new PrintWriter(this.socket.getOutputStream());
            this.output.println("Opponent Found.");
            this.output.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // read in client input and return game data to client
    public void update(GameMap gameMap){
        this.readInput(gameMap);
        this.sendOutput(gameMap);
    }

    // data to be sent to the client
    public void sendOutput(GameMap gameMap){
        ArrayList<String> out = new ArrayList<>();
        // send instructions for how to draw the state of the game and the menu bar
        out.addAll(gameMap.draw(this.menu.getPurchasePending(), this.currentTeam));
        out.addAll(this.menu.draw());
        // output how many lines are to be read
        this.output.println(out.size());
        // output each line
        for(int i=0; i<out.size(); i++){
            this.output.println(out.get(i));
        }
        this.output.flush();
    }

    // input to be read from client
    public void readInput(GameMap gameMap){
        String[] mouseCoordinates = null;
        try{
            // read the mouse coordinates
            String in = this.input.readLine();
            while(in.equals("")){
                in = this.input.readLine();
            }
            mouseCoordinates = in.split(" ");
        }catch(Exception e){
            e.printStackTrace();
        }
        // if coordinates have been sent
        if(mouseCoordinates != null){
            // update the menu with the mouse data
            int x = Integer.parseInt(mouseCoordinates[0]);
            int y = Integer.parseInt(mouseCoordinates[1]);
            boolean clicked;
            if(mouseCoordinates[2].equals("0")){
                clicked = false;
            }else{
                clicked = true;
            }
            menu.update(gameMap, x, y, clicked);
        }
    }

    // send a message to the client when the game has finished
    public void endGame(int result){
        if(result == this.currentTeam){
            this.output.println("WIN");
        }else{
            this.output.println("LOSE");
        }
        this.output.flush();
    }

    // read the clients username at the beginning of the game
    public String readName(){
        String name = "";
        try{
            String in = this.input.readLine();
            while(in.equals("")){
                in = this.input.readLine();
            }
            name = in.trim();
        }catch(Exception e){
            e.printStackTrace();
        }
        return name;
    }

    // when the game has finished, kill communications
    public void kill(){
        try{
            this.input.close();
            this.output.close();
            this.socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
