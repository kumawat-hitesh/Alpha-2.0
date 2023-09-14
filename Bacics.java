import java.util.Scanner;

public class Bacics {

    public static void sum(int a, int b) {
        int sum = a + b;
        System.out.println("Sum is : " + sum);

    }

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

    public static float avgNum(int a, int b, int c) {
        float avg;
        avg = (a + b + c) / (float) 3;
        return avg;
    }

    public static boolean isEven(int n) {
        if (n % 2 != 0) {
            return false;
        }
        return true;
    }

    public static int factorial(int number) {
        int fac = 1;
        for (int i = 1; i <= number; i++) {
            fac = fac * i;
        }

        return fac;
    }

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

        // reverse number
        int n = 10899;
        // while (n > 0) {
        // int lastDigit = n % 10;
        // System.out.print(lastDigit);
        // n/=10;
        // }
        // System.out.println();

        int rev = 0;
        while (n > 0) {
            int lastDigit = n % 10;
            rev = (rev * 10) + lastDigit;
            n /= 10;
        }
        System.out.println(rev);

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter a number : ");
            int a = sc.nextInt();
            System.out.print("Enter another number : ");
            int b = sc.nextInt();
            sum(a, b);
        }

        // try (Scanner sc = new Scanner(System.in);) {
        // System.out.print("Enter a number : ");
        // int number = sc.nextInt();
        // int result = sum(number);
        // System.out.println(result);
        // }

        // try(Scanner sc = new Scanner(System.in);)
        // {
        // System.out.print("Enter first number : ");
        // int num1 = sc.nextInt();
        // System.out.print("Enter second number : ");
        // int num2 = sc.nextInt();
        // System.out.print("Enter third number : ");
        // int num3 = sc.nextInt();
        // float result = avgNum(num1, num2, num3);
        // System.out.println("Average of 3 is : "+result);
        // }

        // System.out.println( isEven(56756));

        // try (Scanner sc = new Scanner(System.in);) {
        // System.out.println("Enter number for calculate factorial : ");
        // int number = sc.nextInt();
        // int result = factorial(number);
        // System.out.println(result);
        // }

        // try (Scanner sc = new Scanner(System.in);) {
        // System.out.print("Enter a number to check palindrome or not : ");
        // int palindrome = sc.nextInt();
        // if (isPalindrome(palindrome)) {
        // System.out.println("The number you entered " + palindrome + " is Palindrome
        // number.");
        // } else {
        // System.out.println("The number you entered " + palindrome + " is NOT
        // Palindrome number.");

        // }

        // }
    }
}
