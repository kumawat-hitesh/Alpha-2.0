public class largest_number_in_array {

    public static int largest(int arr[]) {
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>largest) {
                largest = arr[i];
            }
        }
        return largest;
    }
    public static void main(String[] args) {
        int number[] = {2,4,6,10,8,9};
        int largest = largest(number);
        System.out.println(largest);
    }
}
