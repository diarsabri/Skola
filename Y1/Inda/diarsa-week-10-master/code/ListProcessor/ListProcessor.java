/**
 * Created by Diar on 2016-11-24.
 * Många metoder som testar bland annat slumpa tal,summera tal, och returna tal mellan intervall
 */
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.sort;

public class ListProcessor {


    /**
     * @param from = från det här numret
     * @param to = till det här numret
     * @return får du de mellanliggande talen
     */
    public int[] fillArray(int from, int to) {
        int a = to - from;
        int[] arrays = new int[a];
        for (int i = 0; from < to; from++, i++) {
            arrays[i] = from;
        }
        System.out.println(Arrays.toString(arrays));
        return arrays;
    }

    /**
     * @param from = från det här numret
     * @param to = till det här numret
     * @return får du de mellanliggande talen
     */
    public List<Integer> fillList(int from, int to) {
        int a = to - from;
        int[] arrays = new int[a];
        for (int i = 0; from < to; from++, i++) {
            arrays[i] = from;
        }
        System.out.println(Arrays.toString(arrays));
        List<Integer> intlist = Arrays.stream(arrays).boxed().collect(Collectors.toList());

        return intlist;
    }

    /**
     * @param numbers = tal från 0 upp till ditt angivna tal
     * @return får du tillbaks här, slumpat
     */
    public int[] shuffleArray(int[] numbers) {
        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) {
            int a = i + random.nextInt(numbers.length - i);
            int b = numbers[i];
            numbers[i] = numbers[a];
            numbers[a] = b;
        }
        System.out.println(Arrays.toString(numbers));
        return numbers;

    }

    /**
     * @param numbers = tal från 0 upp till ditt angivna tal
     * @return får du tillbaks här, slumpat
     */
    public List<Integer> shuffleList(List<Integer> numbers)
    {
        Random random = new Random();
        for (int i = 0; i < numbers.size(); i++) {
            int a = i + random.nextInt(numbers.size() - i);
            int b = numbers.get(i);
            numbers.set(i,numbers.get(a));
            numbers.set(a,b);
        }
        return numbers;
    }

    /**
     * @param numbers tal från 0 upp till ditt angivna tal
     * @return får du tillbaks här, summerat
     */
    public int sumArrayIterative(int[] numbers)
    {
        int a = 0;
        for(int i = 0;i<numbers.length;i++) {
            a += numbers[i];
        }
        System.out.println("The sum is: " +a);
        return a;
    }

    /**
     * @param numbers tal från 0 upp till ditt angivna tal
     * @return får du tillbaks här, summerat
     */
    public int sumListIterative(List<Integer> numbers)
    {
        int a = 0;
        for(int i: numbers) {
            a += i;
        }
        System.out.println("The sum is: " +a);
        return a;
    }

    /**
     * @param numbers tal från 0 upp till ditt angivna tal
     * @return får du tillbaks här, summerat
     */
    public int sumArrayRecursive(int[] numbers)
    {
        int a = 0;
        for(int b = 0; b<numbers.length;b++) {
            a = a + numbers[b];
        }
        System.out.println("The sum is: "+a);
        return a;
    }

    /**
     * @param numbers tal från 0 upp till ditt angivna tal
     * @return får du tillbaks här, summerat
     */
    public int sumListRecursive(List<Integer> numbers)
    {
        if(numbers.size()==0) {
            return 0;
        }
        else {
            int a = numbers.get(0) +sumListRecursive(numbers.subList(1,numbers.size()));
            return a;
        }
    }
}


