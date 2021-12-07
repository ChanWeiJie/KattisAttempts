import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Teque {

    public static void balance(OwnDeque front, OwnDeque back) {
        if(front.getSize() - back.getSize() >= 2) {
            back.addFirst(front.removeLast());
            //remove front add to back
        } else if(back.getSize() - front.getSize() >= 1) {
            front.addLast(back.removeFirst());
            //remove back, add to front
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        int n = Integer.parseInt(bf.readLine());
        OwnDeque frontQueue = new OwnDeque(n);
        OwnDeque backQueue = new OwnDeque(n);

        for(int i = 0; i < n; i++) {
            String command = bf.readLine();
            String[] separateCommand = command.split(" ");
            command = separateCommand[0];
            int input = Integer.parseInt(separateCommand[1]);
            if (command.equals("get")) {
                if (input < frontQueue.getSize()) {
                    writer.println(frontQueue.get(input));
                } else {
                    writer.println(backQueue.get(input - frontQueue.getSize()));
                    //need to minus the offset as index in backQueue starts from 0
                }
            } else if (command.equals("push_back")) {
                backQueue.addLast(input);
                balance(frontQueue, backQueue);
            } else if (command.equals("push_front")) {
                frontQueue.addFirst(input);
                balance(frontQueue, backQueue);
            } else if (command.equals("push_middle")) {
                if (frontQueue.getSize() <= backQueue.getSize()) {
                    frontQueue.addLast(input);
                } else {
                    backQueue.addFirst(input);
                    //dont need to balance here
                }
            }
        }
        bf.close();
        writer.flush();
        writer.close();
    }

    public static class OwnDeque {
        int front;
        int rear;
        int[] array;
        int size;

        public OwnDeque(int size) {
            array = new int[size];
            front = - 1;
            rear = - 1;
            this.size = 0;
        }
        public void addFirst(int input){
            if(front == -1 && rear == -1) {
                front = 0;
                rear = 0;
            } else if(front == 0) {
                front = array.length - 1;
            } else {
                front--;
            }
            array[front] = input;
            size++;
        }
        public void addLast(int input) {
            if(front == - 1 && rear == -1) {
                front = 0;
                rear = 0;
            } else if(rear == array.length - 1 ){
                rear = 0;
            } else {
                rear++;
            }
            array[rear] = input;
            size++;
        }

        public int removeFirst(){
            int item = array[front];
            if(front == array.length - 1) {
                front = 0;
            } else {
                front++;
            }
            size--;
            return item;
        }

        public int removeLast() {
            int item = array[rear];
            if(rear == 0) {
                rear = array.length - 1;
            } else {
                rear--;
            }
            size--;
            return item;
        }

        public int get(int index) {
            return array[(front + index) % array.length];
            //finding the actual index 0
            //offset index base on head index
        }

        public int getSize() {
            return size;
        }
    }
}

