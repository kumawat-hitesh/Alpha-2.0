public class BitManipulation {
    public static void oddEven(int n) {
        int bitMask = 1;
        if ((n & bitMask) == 0) {
            System.out.println("Even Number");
        } else {
            System.out.println("Odd Number");
        }
    }

    // get ith bit
    public static int getIthBit(int n, int i) {
        int bitMask = 1 << i;
        if ((n & bitMask) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    // set ith bit
    public static int setIthBit(int n, int i) {
        int bitMask = 1 << i;
        return n | bitMask;
    }

    // clear ith bit
    public static int clearIthBit(int n, int i) {
        int bitMask = ~(1 << i);
        return n & bitMask;
    }

    // clear last i bits
    public static int clearIBit(int n, int i) {
        int bitMask = ~(0) << i;
        return n & bitMask;
    }

    // clear range of bits
    public static int clearRangeOfBits(int n, int i, int j) {
        int a = (~(0) << (j + 1));
        int b = (1 << i) - 1;
        int bitMask = a | b;
        return n & bitMask;
    }

    // check if number is a power of 2 or not
    public static boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }

    // count set bits in a number
    public static int countSetBit(int n) {
        int count = 0;
        while (n > 0) {
            if ((n & 1) != 0) { // check lsb
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    // binary to decimal
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

    public static void main(String[] args) {
        // oddEven(3);
        // oddEven(11);
        // oddEven(10);
        // oddEven(12);
        // System.out.println(getIthBit(7, 1)); //give 1
        // System.out.println(setIthBit(10, 2)); //give 14
        // System.out.println(clearIthBit(10, 1)); //gives 8
        // System.out.println(clearIBit(15, 2)); //gives 12
        // System.out.println(clearRangeOfBits(10, 2, 4)); //gives 2
        // System.out.println(isPowerOfTwo(8)); //true
        // System.out.println(isPowerOfTwo(15)); //false
        // System.out.println(countSetBit(15)); //gives 4
        // binaryToDecimal(111);
    }
}
