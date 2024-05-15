package ds.tire;

public interface Trie {

    /**
     * 将指定的单词插入到前缀树中。
     * 
     */
    void insert(String word);

    /**
     * 如果之前word插入过前缀树，那么此时删掉一次，
     * 如果之前word没有插入过前缀树，那么什么也不做
     * 
     */
    void erease(String word);

    /**
     * 查询前缀树里，word单词出现了几次
     */
    int countWordsEqualTo(String word);

    /**
     * 查询前缀树里，有多少单词以pre做前缀
     */
    int countWordsStartWith(String pre);
}
