import java.util.*;;

public class DynamicProgramming {
    public static int fibonacciMemoization(int n, int[] dp) { // O(n)
        if (n == 0 || n == 1) {
            return n;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        dp[n] = fibonacciMemoization(n - 1, dp) + fibonacciMemoization(n - 2, dp);
        return dp[n];
    }

    public static int fibonacciTabulation(int n) { // O(n)
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Climbing Stairs using recursion
    public static int countWays(int n) {
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;
        return countWays(n - 1) + countWays(n - 2);
    }

    // Climbing Stairs using memoization
    public static int countWaysMemo(int n, int dp[]) {
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;
        if (dp[n] != -1) {
            return dp[n];
        }
        dp[n] = countWaysMemo(n - 1, dp) + countWaysMemo(n - 2, dp);
        return dp[n];
    }

    // climbing stairs using tabulation
    public static int countWaysTabu(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                dp[i] = dp[i - 1] + 0;
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[n];
    }

    // 0 - 1 Knapsack using recursion
    public static int knapsackRec(int[] val, int[] wt, int W, int n) {
        if (W == 0 || n == 0) {
            return 0;
        }
        if (wt[n - 1] <= W) { // valid
            // include
            int ans1 = val[n - 1] + knapsackRec(val, wt, W - wt[n - 1], n - 1);
            // exclude
            int ans2 = knapsackRec(val, wt, W, n - 1);
            return Math.max(ans1, ans2);
        } else {
            return knapsackRec(val, wt, W, n - 1);
        }
    }

    // 0 - 1 Knapsack using memoization
    public static int knapsackMemo(int[] val, int[] wt, int W, int n, int[][] dp) {
        if (W == 0 || n == 0) {
            return 0;
        }
        if (dp[n][W] != -1) {
            return dp[n][W];
        }
        if (wt[n - 1] <= W) { // valid
            // include
            int ans1 = val[n - 1] + knapsackMemo(val, wt, W - wt[n - 1], n - 1, dp);
            // exclude
            int ans2 = knapsackMemo(val, wt, W, n - 1, dp);
            dp[n][W] = Math.max(ans1, ans2);
            return dp[n][W];
        } else {
            dp[n][W] = knapsackMemo(val, wt, W, n - 1, dp);
            return dp[n][W];
        }
    }

    // 0 - 1 Knapsack using tabulation
    public static int knapsackTabu(int[] val, int[] wt, int W) {
        int n = val.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i < dp.length; i++) { // 0th col
            dp[i][0] = 0;
        }
        for (int j = 0; j < dp[0].length; j++) { // 0th row
            dp[0][j] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                int v = val[i - 1]; // ith item val
                int w = wt[i - 1]; // ith item wt
                if (w <= j) { // valid
                    int incProfit = v + dp[i - 1][j - w];
                    int excProfit = dp[i - 1][j];
                    dp[i][j] = Math.max(incProfit, excProfit);
                } else { // invalid
                    int excProfit = dp[i - 1][j];
                    dp[i][j] = excProfit;
                }
            }
        }
        return dp[n][W];
    }

