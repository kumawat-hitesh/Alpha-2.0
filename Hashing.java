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

    // Find Itinerary from Tickets
    public static String getStart(HashMap<String, String> tickets) {
        HashMap<String, String> revMap = new HashMap<>();
        for (String key : tickets.keySet()) {
            revMap.put(tickets.get(key), key);
        }
        for (String key : tickets.keySet()) {
            if (!revMap.containsKey(key)) {
                return key; // starting point
            }
        }
        return null;
    }

    // Two Sum
    public int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            // diff = given target - number given atith index
            int diff = target - arr[i];// check if found difference is presentin the MAP list
            if (visited.containsKey(diff)) {
                // if difference in map matches withthe ith index element in array
                return new int[] {
                        i, visited.get(diff)
                };
            } // add arr element in map to match withfuture element if forms a pair
            visited.put(arr[i], i);
        }
        // if no matches are found
        return new int[] { 0, 0 };
    }

    // Sort by frequency
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i)
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? a.getKey() - b.getKey() : b.getValue() - a.getValue());
        for (Map.Entry<Character, Integer> e : map.entrySet())
            pq.add(e);
        StringBuilder res = new StringBuilder();
        while (pq.size() != 0) {
            char ch = pq.poll().getKey();
            int val = map.get(ch);
            while (val != 0) {
                res.append(ch);
                val--;
            }
        }
        return res.toString();
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

        // HashSet
        // HashSet<Integer> set = new HashSet<>();
        // set.add(1);
        // set.add(2);
        // set.add(4);
        // set.add(1);
        // set.add(2);
        // System.out.println(set); // [1,2,4]

        // Iteration on HashSet -> type:1 O(n)
        // HashSet<String> cities = new HashSet<>();
        // cities.add("Dehli");
        // cities.add("Mumbai");
        // cities.add("Noida");
        // cities.add("Banglore");
        // System.out.println(cities);
        // Iterator it = cities.iterator();
        // while (it.hasNext()) {
        // System.out.println(it.next());
        // }

        // Iteration on HashSet -> type:2 O(n)
        // for (String city : cities) {
        // System.out.println(city);
        // }

        // LinkedHashSet -> maintains insertion order
        // LinkedHashSet<String> lhs = new LinkedHashSet<>();
        // lhs.add("Dehli");
        // lhs.add("Mumbai");
        // lhs.add("Noida");
        // lhs.add("Banglore");
        // System.out.println(lhs);

        // TreeSet -> Sorted in ascending order & null valued are not allowed
        // TreeSet<String> ts = new TreeSet<>();
        // ts.add("Dehli");
        // ts.add("Mumbai");
        // ts.add("Noida");
        // ts.add("Banglore");
        // System.out.println(ts);

        // Count Distinct Elements
        // int num[] = { 4, 3, 2, 5, 6, 7, 3, 4, 2, 1 };
        // HashSet<Integer> set = new HashSet<>();
        // for (int i = 0; i < num.length; i++) {
        // set.add(num[i]);
        // }
        // System.out.println("Total Count : " + set.size());

        // union And Intersection of 2 Arrays
        // int arr1[] = { 7, 3, 9 };
        // int arr2[] = { 6, 3, 9, 2, 9, 4 };
        // HashSet<Integer> set = new HashSet<>();
        // // union
        // for (int i = 0; i < arr1.length; i++) {
        // set.add(arr1[i]);
        // }
        // for (int i = 0; i < arr2.length; i++) {
        // set.add(arr2[i]);
        // }
        // // System.out.println("Union = " + set.size());
        // System.out.println(set);

        // // intersection
        // set.clear();
        // for (int i = 0; i < arr1.length; i++) {
        // set.add(arr1[i]);
        // }
        // int intersectionCount = 0;
        // for (int i = 0; i < arr2.length; i++) {
        // if (set.contains(arr2[i])) {
        // intersectionCount++;
        // // set.remove(arr2[i]);
        // System.out.print("[" + arr2[i] + "]");
        // set.remove(arr2[i]);
        // }
        // }
        // // System.out.println("Intersection = " + intersectionCount);

        // Find Itinerary from Tickets
        // HashMap<String, String> tickets = new HashMap<>(); // O(n)
        // tickets.put("Chennai", "Bengaluru");
        // tickets.put("Mumbai", "Dehli");
        // tickets.put("Goa", "Chennai");
        // tickets.put("Dehli", "Goa");
        // String start = getStart(tickets);
        // System.out.print(start);
        // for (String key : tickets.keySet()) {
        // System.out.print("->" + tickets.get(start));
        // start = tickets.get(start);
        // }
        // System.out.println();

        // Largest subarray with sum 0
        // int[] arr = { 15, -2, 2, -8, 1, 7, 10, 23 };
        // HashMap<Integer, Integer> map = new HashMap<>();
        // int sum = 0;
        // int len = 0;
        // for (int j = 0; j < arr.length; j++) {
        // sum += arr[j];
        // if (map.containsKey(sum)) {
        // len = Math.max(len, j - map.get(sum));
        // } else {
        // map.put(sum, j);
        // }
        // }
        // System.out.println("Largest subarray with sum 0 : " + len);

        // Subarray sum equal to K
        // int[] arr = { 10, 2, -2, -20, 10 };
        // int k = -10;
        // HashMap<Integer, Integer> map = new HashMap<>();
        // map.put(0, 1);
        // int sum = 0;
        // int ans = 0;
        // for (int j = 0; j < arr.length; j++) {
        // sum += arr[j];
        // if (map.containsKey(sum - k)) {
        // ans += map.get(sum - k);
        // }
        // map.put(sum, map.getOrDefault(sum, 0) + 1);
        // }
        // System.out.println("SubArray Sum : " + ans);
    }
}