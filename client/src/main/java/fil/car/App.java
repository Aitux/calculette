package fil.car;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        Socket as = null;
        try {
            while (true) {
                as = new Socket(InetAddress.getLocalHost(), 4000);
                System.out.println("Client: Connection established.");
                DataOutputStream out = new DataOutputStream(as.getOutputStream());
                BufferedReader bfr = new BufferedReader(
                        new InputStreamReader(as.getInputStream()));
                String response = bfr.readLine();
                System.out.println("CLIENT: " + response);
                Scanner in = new Scanner(System.in);
                String operation = in.nextLine();
                if (operation.equals(".")) {
                    return;
                }
                System.out.println(operation);
                out.writeBytes(operation + "\n");
                out.flush();
                response = bfr.readLine();

                System.out.println(response);
            }
        } catch (IOException ex) {
            System.exit(-1);
        }finally {
            try {
                as.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
