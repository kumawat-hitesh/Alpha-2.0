public class Queue {
    static class QueuesUsingArrays {

        static int arr[];
        static int size;
        static int rear;
        QueuesUsingArrays(int n){
            arr = new int[n];
            size = n;
            rear = -1;
        }

        public static boolean isEmpty(){
            return rear == -1;
        }

        public static void add(int data){
            if(rear == size-1){
                System.out.println("Queue is full");
                return;
            }
            rear = rear + 1;
            arr[rear] = data; 
        }
        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is Empty");
                return -1;
            }
            int front = arr[0];
            for (int i = 0; i < rear; i++) {
                arr[i] = arr[i+1];
            }
            rear = rear - 1;
            return front;
        }
        public static int peek(){
            if(isEmpty()){
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
        CircularQueuesUsingArrays(int n){
            arr = new int[n];
            size = n;
            rear = -1;
            front = -1;
        }

        public static boolean isEmpty(){
            return rear == -1 && front == -1;
        }
        public static boolean isFull(){
            return (rear + 1) % size == front;
        }
        //add
        public static void add(int data){
            if(isFull()){
                System.out.println("Queue is full");
                return;
            }
            if(front == -1){
                front = 0; 
            }
            rear = (rear + 1) % size;
            arr[rear] = data; 
        }
        //remove
        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is Empty");
                return -1;
            }
            int result = arr[front];
            if(rear == front){
                rear = front = -1;
            }else{
                front = (front + 1) % size;
            }
            return result;
        }
        //peek
        public static int peek(){
            if(isEmpty()){
                System.out.println("Queue is Empty");
                return -1;
            }
            return arr[front];
        }
    }
    
    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            this.next = null; 
        }
    }

    static class QueueUsingLL{
        public static Node head = null;
        public static Node tail = null;

        public static boolean isEmpty(){
            return head == null && tail == null;
        }
        public static void add(int data){
            Node newNode = new Node(data);
            if(head == null){
                head = tail = newNode;
                return;
            }
            tail.next = newNode;
            tail = newNode;
        }
        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is Empty");
                return -1;
            }
            int front = head.data;
            if(head == tail){
                head = tail = null;
            }else{
                head = head.next;
            }
            return front;
        }
        public static int peek(){
            if(isEmpty()){
                System.out.println("Queue is Empty");
                return -1;
            }
            return head.data; 
        }
    }
    public static void main(String[] args) {
        QueuesUsingArrays qua = new QueuesUsingArrays(5);
        // qua.add(1);
        // qua.add(2);
        // qua.add(3);
        // qua.add(4);
        // qua.add(5);
        // // qua.remove();
        
        // System.out.println("Peek : " + qua.peek());

        // System.out.println("------------------");

        // while (!qua.isEmpty()) {
        //     System.out.println(qua.peek());
        //     qua.remove();
        // }

        CircularQueuesUsingArrays cqua = new CircularQueuesUsingArrays(3);
        // cqua.add(1);
        // cqua.add(2);
        // cqua.add(3);
        // System.out.println(cqua.remove());
        // cqua.add(4);
        // System.out.println(cqua.remove());
        // cqua.add(5);
        // while (!cqua.isEmpty()) {
        //     System.err.println(cqua.peek());
        //     cqua.remove();
        // }
    }
}
