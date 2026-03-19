import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server.");

            BufferedReader userInput = new BufferedReader(
                    new InputStreamReader(System.in));
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true);

            String message;

            while (true) {
                System.out.print("Enter message: ");
                message = userInput.readLine();

                output.println(message);

                String reply = input.readLine();
                System.out.println("Server: " + reply);


                if( message.length() > 10 || message.isEmpty() || message.equals("exit")){
                    output.println("Server received: Error");
                }


                if (message.equalsIgnoreCase("bye")) break;
            }

            socket.close();
            System.out.println("Disconnected.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
