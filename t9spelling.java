import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class T9spelling {
    public static void main(String[] args) throws IOException {
        HashMap<Character, Integer> hm = new HashMap<>();
        hm.put('a', 2);
        hm.put('b', 22);
        hm.put('c', 222);
        hm.put('d', 3);
        hm.put('e', 33);
        hm.put('f', 333);
        hm.put('g', 4);
        hm.put('h', 44);
        hm.put('i', 444);
        hm.put('j', 5);
        hm.put('k', 55);
        hm.put('l', 555);
        hm.put('m', 6);
        hm.put('n', 66);
        hm.put('o', 666);
        hm.put('p', 7);
        hm.put('q', 77);
        hm.put('r', 777);
        hm.put('s', 7777);
        hm.put('t', 8);
        hm.put('u', 88);
        hm.put('v', 888);
        hm.put('w', 9);
        hm.put('x', 99);
        hm.put('y', 999);
        hm.put('z', 9999);
        hm.put(' ', 0);

        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        int numOfCases = Integer.parseInt(bf.readLine());
        for(int i = 0; i < numOfCases; i++) {
            String temp = bf.readLine();
            StringBuilder display = new StringBuilder();
            display.append("Case #" + (i + 1) + ":" +" ");
            char[] arrOfInput = temp.toCharArray();
            display.append(hm.get(arrOfInput[0]));
            int before = hm.get(arrOfInput[0]);
            int beforeFirst = Integer.parseInt(Integer.toString(before).substring(0,1));
            for(int j = 1; j < arrOfInput.length; j++ ) {
                int next = hm.get(arrOfInput[j]);
                int nextFirst = Integer.parseInt(Integer.toString(next).substring(0,1));
                if (nextFirst == beforeFirst){
                    display.append(" ");
                }
                display.append(next);
                beforeFirst = nextFirst;
            }
            writer.write(display.toString());
            writer.println();
        }
        r.close();
        bf.close();
        writer.flush();
        writer.close();
    }
}
