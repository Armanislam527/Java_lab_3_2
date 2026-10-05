import java.net.*;

public class Exp10_UDPClient {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            String message = "hello udp client";
            byte[] sendBuffer = message.getBytes();
            InetAddress serverAddress = InetAddress.getByName("localhost");

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 6000);
            socket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server replied: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
