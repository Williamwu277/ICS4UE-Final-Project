import java.net.ServerSocket;
import java.net.Socket;

// class for the server to be run
public class Server {

    public static final int PORT = 6969;    
    
    // instance variables
    private ServerSocket serverSocket;
    private Socket clientInQueue;
    private int clientCounter = 0;
    
    // start up the server
    public static void main(String[] args) throws Exception{ 
        Server server = new Server();
    }

    // constructor
    public Server() throws Exception{
        this.serverSocket = new ServerSocket(PORT);
        this.clientInQueue = null;
        this.start();
    }
    
    // wait for clients to join the game and then matchmake them with each other
    public void start() throws Exception{     
        System.out.println("Waiting for a connection request from a client ...");
        while(true) {
            // wait until a new client has joined
            Socket clientSocket = this.serverSocket.accept();
            this.clientCounter ++;
            System.out.println("Client " + this.clientCounter + " connected");
            // if there is no other client, make this client wait in queue
            if(this.clientInQueue == null){
                this.clientInQueue = clientSocket;
            }else{
                // if there is another player to match, start a new game thread with them
                Thread gameThread = new Thread(new GameThread(this.clientInQueue, clientSocket));
                gameThread.start();
                this.clientInQueue = null;
            }
        }
    }

}