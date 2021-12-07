import java.util.Scanner;

public class Apaxians {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        StringBuilder nName = new StringBuilder();
        nName.append(name.charAt(0));
        for(int i = 1; i < name.length(); i++) {
            if (name.charAt(i - 1) == name.charAt(i)) {
                continue;
            }
            nName.append(name.charAt(i));
        }
        System.out.print(nName);
    }
}
