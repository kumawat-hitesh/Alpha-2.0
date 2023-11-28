import java.util.*;

public class Hashing {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (map.get(ch) != null) {
                if (map.get(ch) == 1) {
                    map.remove(ch);
                } else {
                    map.put(ch, map.get(ch) - 1);
                }
            } else {
                return false;
            }
        }
        return map.isEmpty();
    }

    public static void main(String[] args) {
        // HashMap<String, Integer> map = new HashMap<>();
        // map.put("India", 200);
        // map.put("China", 150);
        // map.put("US", 100);
        // map.put("Nepal", 5);
        // map.put("Indonesia", 6);
        // System.out.println(map);

        // iteration on hashmap
        // Set<String> keys = map.keySet();
        // System.out.println(keys);

        // for (String k : keys) {
        // System.out.println("Key=" + k + " Value=" + map.get(k));
        // }

        // Linked hashMap
        // LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
        // lhm.put("India", 100);
        // lhm.put("China", 150);
        // lhm.put("US", 50);
        // System.out.println(lhm);

        // TreeMap
        // TreeMap<String, Integer> tm = new TreeMap<>();
        // tm.put("India", 100);
        // tm.put("China", 150);
        // tm.put("US", 50);
        // System.out.println(tm);

        // Majority Element
        // int[] arr = { 1, 3, 2, 5, 1, 3, 1, 5, 1 };
        // int[] arr = { 1, 2 };
        // HashMap<Integer, Integer> map = new HashMap<>();
        // for (int i = 0; i < arr.length; i++) {
        // // if (map.containsKey(arr[i])) {
        // // map.put(arr[i], map.get(arr[i]) + 1);
        // // } else {
        // // map.put(arr[i], 1);
        // // }
        // map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        // }
        // for (Integer key : map.keySet()) {
        // if (map.get(key) > arr.length / 3) {
        // System.out.print(key + " ");
        // }
        // }

        // Valid Anagram
        // String s = "tulip";
        // String t = "lipid";
        // System.out.print(isAnagram(s, t)); //O(n)
    }
}