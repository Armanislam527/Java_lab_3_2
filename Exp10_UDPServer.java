import java.net.*;

public class Exp10_UDPServer {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(6000)) {
            System.out.println("UDP Server is running on port 6000...");

            while (true) {
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received: " + message);

                String upperCase = message.toUpperCase();
                byte[] sendBuffer = upperCase.getBytes();

                Thread.sleep(6);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                socket.send(sendPacket);
                System.out.println("Sent back: " + upperCase);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
