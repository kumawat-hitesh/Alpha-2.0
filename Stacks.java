import java.util.ArrayList;
import java.util.Stack;

public class Stacks {

    static class StackUsingArrayList {
        static ArrayList<Integer> list = new ArrayList<>();

        public static boolean isEmpty() {
            return list.size() == 0;
        }

        public static void push(int data) {
            list.add(data);
        }

        public static int pop() {
            if (isEmpty())
                return -1;
            int top = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return top;
        }

        public static int peek() {
            if (isEmpty())
                return -1;
            return list.get(list.size() - 1);
        }
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class StackUsingLinkedList {
        static Node head = null;

        public static boolean isEmpty() {
            return head == null;
        }

        // push
        public static void push(int data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        }

        // pop
        public static int pop() {
            if (isEmpty())
                return -1;
            int top = head.data;
            head = head.next;
            return top;
        }

        // peek
        public static int peek() {
            if (isEmpty())
                return -1;
            return head.data;
        }
    }

    // push at bottom
    public static void pushAtBottom(Stack<Integer> s, int data) {
        if (s.isEmpty()) {
            s.push(data);
            return;
        }
        int top = s.pop();
        pushAtBottom(s, data);
        s.push(top);
    }

    // reverse string
    public static String reverseString(String str) {
        Stack<Character> s = new Stack<>();
        int idx = 0;
        while (idx < str.length()) {
            s.push(str.charAt(idx));
            idx++;
        }
        StringBuilder result = new StringBuilder("");
        while (!s.isEmpty()) {
            char curr = s.pop();
            result.append(curr);
        }
        return result.toString();
    }

    // reverse stack
    public static void reverseStack(Stack<Integer> s) {
        if (s.isEmpty())
            return;
        int top = s.pop();
        reverseStack(s);
        pushAtBottom(s, top);
    }

    // stock span problem
    public static void stockSpan(int stocks[], int span[]) {
        Stack<Integer> s = new Stack<>();
        span[0] = 1;
        s.push(0);

        for (int i = 1; i < stocks.length; i++) {
            int currPrice = stocks[i];
            while (!s.isEmpty() && currPrice > stocks[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) {
                span[i] = i + 1;
            } else {
                int prevHigh = s.peek();
                span[i] = i - prevHigh;
            }
            s.push(i);
        }
    }

    // Valid Parenthesis
    public static boolean isValid(String str) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                s.push(ch);
            } else {
                if (s.isEmpty()) {
                    return false;
                }
                if ((s.peek() == '(' && ch == ')')
                        || (s.peek() == '[' && ch == ']')
                        || (s.peek() == '{' && ch == '}')) {
                    s.pop();
                } else {
                    return false;
                }
            }
        }
        if (s.isEmpty()) {
            return true;
        }
        return false;
    }

    // Duplicate parenthesis
    public static boolean isDuplicate(String str) {
        Stack<Character> s = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // closing
            if (ch == ')') {
                int count = 0;
                while (s.peek() != '(') {
                    s.pop();
                    count++;
                }
                if (count < 1) {
                    return true; // duplicare
                } else {
                    s.pop(); // opening pair
                }
            } else { // opening
                s.push(ch);
            }
        }
        return false;
    }

    // Max area in histogram
    public static void maxAreaInHistogram(int arr[]) {
        int maxArea = 0;
        int nsr[] = new int[arr.length];
        int nsl[] = new int[arr.length];

        // next smaller right
        Stack<Integer> s = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                nsr[i] = arr.length;
            } else {
                nsr[i] = s.peek();
            }
            s.push(i);
        }

        // next smaller left
        s = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                nsl[i] = -1;
            } else {
                nsl[i] = s.peek();
            }
            s.push(i);
        }

        // current area : width = i-j-1 = nsr[i]-nsl[i]-1
        for (int i = 0; i < arr.length; i++) {
            int height = arr[i];
            int width = nsr[i] - nsl[i] - 1;
            int currArea = height * width;
            maxArea = Math.max(maxArea, currArea);
        }
        System.out.println("Max area in histogram is = " + maxArea);
    }

    // print stack
    public static void printStack(Stack<Integer> s) {
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }

    public static void main(String[] args) {
        // StackUsingArrayList sal = new StackUsingArrayList();
        // sal.push(1);
        // sal.push(2);
        // sal.push(3);
        // while (!sal.isEmpty()) {
        // System.out.println(sal.peek());
        // sal.pop();
        // }

        // StackUsingLinkedList sll = new StackUsingLinkedList();
        // sll.push(1);
        // sll.push(2);
        // sll.push(3);
        // while (!sll.isEmpty()) {
        // System.out.println(sll.peek());
        // sll.pop();
        // }

        // Stack<Integer> s = new Stack<>(); //push at bottom
        // s.push(1);
        // s.push(2);
        // s.push(3);
        // pushAtBottom(s, 4);
        // while (!s.isEmpty()) {
        // System.out.println(s.peek());
        // s.pop();
        // }

        // String str = "abc"; //cba
        // String ans = reverseString(str);
        // System.out.println(ans);

        // Stack<Integer> s = new Stack<>(); //reverse stack
        // s.push(1);
        // s.push(2);
        // s.push(3);
        // reverseStack(s);
        // printStack(s);

        // stock span problem
        // int stocks[] = { 100, 80, 60, 70, 60, 85, 100 };
        // int span[] = new int[stocks.length];
        // stockSpan(stocks, span);
        // for (int i = 0; i < span.length; i++) {
        // System.out.print(span[i] + " ");
        // }

        // Next Greater Element
        // int arr[] = { 6, 8, 0, 1, 3 };
        // int nextGreater[] = new int[arr.length];
        // Stack<Integer> s = new Stack<>();

        // for (int i = arr.length - 1; i >= 0; i--) {
        // // while
        // while (!s.isEmpty() && arr[s.peek()] <= arr[i]) {
        // s.pop();
        // }
        // // if - else
        // if (s.isEmpty()) {
        // nextGreater[i] = -1;
        // } else {
        // nextGreater[i] = arr[s.peek()];
        // }
        // // push in s
        // s.push(i);
        // }
        // for (int j = 0; j < nextGreater.length; j++) {
        // System.out.print(nextGreater[j] + " ");
        // }

        // String str = "({[]})"; //valid parenthesis
        // System.out.println(isValid(str));

        // String str1 = "((a+b))"; //duplicate parenthesis
        // String str2 = "(a+b)";
        // System.out.println(isDuplicate(str2));

        // max area in histogram
        int arr[] = { 2, 1, 5, 6, 2, 3 };
        maxAreaInHistogram(arr);

    }
}
