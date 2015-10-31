package com.zada.hackathon.engine.app;

import com.zada.hackathon.gen.Keyword;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 简单版本的trie树
 *
 * @author Chen Jie
 */
public class SimpleTrie {

    static Logger logger = LoggerFactory.getLogger(SimpleTrie.class);
    Node root;

    public SimpleTrie() {
        root = new Node();
    }

    public void add(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        Node node = root;
        for (int i = 0; i < str.length(); i++) {
//            String curr = str.charAt(i) + "";
            char curr = str.charAt(i);
            boolean containsKey = node.son.containsKey(curr);
            if (containsKey) {

            } else {
                Node newNode = new Node();
                newNode.value = curr;
                node.son.put(curr, newNode);
            }
            node = node.son.get(curr);
        }
        node.isEnd = true;
    }

    public boolean contains(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
//            String curr = word.charAt(i) + "";
            char curr = word.charAt(i);
            boolean containsKey = node.son.containsKey(curr);
            if (containsKey) {
                node = node.son.get(curr);
            } else {
                return false;
            }
        }
        boolean isEnd = node.isEnd;
        return isEnd;
    }

    public List<Keyword> searchFromTrie(String prefix, int length) {
        List<Keyword> list = new ArrayList<>();
        if (StringUtils.isEmpty(prefix)) {
            return list;
        }
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
//            String curr = prefix.charAt(i) + "";
            char curr = prefix.charAt(i);
            boolean containsKey = node.son.containsKey(curr);
            if (containsKey) {
                node = node.son.get(curr);
            } else {
                return list;
            }
        }
        Node newRoot = node;

//        logger.debug("prefix:{}, newroot :{}", prefix, newRoot.value);

        List<String> strings = new ArrayList<>();
        bps(newRoot, strings, prefix, length);

//        Collections.sort(strings, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.length() - o2.length();
//            }
//        });

        Collections.sort(strings);

        for (String str : strings) {
            Keyword keyword = new Keyword(str, 0);
            list.add(keyword);
        }
        return list;
    }

    private void bps(Node root, List<String> words, String prefix, int length) {
        Map<Character, Node> son = root.son;

        for (Node current : son.values()) {
            boolean isEnd = current.isEnd;
            char value = current.value;
            String tmpStr = prefix + value;
            if (words.size() >= length) {
                return;
            }
            if (isEnd) {
                words.add(tmpStr);
            }
        }
        for (Node curr : son.values()) {
            bps(curr, words, prefix + curr.value, length);
        }
    }


    class Node {

        Map<Character, Node> son;

        boolean isEnd;

        char value;

        public Node() {
            super();
            son = new HashMap<>();
        }
    }
}
