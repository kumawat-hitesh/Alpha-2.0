import java.util.Scanner;

public class average_of_three_numbers {
    public static float avgNum(int a, int b, int c) {
        float avg;
        avg = (a + b + c)/(float)3;
        return avg;
    }

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in);)
        {
            System.out.print("Enter first number : ");
            int num1 = sc.nextInt();
            System.out.print("Enter second number : ");
            int num2 = sc.nextInt();
            System.out.print("Enter third number : ");
            int num3 = sc.nextInt();
            float result = avgNum(num1, num2, num3);
            System.out.println("Average of 3 is : "+result);
        }
    }
}
