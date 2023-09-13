public class RecursionBasics {

    // public static void printDec(int n) { // print decreasing from n to 1
    // if (n == 1) {
    // System.out.print(n + " ");
    // return;
    // }
    // System.out.print(n + " ");
    // printDec(n - 1);
    // }

    // public static void printInc(int n) { // print increasing from 1 to n
    // if (n == 1) {

    // System.out.print(n + " ");
    // return;
    // }
    // printInc(n - 1);
    // System.out.print(n + " ");
    // }

    // public static int factorial(int n) { // print factorial of n numbers
    // if (n==0) {
    // return 1;
    // }
    // return (n * factorial(n-1));

    // }

    // public static int calcSum(int n) { // print sum of n numbers
    // if(n == 1){
    // return 1;
    // }
    // return (n + calcSum(n-1));
    // }

    // public static int fibonacci(int n) { // calculate nth fibonacci number
    // if (n==0 || n==1) {
    // return n;
    // }
    // int nm1 = fibonacci(n-1);
    // int nm2 = fibonacci(n-2);
    // return nm1 + nm2;
    // }

    // public static boolean isSorted(int arr[], int i) { //check if array is sorted
    // or not
    // if (i == arr.length - 1) {
    // return true;
    // }
    // if (arr[i] > arr[i + 1]) {
    // return false;
    // }
    // return isSorted(arr, i + 1);s
    // }

    // public static int firstOccurence(int arr[], int key, int i) { //find first
    // occurence of number
    // if (i == arr.length - 1) {
    // return -1;
    // }
    // if (arr[i] == key) {
    // return i;
    // }
    // return firstOccurence(arr, key, i + 1);
    // }

    // public static int lastOccurence(int arr[], int key, int i) { //find last
    // occurence of number
    // if (i == arr.length) {
    // return -1;
    // }
    // int isFound = lastOccurence(arr, key, i + 1);
    // if (isFound == -1 && arr[i] == key) {
    // return i;
    // }
    // return isFound;
    // }

    // public static int power(int x, int n) { //calculate power of number(time
    // complexity o(n))
    // if (n==0) {
    // return 1;
    // }
    // return x * power(x,n-1);

    // }

    // public static int optimizedPower(int x, int n) {
    // if (n==0) {
    // return 1;
    // }
    // int halfPower = optimizedPower(x, n/2);
    // int halfPowerSquare = halfPower * halfPower;
    // //for even number
    // if (n%2==0) {
    // return halfPowerSquare;
    // }
    // //for odd number
    // return x * halfPowerSquare;
    // }

    // public static int tilingProblem(int n) { //tiling problem
    // if (n == 0 | n == 1) {
    // return 1;
    // }
    // int fnm1 = tilingProblem(n - 1);
    // int fnm2 = tilingProblem(n - 2);
    // int totalWays = fnm1 + fnm2;
    // return totalWays;
    // }

    // public static void removeDuplicates(String str, int idx, StringBuilder
    // newStr, boolean map[]) { //remove duplicates from string
    // if (idx == str.length()) {
    // System.out.println(newStr);
    // return;
    // }
    // char currChar = str.charAt(idx);
    // if (map[currChar - 'a'] == true) {
    // removeDuplicates(str, idx+1, newStr, map);
    // }else{
    // map[currChar - 'a'] = true;
    // removeDuplicates(str, idx+1, newStr.append(currChar), map);
    // }
    // }

    // public static int friendsPairing(int n) { // friends pairing problem
    // if (n == 1 || n == 2) {
    // return n;
    // }
    // return friendsPairing(n-1) + (n-1) * friendsPairing(n-2);
    // }
    // public static void printBinString(int n, int lastPlace, String str) {
    // if (n == 0) {
    // System.out.println(str);
    // return;
    // }
    // printBinString(n-1, 0, str+"0");
    // if (lastPlace == 0) {
    // printBinString(n-1, 1, str+"1");
    // }
    // }

    public static void main(String[] args) {
        // printDec(10);
        // printInc(10);
        // int factorial = factorial(7);
        // System.out.println(factorial);
        // System.out.println(calcSum(5));
        // System.out.println(fibonacci(10));
        // int arr[] = {1,2,3,4,5};
        // System.out.println(isSorted(arr, 0)); //true
        // int arr[] = { 1, 5, 3, 4 };
        // System.out.println(isSorted(arr, 0)); // false
        // int arr[] = { 6, 8, 2, 5, 6, 1, 9, 5 };
        // System.out.println(firstOccurence(arr, 5, 0)); //3
        // int arr[] = { 6, 8, 2, 5, 6, 1, 9, 5 };
        // System.out.println(lastOccurence(arr, 5, 0));
        // System.out.println(power(2, 10));
        // System.out.println(optimizedPower(2, 10));
        // System.out.println(tilingProblem(4)); //5
        // String str = "appnnacollege";
        // removeDuplicates(str, 0, new StringBuilder(""), new boolean[26]);
        // System.out.println(friendsPairing(3)); //4
        // printBinString(3, 0, "");
    }
}
