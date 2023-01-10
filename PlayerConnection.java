import java.io.*;
import java.net.*;

public class PlayerConnection {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;

    public PlayerConnection(Socket socket) throws IOException{
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.output = new PrintWriter(this.socket.getOutputStream());
    }
    
}
