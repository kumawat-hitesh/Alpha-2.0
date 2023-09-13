import java.util.Scanner;

public class array_ip_op {
    public static void main(String args[]) {
        int marks[] = new int[50];
        try (Scanner sc = new Scanner(System.in);) {
            System.out.print("Enter marks 1 : ");
            marks[0] = sc.nextInt();
            System.out.print("Enter marks 2 : ");
            marks[1] = sc.nextInt();
            System.out.print("Enter marks 3 : ");
            marks[2] = sc.nextInt();

            System.out.println("Marks 1 = " + marks[0]);
            System.out.println("Marks 2 = " + marks[1]);
            System.out.println("Marks 3 = " + marks[2]);

            int percentage = (marks[0] + marks[1] + marks[2]) / 3;
            System.out.println("Percentage = " + percentage + "%");
        }
    }

}
