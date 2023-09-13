import java.util.Scanner;

public class palindrome_number {

    public static boolean isPalindrome(int number) {

        int palindrome = number;
        int reverse = 0;

        while (palindrome != 0) {
            int reminder = palindrome % 10;
            reverse = reverse * 10 + reminder;
            palindrome /= 10;

        }
        if (number == reverse) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);) {
            System.out.print("Enter a number to check palindrome or not : ");
            int palindrome = sc.nextInt();
            if (isPalindrome(palindrome)) {
                System.out.println("The number you entered " + palindrome + " is Palindrome number.");
            } else {
                System.out.println("The number you entered " + palindrome + " is NOT Palindrome number.");

            }

        }
    }
}
