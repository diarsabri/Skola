import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.math.*;

public class Test {


    public static List changeLang(int i) {
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

    public static void main(String[] args) throws Exception {

        List list = changeLang(0);

        List lang = availableLang();

        System.lineSeparator();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("#" + i + " " + list.get(i).toString());
        }

    }
}