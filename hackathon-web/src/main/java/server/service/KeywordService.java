package server.service;

import com.zada.hackathon.client.ClientException;
import com.zada.hackathon.client.DataClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuai on 15-10-29.
 */
@Service
public class KeywordService {
    private static Logger logger = LoggerFactory.getLogger(KeywordService.class);

    static DataClients dataClients = new DataClients("127.0.0.1:9090,127.0.0.1:9090");

    public List<Keyword> getPromptList(String q, int limit){

        List<Keyword> words;

        KeywordRequest keywordRequest = new KeywordRequest();
        keywordRequest.setWord(q);
        keywordRequest.setNumber(limit);


        try {
            KeywordResponse response = dataClients.searchKeyword(keywordRequest);
            words = response.getWords();
        } catch (ClientException e) {
            logger.warn(e.getMessage(),e);
            words = new ArrayList<Keyword>();
        }
        return words;
    }
}
