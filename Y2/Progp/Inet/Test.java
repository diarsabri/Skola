import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.math.*;

public class Test {


    // private void changeLang(File file) throws FileNotFoundException{

    //     Scanner scanner = new Scanner(file);
    //     ArrayList<String> list = new ArrayList<>();

    //     while(scanner.hasNextLine()) {
    //         String s = scanner.nextLine();
    //         list.add(s);
    //     }
    //     System.out.println(list.toString());
    //     scanner.close();
    // }

    static public List changeLang2(int i) throws FileNotFoundException{
        
        File file = new File("/home/diar/Desktop/Skola/Y2/Progp/Inet/lang.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String> list = new ArrayList<>();

        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            list.add(s);
        }
        // for(int k = 0;k<list.size();k++){
        // System.out.println("#"+k+" "+list.get(k).toString());
        // }
        ArrayList<List<String>> langList = new ArrayList<List<String>>();
        for(int x=0;x<list.size();x+=6){
            int y = Math.min(x+6,list.size());
            langList.add(list.subList(x, y));
        }

        // System.out.println("langList: "+langList.get(i));
        return langList.get(i);
    }
  

    public static void main(String[] args) throws Exception {
        // Test test = new Test();
        // File file = new File("/home/diar/Desktop/Skola/Year2/ProgP/Annat/Inet/diarsa-Inet/ATM/lang.txt");
        // test.changeLang(file);

        List list = changeLang2(0);
        System.lineSeparator();
        for(int i = 0;i<list.size();i++){
            System.out.println("#"+i+" "+list.get(i).toString());
        }
    }
}



            // while (choise != 5) {
            //     int deposit = 1;
            //     switch (choise) {
            //     case 2:
            //         deposit = -1;
            //     case 3:
            //         out.println(list.get(2).toString());
            //         //out.println("Enter amount: ");	
            //         inputLine= readLine();
            //         value = Integer.parseInt(inputLine);
            //         balance += deposit * value;
            //     case 1:
            //         out.println(list.get(3).toString()+balance+list.get(4).toString());
            //         out.println(list.get(1).toString());
            //         // out.println("Current balance is " + balance + " dollars");
            //         // out.println("(1)Balance, (2)Withdrawal, (3)Deposit, (4)Exit");
            //         inputLine=readLine();
            //         choise = Integer.parseInt(inputLine);
            //         break;
            //     case 4:
            //         out.println("Choose language by entering digit: (0)Swe, (1)Eng, (2)Gre");
            //         inputLine = readLine();
            //         list = changeLang2(Integer.parseInt(inputLine));
            //         // out.println(list.get(1).toString());
            //         run();
            //     case 5:
            //         break;
            //    default: 
            //         break;
            //     }
            // }
