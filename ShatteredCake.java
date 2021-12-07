import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ShatteredCake {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        int width = Integer.parseInt(bf.readLine());
        int n = Integer.parseInt(bf.readLine());
        int totalArea = 0;
        while (n > 0) {
            int w, l;
            String input = bf.readLine();
            String[] separateInput = input.split(" ");
            w = Integer.parseInt(separateInput[0]);
            l = Integer.parseInt(separateInput[1]);
            totalArea += w*l;
            n--;
        }
        writer.println(totalArea / width);
        bf.close();
        writer.flush();
        writer.close();
    }
}
