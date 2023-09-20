import java.util.*;

public class GreedyAlgorithms {
    public static void main(String[] args) {
        int start[] = { 1, 3, 0, 5, 8, 5 };
        int end[] = { 2, 4, 6, 7, 9, 9 };

        // if start is in sorted order ans end is not in sorted order then:
        // int activities[][] = new int[start.length][3];
        // for (int i = 0; i < start.length; i++) {
        // activities[i][0] = i;
        // activities[i][1] = start[i];
        // activities[i][2] = end[i];
        // }
        // //lamda function
        // Arrays.sort(activities, Comparator.comparingDouble(o -> o[2]));

        int maxActivity = 1;
        int lastEnd = end[0];

        for (int i = 1; i < end.length; i++) {
            if (start[i] >= lastEnd) {
                maxActivity++;
                lastEnd = end[i];
            }
        }

        System.out.println("Maximum Activity : " + maxActivity);

        int val[] = {60, 100, 120};
        int weight[] = {10, 20, 30};
        int W = 50;

        double ratio[][] = new double[val.length][2];
        for(int i = 0; i < val.length; i++){
            ratio[i][0] = i;
            ratio[i][1] = val[i]/weight[i];
        }
        Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));
        int capacity = W;
        int finalVal = 0;
        for(int i = ratio.length-1; i >= 0; i--){
            int idx = (int)ratio[i][0];
            if (capacity >= weight[idx]) {
                finalVal += val[idx];
                capacity -= weight[idx];
            }else{
                finalVal += (ratio[i][1] * capacity);
                capacity = 0;
                break;
            }
        }
        System.out.println("Maximum Value : " + finalVal);
    }
}
