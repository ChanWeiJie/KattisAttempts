import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class WeakVertices {

    public static boolean makeTriangle(int row, int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[row][i] == 1) { //a node is connected to an edge
                for(int j = i + 1; j < arr.length; j++) {
                    if(arr[row][j] == 1 && arr[i][j] == 1) { //check if the node neighbours form a triangle
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        while (true) {
            int numOfGraphs = Integer.parseInt(bf.readLine());
            if (numOfGraphs == -1) {
                break;
            }
            int[][] arr = new int[numOfGraphs][numOfGraphs];

            for (int r = 0; r < numOfGraphs; r++) {
                String[] details = bf.readLine().split(" ");
                for (int c = 0; c < numOfGraphs; c++) {
                    arr[r][c] = Integer.parseInt(details[c]);
                }
            }

            for (int r = 0; r < numOfGraphs; r++) {
                if (!makeTriangle(r, arr)) {
                    writer.print(r + " ");
                }
            }
            writer.println();
        }
        bf.close();
        writer.flush();
        writer.close();
    }
}
