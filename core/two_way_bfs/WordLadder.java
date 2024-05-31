package core.two_way_bfs;

import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.cn/problems/word-ladder/
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList); // 总单词表
        if (!dict.contains(endWord)) {
            return 0;
        }

        HashSet<String> smaller = new HashSet<>(); // 数量小的一侧
        HashSet<String> bigger = new HashSet<>(); // 数量大的一侧
        HashSet<String> next = new HashSet<>(); // 由数量小的一侧扩展出的下一层列表

        smaller.add(beginWord);
        bigger.add(endWord);

        // len: beginWord -> ... -> endWord
        for (int len = 2; !smaller.isEmpty(); len++) {
            // 数量小的一侧开始扩展
            for (String w : smaller) {
                char[] word = w.toCharArray();
                // 尝试每一个字符
                for (int i = 0; i < word.length; i++) {
                    char old = word[i];
                    for (char change = 'a'; change <= 'z'; change++) {
                        if (change == old) {
                            continue;
                        }
                        word[i] = change;
                        String nextWord = String.valueOf(word);
                        if (bigger.contains(nextWord)) {
                            return len;
                        }
                        if (dict.contains(nextWord)) {
                            next.add(nextWord);
                            dict.remove(nextWord);
                        }
                    }
                    word[i] = old;
                }
            }
            // 下一轮迭代
            if (next.size() <= bigger.size()) {
                // next 是 smaller
                HashSet<String> tmp = smaller;
                smaller = next;
                next = tmp;
            } else {
                // bigger 是 smaller, next 是 bigger
                HashSet<String> tmp = smaller;
                smaller = bigger;
                bigger = next;
                next = tmp;
            }
            // 为下一轮做准备
            next.clear();
        }
        return 0;
    }
}
