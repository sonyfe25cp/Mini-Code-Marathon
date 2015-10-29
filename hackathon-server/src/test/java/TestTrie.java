import com.zada.hackathon.engine.app.SimpleTrie;
import com.zada.hackathon.gen.Keyword;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by omar on 15/10/29.
 */
public class TestTrie extends TestCase {
    static Logger logger = LoggerFactory.getLogger(TestTrie.class);
    String data[] = new String[]{
            "hello",
            "hello world",
            "hello!",
            "hell",
            "ni hao",
            "我爱北京天安门",
            "我爱吃苹果",
            "天安门上太阳升",
            "天安门前升国旗"
    };

    public void testTrieAddAndContains() {
        SimpleTrie trie = new SimpleTrie();
        int addCount = 0;
        for (String tmp : data) {
            trie.add(tmp);
            addCount++;
        }
        logger.info("add {} into trie", addCount);

        int containsCount = 0;
        for (String tmp : data) {
            boolean contains = trie.contains(tmp);
            if (contains) {
                containsCount++;
            }
        }
        logger.info("{} data are found in trie", containsCount);
        assertEquals(containsCount, addCount);
    }

    public void testTrieSearch() {
        SimpleTrie trie = new SimpleTrie();
        for (String tmp : data) {
            trie.add(tmp);
        }

        List<Keyword> hel = trie.searchFromTrie("hel", 4);
        assertEquals(4, hel.size());

        List<String> helSearch = new ArrayList<>();
        for (Keyword keyword : hel) {
            helSearch.add(keyword.getWord());
        }

        List<String> helResults = Arrays.asList(new String[]{
                "hell",
                "hello",
                "hello world",
                "hello!",
        });

        assertEquals(helResults, helSearch);

    }
}
