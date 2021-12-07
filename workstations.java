import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class WorkStations {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        String[] details = bf.readLine().split(" ");
        int numOfResearchers = Integer.parseInt(details[0]);
        int inactivityTime = Integer.parseInt(details[1]);
        PriorityQueue<Integer> arrivalQue = new PriorityQueue<>();
        PriorityQueue<Integer> departureQue = new PriorityQueue<>();
        int saves = 0;

        for(int i = 0; i < numOfResearchers; i++) {
            details = bf.readLine().split(" ");
            int arrivalTime = Integer.parseInt(details[0]);
            int useTime = Integer.parseInt(details[1]);
            arrivalQue.add(arrivalTime);
            departureQue.add(arrivalTime + useTime);
        }

        while(!arrivalQue.isEmpty()) {
            int curr = arrivalQue.poll();
            while(departureQue.peek() + inactivityTime < curr) {
                //remove every departureTime where difference
                //between arrivaltime - previous guy departure > inactivityTime
                departureQue.poll();
            }
            if(departureQue.peek() <= curr) {
                //if next researcher arrivaltime <= previous guy departure
                // use the same station
                saves++;
                departureQue.poll();
            }
        }
        writer.println(saves);
        bf.close();
        writer.flush();
        writer.close();
    }
}



