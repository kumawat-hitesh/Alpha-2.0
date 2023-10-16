import java.util.ArrayList;
import java.util.HashMap;
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
    // static class Info {
    // int diameter;
    // int height;

    // Info(int diameter, int height) {
    // this.diameter = diameter;
    // this.height = height;
    // }
    // }

    // public static Info diameter2(Node root) {
    // if (root == null) {
    // return new Info(0, 0);
    // }
    // Info leftInfo = diameter2(root.left);
    // Info rightInfo = diameter2(root.right);

    // int diameter = Math.max(Math.max(leftInfo.diameter, rightInfo.diameter),
    // leftInfo.height + rightInfo.height + 1);
    // int height = Math.max(leftInfo.height, rightInfo.height) + 1;

    // return new Info(diameter, height);
    // }

    // Sub Tree of another tree
    public static boolean isSubtree(Node root, Node subRoot) {
        if (root == null) {
            return false;
        }
        if (root.data == subRoot.data) {
            if (isIdentical(root, subRoot)) {
                return true;
            }
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public static boolean isIdentical(Node root, Node subRoot) {
        if (root == null && subRoot == null) {
            return true;
        } else if (root == null || subRoot == null || root.data != subRoot.data) {
            return false;
        }
        if (!isIdentical(root.left, subRoot.left)) {
            return false;
        }
        if (!isIdentical(root.right, subRoot.right)) {
            return false;
        }
        return true;
    }

    // Top View of a tree
    static class Info {
        Node node;
        int horizontalDistance;

        public Info(Node node, int horizontalDistance) {
            this.node = node;
            this.horizontalDistance = horizontalDistance;
        }
    }

    public static void topView(Node root) {
        Queue<Info> q = new LinkedList<>();
        HashMap<Integer, Node> map = new HashMap<>();
        int min = 0, max = 0;
        q.add(new Info(root, 0));
        q.add(null);

        while (!q.isEmpty()) {
            Info curr = q.remove();
            if (curr == null) {
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                if (!map.containsKey(curr.horizontalDistance)) { // first time my HD is occouring
                    map.put(curr.horizontalDistance, curr.node);
                }

                if (curr.node.left != null) {
                    q.add(new Info(curr.node.left, curr.horizontalDistance - 1));
                    min = Math.min(min, curr.horizontalDistance - 1);
                }

                if (curr.node.right != null) {
                    q.add(new Info(curr.node.right, curr.horizontalDistance + 1));
                    max = Math.max(max, curr.horizontalDistance + 1);
                }
            }
        }

        for (int i = min; i <= max; i++) {
            System.out.print(map.get(i).data + " ");
        }
        System.out.println();
    }

    // Kth Level of BT
    public static void KLevel(Node root, int level, int k) {
        if (root == null) {
            return;
        }
        if (level == k) {
            System.out.print(root.data + " ");
            return;
        }
        KLevel(root.left, level + 1, k);
        KLevel(root.right, level + 1, k);
    }

    // Lowest Common Ancestor (Basic Approach) (1st Approach)
    public static boolean getPath(Node root, int n, ArrayList<Node> path) {
        if (root == null)
            return false;
        path.add(root);
        if (root.data == n) {
            return true;
        }
        boolean foundLeft = getPath(root.left, n, path);
        boolean fountRight = getPath(root.right, n, path);

        if (foundLeft || fountRight) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    public static Node lowestCommonAncestor1(Node root, int n1, int n2) {
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        getPath(root, n1, path1);
        getPath(root, n2, path2);
        int i = 0;
        for (; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) != path2.get(i)) {
                break;
            }
        }
        Node lca = path1.get(i - 1);
        return lca;
    }

    // Lowest Common Ancestor (*Leetcode) (2nd Approach)
    public static Node lowestCommonAncestor2(Node root, int p, int q) {
        if (root == null || root.data == p || root.data == q) {
            return root;
        }
        Node leftLca = lowestCommonAncestor2(root.left, p, q);
        Node rightLca = lowestCommonAncestor2(root.right, p, q);
        if (leftLca == null)
            return rightLca;
        if (rightLca == null)
            return leftLca;
        return root;
    }

    // Minimun distance between two nodes
    public static int minDist(Node root, int n1, int n2) {
        Node lca = lowestCommonAncestor2(root, n1, n2);
        int dist1 = lcaDist(lca, n1);
        int dist2 = lcaDist(lca, n2);
        return dist1 + dist2;
    }

    public static int lcaDist(Node root, int n) {
        if (root == null)
            return -1;
        if (root.data == n) {
            return 0;
        }
        int leftDist = lcaDist(root.left, n);
        int rightDist = lcaDist(root.right, n);

        if (leftDist == -1 && rightDist == -1) {
            return -1;
        } else if (leftDist == -1) {
            return rightDist + 1;
        } else {
            return leftDist + 1;
        }
    }

    // Kth Ancestor
    public static int kthAncestor(Node root, int k, int node) {
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

    // Transform to sum tree
    public static int transform(Node root) {
        if (root == null) {
            return 0;
        }
        int leftChild = transform(root.left);
        int rightChild = transform(root.right);
        int data = root.data;
        int newLeft = (root.left == null) ? 0 : root.left.data;
        int newRight = (root.right == null) ? 0 : root.right.data;
        root.data = newLeft + leftChild + newRight + rightChild;
        return data;
    }

    public static void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
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

        // 1
        // / \
        // 2 3
        // / \ /\
        // 4 5 6 7

        // System.out.print(height(root)); //Height of tree
        // System.out.print(count(root)); //count all nodes in BT
        // System.out.print(sumOfNodes(root)); //Sum of all nodes
        // System.out.print(diameter(root)); //Diameter of tree (basic approach)
        // System.out.print(diameter2(root).diameter); //Diameter of tree (optimized
        // approach)
        // topView(root); //Top view of a tree
        // KLevel(root, 1, 3); // Kth Level -> 4 5 6 7
        // System.out.print(lowestCommonAncestor1(root, 4, 5).data); //2: Lowest common
        // Ancestor (Basic approach)
        // System.out.print(lowestCommonAncestor2(root, 4, 5).data); //2: Lowest common
        // Ancestor
        // System.out.print(minDist(root, 4, 3)); // 3: Minimum dist b/w two nodes
        // transform(root); //Transform to sum tree
        // preorder(root); //27 9 0 0 13 0 0
    }
}
