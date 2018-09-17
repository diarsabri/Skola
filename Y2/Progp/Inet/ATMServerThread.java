import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * @author Viebrapadata
 */
public class ATMServerThread extends Thread {
    private Socket socket = null;
    private BufferedReader in;
    PrintWriter out;

    public ATMServerThread(Socket socket) {
        super("ATMServerThread");
        this.socket = socket;
    }

    private boolean validateUser() {
        return true;
    }

    int balance = 1000;
    int choice;

    public void run() {

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            int menuOption, userInput;

            validateUser();

            C: while (true) {
                menuOption = Integer.parseInt(in.readLine());

                if (menuOption == 0) {
                    break C;
                } else if (menuOption == 1) {
                    out.println(3);
                    out.println(-2); // tells that a value is incoming
                    out.println(balance);

                    out.println(0);
                } else if (menuOption == 2 || menuOption == 3) {
                    out.println(2);
                    out.println(-3);

                    userInput = Integer.parseInt(in.readLine());

                    if (menuOption == 2) {
                        balance -= userInput;
                    } else if (menuOption == 3) {
                        balance += userInput;
                    }
                    out.println(3);
                    out.println(-2); // tells that a value is incoming
                    out.println(balance);

                    out.println(0);
                }
            }

            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
