package core.tire;

import java.util.HashMap;

public class TrieImpl2 implements Trie {

    static class TrieNode {
        public int pass;
        public int end;
        public HashMap<Integer, TrieNode> nexts;

        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    private TrieNode root;

    public TrieImpl2() {
        root = new TrieNode();
    }

    @Override
    public void insert(String word) {
        TrieNode node = root;
        node.pass++;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a'; // 对应走向哪条路
            if (!node.nexts.containsKey(path)) {
                node.nexts.put(path, new TrieNode());
            }
            node = node.nexts.get(path);
            node.pass++;
        }
        node.end++;
    }

    @Override
    public void erease(String word) {
        if (countWordsEqualTo(word) <= 0) {
            return;
        }

        TrieNode node = root, next;
        node.pass--;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            next = node.nexts.get(path);
            if (--next.pass == 0) {
                node.nexts.remove(path);
                return;
            }
            node = next;
        }
        node.end--;
    }

    @Override
    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (!node.nexts.containsKey(path)) {
                return 0;
            }
            node = node.nexts.get(path);
        }
        return node.end;
    }

    @Override
    public int countWordsStartWith(String pre) {
        TrieNode node = root;
        for (int i = 0, path; i < pre.length(); i++) {
            path = pre.charAt(i) - 'a';
            if (!node.nexts.containsKey(path)) {
                return 0;
            }
            node = node.nexts.get(path);
        }
        return node.pass;
    }

}
