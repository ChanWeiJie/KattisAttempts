import java.util.Scanner;

public class NumberFun {
    public static String check(double a, double b, int c) {
        if(a + b == c || a - b == c || a * b == c || a / b == c ||
                b - a == c || b / a == c) {
            return "Possible";
        } else {
            return "Impossible";
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int[][] arr = new int[row][3];

        for(int i = 0; i < row; i++) {
            for( int j = 0; j < 3; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for(int k = 0; k < row; k++) {
            System.out.println(check(arr[k][0],arr[k][1],arr[k][2]));
        }

    }
}
