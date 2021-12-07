import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class LostMap {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        ArrayList<IntegerTriple> edgeList = new ArrayList<>();

        int numOfVillage = Integer.parseInt(bf.readLine());
        //make my edgelist
        for (int i = 0; i < numOfVillage; i++) {
            String[] inputs = bf.readLine().split(" ");
            for (int j = 0; j < numOfVillage ; j++) {
                edgeList.add(new IntegerTriple(i, j, Integer.parseInt(inputs[j])));
                //triple contains village a , village b , weight between them
            }
        }
        //sort edgeList by smaller weights first
        Collections.sort(edgeList);

        //set up disjoint sets
        UnionFind disjointSets = new UnionFind(numOfVillage);

        //Start of Krusal (for loop runs faster than while loop)
        for(IntegerTriple edge : edgeList) {
            if (!disjointSets.isSameSet(edge.first, edge.second)) { //dont belong to same set (doesnt form a cycle)
                disjointSets.unionSet(edge.first, edge.second); //union them
                String str = (edge.first + 1) + " " + (edge.second + 1); //need plus 1, village starts from 1
                writer.println(str);
            }
        }
        /*
        while(!edgeList.isEmpty()) {
            IntegerTriple temp = edgeList.get(0); //get first edge
            if (!disjointSets.isSameSet(temp.first, temp.second)) { //dont belong to same set (doesnt form a cycle)
                disjointSets.unionSet(temp.first, temp.second); //union them
                String str = (temp.first + 1) + " " + (temp.second + 1); //need plus 1, village starts from 1
                writer.println(str);
            }
            edgeList.remove(0); //remove edge
        }
         */

        bf.close();
        writer.flush();
        writer.close();
    }
}

class UnionFind {
    public int[] p;
    public int[] rank;

    public UnionFind(int N) {
        p = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
        }
    }

    public int findSet(int i) {
        if (p[i] == i) return i;
        else {
            p[i] = findSet(p[i]);
            return p[i];
        }
    }

    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree short
            if (rank[x] > rank[y])
                p[y] = x;
            else {
                p[x] = y;
                if (rank[x] == rank[y])
                    rank[y] = rank[y]+1;
            }
        }
    }
}

class IntegerTriple implements Comparable<IntegerTriple>{
    int first;
    int second;
    int weight;

    public IntegerTriple(int i, int j, int weight){
        this.first = i;
        this.second = j;
        this.weight = weight;
    }

    @Override
    public int compareTo(IntegerTriple o) {
        return Integer.compare(this.weight, o.weight);
    }
}
