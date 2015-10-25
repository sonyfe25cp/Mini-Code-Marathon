package com.zada.hackathon.engine.app;

import com.zada.hackathon.engine.service.ADataService;
import com.zada.hackathon.gen.Keyword;
import com.zada.hackathon.gen.KeywordRequest;
import com.zada.hackathon.gen.KeywordResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.swing.StringUIClientPropertyKey;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OmarTech on 15-10-24.
 */
public class KeywordSearchServer extends ADataService {
    static Logger logger = LoggerFactory.getLogger(KeywordSearchServer.class);
    SimpleTrie trie = new SimpleTrie();

    @Option(name = "-f", usage = "set the data file")
    protected String filePath = "sentences";

    @Override
    public void prepare() {//prepare the dataset

        long l1 = System.currentTimeMillis();
        File dataFile = new File(filePath);
        if ((!dataFile.exists()) || (!dataFile.isFile())) {
            logger.error("Please set the datafile with argument: -f ");
            System.exit(1);
        }
        try {
            List<String> lines = FileUtils.readLines(dataFile);
            logger.info("Begin to parse dataset.");
            if (lines != null && lines.size() > 0) {
                int i = 0;
                for (String line : lines) {
                    trie.add(line);
                    i++;
                    if (i % 10000 == 0) {
                        logger.info("pass " + i + " lines.");
                    }
                }
                logger.info("{} lines are passed in to engine.", i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long l2 = System.currentTimeMillis();
        logger.info("Server init over, cost: {}s", (l2 - l1) / 1000.0);
    }

    @Override
    public KeywordResponse searchKeyword(KeywordRequest req) throws TException {
        long l1 = System.currentTimeMillis();
        String word = req.getWord();
        int number = req.getNumber();

        KeywordResponse response = new KeywordResponse();
        List<Keyword> keywords = new ArrayList<>();
        if (word.length() < 3) {
            response.setWords(keywords);
            return response;
        }

        word = StringUtils.trim(word);
        word = word.replace(" ", " ");


        keywords = trie.searchFromTrie(word, number);
        response.setWords(keywords);

        long l2 = System.currentTimeMillis();
        logger.info("req:{}, cost:{}ms, size:{}, results:{}", new String[]{req.toString(), (l2 - l1) + "", response.getWords().size() + "", response.toString()});
        return response;
    }

    public static void main(String[] args) {

        KeywordSearchServer searchServer = new KeywordSearchServer();
        searchServer.port = 9090;
        searchServer.parseArgsAndRun(args);

    }
}
