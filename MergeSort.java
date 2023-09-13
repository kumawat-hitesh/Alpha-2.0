public class MergeSort {
    public static void mergeSort(int arr[], int si, int ei) {
        if (si >= ei) { // base case
            return;
        }
        int mid = si + (ei - si) / 2;
        mergeSort(arr, si, mid); // recursive call for left part
        mergeSort(arr, mid + 1, ei); // recursive call for right part
        merge(arr, si, mid, ei); // merge all divided left and right arrays
    }

    public static void merge(int arr[], int si, int mid, int ei) {
        int temp[] = new int[ei - si + 1];
        int i = si; // index for left sorted part
        int j = mid + 1; // index for right sorted part
        int k = 0; // index for temp arr
        while (i <= mid && j <= ei) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= ei) {
            temp[k++] = arr[j++];
        }
        // copy temp to original array
        for (k = 0, i = si; k < temp.length; k++, i++) {
            arr[i] = temp[k];
        }
    }

    // print array
    public static void printArr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = { 6, 3, 9, 5, 2, 8 };
        mergeSort(arr, 0, arr.length - 1);
        printArr(arr);

    }
}
