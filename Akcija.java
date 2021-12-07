import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Akcija {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(arr);
        long sum = 0;
        for(int i = n - 1; i > n % 3; i = i - 3) {
            sum += arr[i];
            sum += arr[i- 1];
        }
        for(int i = 0;i < n % 3; i++) {
            sum+=arr[i];
        }
        writer.print(sum);
        bf.close();
        writer.flush();
        writer.close();

    }
}
