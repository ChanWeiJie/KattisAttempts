import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Joinstrings {
    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        int numOfStr = Integer.parseInt(bf.readLine());
        OwnLinkedList[] arr = new OwnLinkedList[numOfStr + 1];
        for(int i = 1; i < numOfStr + 1; i++) { //read in the inputs
            arr[i] = new OwnLinkedList(new Node(bf.readLine()));
        }

        int lastIndex = 1;
        for(int i = 1; i < numOfStr; i++) {
            String indexes = bf.readLine();
            String[] separateIndexes = indexes.split(" ");
            int a = Integer.parseInt(separateIndexes[0]);
            int b = Integer.parseInt(separateIndexes[1]);
            arr[a].append(arr[b]);
            lastIndex = a;
        }

        OwnLinkedList lst = arr[lastIndex];
        Node cur = lst.head;
        while(cur != null) {
            writer.write(cur.data);
            cur = cur.next;
        }
        r.close();
        bf.close();
        writer.flush();
        writer.close();
    }
    public static class Node {
        public String data;
        public Node next;
        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class OwnLinkedList {
        Node head;
        Node tail;
        public OwnLinkedList(Node node) {
            this.head = node;
            this.tail = node;
        }
        public void append(OwnLinkedList lst) {
            this.tail.next = lst.head;
            this.tail = lst.tail;
        }
    }
}
