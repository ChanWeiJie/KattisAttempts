import java.io.*;
import java.util.*;

public class Kattissquest {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        TreeMap<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
        StringBuilder str = new StringBuilder();
        long totalGold = 0;
        int N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            String[] separateInput = bf.readLine().split(" ");
            String command = separateInput[0];

            if(command.equals("add")) {
                int energy = Integer.parseInt(separateInput[1]);
                int gold = Integer.parseInt(separateInput[2]);

                if(map.containsKey(energy)) {
                    map.get(energy).add(gold);
                } else {
                    //priorityque should be in descending order
                    PriorityQueue<Integer> goldQueue = new PriorityQueue<>(Collections.reverseOrder());
                    goldQueue.add(gold);
                    map.put(energy, goldQueue);
                }

            } else if(command.equals("query")) {
                int energySes = Integer.parseInt(separateInput[1]);
                //floorEntery gets key-value mapping associated with the least key strictly greater than the given key
                // or null if there is no such key.
                Map.Entry<Integer, PriorityQueue<Integer>> entry = map.floorEntry(energySes);
                while(entry != null) {
                    totalGold += entry.getValue().poll();

                    if(entry.getValue().isEmpty()) {
                        map.remove(entry.getKey());
                    }

                    energySes -= entry.getKey();
                    //do this untill the key are all > energySes -> (entry == NULL)
                    entry = map.floorEntry(energySes);
                }
                str.append(totalGold + "\n");
                totalGold = 0;
            }
        }
        writer.print(str);
        bf.close();
        writer.flush();
        writer.close();
    }
}

