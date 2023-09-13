import java.util.Scanner;

public class binary_search {
    public static int binarySearch(int arr[], int key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (key == arr[mid]) {
                return mid;
            } else if (key < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);) {
            System.out.print("Enter size of array : ");
            int size = sc.nextInt();
            System.out.print("Enter " + size + " elements in array : ");
            int arr[] = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.print("Enter the element to be searched : ");
            int key = sc.nextInt();
            int bs = binarySearch(arr, key);
            if (bs == -1) {
                System.out.print("Element you entered " + key + " is Not founded!");
            } else {
                System.out.print("Element " + key + " found at index : " + bs);
            }
        }
    }
}
