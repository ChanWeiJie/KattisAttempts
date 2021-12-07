import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


public class PasswordHacking {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        int n = Integer.parseInt(bf.readLine());
        ArrayList<Double> lst = new ArrayList<>();
        for(int i = 0; i < n; i++){
            String[] input = bf.readLine().split(" ");
            lst.add(Double.parseDouble(input[1]));
        }
        Collections.sort(lst);
        Collections.reverse(lst);
        Double total = 0.0;
        for(int i = 0; i < n; i++) {
            total +=  (i+1) * lst.get(i);
        }
        writer.println(total);
        bf.close();
        writer.flush();
        writer.close();
    }
}
