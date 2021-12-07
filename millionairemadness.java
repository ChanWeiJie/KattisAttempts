import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class Millionairemadness {

    public static final int[][] DIRECTIONS = {{1,0}, {-1,0}, {0, -1}, {0,1}};
    // {1,0} down , {-1 ,0} up , {0, -1} left, {0,1} right

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        String[] rc = bf.readLine().split(" ");
        int row = Integer.parseInt(rc[0]);
        int col = Integer.parseInt(rc[1]);

        Vertex[][] vertices = new Vertex[row][col];
        boolean[][] visited = new boolean[row][col]; //taken array

        //reading in the arrayMatrix
        for (int i = 0; i < row ; i++) {
            String[] input = bf.readLine().split(" ");
            for (int j = 0; j < col ; j++) {
                vertices[i][j] = new Vertex(i,j, Integer.parseInt(input[j]));
                visited[i][j] = false;
            }
        }

        //start of BFS
        int ladderHeight = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        Vertex source = new Vertex(0,0,0); // start at zero not at height of the first coin
        Vertex dest = vertices[row - 1][col - 1];
        pq.add(source);
        visited[source.row][source.col] = true;

        while(!pq.isEmpty()) {
            Vertex cur = pq.poll();
            visited[cur.row][cur.col] = true; //update visit only when off the queue
            //for all the neighbours
            if(cur.row == dest.row && cur.col == dest.col) {
                ladderHeight = cur.height;
                break; //need break when found the very first SP
            } else {
                for (int[] eachRow : DIRECTIONS) {
                    int nextRow = cur.row + eachRow[0];
                    int nextCol = cur.col + eachRow[1];
                    if ((nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col)) { //valid neighbour ?
                        if (!visited[nextRow][nextCol]) { //neighbour visited?
                            pq.add(new Vertex(nextRow, nextCol, Math.max(cur.height,
                                    vertices[nextRow][nextCol].height - vertices[cur.row][cur.col].height)));
                            //^ getting edges
                        }
                    }
                }
            }
        }
        writer.println(ladderHeight);
        bf.close();
        writer.flush();
        writer.close();
    }
}

class Vertex implements Comparable<Vertex>{
    int row;
    int col;
    int height;

    public Vertex(int r, int c, int h) {
        this.row = r;
        this.col = c;
        this.height = h;
    }

    @Override
    public int compareTo(Vertex o) {
        if(this.height < o.height) {
            return -1; //no swap
        } else if(this.height > o.height) {
            return 1;
        } else {
            return 0;
        }
    }
}