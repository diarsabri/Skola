import java.io.*;
import java.net.*;
import java.util.*;

// https://cloud.smartdraw.com/share.aspx/?pubDocShare=BE716D12155D08BAC98179A718176CE6B9E

//  sudo netstat -ntulp
//  ss -tanp | grep 8989 | head -1 | sed 's_.*pid=\(.*\),.*_\1_' | xargs kill

// To run:  java ATMServer &
//          java ATMClient 127.0.0.1

/**
 * @author Diar Sabri & Kevin Nordwall
 */
public class ATMClient {
    private static int connectionPort = 8989;

    public static List changeLang2(int i) {
        try {
            File file = new File("/home/diar/Desktop/Skola/Y2/Progp/Inet/lang.txt");
            Scanner scanner = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                list.add(s);
            }

            ArrayList<List<String>> langList = new ArrayList<List<String>>();
            for (int x = 0; x < list.size(); x += 9) {
                int y = Math.min(x + 9, list.size());
                langList.add(list.subList(x, y));
            }

            scanner.close();
            return langList.get(i);

        } catch (FileNotFoundException e) {
            System.out.println("No such file");
        }
        return null;
    }

    public static List availableLang() {
        try {
            File file = new File("/home/diar/Desktop/Skola/Y2/Progp/Inet/lang.txt");
            Scanner scanner = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                list.add(s);
            }

            ArrayList<List<String>> langList = new ArrayList<List<String>>();
            for (int x = 0; x < list.size(); x += 9) {
                int y = Math.min(x + 9, list.size());
                langList.add(list.subList(x, y));
            }

            scanner.close();

            ArrayList<String> langs = new ArrayList<String>();

            for (int i = 0; i < langList.size(); i++) {
                langs.add(langList.get(i).get(8));
            }

            return langs;

        } catch (FileNotFoundException e) {
            System.out.println("No such file");
        }
        return null;
    }

    public static long readLong(Scanner scanner) {
        try {
            String x = scanner.nextLine().trim();
            long res = Long.valueOf(x);
            return res;
        } catch (InputMismatchException e) {
            System.out.println("Digits only");
        } catch (NumberFormatException e) {
            System.out.println("Digits only");
        }
        return 0;
    }

    public static int readInt(Scanner scanner) {
        try {
            String x = scanner.nextLine().trim();
            int res = Integer.valueOf(x);
            return res;
        } catch (InputMismatchException e) {
            System.out.println("Digits only");
        } catch (NumberFormatException e) {
            System.out.println("Digits only");
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {

        Socket ATMSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String adress = "";

        try {
            adress = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing argument ip-adress");
            System.exit(1);
        }
        try {
            ATMSocket = new Socket(adress, connectionPort);
            out = new PrintWriter(ATMSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(ATMSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + adress);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't open connection to " + adress);
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);
        List list = changeLang2(1); // English as initial language
        List langs = availableLang();
        int userInput;
        int number;

        long cardNumber, pin;
        long validUser;

        System.out.println("Enter card number");
        cardNumber = readLong(scanner);
        System.out.println("Enter pin");
        pin = readLong(scanner);

        out.println(cardNumber);
        out.println(pin);
        validUser = Long.parseLong(in.readLine());

        if (validUser == 1) {
            System.out.println("Successful login! \n");
        } else {

            V1: while (true) {
                System.out.println("Invalid input, try again.");
                System.out.println("Enter card number");
                cardNumber = readLong(scanner);
                System.out.println("Enter pin");
                pin = readLong(scanner);

                out.println(cardNumber);
                out.println(pin);
                validUser = Long.parseLong(in.readLine());

                if (validUser == 1) {
                    System.out.println("Successful login!");
                    break V1;
                }
            }
        }

        System.out.println("Contacting bank ... ");
        System.out.println(list.get(1));
        System.out.print("> ");

        int menuOption = readInt(scanner);
        userInput = menuOption;
        int toServer, fromServer;

        A: while (true) {
            if (menuOption == 5) { // break
                System.out.println(list.get(5));
                out.println(0);
                break A;
            } else if (menuOption == 4) { // change language
                System.out.println("Choose language by entering digit");
                for (int i = 0;i<langs.size();i++) {
                    System.out.println("("+i+")"+" "+langs.get(i));
                }
                System.out.print("> ");
                userInput = readInt(scanner); // choosing language
                list = changeLang2(userInput); // sets language
                System.out.println(list.get(0)); // prints welcome message in specified language
                System.out.print("> ");

                menuOption = readInt(scanner); // next menuoption
                userInput = menuOption; // next menuoption
            } else if (menuOption <= 0 || menuOption > 5) {
                System.out.println(list.get(6));

                System.out.println(list.get(1));
                System.out.print("> ");
                menuOption = readInt(scanner); // next menuoption
                userInput = menuOption; // next menuoption
            } else if (menuOption > 0 && (menuOption != 4 && menuOption != 5)) { // everything else here
                toServer = menuOption;
                out.println(toServer);
                fromServer = Integer.parseInt(in.readLine());

                B: while (true) {

                    if (fromServer == 0) {
                        break B;
                    } else if (fromServer == -2) { // value of users balance
                        number = Integer.parseInt(in.readLine());
                        System.out.println(number + " " + list.get(4).toString());
                        fromServer = Integer.parseInt(in.readLine());
                    } else if (fromServer == -3) {
                        number = readInt(scanner);
                        out.println(number);
                        fromServer = Integer.parseInt(in.readLine());
                    } else if (fromServer == -4) {
                        System.out.println(list.get(7));

                        fromServer = Integer.parseInt(in.readLine());
                    } else if (fromServer > 0) {
                        System.out.println(list.get(fromServer));
                        fromServer = Integer.parseInt(in.readLine());
                    }

                }
                System.out.println(list.get(1));
                System.out.print("> ");
                menuOption = readInt(scanner);
                toServer = menuOption;
            }

        }

        scanner.close();
        out.close();
        in.close();
        ATMSocket.close();
    }
}
