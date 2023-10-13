import java.util.LinkedList;
import java.util.Queue;

public class BinaryTrees {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        static int idx = -1;

        public static Node buildTree(int nodes[]) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }

        public static void preorder(Node root) {
            if (root == null)
                return;
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }

        public static void inorder(Node root) {
            if (root == null)
                return;
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }

        public static void postorder(Node root) {
            if (root == null)
                return;
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }

        // level order tree traversal
        public static void levelorder(Node root) {
            if (root == null)
                return;
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);

            while (!q.isEmpty()) {
                Node currNode = q.remove();
                if (currNode == null) {
                    System.out.println();
                    if (q.isEmpty())
                        break;
                    else
                        q.add(null);
                } else {
                    System.out.print(currNode.data + " ");
                    if (currNode.left != null) {
                        q.add(currNode.left);
                    }
                    if (currNode.right != null) {
                        q.add(currNode.right);
                    }
                }
            }
        }
    }

    // height of binary tree
    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        return Math.max(lh, rh) + 1;
    }

    // count all nodes in tree
    public static int count(Node root) {
        if (root == null)
            return 0;
        int leftCount = count(root.left);
        int rightCount = count(root.right);
        return leftCount + rightCount + 1;
    }

    // Sum of all Nodes
    public static int sumOfNodes(Node root) {
        if (root == null)
            return 0;
        int leftSum = sumOfNodes(root.left);
        int rightSum = sumOfNodes(root.right);
        return leftSum + rightSum + root.data;
    }

    // Diameter of binary tree (Basic Approach) O(n^2)
    public static int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        int leftDiameter = diameter(root.left);
        int leftHeight = height(root.left);
        int rightDiameter = diameter(root.right);
        int rightHeight = height(root.right);
        int selfDiameter = leftHeight + rightHeight + 1;
        return Math.max(selfDiameter, Math.max(leftDiameter, rightDiameter));
    }

    // Diameter of binary tree (optimized way) O(n)
    static class Info {
        int diameter;
        int height;

        Info(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
    }

    public static Info diameter2(Node root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftInfo = diameter2(root.left);
        Info rightInfo = diameter2(root.right);

        int diameter = Math.max(Math.max(leftInfo.diameter, rightInfo.diameter),
                leftInfo.height + rightInfo.height + 1);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new Info(diameter, height);
    }

    // Lowest Common Ancestor (*Leetcode)
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == p || root == q || root == null) {
            return root;
        }
        Node leftLca = lowestCommonAncestor(root.left, p, q);
        Node rightLca = lowestCommonAncestor(root.right, p, q);
        if (leftLca == null)
            return rightLca;
        if (rightLca == null)
            return leftLca;
        return root;
    }

    // Kth Ancestor
    public int kthAncestor(Node root, int k, int node) {
        if (root == null)
            return -1;
        if (root.data == node)
            return 0;
        int leftDist = kthAncestor(root.left, k, node);
        int rightDist = kthAncestor(root.right, k, node);
        if (leftDist == -1 && rightDist == -1) {
            return -1;
        }
        int max = Math.max(leftDist, rightDist);
        if (max + 1 == k) {
            return root.data;
        }
        return max + 1;
    }

    public static void main(String[] args) {
        // int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        // BinaryTree tree = new BinaryTree();
        // Node root = tree.buildTree(nodes);
        // System.out.println(root.data);

        // tree.preorder(root);
        // System.out.println();
        // tree.inorder(root);
        // System.out.println();
        // tree.postorder(root);
        // System.out.println();

        // tree.levelorder(root);

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

//                     1
//                    /  \
//                   2    3
//                  / \   /\
//                 4   5 6  7

        // System.out.print(height(root)); //Height of tree
        // System.out.print(count(root)); //count all nodes in BT
        // System.out.print(sumOfNodes(root)); //Sum of all nodes
        // System.out.print(diameter(root)); //Diameter of tree (basic approach)
        // System.out.print(diameter2(root)); //Diameter of tree (optimized approach)

    }
}
