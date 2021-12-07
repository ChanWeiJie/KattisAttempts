import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SortOfsorting {
    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);
        while(true) {
            int n = Integer.parseInt(bf.readLine());
            if (n == 0) {
                break;
            } else {
                String[] names = new String[n];
                for (int i = 0; i < n; i++) {
                    names[i] = bf.readLine();
                }
                Arrays.sort(names, Comparator.comparing(x -> x.substring(0, 2)));
                for (int i = 0; i < n; i++) {
                    writer.write(names[i]);
                    writer.println();
                }
                writer.println();
                writer.flush();
            }
        }
        r.close();
        bf.close();
        writer.close();
    }
}
