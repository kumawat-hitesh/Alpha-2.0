import java.util.Scanner;

public class check_polarity {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in);) {
            System.out.println("Enter a number to check it polarity : ");
            int x = sc.nextInt();
            if (x < 0) {
                System.out.println("Negative Number.");
            } else {
                System.out.println("Positive Number.");
            }
        }
    }
}
