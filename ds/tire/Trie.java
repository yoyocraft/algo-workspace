package ds.tire;

public interface Trie {

    void insert(String word);

    void erease(String word);

    int countWordsEqualTo(String word);

    int countWordsStartingWith(String pre);
}
