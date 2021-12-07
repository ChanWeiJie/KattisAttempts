import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Ladice {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        String[] details = bf.readLine().split(" ");
        int item = Integer.parseInt(details[0]);
        int drawers = Integer.parseInt(details[1]);
        String success = "LADICA";
        String fail = "SMECE";
        UnionFind disjointSet = new UnionFind(drawers); // model each drawer as a disjointSet
        for (int i = 0; i < item; i++) {
            details = bf.readLine().split(" ");
            int drawer1 = Integer.parseInt(details[0]) - 1; // 0 base indexing
            int drawer2 = Integer.parseInt(details[1]) - 1;
            disjointSet.unionSet(drawer1,drawer2);
            boolean occupied = disjointSet.filled(disjointSet.findSet(drawer1));
            if(occupied) {
                writer.println(success);
            } else {
                writer.println(fail);
            }
        }
        bf.close();
        writer.flush();
        writer.close();
    }
}

class UnionFind {
    public int[] p;
    public int[] rank;
    public int[] available;

    public UnionFind(int N) {
        p = new int[N];
        rank = new int[N];
        available = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
            available[i] = 1; // each drawer is available at first
        }
    }

    public int findSet(int i) {
        if (p[i] == i) return i;
        else {
            p[i] = findSet(p[i]);
            return p[i];
        }
    }

    //method to decrement availability of parent
    public boolean filled(int set) {
        if (available[set] != 0) {
            available[set]--;
            return true;
        }
        return false;
    }

    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree short
            // parent node will keep track of how many drawers are available
            if (rank[x] >= rank[y]) {
                p[y] = x;
                available[x] += available[y];
                available[y] = 0;
            } else {
                p[x] = y;
                if (rank[x] == rank[y]) {
                    rank[y]++;
                    available[y] += available[x];
                    available[x] = 0;
                }
            }
        }
    }
}