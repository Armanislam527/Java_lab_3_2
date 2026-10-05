import java.io.*;
import java.net.*;

public class Exp9_TCPClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            String message = "hello from client";
            output.write(message + "\n");
            output.flush();

            String response = input.readLine();
            System.out.println("Server replied: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
