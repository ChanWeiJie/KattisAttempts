public class Peasoup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfRes = Integer.parseInt(sc.nextLine()); 
        while(numOfRes > 0) {
            int noOfMenuItems = Integer.parseInt(sc.nextLine());
            boolean pancake = false;
            boolean peasoup = false;
            String resName = sc.nextLine();
            for(int i = noOfMenuItems; i > 0; i--) {
                String dish = sc.nextLine();
                if (dish.equals("pancakes")) {
                    pancake = true;
                } else {
                    if (dish.equals("pea soup")) {
                        peasoup = true;
                    }
                }
            }
            if(pancake && peasoup) {
                System.out.println(resName);
                return;
            }
            numOfRes--;
        }
        System.out.println("Anywhere is fine I guess");
    }
}


