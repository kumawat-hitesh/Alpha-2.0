public class BackTracking {

    // change array using backtracking
    public static void changeArr(int arr[], int i, int val) {
        // base case
        if (i == arr.length) {
            System.out.println("without back track");
            printArr(arr);
            return;
        }
        // work
        arr[i] = val;
        changeArr(arr, i + 1, val + 1);
        arr[i] = arr[i] - 2; // backtrack
    }

    public static void printArr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // subset of a given string using backtracking
    public static void findSubsets(String str, String ans, int i) {
        // base case
        if (i == str.length()) {
            if (ans.length() == 0) {
                System.out.println("NULL");
            } else {
                System.out.println(ans);

            }
            return;
        }
        // work
        findSubsets(str, ans + str.charAt(i), i + 1);
        findSubsets(str, ans, i + 1);
    }

    // find permutation of a given string using backtracking
    public static void findPermutation(String str, String ans) {
        // base case
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        // recursion
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            String newStr = str.substring(0, i) + str.substring(i + 1, str.length());
            findPermutation(newStr, ans + curr);
        }
    }

    public static void main(String[] args) {

        // int arr[] = new int[5];
        // changeArr(arr, 0, 1);
        // System.out.println("with back track");
        // printArr(arr);
        // String str = "abc";
        // findSubsets(str, "", 0);
        // String str = "abc";
        // findPermutation(str, "");
    }
}
