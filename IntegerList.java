import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


public class IntegerList {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        int c = Integer.parseInt(bf.readLine());
        for(int i = 0; i < c; i++) {
            char[] command = bf.readLine().toCharArray();
            int numOfElement = Integer.parseInt(bf.readLine());
            String input = bf.readLine().replaceAll("\\[", "").replaceAll("\\]","");
            String[] inputs = input.split(",");
            ArrayList<Integer> lst = new ArrayList<>();

            for(int j = 0; j < numOfElement; j++) {
                lst.add(Integer.parseInt(inputs[j]));
            }
            for(int k = 0; k < command.length; k++){
                char com = command[k];
                if(com == 'R'){
                    Collections.reverse(lst);
                } else if(com == 'D') {
                    if(lst.isEmpty()) {
                        writer.write("error");
                    } else {
                        lst.remove(0);
                    }
                }
            }
            for(int x = 0; x < lst.size(); x++){
                if(x == 0) {
                    writer.write("[" + lst.get(x) + ",");
                } else if( x == lst.size() - 1) {
                    writer.write(lst.get(x) + "]");
                } else {
                    writer.write(lst.get(x) + ",");
                }
            }
            writer.println();
        }
        bf.close();
        writer.flush();
        writer.close();
    }
}
