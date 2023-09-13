import java.lang.Math;

public class binToDec {

    public static void binaryToDecimal(int binNum) {
        int decNum = 0;
        int pow = 0;
        
        while (binNum > 0) {
            int lastDigit = binNum % 10;

            decNum = decNum + (lastDigit * (int) Math.pow(2, pow));
            pow++;

            binNum = binNum / 10;
        }
        System.out.println("Binary to decimal : " + decNum);
    }

    public static void main(String args[]) {
        System.out.println("hello");
        binaryToDecimal(111);
    }
}
