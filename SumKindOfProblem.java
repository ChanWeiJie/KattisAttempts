import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SumKindOfProblem {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        int p = Integer.parseInt(bf.readLine());
        for(int i = 0; i < p; i++) {
            String inputs = bf.readLine();
            String[] separateInputs = inputs.split(" ");
            int k = Integer.parseInt(separateInputs[0]);
            int n = Integer.parseInt(separateInputs[1]);
            int multiply = n*n;
            int mulPlusN = multiply + n;
            writer.write(k + " " + (mulPlusN / 2) + " " + (multiply) + " " + mulPlusN);
            writer.println();
        }
        bf.close();
        writer.flush();
        writer.close();
    }
}
