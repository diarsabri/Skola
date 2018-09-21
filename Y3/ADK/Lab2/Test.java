public class Test {

    public int partDist(String w1, String w2, int w1len, int w2len) {
        if (w1len == 0)
          return w2len;
        if (w2len == 0)
          return w1len;
        int res = partDist(w1, w2, w1len - 1, w2len - 1) + 
        (w1.charAt(w1len - 1) == w2.charAt(w2len - 1) ? 0 : 1);
        int addLetter = partDist(w1, w2, w1len - 1, w2len) + 1;
        if (addLetter < res)
          res = addLetter;
        int deleteLetter = partDist(w1, w2, w1len, w2len - 1) + 1;
        if (deleteLetter < res)
          res = deleteLetter;

          // System.out.println("res"+"   "+res);
          // System.out.println("add"+"   "+addLetter);
          // System.out.println("del"+"   "+deleteLetter);
          // System.out.println("\n");
        return res;
      }

    public static void main(String[] args)
    {
        Test test = new Test();

        System.out.print(test.partDist("labd","blad", 0, 0));
        System.out.print(test.partDist("labd","blad", 0, 1));
        System.out.print(test.partDist("labd","blad", 0, 2));
        System.out.print(test.partDist("labd","blad", 0, 3));
        System.out.print(test.partDist("labd","blad", 0, 4));
        System.out.println("\n");

        System.out.print(test.partDist("labd","blad", 1, 1));
        System.out.print(test.partDist("labd","blad", 2, 1));
        System.out.print(test.partDist("labd","blad", 3, 1));
        System.out.print(test.partDist("labd","blad", 4, 1));
        System.out.println("\n");

        System.out.print(test.partDist("labd","blad", 1, 2));
        System.out.print(test.partDist("labd","blad", 2, 2));
        System.out.print(test.partDist("labd","blad", 3, 2));
        System.out.print(test.partDist("labd","blad", 4, 2));
        System.out.println("\n");

        System.out.print(test.partDist("labd","blad", 1, 3));
        System.out.print(test.partDist("labd","blad", 2, 3));
        System.out.print(test.partDist("labd","blad", 3, 3));
        System.out.print(test.partDist("labd","blad", 4, 3));
        System.out.println("\n");


        System.out.print(test.partDist("labd","blad", 1, 4));
        System.out.print(test.partDist("labd","blad", 2, 4));
        System.out.print(test.partDist("labd","blad", 3, 4));
        System.out.print(test.partDist("labd","blad", 4, 4));
        System.out.println("\n");




     
    }


}