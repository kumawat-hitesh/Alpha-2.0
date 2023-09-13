public class max_sub_array_sum_kadanes {

    public static void kadanes(int numbers[]) {
        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for(int i = 0; i < numbers.length; i++){
            currSum = currSum + numbers[i];
            if (currSum < 0) {
                currSum = 0;
            }
            maxSum = Math.max(maxSum, currSum);
        }
        System.out.println("Max sum : "+maxSum);
    }

    public static void main(String[] args) {
        int numbers[] = {2,-3,4,-1,-2,1,5,-3};
        kadanes(numbers);
    }
}
