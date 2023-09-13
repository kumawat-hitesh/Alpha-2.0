public class linear_search {
    public static int linearSearch(int number[], int key) {
        for (int i = 0; i < number.length; i++) {
            if (number[i] == key) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int number[] = {12,34,32,10,8};
        int key = 32;
        int index = linearSearch(number, key);
        if (index == -1) {
            System.out.println("Not Found!");
        }else{
            System.out.println(key+" founded at index number : "+index);
        }
        
    }
}