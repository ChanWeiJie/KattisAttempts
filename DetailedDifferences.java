import java.util.Scanner;

public class DetailedDifferences {
    public static String compare(String s1, String s2) {
        String temp = "";
        for(int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                temp += ".";
            } else {
                temp += "*";
            }
        }
        return temp;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int row = num * 2;
        String arr[][] = new String[row][1];

        for(int i = 0; i < row; i++) {
            arr[i][0] = sc.next();
        }

        for(int j = 0; j < row; j+= 2) {
            System.out.println(arr[j][0]);
            System.out.println(arr[j+1][0]);
            System.out.println(compare(arr[j][0], arr[j+1][0]) + "\r\n");
        }
    }
}
