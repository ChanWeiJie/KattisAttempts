import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

public class Dominos {
    public static boolean[] visited;
    public static ArrayList<ArrayList<Integer>> adjList;
    public static Stack<Integer> toposort = new Stack<>();

    static void DFSforTOPO(int u) {
        visited[u] = true;
        for (int i = 0; i < adjList.get(u).size(); i++) { // get all the neighbours
            if(!visited[adjList.get(u).get(i)]){
                DFSforTOPO(adjList.get(u).get(i));
            }
        }
        toposort.push(u);
    }

    static void DFSNormal(int u) {
        visited[u] = true;
        for (int i = 0; i < adjList.get(u).size(); i++) { // get all the neighbours
            if(!visited[adjList.get(u).get(i)]){
                DFSNormal(adjList.get(u).get(i));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        int numOfTestCase = Integer.parseInt(bf.readLine());
        for (int i = 0; i < numOfTestCase ; i++) {
            String[] nm = bf.readLine().split(" ");
            int numOfDomino = Integer.parseInt(nm[0]);
            int lines = Integer.parseInt(nm[1]);
            visited = new boolean[numOfDomino];
            adjList = new ArrayList<>(); //reset my adjList

            for (int j = 0; j < numOfDomino; j++) {
                adjList.add(new ArrayList<>()); //initializing each index with an ArrayList
                visited[j] = false; //intially all false
            }

            for (int j = 0; j < lines; j++) {
                String[] xy = bf.readLine().split(" ");
                int x = Integer.parseInt(xy[0]) - 1; // adjlist is 0 based index
                int y = Integer.parseInt(xy[1]) - 1;
                adjList.get(x).add(y); //get the respective index then add the neighbour
            }

            toposort = new Stack<>();//clear toposort

            for (int j = 0; j < numOfDomino; j++) {
                if(!visited[j]) {
                    DFSforTOPO(j); //add to toposort dont need transpose, using a stack
                }
            }

            int count = 0;
            for(int j = 0; j < numOfDomino; j++) { //re-set all to not visited because of the call to DFSforTOPO
                visited[j] = false;
            }

            while(!toposort.isEmpty()) { //using a stack to pop things out from last to first
                int temp = toposort.pop();
                if(!visited[temp]) {
                    count++;
                    DFSNormal(temp);
                }
            }
            writer.println(count);
        }
        bf.close();
        writer.flush();
        writer.close();
    }
}