    // Target Sum Subset O(n * w)
    public static boolean targetSumSubset(int arr[], int sum) {
        int n = arr.length;
        boolean dp[][] = new boolean[n + 1][sum + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                int v = arr[i - 1];
                // include
                if (v <= j && dp[i - 1][j - v] == true) {
                    dp[i][j] = true;
                }
                // exclude
                else if (dp[i - 1][j] == true) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[n][sum];
    }

    // Unbounded Knapsack using Tabulation O(n * w)
    public static int unboundedKnapsack(int[] val, int[] wt, int W) {
        int n = val.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i < dp.length; i++) { // 0th col
            dp[i][0] = 0;
        }
        for (int j = 0; j < dp[0].length; j++) { // 0th row
            dp[0][j] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                int v = val[i - 1]; // ith item val
                int w = wt[i - 1]; // ith item wt
                if (w <= j) { // valid
                    int includeProfit = v + dp[i][j - w];
                    int excludeProfit = dp[i - 1][j];
                    dp[i][j] = Math.max(includeProfit, excludeProfit);
                } else { // invalid
                    int excProfit = dp[i - 1][j];
                    dp[i][j] = excProfit;
                }
            }
        }
        return dp[n][W];
    }

    // Rod Cutting
    public static int rodCutting(int length[], int prices[], int totalRod) {
        int n = length.length;
        int[][] dp = new int[n + 1][totalRod + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < totalRod + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < totalRod + 1; j++) {
                int v = prices[i - 1]; // ith item val
                int w = length[i - 1]; // ith item wt
                if (w <= j) { // valid
                    int includeProfit = v + dp[i][j - w];
                    int excludeProfit = dp[i - 1][j];
                    dp[i][j] = Math.max(includeProfit, excludeProfit);
                } else { // invalid
                    int excProfit = dp[i - 1][j];
                    dp[i][j] = excProfit;
                }
            }
        }
        return dp[n][totalRod];
    }

    // Longest common subsequence using recursion
    public static int lcs(String str1, String str2, int n, int m) {
        if (n == 0 || m == 0) {
            return 0;
        }
        if (str1.charAt(n - 1) == str2.charAt(m - 1)) { // same
            return lcs(str1, str2, n - 1, m - 1) + 1;
        } else { // diff
            int ans1 = lcs(str1, str2, n - 1, m);
            int ans2 = lcs(str1, str2, n, m - 1);
            return Math.max(ans1, ans2);
        }
    }

    // Longest common subsequence using memoization
    public static int lcsMemo(String str1, String str2, int n, int m, int dp[][]) {
        if (n == 0 || m == 0) {
            return 0;
        }
        if (dp[n][m] != -1) {
            return dp[n][m];
        }
        if (str1.charAt(n - 1) == str2.charAt(m - 1)) { // same
            return dp[n][m] = lcsMemo(str1, str2, n - 1, m - 1, dp) + 1;
        } else { // diff
            int ans1 = lcsMemo(str1, str2, n - 1, m, dp);
            int ans2 = lcsMemo(str1, str2, n, m - 1, dp);
            return dp[n][m] = Math.max(ans1, ans2);
        }
    }

    // Longest common subsequence using tabulation
    public static int lcsTabu(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    int ans1 = dp[i - 1][j];
                    int ans2 = dp[i][j - 1];
                    dp[i][j] = Math.max(ans1, ans2);
                }
            }
        }
        return dp[n][m];
    }

    // Longest common substring using tabulation
    public static int longestCommonSubstring(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int ans = 0;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0 | j == 0) {
                    dp[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return ans;
    }

    // Longest increasing subsequence using tabulation
    public static int longestCommonSubsequence(int arr1[], int arr2[]) {
        int n = arr1.length;
        int m = arr2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0 | j == 0) {
                    dp[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    int ans1 = dp[i - 1][j];
                    int ans2 = dp[i][j - 1];
                    dp[i][j] = Math.max(ans1, ans2);
                }
            }
        }
        return dp[n][m];
    }

    public static int longestIncreasingSubsequence(int arr1[]) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }
        int arr2[] = new int[set.size()];
        int i = 0;
        for (int num : set) {
            arr2[i] = num;
            i++;
        }
        Arrays.sort(arr2);
        return longestCommonSubsequence(arr1, arr2);
    }

    // Edit Distance
    public static int editDistance(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                if (j == 0) {
                    dp[i][j] = i;
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int add = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    int replace = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(add, Math.min(delete, replace));
                }
            }
        }
        return dp[n][m];
    }

    // Wildcard Matching
    public static boolean wildcarsMatching(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        // pattern = " "
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = false;
        }
        // s = " "
        for (int j = 1; j < m + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        // bottom up
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                // case -> ith char == jth char || jth == '?'
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[n][m];
    }

    // Catalan's Number using recursion
    public static int catalanRec(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += catalanRec(i) * catalanRec(n - i - 1);
        }
        return ans;
    }

    // Catalan's Number using Memoization
    public static int catalanMemo(int n, int[] dp) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += catalanMemo(i, dp) * catalanMemo(n - i - 1, dp);
        }
        return dp[n] = ans;
    }

    // Catalan's Number using tabulation O(n^2)
    public static int catalanTabu(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    // Counting BST's
    public static int countBST(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                int left = dp[j];
                int right = dp[i - j - 1];
                dp[i] += left * right;
            }
        }
        return dp[n];
    }

    // Mountain ranges
    public static int mountainRanges(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // i pairs mountain ranges => Ci
            for (int j = 0; j < i; j++) {
                int inside = dp[j];
                int outside = dp[i - j - 1];
                dp[i] += inside * outside; // Ci = Cj * Ci-j-1
            }
        }
        return dp[n];
    }

    // Matrix Chain Multiplication using recursion
    public static int matrixChainMultiplicationRec(int arr[], int i, int j) {
        if (i == j) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int cost1 = matrixChainMultiplicationRec(arr, i, k);
            int cost2 = matrixChainMultiplicationRec(arr, k + 1, j);
            int cost3 = arr[i - 1] * arr[k] * arr[j];
            int finalCost = cost1 + cost2 + cost3;
            ans = Math.min(ans, finalCost);
        }
        return ans;
    }

    // Matrix Chain Multiplication using memoization
    public static int matrixChainMultiplicationMemo(int arr[], int i, int j, int[][] dp) {
        if (i == j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int cost1 = matrixChainMultiplicationMemo(arr, i, k, dp);
            int cost2 = matrixChainMultiplicationMemo(arr, k + 1, j, dp);
            int cost3 = arr[i - 1] * arr[k] * arr[j];
            ans = Math.min(ans, cost1 + cost2 + cost3);
        }
        return dp[i][j] = ans;
    }

    // Matrix Chain Multiplication using tabulation
    public static int matrixChainMultiplicationTabu(int arr[]) {
        int n = arr.length;
        int dp[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        for (int len = 2; len <= n - 1; len++) {
            for (int i = 1; i <= n - len; i++) {
                int j = i + len - 1; // col
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int cost1 = dp[i][k];
                    int cost2 = dp[k + 1][j];
                    int cost3 = arr[i - 1] * arr[k] * arr[j];
                    dp[i][j] = Math.min(dp[i][j], cost1 + cost2 + cost3);
                }
            }
        }

        return dp[1][n - 1];
    }

    // Minimum Partitioning
    public static int minimumPartitioning(int arr[]) {
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        int W = sum / 2;
        int dp[][] = new int[n + 1][W + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                if (arr[i - 1] <= j) { // valid
                    dp[i][j] = Math.max(arr[i - 1] + dp[i - 1][j - arr[i - 1]], dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        int sum1 = dp[n][W];
        int sum2 = sum - sum1;
        return Math.abs(sum1 - sum2);
    }

    // Minimum array Jumpa
    public static int minJumps(int arr[]) {
        int n = arr.length;
        int dp[] = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[n - 1] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int steps = arr[i];
            int ans = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + steps && j < n; j++) {
                if (dp[j] != -1) {
                    ans = Math.min(ans, dp[j] + 1);
                }
            }
            if (ans != Integer.MAX_VALUE) {
                dp[i] = ans;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        // int n = 5;
        // int dp[] = new int[n + 1];
        // System.out.println(fibonacciMemoization(n, dp));
        // System.out.println(fibonacciTabulation(n));

        // climbing stairs using recursion
        // System.out.println(countWays(5));

        // climbing stairs using memoization
        // int dp[] = new int[5+1];
        // Arrays.fill(dp, -1);
        // System.out.println(countWaysMemo(5, dp));

        // climbing stairs using tabulation
        // System.out.println(countWaysTabu(5));

        // 0 - 1 Knapsack using recursion
        // int val[] = { 15, 14, 10, 45, 30 };
        // int wt[] = { 2, 5, 1, 3, 4 };
        // int W = 7;
        // System.out.println(knapsackRec(val, wt, W, val.length));

        // 0 - 1 Knapsack using memoization
        // int val[] = { 15, 14, 10, 45, 30 };
        // int wt[] = { 2, 5, 1, 3, 4 };
        // int W = 7;
        // int[][] dp = new int[val.length + 1][W + 1];
        // for (int i = 0; i < dp.length; i++) {
        // for (int j = 0; j < dp[0].length; j++) {
        // dp[i][j] = -1;
        // }
        // }
        // System.out.println(knapsackMemo(val, wt, W, val.length, dp));

        // 0 - 1 Knapsack using tabulation
        // int val[] = { 15, 14, 10, 45, 30 };
        // int wt[] = { 2, 5, 1, 3, 4 };
        // int W = 7;
        // int[][] dp = new int[val.length + 1][W + 1];
        // for (int i = 0; i < dp.length; i++) {
        // for (int j = 0; j < dp[0].length; j++) {
        // dp[i][j] = -1;
        // }
        // }
        // System.out.println(knapsackTabu(val, wt, W));

        // Target Sum Subset O(n * w)
        // int arr[] = {4,2,7,1,3};
        // int sum = 10;
        // System.out.println(targetSumSubset(arr, sum));

        // Unbounded Knapsack using Tabulation
        // int val[] = { 15, 14, 10, 45, 30 };
        // int wt[] = { 2, 5, 1, 3, 4 };
        // int W = 7;
        // int[][] dp = new int[val.length + 1][W + 1];
        // for (int i = 0; i < dp.length; i++) {
        // for (int j = 0; j < dp[0].length; j++) {
        // dp[i][j] = -1;
        // }
        // }
        // System.out.println(unboundedKnapsack(val, wt, W));

        // Rod Cutting
        // int length[] = {1,2,3,4,5,6,7,8};
        // int preices[] = {1,5,8,9,10,17,17,20};
        // int totRod = 8;
        // System.out.println(rodCutting(length, preices, totRod));

        // Longest common subsequence using recursion
        // String str1 = "abcde";
        // String str2 = "ace";
        // System.out.println(lcs(str1, str2, str1.length(), str2.length()));

        // Longest common subsequence using memoization
        // String str1 = "abcde";
        // String str2 = "ace";
        // int n = str1.length();
        // int m = str2.length();
        // int dp[][] = new int[n + 1][m + 1];
        // for (int i = 0; i < n + 1; i++) {
        // for (int j = 0; j < m + 1; j++) {
        // dp[i][j] = -1;
        // }
        // }
        // System.out.println(lcsMemo(str1, str2, str1.length(), str2.length(), dp));

        // Longest common subsequence using memoization
        // String str1 = "abcde";
        // String str2 = "ace";
        // System.out.println(lcsTabu(str1, str2));

        // Longest common substring using tabulation
        // String str1 = "ABCDE";
        // String str2 = "ABGCE";
        // System.out.println(longestCommonSubstring(str1, str2)); //2 -> AB

        // Longest common substring using tabulation
        // int arr1[] = { 50, 3, 10, 7, 40, 80 };
        // System.out.println(longestIncreasingSubsequence(arr1));

        // Edit Distance
        // String word1 = "intention";
        // String word2 = "execution";
        // System.out.println(editDistance(word1, word2));

        // Wildcard Matching
        // String s = "baaabab";
        // String p = "*****ba*****ab";
        // System.out.println(wildcarsMatching(s, p));

        // Catalan's Number using recursion
        // int n = 4;
        // System.out.println(catalanRec(n));

        // Catalan's Number using Memoization
        // int n = 4;
        // int dp[] = new int[n + 1];
        // Arrays.fill(dp, -1);
        // System.out.println(catalanMemo(n, dp));

        // Catalan's Number using Memoization
        // int n = 4;
        // System.out.println(catalanTabu(n));

        // Counting BST's
        // int n = 3;
        // System.out.println(countBST(n));

        // Mountain ranges
        // int n = 4;
        // System.out.println(mountainRanges(n));

        // Matrix Chain Multiplication using recursion
        // int arr[] = { 1, 2, 3, 4, 3 };
        // int n = arr.length;
        // System.out.println(matrixChainMultiplicationRec(arr, 1, n - 1));

        // Matrix Chain Multiplication using memoization
        // int arr[] = { 1, 2, 3, 4, 3 };
        // int n = arr.length;
        // int dp[][] = new int[n][n];
        // for (int i = 0; i < n; i++) {
        // Arrays.fill(dp[i], -1);
        // }
        // System.out.println(matrixChainMultiplicationMemo(arr, 1, n - 1,dp));

        // Matrix Chain Multiplication using tabulation
        // int arr[] = { 1, 2, 3, 4, 3 };
        // System.out.println(matrixChainMultiplicationTabu(arr));

        // Minimum Partitioning
        // int[] arr = { 1, 6, 11, 5 };
        // System.out.println(minimumPartitioning(arr));

        // Minimum Array jumps
        // int arr[] = { 2, 3, 1, 1, 4 };
        // System.out.println(minJumps(arr));
    }
}
