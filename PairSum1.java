import java.util.ArrayList;

public class PairSum1 {
    // public static boolean pairSum1(ArrayList<Integer> list, int target) { //
    // Brute Force method
    // for (int i = 0; i < list.size(); i++) {
    // for (int j = i+1; j < list.size(); j++) {
    // if(list.get(i) + list.get(j) == target){
    // return true;
    // }
    // }
    // }
    // return false;
    // }

    public static boolean pairSum1(ArrayList<Integer> list, int target) { // 2 pointer approach
        int lp = 0;
        int rp = list.size() - 1;
        while (lp < rp) {
            int sum = list.get(lp) + list.get(rp);
            if (sum == target) {
                return true;
            } else if (sum > target) {
                rp--;
            } else {
                lp++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(pairSum1(list, 5));
    }
}
