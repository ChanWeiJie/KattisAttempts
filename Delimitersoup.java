import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;

public class Delimitersoup {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        int l = Integer.parseInt(bf.readLine());
        char[] arr = bf.readLine().toCharArray();
        Stack<Character> stk = new Stack<>();
        for(int i = 0; i < l; i++) {
            char c = arr[i];
            if(c == ' ') {
                continue;
            } else if(c == ')' || c == ']' || c == '}' ) {
                if(stk.isEmpty()) {
                    writer.write(c + " " + i);
                    writer.close();
                } else {
                    char check = stk.pop();
                    if(check != (c == ')' ? c - 1 : c - 2)) {
                        writer.write(c + " " + i);
                        writer.close();
                    }
                }
            } else {
                stk.push(c);
            }
        }
        writer.write("ok so far");
        bf.close();
        writer.flush();
        writer.close();
    }
}
