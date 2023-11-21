import java.util.*;
import java.util.LinkedList;

public class Heaps {
    static class Student implements Comparable<Student> {
        String name;
        int rank;

        public Student(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }

        @Override
        public int compareTo(Student s2) {
            return this.rank - s2.rank;
        }
    }

    // insert in heap
    static class Heap {
        ArrayList<Integer> arr = new ArrayList<>();

        public void add(int data) {
            arr.add(data);

            int x = arr.size() - 1;
            int parent = (x - 1) / 2;
            while (arr.get(x) < arr.get(parent)) {
                // swap
                int temp = arr.get(x);
                arr.set(x, arr.get(parent));
                arr.set(parent, temp);
                x = parent;
                parent = (x - 1) / 2;
            }
        }

        public int peek() {
            return arr.get(0);
        }

        private void heapify(int i) { // (logn)
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int minIdx = i;
            if (left < arr.size() && arr.get(minIdx) > arr.get(left)) {
                minIdx = left;
            }
            if (right < arr.size() && arr.get(minIdx) > arr.get(right)) {
                minIdx = right;
            }
            if (minIdx != i) {
                // swap
                int temp = arr.get(i);
                arr.set(i, arr.get(minIdx));
                arr.set(minIdx, temp);

                heapify(minIdx);
            }
        }

        public int remove() { // o(logn)
            int data = arr.get(0);

            // swap first and last
            int temp = arr.get(0);
            arr.set(0, arr.get(arr.size() - 1));
            arr.set(arr.size() - 1, temp);

            // step2 - delete last
            arr.remove(arr.size() - 1);

            // step3 - heapify
            heapify(0);
            return data;
        }

        public boolean isEmpty() {
            return arr.size() == 0;
        }
    }

    public static void heapify(int arr[], int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * 1 + 2;
        int maxIdx = i;
        if (left < size && arr[left] > arr[maxIdx]) {
            maxIdx = left;
        }
        if (right < size && arr[right] > arr[maxIdx]) {
            maxIdx = right;
        }
        if (maxIdx != i) {
            // swap
            int temp = arr[i];
            arr[i] = arr[maxIdx];
            arr[maxIdx] = temp;
            heapify(arr, maxIdx, size);
        }
    }

