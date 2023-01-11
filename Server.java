import java.net.*;

public class Server {
    public static final int PORT = 6969;    
    
    private ServerSocket serverSocket;
    private Socket clientInQueue;
    private int clientCounter = 0;
    
    public static void main(String[] args) throws Exception{ 
        Server server = new Server();
    }

    public Server() throws Exception{
        this.serverSocket = new ServerSocket(PORT);
        this.clientInQueue = null;
        this.start();
    }
    
    public void start() throws Exception{     
        System.out.println("Waiting for a connection request from a client ...");
        while(true) {
            Socket clientSocket = this.serverSocket.accept();
            this.clientCounter ++;
            System.out.println("Client " + this.clientCounter + " connected");
            if(this.clientInQueue == null){
                this.clientInQueue = clientSocket;
            }else{
                Thread gameThread = new Thread(new GameThread(this.clientInQueue, clientSocket));
                gameThread.start();
                this.clientInQueue = null;
            }
        }
    }
}