public class Array {

    //element present twice in array
    public static boolean twiceElement(int arr[]) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] == arr[j]){
                    return true;
                }
            }
        }
        return false;
    }

    //search an element in sorted and rotated array
    public static int search(int arr[], int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if(arr[mid] == target) return mid;
            if (arr[low] < arr[mid]) { //left sorted part
                if (arr[low] <= target && target < arr[mid]) {
                    high = mid - 1;
                } else{
                    low = mid + 1;
                }
            } else{ //right sorted part
                if (arr[mid] < target && target <= arr[high]) {
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    //Trapping rain water
    public static int trappingRainWater(int height[]) {
        int n = height.length;
        //left max
        int leftMax[] = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i-1]);
        }
        //right max
        int rightMax[] = new int[n];
        rightMax[n-1] = height[n-1];
        for (int i = n-2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i+1]);
        }
        //loop max
        int trappedWater = 0;
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            trappedWater += waterLevel-height[i];
        }
        return trappedWater;
    }
    
    //Best time to buy and sell stocks
    public static int buyAndSell(int prices[]) {
        int buyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (buyPrice < prices[i]) {
                int profit = prices[i] - buyPrice;
                maxProfit = Math.max(profit, maxProfit);
            }
            else{
                buyPrice = prices[i];
            }
        }
        return maxProfit;
    }
    
    public static void main(String[] args) {
        // int arr[] = {1,2,3,4,5,6,7};
        // boolean a = twiceElement(arr);
        // System.out.println(a);

        // int arr[] = {4, 5, 6, 7, 0, 1, 2};
        // int target = 0;
        // int search = search(arr, target);
        // System.out.println(search);
        
        // int height[] = {0, 1, 0,  2, 1, 0, 1, 3, 2, 1, 2, 1};
        // int trappedWater = trappingRainWater(height);
        // System.out.println("Total Trapped Water is : " + trappedWater);
        
        // int prices[] = {7, 1, 5, 3, 6,  4};
        // int profit = buyAndSell(prices);
        // System.out.println("Maximum profit is : " + profit);


    }
}


