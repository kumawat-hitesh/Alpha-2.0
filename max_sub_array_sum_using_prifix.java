
public class max_sub_array_sum_using_prifix {

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

    public static void main(String[] args) {
        int arr[] = { 2, 4, 6, 8, 10 };
        maxSubArrSumPrefix(arr);
    }
}
