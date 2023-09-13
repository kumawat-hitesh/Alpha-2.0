import java.util.Scanner;


//sum function with return type(int).
/*
public class sum_function {
    public static int sum(int a, int b) {
        int sum = a + b;
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number : ");
        int a = sc.nextInt();
        System.out.print("Enter another number : ");
        int b = sc.nextInt();

        int result = sum(a, b);
        System.out.println("Sum is : " + result);
    }
 */



//sum function without return type(void).
public class sum_function {

    public static void sum(int a, int b) {
        int sum = a + b;
        System.out.println("Sum is : "+sum);
        
    }



    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter a number : ");
            int a = sc.nextInt();
            System.out.print("Enter another number : ");
            int b = sc.nextInt();
            sum(a, b);
        }
    }
}
