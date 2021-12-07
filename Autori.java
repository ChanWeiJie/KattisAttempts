import java.util.Scanner;

public class Autori {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        String newName = "" + name.charAt(0);

        for(int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == 45) {
                newName += name.charAt(i+1);
            }
        }
        System.out.println(newName);
    }
}
