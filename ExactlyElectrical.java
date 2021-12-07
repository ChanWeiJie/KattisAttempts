import java.util.Scanner;

public class ExactlyElectrical {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        int energy = sc.nextInt();
        int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
        if((energy < distance) || (energy - distance) % 2 == 1) {
            System.out.println("N");
        } else {
            System.out.println("Y");
        }
    }
}
