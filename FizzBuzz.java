import java.util.Scanner;

public class FizzBuzz {
    public static void print(int a, int b, int c) {
        if (a % b == 0 && a % c == 0) {
            System.out.println("FizzBuzz");
        } else if (a % b == 0) {
            System.out.println("Fizz");
        } else if (a % c == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(a);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();

        for (int i = 1; i < z + 1; i++) {
            print(i, x , y);
        }
    }
}
