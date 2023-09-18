import java.util.*;
import java.util.LinkedList;

import javax.print.DocFlavor.STRING;

public class Queues {
    static class QueuesUsingArrays {

        static int arr[];
        static int size;
        static int rear;

        QueuesUsingArrays(int n) {
            arr = new int[n];
            size = n;
            rear = -1;
        }

        public static boolean isEmpty() {
            return rear == -1;
        }

        public static void add(int data) {
            if (rear == size - 1) {
                System.out.println("Queue is full");
                return;
            }
            rear = rear + 1;
            arr[rear] = data;
        }

        public static int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            int front = arr[0];
            for (int i = 0; i < rear; i++) {
                arr[i] = arr[i + 1];
            }
            rear = rear - 1;
            return front;
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            return arr[0];
        }
    }

    static class CircularQueuesUsingArrays {

        static int arr[];
        static int size;
        static int rear;
        static int front;

        CircularQueuesUsingArrays(int n) {
            arr = new int[n];
            size = n;
            rear = -1;
            front = -1;
        }

        public static boolean isEmpty() {
            return rear == -1 && front == -1;
        }

        public static boolean isFull() {
            return (rear + 1) % size == front;
        }

        // add
        public static void add(int data) {
            if (isFull()) {
                System.out.println("Queue is full");
                return;
            }
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % size;
            arr[rear] = data;
        }

        // remove
        public static int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            int result = arr[front];
            if (rear == front) {
                rear = front = -1;
            } else {
                front = (front + 1) % size;
            }
            return result;
        }

        // peek
        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            return arr[front];
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

    static class QueueUsingLL {
        public static Node head = null;
        public static Node tail = null;

        public static boolean isEmpty() {
            return head == null && tail == null;
        }

        public static void add(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = tail = newNode;
                return;
            }
            tail.next = newNode;
            tail = newNode;
        }

        public static int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            int front = head.data;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
            return front;
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            return head.data;
        }
    }

    static class QueueUsing2Stacks {
        static Stack<Integer> s1 = new Stack<>();
        static Stack<Integer> s2 = new Stack<>();

        public static boolean isEmpty() {
            return s1.isEmpty();
        }

        public static void add(int data) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s1.push(data);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        public static int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            return s1.pop();
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            return s1.peek();
        }
    }

    static class StackUsing2Queues {
        static Queue<Integer> q1 = new LinkedList<>();
        static Queue<Integer> q2 = new LinkedList<>();

        public static boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        public static void push(int data) {
            if (!q1.isEmpty())
                q1.add(data);
            else
                q2.add(data);
        }

        public static int pop() {
            if (isEmpty()) {
                System.out.println("Empty Stack");
                return -1;
            }
            int top = -1;
            if (!q1.isEmpty()) { // case 1
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    if (q1.isEmpty()) {
                        break;
                    }
                    q2.add(top);
                }
            } else { // case 2
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    if (q2.isEmpty()) {
                        break;
                    }
                    q1.add(top);
                }
            }
            return top;
        }
    }

    // First non repeating letter in stream of letters
    public static void printNonRepeating(String str) {
        int freq[] = new int[26];
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            q.add(ch);
            freq[ch - 'a']++;
            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) {
                q.remove();
            }
            if (q.isEmpty()) {
                System.out.print(-1 + " ");
            } else {
                System.out.print(q.peek() + " ");
            }
        }
    }

    // Interleave two half of queue
    public static void interLeave(Queue<Integer> q) {
        Queue<Integer> q1 = new LinkedList<>();
        int size = q.size();
        for (int i = 0; i < size / 2; i++) {
            q1.add(q.remove());
        }
        while (!q1.isEmpty()) {
            q.add(q1.remove());
            q.add(q.remove());
        }
    }

    // Reverse a Queue
    public static void reverseQueue(Queue<Integer> q) {
        Stack<Integer> s = new Stack<>();
        while (!q.isEmpty()) {
            s.push(q.remove());
        }
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
    }

    public static void main(String[] args) {
        // QueuesUsingArrays qua = new QueuesUsingArrays(5);
        // qua.add(1);
        // qua.add(2);
        // qua.add(3);
        // qua.add(4);
        // qua.add(5);
        // // qua.remove();

        // System.out.println("Peek : " + qua.peek());

        // System.out.println("------------------");

        // while (!qua.isEmpty()) {
        // System.out.println(qua.peek());
        // qua.remove();
        // }

        // CircularQueuesUsingArrays cqua = new CircularQueuesUsingArrays(3);
        // cqua.add(1);
        // cqua.add(2);
        // cqua.add(3);
        // System.out.println(cqua.remove());
        // cqua.add(4);
        // System.out.println(cqua.remove());
        // cqua.add(5);
        // while (!cqua.isEmpty()) {
        // System.err.println(cqua.peek());
        // cqua.remove();
        // }

        // QueueUsing2Stacks qs = new QueueUsing2Stacks();
        // qs.add(1);
        // qs.add(2);
        // qs.add(3);
        // qs.add(4);

        // while (!qs.isEmpty()) {
        // System.out.println(qs.remove());
        // }

        // StackUsing2Queues sq = new StackUsing2Queues();
        // sq.push(1);
        // sq.push(2);
        // sq.push(3);
        // while (!sq.isEmpty()) {
        // System.out.println(sq.pop());
        // }

        // First non repeating letter in stream of letters
        // String str = "aabccxb";
        // printNonRepeating(str);

        // Interleave two half of queue
        // Queue<Integer> q = new LinkedList<>();
        // q.add(1);
        // q.add(2);
        // q.add(3);
        // q.add(4);
        // q.add(5);
        // q.add(6);
        // q.add(7);
        // q.add(8);
        // q.add(9);
        // q.add(10);
        // // interLeave(q);
        // reverseQueue(q);
        // while (!q.isEmpty()) {
        // System.out.print(q.remove() + " ");
        // }

    }
}