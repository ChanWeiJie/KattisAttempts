import java.util.Scanner;

public class Pot {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int arr[] = new int[num];
        int power[] = new int[num];
        int total = 0;

        for(int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        for(int j = 0; j < power.length; j++ ) {
            power[j] = arr[j] % 10;
        }

        for(int k = 0; k < arr.length; k++) {
            int value = arr[k] / 10; //need divide by 10 to get the first 1 or 2 digits.
            total = total + (int) Math.pow(value, power[k]);
        }
        System.out.println(total);

    }
}
