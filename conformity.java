import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Conformity {
    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        int n = Integer.parseInt(bf.readLine());
        int count = 0;
        int popular = 0;
        HashMap<String , Integer> hm = new HashMap<>();
        for(int i = 0; i < n; i++) {
            String numInfo = bf.readLine();
            String[] numbers = numInfo.split(" ");
            Arrays.sort(numbers);
            String temp = "";

            for (String num : numbers) {
                temp += num;
            }

            if(hm.containsKey(temp)) {
                hm.put(temp, hm.get(temp) + 1);
            } else {
                hm.put(temp, 1);
            }

            if(hm.get(temp) > popular) {
                popular = hm.get(temp);
            }
        }

        for(Map.Entry<String, Integer> entry : hm.entrySet()) {
            if(entry.getValue() == popular) {
                count += entry.getValue();
            }
        }
        writer.print(count);
        r.close();
        bf.close();
        writer.flush();
        writer.close();
    }
}
