import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Coconut {
    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);
        String inputs = bf.readLine();
        String[] input = inputs.split(" ");
        int numOfSyl = Integer.parseInt(input[0]);
        int players = Integer.parseInt(input[1]);
        int folded = 0;
        LinkedList<Person> listOfPerson = new LinkedList<>();
        for(int i = 1; i < players + 1; i++){
            Person p = new Person(i, folded);
            listOfPerson.add(p);
        }
        int index = 0;
        while(listOfPerson.size() > 1) {
            index = (index + numOfSyl - 1) % listOfPerson.size();
            if(listOfPerson.get(index).getState() == 0) {
                listOfPerson.get(index).updateState(1);
                listOfPerson.add(index, new Person(listOfPerson.get(index).getPid(),1));
            } else if(listOfPerson.get(index).getState() == 1) {
                listOfPerson.get(index).updateState(2);
                index++;
            } else { // state is 2
                listOfPerson.remove(index);
            }
        }
        writer.print(listOfPerson.get(0).getPid());
        r.close();
        bf.close();
        writer.flush();
        writer.close();
    }
}

class Person {
    int pid; //playerid
    int state; // 0 folded, 1 fist, 2 palm

    public Person(int first, int second){
        this.pid = first;
        this.state = second;
    }

    public int getPid() { return this.pid;}

    int getState() { return this.state;}

    void updateState(int value){ this.state = value;}
}