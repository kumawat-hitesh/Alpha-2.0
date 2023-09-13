public class sub_array_sum {

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

    public static void main(String[] args) {
        int arr[] = { 2, 4, 6, 8, 10 };
        subArraySum(arr);    
    }
}
