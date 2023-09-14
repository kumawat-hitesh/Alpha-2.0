import java.util.Scanner;

public class Strings {

    public static void printCharacters(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i) + " ");
        }
    }

    public static boolean isPalindrome(String str) {
        for (int i = 0; i <= str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static String toUpperCase(String str) {

        StringBuilder sb = new StringBuilder();

        char ch = Character.toUpperCase(str.charAt(0));
        sb.append(ch);

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == ' ' && i < str.length() - 1) {
                sb.append(str.charAt(i));
                i++;
                sb.append(Character.toUpperCase(str.charAt(i)));
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    // String Compression
    public static String compreess(String str) {
        String newStr = "";
        for (int i = 0; i < str.length(); i++) {
            Integer count = 1;
            while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                count++;
                i++;
            }
            newStr += str.charAt(i);
            if (count > 1) {
                newStr += count.toString();
            }
        }
        return newStr;
    }

    // Get shortest path
    public static float getShortestPath(String path) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < path.length(); i++) {
            char dir = path.charAt(i);
            if (dir == 'E') {
                x++;
            } else if (dir == 'W') {
                x--;
            } else if (dir == 'N') {
                y++;
            } else {
                y--;
            }
        }
        int X2 = x * x;
        int Y2 = y * y;
        return (float) Math.sqrt(X2 + Y2);
    }

    public static void main(String[] args) {

        /*
         * char arr[] = {'a','b','c','d'};
         * String str = "abcd";
         * String str2 = new String("Harsh");
         * System.out.println(str2);
         */

        try (// input output
                Scanner sc = new Scanner(System.in)) {
            String name = sc.next();
            System.out.println(name); // print only single word because of .next()
            // function.
            // String name = sc.nextLine();
            // System.out.println(name);
        }

        /*
         * String name = "Hitesh";
         * int n = name.length();
         * System.out.println(n); //output is 6 .
         */

        // String fName = "Hitesh";
        // String lName = "Kumawat";
        // String fullName = fName + " " + lName;
        // System.out.println(fullName);

        // System.out.println(fName.charAt(0)); //print H

        // printCharacters(fullName); //print all characters in full name.

        // String str = "racecar";
        // String str1 = "harsh";
        // System.out.println(isPalindrome(str)); //return true
        // System.out.println(isPalindrome(str1)); //return false

        // String str = "hi, i am hitesh!";
        // System.out.println(toUpperCase(str));

        // print largest string
        // String fruits[] = { "apple", "mango", "banana","Melon" };
        // String largest = fruits[0];
        // for (int i = 1; i < fruits.length; i++) {
        // if (largest.compareToIgnoreCase(fruits[i]) < 0) {
        // largest = fruits[i];
        // }
        // }
        // System.out.println(largest); //prints Melon

        // String str = "aaaabbbccdddd"; //String compression
        // System.out.println(compreess(str));

        // get shortest path
        // String path = "WNEENESENNN";
        // String path = "NS"; // 0.0 ans
        // System.out.println(getShortestPath(path));
    }
}
