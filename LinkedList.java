public class LinkedList {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head; // head node
    public static Node tail; // tail node
    public static int size; // ststic variable for size of linked list

    public void addFirst(int data) { // add node at the first
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int data) { // add node at the last
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void addInMiddle(int index, int data) { // add node in the middle
        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i = 0;
        while (i < index - 1) {
            temp = temp.next;
            i++;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public int removeFirst() { // remove data from the first
        if (size == 0) {
            System.out.println("LL is empty.");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    public int removeLast() { // remove data from the last
        if (size == 0) {
            System.out.println("LL is empty.");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        // prev : i = size - 2;
        Node prev = head;
        for (int i = 0; i < size - 2; i++) {
            prev = prev.next;
        }
        int val = prev.next.data;
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    // iterative search
    public int iterativeSearch(int key) { // TC : O(n) = linear time
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.data == key) {
                return i;
            }
            temp = temp.next;
            i++;
        }
        return -1;
    }

    // recursive search
    public int helper(Node head, int key) {
        if (head == null) {
            return -1;
        }
        if (head.data == key) {
            return 0;
        }
        int index = helper(head.next, key);
        if (index == -1) {
            return -1;
        }
        return index + 1;
    }

    public int recursiveSearch(int key) { // TC : O(n) = linear time
        return helper(head, key);
    }

    // reverse a LinkedList
    public void reverseLinkedList() {
        Node prev = null;
        Node curr = head;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    // Delete Nth From End
    public void deleteNthFromEnd(int n) {
        // calculate size
        int size = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        if (size == n) {
            head = head.next; // remove first
            return;
        }
        // size-n
        int i = 1;
        int iToFind = size - n;
        Node prev = head;
        while (i < iToFind) {
            prev = prev.next;
            i++;
        }
        prev.next = prev.next.next;
        return;
    }

    // check if Linked list is palindrome or not
    public Node findMid(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
        }
        return slow; // slow is mid node
    }

    public boolean checkPalindrome() {
        if (head == null || head.next == null) {
            return true;
        }
        // step 1 - find mid node
        Node midNode = findMid(head);
        // step 2 - reverse 2nd half
        Node prev = null;
        Node curr = midNode;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev;
        Node left = head;
        // step 3 - check left and right half
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    // detecting cycle in linkedlist
    public static boolean isCycle() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true; // cycle exists
            }
        }
        return false; // cycle does't exists
    }

    // remove a cycle in linked list
    public void removeCycle() {
        // detect a cycle
        Node slow = head;
        Node fast = head;
        boolean cycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                cycle = true;
                break;
            }
        }
        if (cycle == false) {
            return;
        }
        // find meeting point
        slow = head;
        Node prev = null; // last node
        while (slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }
        // remove cycle : last.next = null
        prev.next = null;
    }

    // MERGE SORT on linked list
    public Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        // get mid
        Node mid = getMid(head);
        // left & Right MS
        Node rightHead = mid.next;
        mid.next = null;
        Node newleft = mergeSort(head);
        Node newRight = mergeSort(rightHead);

        // merge
        return merge(newleft, newRight);
    }

    // Find Mid node
    private Node getMid(Node head) {
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // mid node
    }

    // Merge
    private Node merge(Node head1, Node head2) {
        Node mergedLinkedList = new Node(-1);
        Node temp = mergedLinkedList;
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
                temp = temp.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
                temp = temp.next;
            }
        }
        while (head1 != null) {
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }
        while (head2 != null) {
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }
        return mergedLinkedList.next;
    }

    // Zig-Zag linked list
    public void zigZag() {
        // find mid
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;
        // reverse 2nd half
        Node curr = mid.next;
        mid.next = null;
        Node prev = null;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node rightHead = prev;
        Node leftHead = head;
        Node nextL, nextR;
        // alternate merging
        while (leftHead != null && rightHead != null) {
            nextL = leftHead.next;
            leftHead.next = rightHead;
            nextR = rightHead.next;
            rightHead.next = nextL;

            rightHead = nextR;
            leftHead = nextL;
        }
    }

    // PRACTICE QUESTIONS
    // Q.1
    public Node getInsertionNode(Node head1, Node head2) {
        Node temp1 = head1;
        Node temp2 = head2;

        while (temp1 != temp2) {
            temp1 = temp1.next != null ? temp1.next : head2;
            temp2 = temp2.next != null ? temp2.next : head1;
        }
        return temp1;
    }

    // Delete N nodes after M nodes
    // Q.2
    public void deleteMAfterN(Node head, int M, int N) {
        Node curr = head, t;
        int count;
        while (curr != null) {
            for (count = 1; count < M && curr != null; count++)
                curr = curr.next;
            if (curr == null)
                return;
            t = curr.next;
            for (count = 1; count <= N && t != null; count++) {
                // Node temp = t;
                t = t.next;
            }
            curr.next = t;
            curr = t;
        }
    }

    // Swapping nodes in LL
    // Q.3
    public void swapNodes(int x, int y) {
        if (x == y)
            return;
        Node prevX = null, currX = head;
        while (currX != null && currX.data != x) {
            prevX = currX;
            currX = currX.next;
        }
        Node prevY = null, currY = head;
        while (currY != null && currY.data != y) {
            prevY = currY;
            currY = currY.next;
        }
        if (currX == null || currY == null)
            return;
        if (prevX != null)
            prevX.next = currY;
        else
            head = currY;
        if (prevY != null)
            prevY.next = currX;
        else
            head = currX;
        Node temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
    }

    public void printLinkedList() { // print linked list
        if (head == null) {
            System.out.println("Linkedlist is Empty.");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        // ll.addFirst(3); //linked list part 1
        // ll.addFirst(2);
        // ll.addFirst(1);
        // ll.addLast(4);
        // ll.addLast(5);
        // ll.addLast(6);
        // ll.addInMiddle(4, 5);
        // ll.printLinkedList();
        // ll.removeFirst();
        // ll.printLinkedList();
        // ll.removeLast();
        // System.out.println(ll.iterativeSearch(5)); // 4
        // System.out.println(ll.iterativeSearch(10)); // -1
        // System.out.println(ll.recursiveSearch(5)); // 4
        // System.out.println(ll.recursiveSearch(10)); // -1
        // ll.printLinkedList();
        // ll.reverseLinkedList();
        // ll.printLinkedList();
        // ll.deleteNthFromEnd(3);
        // ll.printLinkedList();
        // System.out.println(ll.size); // size of linked list
        // ll.addFirst(1);1

        // head = new Node(1); //linked list part 2
        // head.next = new Node(2);
        // head.next.next = new Node(3);
        // head.next.next.next = head;
        // System.out.println(isCycle());

        // head = new Node(1);
        // Node temp = new Node(2);
        // head.next = temp;
        // head.next.next = new Node(3);
        // head.next.next.next = temp;
        // //1->2->3->2
        // System.out.println(isCycle()); //true
        // ll.removeCycle(); //remove cycle
        // System.out.println(isCycle()); //false

        // ll.addFirst(1);
        // ll.addFirst(2);
        // ll.addFirst(3);
        // ll.addFirst(4);
        // ll.addFirst(5);
        // ll.addFirst(6);
        // ll.printLinkedList();
        // ll.head = ll.mergeSort(ll.head); //merge sort
        // ll.printLinkedList();

        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.addLast(5);
        ll.addLast(6);
        ll.addLast(7);
        ll.printLinkedList();
        ll.zigZag();
        ll.printLinkedList();
    }
}
