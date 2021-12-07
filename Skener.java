import java.util.Scanner;

public class Skener {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int scaleRow = sc.nextInt();
        int scaleCol = sc.nextInt();

        for (int i = 0; i < row ; i++) {
            String str = sc.next();
            String output = "";
            for (int j = 0; j < col; j++) {
                String currLetter = str.substring(j, j + 1);

                for (int zc = 0; zc < scaleCol; zc++) {
                    output += currLetter;
                }
            }
            for (int zr = 0; zr < scaleRow; zr++) {
                System.out.println(output);
            }
        }
    }
}
