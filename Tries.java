import java.util.*;

class TrieNode { // Q.1 For Group Anagrams Together
    List<String> data;
    TrieNode children[];
    boolean isEnd;

    TrieNode() {
        data = new ArrayList<>();
        children = new TrieNode[26];
        isEnd = false;
    }
}

public class Tries {
    private static class Node2 { // Q.2 Longest Word in Dictionary
        private char data2;
        private String word;
        private boolean isEnd2;
        private Node2[] children;

        public Node2(char data) {
            this.data2 = data;
            this.word = null;
            this.isEnd2 = false;
            this.children = new Node2[26];
        }
    }

    private Node2 root2 = new Node2('/');
    private String ans2 = "";

    private void insert2(String word) {
        Node2 curr = this.root2;
        for (int i = 0; i < word.length(); i++) {
            int childIdx = word.charAt(i) - 'a';
            if (curr.children[childIdx] == null) {
                curr.children[childIdx] = new Node2(word.charAt(i));
            }
            curr = curr.children[childIdx];
        }
        curr.isEnd2 = true;
        curr.word = word;
    }

    private void dfs(Node2 node) {
        if (node == null) {
            return;
        }
        if (node.word != null) {
            if (node.word.length() > ans2.length()) {
                ans2 = node.word;
            } else if (node.word.length() == ans2.length() &&
                    node.word.compareTo(ans) < 0) {
                ans2 = node.word;
            }
        }
        for (Node2 child : node.children) {
            if (child != null && child.word != null) {
                dfs(child);
            }
        }
    }

    public String longestWord(String[] words) {
        for (String word : words) {
            insert2(word);
        }
        Node2 curr = this.root2;
        dfs(curr);
        return ans2;
    }

    static class Node {
        Node children[] = new Node[26];
        boolean endOfWord = false;
        int freq; // for prefix problem

        public Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            freq = 1; // for prefix problem
        }
    }

    public static Node root = new Node();

    public static void insert(String word) { // O(L) L->Level
        Node curr = root;
        for (int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            } else { // for prefix problem
                curr.children[idx].freq++;
            }
            curr = curr.children[idx];
        }
        curr.endOfWord = true;
    }

    public static boolean search(String key) { // O(L)
        Node curr = root;
        for (int level = 0; level < key.length(); level++) {
            int idx = key.charAt(level) - 'a';
            if (curr.children[idx] == null)
                return false;
            curr = curr.children[idx];
        }
        return curr.endOfWord == true;
    }

    // Word break problem
    public static boolean wordBreak(String key) {
        if (key.length() == 0)
            return true;
        for (int i = 1; i <= key.length(); i++) {
            if (search(key.substring(0, i)) && wordBreak(key.substring(i))) {
                return true;
            }
        }
        return false;
    }

    // Prefix Problem
    public static void findPrefix(Node root, String ans) { // O(L)
        if (root == null)
            return;
        if (root.freq == 1) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                findPrefix(root.children[i], ans + (char) (i + 'a'));
            }
        }
    }

    // StartsWith Problem
    public static boolean startsWith(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }
        return true;
    }

    // Count Unique Substrings
    public static int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                count += countNodes(root.children[i]);
            }
        }
        return count + 1;
    }

    // Longest Word With all Prefixes
    public static String ans = "";

    public static void longestWord(Node root, StringBuilder temp) {
        if (root == null) {
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].endOfWord == true) {
                char ch = (char) (i + 'a');
                temp.append(ch);
                if (temp.length() > ans.length()) {
                    ans = temp.toString();
                }
                longestWord(root.children[i], temp);
                temp.deleteCharAt(temp.length() - 1); // backtrack
            }
        }
    }

    // Q.1 Group Anagrams Together
    static TrieNode root1;
    List<List<String>> ans1;

    public List<List<String>> groupAnagrams(String[] strs) {
        ans1 = new ArrayList<>();
        root1 = new TrieNode();
        for (String word : strs) {
            build(word);
        }
        dfs(root1);
        return ans1;
    }

    public void build(String s) {
        TrieNode temp = root1;
        char[] word = s.toCharArray();
        Arrays.sort(word);
        for (char c : word) {
            TrieNode child = temp.children[c - 'a'];
            if (child == null)
                temp.children[c - 'a'] = new TrieNode();
            temp = temp.children[c - 'a'];
        }
        temp.isEnd = true;
        temp.data.add(s);
    }

    public void dfs(TrieNode rt) {
        if (rt.isEnd) {
            ans1.add(rt.data);
        }
        for (int i = 0; i < 26; i++) {
            if (rt.children[i] != null)
                dfs(rt.children[i]);
        }
    }

    // Q.2 Longest Word in Dictionary

    public static void main(String[] args) {
        // String words[] = { "the", "a", "there", "their", "any", "three" };
        // for (int i = 0; i < words.length; i++) {
        // insert(words[i]);
        // }
        // // System.out.println(search("there")); //true
        // // System.out.println(search("thor")); //false

        // String arr[] = { "i", "like", "sam", "samsung", "mobile", "ice" };
        // for (int i = 0; i < arr.length; i++) {
        // insert(arr[i]);
        // }
        // String key = "ilikesamsung";
        // System.out.println(wordBreak(key)); // true

        // prefix problem
        // String[] arr = { "zebra", "dog", "duck", "dove" };
        // for (int i = 0; i < arr.length; i++) {
        // insert(arr[i]);
        // }
        // root.freq = -1;
        // findPrefix(root, "");

        // StartsWith Problem
        // String words[] = { "apple", "app", "mango", "man", "woman" };
        // String prefix1 = "app";
        // String prefix2 = "moon";
        // for (int i = 0; i < words.length; i++) {
        // insert(words[i]);
        // }
        // System.out.println(startsWith(prefix1)); // true
        // System.out.println(startsWith(prefix2)); // false

        // Count Unique Substrings
        // String str = "ababa"; //ans 10
        // String str = "apple"; //ans 15
        // suffix -> insert in trie
        // for (int i = 0; i < str.length(); i++) {
        // String suffix = str.substring(i);
        // insert(suffix);
        // }
        // System.out.println(countNodes(root));

        // Longest Word With all Prefixes
        // String words[] = { "a", "banana", "app", "appl", "ap", "apply", "apple" };
        // for (int i = 0; i < words.length; i++) {
        // insert(words[i]);
        // }
        // longestWord(root, new StringBuilder(""));
        // System.out.println(ans);
    }
}