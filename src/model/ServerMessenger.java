package model;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMessenger {
    private ServerSocket serverSocket;

    public ServerMessenger(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    /*the startServer method is a method that will start the server and
    keep the server running while the serverSocket is not closed.*/
    public void startServer(){
       try {
           while (!serverSocket.isClosed()) {
               Socket socket = serverSocket.accept();
               System.out.println("A new client have been connected");

               ClientManager clientHandler = new ClientManager(socket);
               Thread thread = new Thread(clientHandler);
               thread.start();
           }
       } catch (IOException e){

       }
    }
    public void closeServer(){
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e){
            e.getStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        ServerMessenger server = new ServerMessenger(serverSocket);
        server.startServer();
    }
}
