import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class ClassyProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        int cases = Integer.parseInt(bf.readLine());
        for(int i = 0; i < cases; i++) {
            int people = Integer.parseInt(bf.readLine());
            ArrayList<Person> lst = new ArrayList<>();
            for(int j = 0; j < people; j++) {
                String[] input = bf.readLine().split(" ");
                String name = input[0].replace(":","");
                lst.add(new Person(name, input[1]));
            }
            Collections.sort(lst);
            for(int k = 0; k < lst.size(); k++) {
                writer.println(lst.get(k).name);
            }
            writer.write("=====================================================");
            writer.println();
        }
        bf.close();
        writer.flush();
        writer.close();
    }

    public static class Person implements Comparable<Person> {
        String name;
        ArrayList<Integer> ranks;

        public Person(String name, String position) {
            this.name = name;
            this.ranks = new ArrayList<>();
            String[] ranking = position.split("-");
            for(int i = ranking.length - 1;  i >= 0; i--) {
                if (ranking[i].equals("upper")) {
                    ranks.add(3);
                } else if (ranking[i].equals("middle")) {
                    ranks.add(2);
                } else {
                    ranks.add(1);
                }
            }
            for (int i = 0; i < 10; i++) {
                ranks.add(2);
            }

        }

        @Override
        public int compareTo(Person o) {
            for (int i = 0; i < 10; i++) {
                if (this.ranks.get(i).equals(o.ranks.get(i))) {
                    continue;
                }
                if (this.ranks.get(i) > o.ranks.get(i)) {
                    return -1;
                }
                if (this.ranks.get(i) < o.ranks.get(i)) {
                    return 1;
                }
            }
            return this.name.compareTo(o.name);
        }
    }
}