    public static void heapSort(int arr[]) {
        // step:1 built max heap
        int n = arr.length;
        for (int i = n / 2; i >= 0; i--) {
            heapify(arr, i, n);
        }
        // step:2 push larget at end
        for (int i = n - 1; i >= 0; i--) {
            // swap : largest(first) -> last
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, 0, i);
        }
    }

    // Nearby Cars
    static class Point implements Comparable<Point> {
        int x;
        int y;
        int distSq;
        int idx;

        public Point(int x, int y, int distSq, int idx) {
            this.x = x;
            this.y = y;
            this.distSq = distSq;
            this.idx = idx;
        }

        @Override
        public int compareTo(Point p2) {
            return this.distSq - p2.distSq;
        }
    }

    // Weakest Soldier
    static class Row implements Comparable<Row> {
        int soldier;
        int idx;

        public Row(int soldier, int idx) {
            this.soldier = soldier;
            this.idx = idx;
        }

        @Override
        public int compareTo(Row r2) {
            if (this.soldier == r2.soldier) {
                return this.idx - r2.idx;
            } else {
                return this.soldier - r2.soldier;
            }
        }
    }

    // Sliding Window Maximum
    static class Pair implements Comparable<Pair> {
        int val;
        int idx;

        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(Pair p2) {
            // ascending
            // return this.val - p2.val;
            // decending
            return p2.val - this.val;
        }
    }

    // Q.1 : K’th largest element in a stream
    static PriorityQueue<Integer> min;
    static int k;

    static List<Integer> getAllKthNumber(int arr[]) {
        List<Integer> list = new ArrayList<>();
        for (int val : arr) {
            if (min.size() < k)
                min.add(val);
            else {
                if (val > min.peek()) {
                    min.poll();
                    min.add(val);
                }
            }
            if (min.size() >= k)
                list.add(min.peek());
            else
                list.add(-1);
        }
        return list;
    }

    // Q.2 : Minimum time required to fill given N slots
    public static void minTime(int arr[], int N, int K) {
        Queue<Integer> q = new LinkedList<>();
        boolean vis[] = new boolean[N + 1];
        int time = 0;
        for (int i = 0; i < K; i++) {
            q.add(arr[i]);
            vis[arr[i]] = true;
        }
        while (q.size() > 0) {
            for (int i = 0; i < q.size(); i++) {
                int curr = q.poll();
                if (curr - 1 >= 1 &&
                        !vis[curr - 1]) {
                    vis[curr - 1] = true;
                    q.add(curr - 1);
                }
                if (curr + 1 <= N && !vis[curr + 1]) {
                    vis[curr + 1] = true;
                    q.add(curr + 1);
                }
            }
            time++;
        }
        System.out.println(time - 1);
    }

    // Q.3 : Path With Minimum Effort
    static String decode(String str) {
        Stack<Integer> integerstack = new Stack<>();
        Stack<Character> stringstack = new Stack<>();
        String temp = "", result = "";
        for (int i = 0; i < str.length(); i++) {
            int count = 0;
            if (Character.isDigit(str.charAt(i))) {
                while (Character.isDigit(str.charAt(i))) {
                    count = count * 10 + str.charAt(i) - '0';
                    i++;
                }
                i--;
                integerstack.push(count);
            } else if (str.charAt(i) == ']') {
                temp = "";
                count = 0;
                if (!integerstack.isEmpty()) {
                    count = integerstack.peek();
                    integerstack.pop();
                }
                while (!stringstack.isEmpty() && stringstack.peek() != '[') {
                    temp = stringstack.peek() + temp;
                    stringstack.pop();
                }
                if (!stringstack.empty() && stringstack.peek() == '[')
                    stringstack.pop();
                for (int j = 0; j < count; j++)
                    result = result + temp;
                for (int j = 0; j < result.length(); j++)
                    stringstack.push(result.charAt(j));
                result = "";
            } else if (str.charAt(i) == '[') {
                if (Character.isDigit(str.charAt(i - 1)))
                    stringstack.push(str.charAt(i));
                else {
                    stringstack.push(str.charAt(i));
                    integerstack.push(1);
                }
            } else
                stringstack.push(str.charAt(i));
        }
        while (!stringstack.isEmpty()) {
            result = stringstack.peek() + result;
            stringstack.pop();
        }
        return result;
    }

    // Q.4 : Minimum Operations to Halve Array Sum
    static int minops(ArrayList<Integer> nums) {
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < nums.size(); i++) {
            pq.add(-nums.get(i));
        }
        double temp = sum;
        int cnt = 0;
        while (temp > sum / 2) {
            int x = -pq.peek();
            pq.remove();
            temp -= Math.ceil(x * 1.0 / 2);
            pq.add(x / 2);
            cnt++;
        }
        return cnt;
    }

    // Q.5 : Merge K Sorted Linked List
    static Node mergeKList(Node[] arr, int K) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new NodeComparator());
        // Node at[] = new Node[K];
        Node head = new Node(0);
        Node last = head;
        for (int i = 0; i < K; i++) {
            if (arr[i] != null) {
                queue.add(arr[i]);
            }
        }
        if (queue.isEmpty())
            return null;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            last.next = curr;
            last = last.next;
            if (curr.next != null) {
                queue.add(curr.next);
            }
        }
        return head.next;
    }

    public static void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        // pq.add(3);
        // pq.add(4);
        // pq.add(1);
        // pq.add(7);
        // while (!pq.isEmpty()) {
        // System.out.println(pq.peek());
        // pq.remove();
        // }

        // PriorityQueue<Student> pq = new PriorityQueue<>();
        // pq.add(new Student("A", 4));
        // pq.add(new Student("B", 5));
        // pq.add(new Student("C", 2));
        // pq.add(new Student("D", 12));
        // while (!pq.isEmpty()) {
        // System.out.println(pq.peek().name + "->" + pq.peek().rank);
        // pq.remove();
        // }

        // Heap h = new Heap();
        // h.add(3);
        // h.add(4);
        // h.add(1);
        // h.add(5);
        // while (!h.isEmpty()) {
        // System.out.println(h.peek());
        // h.remove();
        // }

        // int arr[] = {1,2,4,5,3};
        // heapSort(arr);

        // Nearby Cars
        // int points[][] = { { 3, 3 }, { 5, -1 }, { -2, 4 } };
        // int k = 2;
        // PriorityQueue<Point> pq = new PriorityQueue<>();
        // for (int i = 0; i < points.length; i++) {
        // int distSq = points[i][0] * points[i][0] + points[i][1] * points[i][1];
        // pq.add(new Point(points[i][0], points[i][1], distSq, i));
        // }
        // for (int i = 0; i < k; i++) {
        // System.out.println("C" + pq.remove().idx);
        // }

        // Connect N ropes with minimum cost
        // int ropes[] = { 2, 3, 3, 4, 6 };
        // PriorityQueue<Integer> pq = new PriorityQueue<>();
        // for (int i = 0; i < ropes.length; i++) {
        // pq.add(ropes[i]);
        // }
        // int cost = 0;
        // while (pq.size() > 1) {
        // int min1 = pq.remove();
        // int min2 = pq.remove();
        // cost += min1 + min2;
        // pq.add(min1 + min2);
        // }
        // System.out.println("Cost of connecting n ropes is : " + cost);

        // Weakest Soldier
        // int army[][] = {
        // { 1, 0, 0, 0 },
        // { 1, 1, 1, 1 },
        // { 1, 0, 0, 0 },
        // { 1, 0, 0, 0 }
        // };
        // int k = 2;
        // PriorityQueue<Row> pq = new PriorityQueue<>();
        // for (int i = 0; i < army.length; i++) {
        // int count = 0;
        // for (int j = 0; j < army[0].length; j++) {
        // count += army[i][j] == 1 ? 1 : 0;
        // }
        // pq.add(new Row(count, i));
        // }
        // for (int i = 0; i < k; i++) {
        // System.out.println("R" + pq.remove().idx);
        // }

        // Sliding Window Maximum O(nlog(k))
        // int arr[] = { 1, 3, -1, -3, 5, 3, 6, 7 };
        // int k = 3;
        // int n = arr.length;
        // int res[] = new int[n - k + 1];
        // PriorityQueue<Pair> pq = new PriorityQueue<>();
        // // 1st Window
        // for (int i = 0; i < k; i++) {
        // pq.add(new Pair(arr[i], i));
        // }
        // res[0] = pq.peek().val;
        // for (int i = k; i < n; i++) {
        // while (pq.size() > 0 && pq.peek().idx <= (i - k)) {
        // pq.remove();
        // }
        // pq.add(new Pair(arr[i], i));
        // res[i - k + 1] = pq.peek().val;
        // }
        // for (int i = 0; i < res.length; i++) {
        // System.out.print(res[i] + " ");
        // }

        // Q.1 : K’th largest element in a stream
        // min = new PriorityQueue<>();
        // k = 4;
        // int arr[] = { 1, 2, 3, 4, 5, 6 };
        // List<Integer> res = getAllKthNumber(arr);
        // for (int x : res)
        // System.out.print(x + " ");

        // Q.2 : Minimum time required to fill given N slots
        // int N = 6;
        // int arr[] = { 2, 6 };
        // int K = arr.length;
        // minTime(arr, N, K);

        // Q.3 : Path With Minimum Effort
        // String str = "3[b2[ca]]";
        // System.out.println(decode(str));

        // Q.4 : Minimum Operations to Halve Array Sum
        // ArrayList<Integer> nums = new ArrayList<Integer>(List.of(
        // 4, 6, 3, 9, 10, 2));
        // int count = minops(nums);
        // System.out.println(count);

        // Q.5 : Merge K Sorted Linked List
        int N = 3;
        Node[] a = new Node[N];
        Node head1 = new Node(1);
        a[0] = head1;
        head1.next = new Node(3);
        head1.next.next = new Node(5);
        head1.next.next.next = new Node(7);
        Node head2 = new Node(2);
        a[1] = head2;
        head2.next = new Node(4);
        head2.next.next = new Node(6);
        head2.next.next.next = new Node(8);
        Node head3 = new Node(0);
        a[2] = head3;
        head3.next = new Node(9);
        head3.next.next = new Node(10);
        head3.next.next.next = new Node(11);
        Node res = mergeKList(a, N);
        if (res != null)
            printList(res);
        System.out.println();

    }
}

class Node {
    int data;
    Node next;

    Node(int key) {
        data = key;
        next = null;
    }
}

class NodeComparator implements Comparator<Node> {
    public int compare(Node k1, Node k2) {
        if (k1.data > k2.data)
            return 1;
        else if (k1.data < k2.data)
            return -1;
        return 0;
    }
}