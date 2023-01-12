import java.io.*;
import java.net.*;
import java.util.*;

public class PlayerConnection {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private MenuBar menu;
    private int currentTeam;

    public PlayerConnection(Socket socket, int currentTeam){
        this.socket = socket;
        this.currentTeam = currentTeam;
        this.menu = new MenuBar(this.currentTeam, Const.WIDTH-MenuBar.MENU_WIDTH, 0 ,MenuBar.MENU_WIDTH, Const.HEIGHT);
        try{
            this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.output = new PrintWriter(this.socket.getOutputStream());
            System.out.println("WHAT THE FUCK IS GOING ON");
            this.output.println("Opponent Found.");
            this.output.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update(GameMap gameMap){
        this.readInput(gameMap);
        this.sendOutput(gameMap);
    }

    public void sendOutput(GameMap gameMap){
        ArrayList<String> out = new ArrayList<>();
        out.addAll(gameMap.draw(this.menu.getPurchasePending()));
        out.addAll(this.menu.draw());
        this.output.println(out.size());
        for(int i=0; i<out.size(); i++){
            this.output.println(out.get(i));
        }
        this.output.flush();
    }

    public void readInput(GameMap gameMap){
        String[] mouseCoordinates = null;
        try{
            String in = this.input.readLine();
            while(in.equals("")){
                in = this.input.readLine();
            }
            mouseCoordinates = in.split(" ");
        }catch(Exception e){
            e.printStackTrace();
        }
        if(mouseCoordinates != null){
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

    public void endGame(int result){
        if(result == this.currentTeam){
            this.output.println("WIN");
        }else{
            this.output.println("LOSE");
        }
        this.output.flush();
    }

    public String readName(){
        String name = "";
        try{
            String in = this.input.readLine();
            while(in.equals("")){
                in = this.input.readLine();
            }
            name = in.strip();
        }catch(Exception e){
            e.printStackTrace();
        }
        return name;
    }

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
