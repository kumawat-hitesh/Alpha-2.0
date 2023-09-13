import java.util.*;

public class reverse_an_array {
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

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);) {
            System.out.print("Enter size of array : ");
            int size = sc.nextInt();
            System.out.print("Enter elements in array : ");
            int arr[] = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = sc.nextInt();
            }
            reverseArray(arr);
            System.out.print("Reversed array : ");
            for (int i = 0; i < size; i++) {
                System.out.print(arr[i]+" ");
            }
        }
    }
}
