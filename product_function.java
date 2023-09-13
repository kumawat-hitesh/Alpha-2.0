import java.util.Scanner;

public class product_function {

    public static int product(int num1, int num2) {
        int mul = num1 * num2;
        return mul;
    }

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){ 
        System.out.print("Enter a number : ");
        int num1 = sc.nextInt();
        System.out.print("Enter another number : ");
        int num2 = sc.nextInt();

        int product = product(num1, num2);
        System.out.print("Product is : " + product);}

    }
}
