public class reverse_number {
    public static void main(String[] args) {
        int n = 10899;
        // while (n > 0) {
        //     int lastDigit = n % 10;
        //     System.out.print(lastDigit);
        //     n/=10;
        // }
        // System.out.println();

        int rev = 0;
        while (n > 0) {
            int lastDigit = n % 10;
            rev = (rev*10) + lastDigit;
            n/=10;
        }
        System.out.println(rev);

    }
}



