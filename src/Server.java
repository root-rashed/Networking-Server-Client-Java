import java.io.*;
import java.net.*;
import java.security.cert.CertificateRevokedException;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server is waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);


            String clientMessage;

            while ((clientMessage = input.readLine()) != null) {
                System.out.println("Client says: " + clientMessage);
                output.println("Server received: " + clientMessage);

                if (clientMessage.equalsIgnoreCase("bye")) break;

            }

            socket.close();
            serverSocket.close();
            System.out.println("Connection closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}