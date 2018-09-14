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

    private String readLine() throws IOException {
        String str = in.readLine();
        // System.out.println("" + socket + " : " + str);
        return str;
    }

    private boolean validateUser() {
        return true;
    }

    static public List changeLang2(int i) {
        try {
            File file = new File("/home/diar/Desktop/Skola/Y2/Progp/Inet/lang.txt");
            Scanner scanner = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                list.add(s);
            }
            // for(int k = 0;k<list.size();k++){
            // System.out.println("#"+k+" "+list.get(k).toString());
            // }
            ArrayList<List<String>> langList = new ArrayList<List<String>>();
            for (int x = 0; x < list.size(); x += 6) {
                int y = Math.min(x + 6, list.size());
                langList.add(list.subList(x, y));
            }

            // System.out.println("langList: "+langList.get(i));
            scanner.close();
            return langList.get(i);

        } catch (FileNotFoundException e) {
            System.out.println("No such file");
        }
        return null;
    }

    List list = changeLang2(1);
    int balance = 1000;
    int choice;

    public void run() {

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            int menuOption, userInput;

            validateUser();

            menuOption = Integer.parseInt(in.readLine());

            B: while (true) {
                if (menuOption == 0) {
                    out.println(0);
                    break B;
                } else if (menuOption == 1) {
                    out.println(3);
                    out.println(-2); // tells that a value is incoming
                    out.println(balance);

                    out.println(0);
                } else if (menuOption == 2 | menuOption == 3) {
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

            out.println(list.get(5).toString());
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
