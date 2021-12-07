import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

public class FerryLoading4 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        int c = Integer.parseInt(bf.readLine());
        for(int i = 0; i < c; i++) {
            String[] separateInputs = bf.readLine().split(" ");
            int boatLen = Integer.parseInt(separateInputs[0]) * 100;
            int cars = Integer.parseInt(separateInputs[1]);
            LinkedList<Integer> leftQueue = new LinkedList<>();
            LinkedList<Integer> rightQueue = new LinkedList<>();
            for (int j = 0; j < cars; j++) {
                separateInputs = bf.readLine().split(" ");
                int carLen = Integer.parseInt(separateInputs[0]);
                String indicator = separateInputs[1];
                if (indicator.equals("left")) {
                    leftQueue.add(carLen);
                } else {
                    rightQueue.add(carLen);
                }
            }
            int count = 0;
            boolean left = true;
            while (!leftQueue.isEmpty() || !rightQueue.isEmpty()) {
                int loadLength = 0;
                if(left) {
                    while(!leftQueue.isEmpty() && loadLength + leftQueue.peek() <= boatLen) {
                        loadLength += leftQueue.peek();
                        leftQueue.removeFirst();
                    }
                    left = false;
                    count++;
                } else if(!left) {
                    while(!rightQueue.isEmpty() && loadLength + rightQueue.peek() <= boatLen) {
                        loadLength += rightQueue.peek();
                        rightQueue.removeFirst();
                    }
                    left = true;
                    count++;
                }
            }
            writer.println(count);
        }
        bf.close();
        writer.flush();
        writer.close();
    }
}
