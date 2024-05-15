package ds.tire;

public class TrieImpl1 implements Trie {

    static class TrieNode {
        public int pass;
        public int end;
        public TrieNode[] nexts;

        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    private TrieNode root;

    public TrieImpl1() {
        root = new TrieNode();
    }

    @Override
    public void insert(String word) {
        TrieNode node = root;
        node.pass++;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a'; // 对应走向哪条路
            if (node.nexts[path] == null) {
                node.nexts[path] = new TrieNode();
            }
            node = node.nexts[path];
            node.pass++;
        }
        node.end++;
    }

    @Override
    public void erease(String word) {
        if (countWordsEqualTo(word) <= 0) {
            return;
        }

        TrieNode node = root;
        node.pass--;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (--node.nexts[path].pass == 0) {
                node.nexts[path] = null;
                return;
            }
            node = node.nexts[path];
        }
        node.end--;
    }

    @Override
    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (node.nexts[path] == null) {
                return 0;
            }
            node = node.nexts[path];
        }
        return node.end;
    }

    @Override
    public int countWordsStartWith(String pre) {
        TrieNode node = root;
        for (int i = 0, path; i < pre.length(); i++) {
            path = pre.charAt(i) - 'a';
            if (node.nexts[path] == null) {
                return 0;
            }
            node = node.nexts[path];
        }
        return node.pass;
    }

}
