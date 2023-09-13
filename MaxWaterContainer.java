import java.util.ArrayList;

public class MaxWaterContainer {

    // public static int storeWater(ArrayList<Integer> height) { // Brute Force
    // method
    // int maxWater = 0;
    // for (int i = 0; i < height.size(); i++) {
    // for (int j = i+1; j < height.size(); j++) {
    // int h = Math.min(height.get(i), height.get(j));
    // int w = j - i;
    // int currWater = h * w;
    // maxWater = Math.max(maxWater, currWater);
    // }
    // }
    // return maxWater;
    // }

    public static int storeWater(ArrayList<Integer> height) { // 2 Pointer Approach
        int maxWater = 0;
        int leftPointer = 0;
        int rightPointer = height.size() - 1;

        while (leftPointer < rightPointer) {
            // calculate water area
            int h = Math.min(height.get(leftPointer), height.get(rightPointer));
            int w = rightPointer - leftPointer;
            int currWater = h * w;
            maxWater = Math.max(currWater, maxWater);

            // update
            if (leftPointer < rightPointer) {
                leftPointer++;
            } else {
                rightPointer--;
            }
        }
        return maxWater;
    }

    public static void main(String[] args) {
        ArrayList<Integer> height = new ArrayList<>();
        height.add(1);
        height.add(8);
        height.add(6);
        height.add(2);
        height.add(5);
        height.add(4);
        height.add(8);
        height.add(3);
        height.add(7);
        System.out.println(storeWater(height));
    }
}
