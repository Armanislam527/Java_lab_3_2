import java.io.*;
import java.net.*;

public class Exp9_TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("TCP Server is running on port 5000...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    String message = in.readLine();
                    System.out.println("Received from client: " + message);

                    if (message != null) {
                        String upperCase = message.toUpperCase();
                        out.println(upperCase);
                        System.out.println("Sent back: " + upperCase);
                    }
                }
    }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
