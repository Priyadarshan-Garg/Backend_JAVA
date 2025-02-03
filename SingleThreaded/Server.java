package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void run() throws IOException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while (true){
            try{
                System.out.println("Server is listening "+port);
               Socket gotAccepted = socket.accept();
                System.out.println("Connection got accepted from client " +gotAccepted.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(gotAccepted.getOutputStream());
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(gotAccepted.getInputStream()));
                toClient.print("Hello from the Server");
                toClient.close();
                fromClient.close();
                gotAccepted.close();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
