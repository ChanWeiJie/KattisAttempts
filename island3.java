import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Island3 {

    public static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    // {1,0} down , {-1 ,0} up , {0, -1} left, {0,1} right

    public static void BFS(Land source, boolean[][] visited, char[][] grid, int maxR, int maxC) {
        Queue<Land> temp = new LinkedList<>();
        temp.add(source);
        while (!temp.isEmpty()) {
            Land t = temp.poll();
            //for all neighbours to t
            for (int[] eachRow : DIRECTIONS) {
                int nextRow = t.row + eachRow[0];
                int nextCol = t.col + eachRow[1];
                //valid range checking (check if got neighbour)
                if((nextRow >= 0 && nextRow < maxR
                        && nextCol >= 0 && nextCol < maxC
                        && grid[nextRow][nextCol] != 'W')) {
                    if(!visited[nextRow][nextCol]) {
                        visited[nextRow][nextCol] = true;
                        temp.add(new Land(nextRow, nextCol)); // only add to queue those that are L/C
                    }
                }
            }
        }
    }
    public static void main (String[]args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        String[] rc = bf.readLine().split(" ");
        int row = Integer.parseInt(rc[0]);
        int col = Integer.parseInt(rc[1]);

        char[][] grid = new char[row][col]; //Matrix
        boolean[][] visited = new boolean[row][col]; //visited array
        Queue<Land> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            String input = bf.readLine();
            for (int j = 0; j < col; j++) {
                grid[i][j] = input.charAt(j);
                visited[i][j] = false;
                if (grid[i][j] == 'L') {
                    queue.add(new Land(i, j));
                }
            }
        }

        //Start of BFS
        int islandCounter = 0;
        while (!queue.isEmpty()) {
            Land source = queue.poll();
            if(!visited[source.row][source.col]) {
                visited[source.row][source.col] = true;
                BFS(source, visited, grid, row, col);
                islandCounter++;
            }
        }
        writer.println(islandCounter);
        bf.close();
        writer.flush();
        writer.close();
    }
}

class Land {
    int row;
    int col;

    public Land(int r , int c) {
        this.row = r;
        this.col = c;
    }
}