
public class smallest_number_in_array {

    public static int smallest(int arr[]) {
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (smallest > arr[i]) {
                smallest = arr[i];
            }
        }
        return smallest;
    }

    public static void main(String[] args) {
        int number[] = { 2, -3, 4, 6, 10, 8, 9 };
        int smallest = smallest(number);
        System.out.println(smallest);
    }
}
