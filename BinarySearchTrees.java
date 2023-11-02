import java.util.*;

public class BinarySearchTrees {
    static class Node {
        int data;
        Node left;
        Node right;

        int height; // this is for AVL tree

        Node(int data) {
            this.data = data;
            height = 1;
        }
    }

    // Insert in a binary search tree
    // public static Node insert(Node root, int val) { // TC : O(H) where H is
    // height of tree and in worst case it is O(n)
    // if (root == null) {
    // root = new Node(val);
    // return root;
    // }
    // if (val < root.data) {
    // // left subtree
    // root.left = insert(root.left, val);
    // } else {
    // // right subtree
    // root.right = insert(root.right, val);
    // }
    // return root;
    // }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Search in a BST
    public static boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }

        if (root.data == key) {
            return true;
        }

        if (key < root.data) {
            return search(root.left, key);
        }

        else {
            return search(root.right, key);
        }
    }

    // Delete Node
    public static Node delete(Node root, int val) {
        if (root.data < val) {
            root.right = delete(root.right, val);
        } else if (root.data > val) {
            root.left = delete(root.left, val);
        } else {
            // case 1 - leaf node
            if (root.right == null && root.left == null) {
                return null;
            }
            // case 2 - single child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // case 3 - both children
            Node IS = findInorderSuccessor(root.right);
            root.data = IS.data;
            root.right = delete(root.right, IS.data);
        }
        return root;
    }

    public static Node findInorderSuccessor(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Print in range
    public static void printInRange(Node root, int k1, int k2) {
        if (root == null)
            return;
        if (k1 <= root.data && root.data <= k2) {
            printInRange(root.left, k1, k2);
            System.out.print(root.data + " ");
            printInRange(root.right, k1, k2);
        } else if (root.data < k1) {
            printInRange(root.left, k1, k2);
        } else {
            printInRange(root.right, k1, k2);
        }
    }

    // print root to leaf
    public static void printPath(ArrayList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + "->");
        }
        System.out.println("NULL");
    }

    public static void printRoot2Leaf(Node root, ArrayList<Integer> path) {
        if (root == null)
            return;
        path.add(root.data);
        if (root.left == null && root.right == null) {
            printPath(path);
        }
        printRoot2Leaf(root.left, path);
        printRoot2Leaf(root.right, path);
        path.remove(path.size() - 1);
    }

    // Valid BST
    public static boolean isValidBST(Node root, Node min, Node max) {
        if (root == null)
            return true;
        if (min != null && root.data <= min.data) {
            return false;
        } else if (max != null && root.data >= max.data) {
            return false;
        }
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

    // Mirror a BST
    public static Node createMirrorBST(Node root) { // O(n)
        if (root == null)
            return null;

        Node leftMirror = createMirrorBST(root.left);
        Node rightMiror = createMirrorBST(root.right);

        root.left = rightMiror;
        root.right = leftMirror;
        return root;
    }

    public static void preorder(Node root) {
        if (root == null)
            return;

        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // Sorted array to BST
    public static Node sortedArrayToBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node root = new Node(arr[mid]);
        root.left = sortedArrayToBST(arr, start, mid - 1);
        root.right = sortedArrayToBST(arr, mid + 1, end);
        return root;
    }

    // BST to balanced BST
    public static Node BSTToBalancedBST(Node root) {
        // inorder sequence
        ArrayList<Integer> inorder = new ArrayList<>();
        getInorder(root, inorder);
        // sorted inorder -> balanced BST
        root = createBST(inorder, 0, inorder.size() - 1);
        return root;
    }

    public static void getInorder(Node root, ArrayList<Integer> inorder) {
        if (root == null)
            return;
        getInorder(root.left, inorder);
        inorder.add(root.data);
        getInorder(root.right, inorder);
    }

    public static Node createBST(ArrayList<Integer> inorder, int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        Node root = new Node(inorder.get(mid));
        root.left = createBST(inorder, start, mid - 1);
        root.right = createBST(inorder, mid + 1, end);
        return root;
    }

    // size of largest BST in BT
    static class Info {
        boolean isBST;
        int size;
        int min;
        int max;

        public Info(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    public static int maxBST = 0;

    public static Info largestBST(Node root) {
        if (root == null) {
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Info leftInfo = largestBST(root.left);
        Info rightInfo = largestBST(root.right);
        int size = leftInfo.size + rightInfo.size + 1;
        int min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min));
        int max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max));
        if (root.data <= leftInfo.max || root.data >= rightInfo.min) {
            return new Info(false, size, min, max);
        }
        if (leftInfo.isBST && rightInfo.isBST) {
            maxBST = Math.max(maxBST, size);
            return new Info(true, size, min, max);
        }
        return new Info(false, size, min, max);
    }

    // Merge 2 BST to form a single balanced BST
    public static Node mergeBSTs(Node root1, Node root2) {
        // step:1
        ArrayList<Integer> arr1 = new ArrayList<>();
        getInorder(root1, arr1);

        // step:2
        ArrayList<Integer> arr2 = new ArrayList<>();
        getInorder(root2, arr2);

        // merge
        ArrayList<Integer> finalArr = new ArrayList<>();
        int i = 0, j = 0;
        while (i < arr1.size() && j < arr2.size()) {
            if (arr1.get(i) <= arr2.get(j)) {
                finalArr.add(arr1.get(i++));
            } else {
                finalArr.add(arr2.get(j++));
            }
        }
        while (i < arr1.size()) {
            finalArr.add(arr1.get(i++));
        }
        while (j < arr2.size()) {
            finalArr.add(arr2.get(j++));
        }

        // sorted AL -> balanced BST
        return createBST(finalArr, 0, finalArr.size() - 1);
    }

    // AVL tree
    public static Node root;

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static int getBalanceFactor(Node root) {
        if (root == null)
            return 0;
        return height(root.left) - height(root.right);
    }

    // left rotate subtree with node x
    public static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // perform rotation
        y.left = x;
        x.right = T2;

        // update heights
        x.height = max(height(x.left), height(x.right) + 1);
        y.height = max(height(y.left), height(y.right) + 1);

        // return new root
        return y;
    }

    // right rotate subtree with node x
    public static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // perform rotation
        x.right = y;
        y.left = T2;

        // update heights
        y.height = max(height(y.left), height(y.right) + 1);
        x.height = max(height(x.left), height(x.right) + 1);

        // return new root
        return x;
    }

    public static Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.data) {
            root.left = insert(root.left, key);
        } else if (key > root.data) {
            root.right = insert(root.right, key);
        } else {
            return root; // duplicate keys not allowed
        }
        // update root right
        root.height = 1 + Math.max(height(root.left), height(root.right));
        // get root balance factor
        int balanceFactor = getBalanceFactor(root);
        // left-left case
        if (balanceFactor > 1 && key < root.left.data) {
            return rightRotate(root);
        }
        // right-right case
        if (balanceFactor < -1 && key > root.right.data) {
            return leftRotate(root);
        }
        // left-right case
        if (balanceFactor > 1 && key > root.left.data) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        // right-left case
        if (balanceFactor < -1 && key < root.right.data) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root; // returned if AVL is balanced
    }

    public static void main(String[] args) {
        // int[] values = { 5, 1, 3, 4, 2, 7 };
        // Node root = null;

        // for (int i = 0; i < values.length; i++) {
        // root = insert(root, values[i]);
        // }
        // // inorder(root);

        // if (search(root, 10)) {
        // System.out.println("Found");
        // } else {
        // System.out.println("Not Found");
        // }

        // delete node
        // int[] values = { 8, 5, 3, 1, 4, 6, 10, 11, 14 };
        // int[] values = { 2, 2, 2 };
        // Node root = null;
        // for (int i = 0; i < values.length; i++) {
        // root = insert(root, values[i]);
        // }
        // inorder(root);
        // root = delete(root, 5);
        // System.out.println();
        // inorder(root);

        // print in range
        // printInRange(root, 5, 12);

        // print root to leaf
        // printRoot2Leaf(root, new ArrayList<>());

        // valid BST
        // if (isValidBST(root, null, null)) {
        // System.out.println("Valid");
        // } else {
        // System.out.println("Not valid");
        // }

        // mirror BST
        // Node root = new Node(8);
        // root.left = new Node(5);
        // root.right = new Node(10);
        // root.left.left = new Node(3);
        // root.left.right = new Node(6);
        // root.right.right = new Node(11);

        // root = createMirrorBST(root);
        // preorder(root);

        // sorted array ot BST
        // int arr[] = { 3, 5, 6, 8, 10, 11, 12 };
        // Node root = sortedArrayToBST(arr, 0, arr.length-1);
        // preorder(root);

        // BST to balanced BST
        // Node root = new Node(8);
        // root.left = new Node(6);
        // root.left.left = new Node(5);
        // root.left.left.left = new Node(3);
        // root.right = new Node(10);
        // root.right.right = new Node(11);
        // root.right.right.right = new Node(12);

        // root = BSTToBalancedBST(root);
        // preorder(root);

        // size of largest BST in BT
        // Node root = new Node(50);
        // root.left = new Node(30);
        // root.left.left = new Node(5);
        // root.left.right = new Node(20);

        // root.right = new Node(60);
        // root.right.left = new Node(45);
        // root.right.right = new Node(70);
        // root.right.right.left = new Node(65);
        // root.right.right.right = new Node(80);

        // Info info = largestBST(root);
        // System.out.println("Largest BST size : " + maxBST);

        // Merge 2 BST to form a single balanced BST
        // Node root1 = new Node(2);
        // root1.left = new Node(1);
        // root1.right = new Node(4);

        // Node root2 = new Node(9);
        // root2.left = new Node(3);
        // root2.right = new Node(12);

        // Node root = mergeBSTs(root1, root2);
        // preorder(root);

        // AVL tree
        // root = insert(root, 10);
        // root = insert(root, 20);
        // root = insert(root, 30);
        // root = insert(root, 40);
        // root = insert(root, 50);
        // root = insert(root, 25);
        // preorder(root);
    }
}
