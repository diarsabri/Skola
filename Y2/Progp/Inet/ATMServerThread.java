import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * @author Diar Sabri & Kevin Nordwall
 */
public class ATMServerThread extends Thread {
    private Socket socket = null;
    private BufferedReader in;
    PrintWriter out;

    public ATMServerThread(Socket socket) {
        super("ATMServerThread");
        this.socket = socket;
    }

    /*
    Function for validating user, takes cardnumber and pin as input.
    Reads the file with that cardnumber and checks to see if the pin code is correct. returns 0 or 1.
    */
    private long validateUser(long cardNumber, long pin) {
        try {
            File file = new File("/home/diar/Desktop/Skola/Y2/Progp/Inet/Users/" + cardNumber + ".txt");
            Scanner scanner = new Scanner(file);
            int filePin = Integer.parseInt(scanner.nextLine());
            int balance = Integer.parseInt(scanner.nextLine());

            if (filePin != pin) {
                scanner.close();
                return 0;
            } else if (filePin == pin) {
                scanner.close();
                return 1;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("FNF");
        }
        return 0;
    }

    /*
    Reads user balance. Takes another input that is for adding/subtracting funds.
    */
    private int userBalance(long cardNumber, int difference) throws IOException {
        try {
            File fileOld = new File("/home/diar/Desktop/Skola/Y2/Progp/Inet/Users/" + cardNumber + ".txt");
            Scanner scanner = new Scanner(fileOld);
            int filePin = Integer.parseInt(scanner.nextLine());
            int balance = Integer.parseInt(scanner.nextLine());

            fileOld.delete();

            File fileNew = new File("/home/diar/Desktop/Skola/Y2/Progp/Inet/Users/" + cardNumber + ".txt");
            fileNew.createNewFile();

            balance += difference;

            PrintStream f = new PrintStream(fileNew);
            f.println(filePin);
            f.println(balance);

            return balance;

        } catch (FileNotFoundException e) {
            System.out.println("FNF");
        }
        return 0;
    }

    int balance;

    public void run() {

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            int menuOption, userInput;

            long cardNumber, pin;
            long validUser;

            // Initial login attempt
            cardNumber = Long.parseLong(in.readLine());
            pin = Long.parseLong(in.readLine());
            validUser = validateUser(cardNumber, pin);
            if (validUser == 1) {
                out.println(validUser);
            } else if (validUser == 0) {    // enters while loop if not valid, repeats until input is valid
                out.println(validUser);
                V2: while (true) {
                    cardNumber = Long.parseLong(in.readLine());
                    pin = Long.parseLong(in.readLine());
                    validUser = validateUser(cardNumber, pin);

                    out.println(validUser);

                    if (validUser == 1) {
                        break V2;
                    }
                }
            }

            //successfull login
            C: while (true) {
                // menuoption 1,2,3.
                menuOption = Integer.parseInt(in.readLine());

                if (menuOption == 0) {
                    break C;
                } else if (menuOption == 1) {   // just prints user current balance. Uses code -2 to client.
                    out.println(3);
                    out.println(-2); // tells that a value is incoming
                    balance = userBalance(cardNumber, 0);
                    out.println(balance);

                    out.println(0);
                } else if (menuOption == 2 || menuOption == 3) {    // withdraw/insert. Uses codes -3 and -2 to client.
                    out.println(2);
                    out.println(-3);

                    userInput = Integer.parseInt(in.readLine());

                    balance = userBalance(cardNumber, 0);

                    if (menuOption == 2) {
                        if (balance < userInput) {
                            out.println(-4);
                        } else if (balance >= userInput) {
                            balance = userBalance(cardNumber, -userInput);
                        }
                    } else if (menuOption == 3) {
                        balance = userBalance(cardNumber, userInput);
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
