import java.util.Scanner;

public class factorial_function {

    public static int factorial(int number) {
        int fac = 1;
        for (int i = 1; i <= number; i++) {
            fac = fac * i;
        }
        
        return fac;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);) {
            System.out.println("Enter number for calculate factorial : ");
            int number = sc.nextInt();
            int result = factorial(number);
            System.out.println(result);
        }
    }
}
