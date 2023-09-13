import java.util.Scanner;

public class sum_of_digits {

    public static int sum(int number) {
        int sum = 0;
        int digit = number;
        while (digit > 0) {
            int reminder = digit % 10;
            sum = sum + reminder;
            digit /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);) {
            System.out.print("Enter a number : ");
            int number = sc.nextInt();
            int result = sum(number);
            System.out.println(result);
        }
    }
}
