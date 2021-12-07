import java.util.*;

public class Bestrelayteam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfRunners = sc.nextInt();
        Runner[] runners = new Runner[numOfRunners];

        for(int i = 0; i<numOfRunners; i++){
            String name = sc.next();
            double flyTime = sc.nextDouble();
            double otherTime = sc.nextDouble();
            runners[i] = new Runner(name, flyTime, otherTime);
        }

        Arrays.sort(runners); // sort the runners by otherTime

        String[] teamNames = null;
        double teamTime = 9999; // dummy value to compare the first team
        for(int k = 0; k < numOfRunners; k++) {
            String[] tempTeamNames = new String[4];
            tempTeamNames[0] = runners[k].getName();
            double tempTeamTime = runners[k].getFlyTime();
            int count = 1;
            for(int l = 0; count < 4 ; l++) {
                if(l == k) {
                    continue;
                } else {
                    tempTeamNames[count] = runners[l].getName();
                    tempTeamTime += runners[l].getOtherTime();
                    count++;
                }
            }
            if(tempTeamTime < teamTime) {
                teamTime = tempTeamTime;
                teamNames = tempTeamNames;
            }
        }
        System.out.println(teamTime);
        for(int x = 0; x < 4; x++ ){
            System.out.println(teamNames[x]);
        }
    }
}

class Runner implements Comparable<Runner> { //allows comparison between runners
    String name;
    double flyTime;
    double otherTime;

    //constructor
    public Runner(String name, double flyTime, double otherTime) {
        this.name = name;
        this.flyTime = flyTime;
        this.otherTime = otherTime;
    }

    //getters
    public String getName() {
        return name;
    }
    public double getFlyTime() {
        return flyTime;
    }
    public double getOtherTime(){
        return  otherTime;
    }

    @Override
    public int compareTo(Runner other) {
        if(this.otherTime < other.otherTime) {
            return -1;
        } else if(this.otherTime > other.otherTime) {
            return 1;
        } else {
            return 0;
        }
    }
}
