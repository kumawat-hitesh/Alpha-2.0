import java.util.ArrayList;
import java.util.Scanner;

public class Arrays {

    // Reverse an array
    public static void reverseArray(int arr[]) {
        int first = 0, last = arr.length - 1;
        while (first < last) {
            int temp = arr[last];
            arr[last] = arr[first];
            arr[first] = temp;

            first++;
            last--;
        }
    }

    // smallest number in array
    public static int smallest(int arr[]) {
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (smallest > arr[i]) {
                smallest = arr[i];
            }
        }
        return smallest;
    }

    // largest number in array
    public static int largest(int arr[]) {
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }
        return largest;
    }

    // Pairs in array
    public static void pairs(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                System.out.print(" (" + arr[i] + "," + arr[j] + ") ");
            }
            System.out.println();
        }
    }

    // Pairs Sum-1
    // public static boolean pairSum1(ArrayList<Integer> list, int target) { //
    // Brute Force method
    // for (int i = 0; i < list.size(); i++) {
    // for (int j = i+1; j < list.size(); j++) {
    // if(list.get(i) + list.get(j) == target){
    // return true;
    // }
    // }
    // }
    // return false;
    // }
    public static boolean pairSum1(ArrayList<Integer> list, int target) { // 2 pointer approach
        int lp = 0;
        int rp = list.size() - 1;
        while (lp < rp) {
            int sum = list.get(lp) + list.get(rp);
            if (sum == target) {
                return true;
            } else if (sum > target) {
                rp--;
            } else {
                lp++;
            }
        }
        return false;
    }

    // Pair Sum-2
    public static boolean pairSum2(ArrayList<Integer> list, int target) {
        int bp = -1;
        int n = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > list.get(i + 1)) {
                bp = i;
                break;
            }
        }
        int lp = bp + 1;
        int rp = bp;
        while (lp != rp) {
            if (list.get(lp) + list.get(rp) == target) {
                return true;
            } else if (list.get(lp) + list.get(rp) < target) {
                lp = (lp + 1) % n;
            } else {
                rp = (rp - 1 + n) % n;
            }
        }
        return false;
    }

    // sub array sum
    public static void subArraySum(int arr[]) {
        int currSum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                currSum = 0;
                for (int k = i; k <= j; k++) {
                    currSum = currSum + arr[k];
                }
                System.out.print(currSum + " ");
            }
            System.out.println();
        }
    }

    // maximum sub array sum -> brute force method
    public static void maxSubArrSum(int arr[]) {
        int currSum;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                currSum = 0;
                for (int k = i; k <= j; k++) {
                    currSum += arr[k];
                }
                if (maxSum < currSum) {
                    maxSum = currSum;
                }
            }
        }
        System.out.print("Maximum sum of sub Array is : " + maxSum);
    }

    // maximum sub array sum -> using prefix array
    public static void maxSubArrSumPrefix(int arr[]) {
        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;

        int preArr[] = new int[arr.length];

        preArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preArr[i] = preArr[i - 1] + arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (i == 0) {
                    currSum = preArr[j];
                } else {
                    currSum = preArr[j] - preArr[i - 1];
                }
            }
            if (maxSum < currSum) {
                maxSum = currSum;
            }
        }
        System.out.println("Max sum : " + maxSum);
    }

    // maximum sub array sum -> kadanes algorithm
    public static void kadanes(int numbers[]) {
        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < numbers.length; i++) {
            currSum = currSum + numbers[i];
            if (currSum < 0) {
                currSum = 0;
            }
            maxSum = Math.max(maxSum, currSum);
        }
        System.out.println("Max sum : " + maxSum);
    }

    // element present twice in array
    public static boolean twiceElement(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    // search an element in sorted and rotated array
    public static int search(int arr[], int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[low] < arr[mid]) { // left sorted part
                if (arr[low] <= target && target < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else { // right sorted part
                if (arr[mid] < target && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    // Trapping rain water
    public static int trappingRainWater(int height[]) {
        int n = height.length;
        // left max
        int leftMax[] = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        // right max
        int rightMax[] = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        // loop max
        int trappedWater = 0;
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            trappedWater += waterLevel - height[i];
        }
        return trappedWater;
    }

    // Best time to buy and sell stocks
    public static int buyAndSell(int prices[]) {
        int buyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (buyPrice < prices[i]) {
                int profit = prices[i] - buyPrice;
                maxProfit = Math.max(profit, maxProfit);
            } else {
                buyPrice = prices[i];
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {

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

        // reverse an array
        // try (Scanner sc = new Scanner(System.in);) {
        // System.out.print("Enter size of array : ");
        // int size = sc.nextInt();
        // System.out.print("Enter elements in array : ");
        // int arr[] = new int[size];
        // for (int i = 0; i < size; i++) {
        // arr[i] = sc.nextInt();
        // }
        // reverseArray(arr);
        // System.out.print("Reversed array : ");
        // for (int i = 0; i < size; i++) {
        // System.out.print(arr[i]+" ");
        // }
        // }

        // int number[] = { 2, -3, 4, 6, 10, 8, 9 };
        // int smallest = smallest(number);
        // System.out.println(smallest);

        // int number[] = {2,4,6,10,8,9};
        // int largest = largest(number);
        // System.out.println(largest);

        // int arr[] = {2,4,6,8,10};
        // pairs(arr);

        // ArrayList<Integer> list = new ArrayList<>();
        // list.add(1);
        // list.add(2);
        // list.add(3);
        // list.add(4);
        // list.add(5);
        // list.add(6);
        // System.out.println(pairSum1(list, 5));

        // ArrayList<Integer> list = new ArrayList<>();
        // list.add(11);
        // list.add(15);
        // list.add(6);
        // list.add(8);
        // list.add(9);
        // list.add(10);
        // System.out.println(pairSum2(list, 16));

        // int arr[] = { 2, 4, 6, 8, 10 };
        // subArraySum(arr);

        // int arr[] = { 2, 4, 6, 8, 10 };
        // maxSubArrSum(arr);

        // int arr[] = { 2, 4, 6, 8, 10 };
        // maxSubArrSumPrefix(arr);

        // int numbers[] = {2,-3,4,-1,-2,1,5,-3};
        // kadanes(numbers);

        // int arr[] = {1,2,3,4,5,6,7};
        // boolean a = twiceElement(arr);
        // System.out.println(a);

        // int arr[] = {4, 5, 6, 7, 0, 1, 2};
        // int target = 0;
        // int search = search(arr, target);
        // System.out.println(search);

        // int height[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        // int trappedWater = trappingRainWater(height);
        // System.out.println("Total Trapped Water is : " + trappedWater);

        // int prices[] = {7, 1, 5, 3, 6, 4};
        // int profit = buyAndSell(prices);
        // System.out.println("Maximum profit is : " + profit);

        // // print 2D array!
        // for (int i = 0; i < matrix.length; i++) {
        // for (int j = 0; j < matrix[0].length; j++) {
        // System.out.print(matrix[i][j] + " ");
        // }
        // System.out.println();
        // }
        // System.out.println();
    }
}
