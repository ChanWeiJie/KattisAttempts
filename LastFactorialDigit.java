import java.util.Scanner;

public class LastFactorialDigit {
    public static int factorial(int number) {
        int fact = 1;
        for (int i = 1; i<= number; i++){
            fact = fact*i;
        }
        return fact;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[sc.nextInt()];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }


        for(int j = 0; j < arr.length; j++) {
            int temp = factorial(arr[j]);
            if (temp >= 10) {
                System.out.println(temp % 10);
            } else {
                System.out.println(temp);
            }
        }
    }
}
