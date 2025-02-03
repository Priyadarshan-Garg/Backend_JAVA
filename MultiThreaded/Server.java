package MultiThreaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;
//import ;

public class Server {
    public Consumer<Socket> getConsumer (){
        return (clientSocket)->{
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Hello from the Server");
                toClient.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
        public static void run() throws IOException{
            int port = 8010;
            Server server = new Server();
            try {
                ServerSocket jerverSocket = new ServerSocket(port);
                jerverSocket.setSoTimeout(10000);
                System.out.println("Server is listening "+port);;
            while(true){
                Socket socketAccepted  = jerverSocket.accept();
                Thread thread = new Thread(()->server.getConsumer().accept(socketAccepted));
                thread.start();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {

    }
}
