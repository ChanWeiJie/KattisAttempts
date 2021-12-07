import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HumanCannonBall {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        int index = 0;
        String[] inputs = bf.readLine().split(" ");
        Coordinates start = new Coordinates(Double.parseDouble(inputs[0]),
                Double.parseDouble(inputs[1]), false, index);
        index++;

        inputs = bf.readLine().split(" ");

        ArrayList<Coordinates> coordinates = new ArrayList<>();
        ArrayList<IntegerTriple> edgeList = new ArrayList<>();

        coordinates.add(start); // first item is the source
        int numOfCannons = Integer.parseInt(bf.readLine());
        int runRate = 5;
        int cannonOverHead = 2;

        for (int i = 0; i < numOfCannons ; i++) {
            String[] cannonInputs = bf.readLine().split(" ");
            //next few items is the cannons
            coordinates.add(new Coordinates(Double.parseDouble(cannonInputs[0]),
                    Double.parseDouble(cannonInputs[1]),true, index));
            index++;
        }

        Coordinates end = new Coordinates(Double.parseDouble(inputs[0]),
                Double.parseDouble(inputs[1]), false, index);
        coordinates.add(end);// last item is the destination

        for (Coordinates temp1 : coordinates) {
            for (Coordinates temp2 : coordinates) {
                if(temp1.x != temp2.x && temp1.y != temp2.y) { //not the same coordinate
                    //calculate difference in distance between the coordinate
                    double x = temp1.x - temp2.x; // a - b or b - a no diff since going to square
                    double y = temp1.y - temp2.y;
                    double distance = Math.sqrt(x * x + y * y);
                    double timeNeeded = distance / runRate;
                    double cannonTime = Double.POSITIVE_INFINITY;
                    if (temp1.isCannon) {
                        //cannon time to some other coordinate need to absolute in case cannon overshoots
                        cannonTime = cannonOverHead + (Math.abs(distance - 50) / runRate);
                    }
                    edgeList.add(new IntegerTriple(temp1, temp2, Math.min(timeNeeded, cannonTime)));
                    // System.out.println(Math.min(timeNeeded, cannonTime));
                }
            }
        }

        ArrayList<Double> D = new ArrayList<>();
        //initialize SSSP
        D.addAll(Collections.nCopies(coordinates.size(), Double.POSITIVE_INFINITY));
        D.set(coordinates.get(0).index, 0.0);

        //start of bellman Ford
        for (int i = 0; i < coordinates.size() - 1; i++) {
            for (int j = 0; j < edgeList.size(); j++) {
                IntegerTriple temp = edgeList.get(j);
                if(D.get(temp.first.index) != Double.POSITIVE_INFINITY
                        && D.get(temp.second.index) > D.get(temp.first.index) + temp.time) {
                    D.set(temp.second.index, D.get(temp.first.index) + temp.time);
                }
            }
        }
        writer.println(D.get(coordinates.size() - 1));
        writer.flush();
        writer.close();
        bf.close();
    }
}

//coordinates
class Coordinates {
    Double x;
    Double y;
    boolean isCannon;
    int index;

    public Coordinates(double x, double y, boolean isCannon, int index) {
        this.x = x;
        this.y = y;
        this.isCannon = isCannon;
        this.index = index;
    }
}

class IntegerTriple implements Comparable<IntegerTriple>{
    Coordinates first;
    Coordinates second;
    double time;

    public IntegerTriple(Coordinates i, Coordinates j, double time){
        this.first = i;
        this.second = j;
        this.time = time;
    }

    @Override
    public int compareTo(IntegerTriple o) {
        if(this.time < o.time) {
            return -1;
        } else if(this.time > o.time) {
            return 1;
        } else {
            return 0;
        }
    }
}

