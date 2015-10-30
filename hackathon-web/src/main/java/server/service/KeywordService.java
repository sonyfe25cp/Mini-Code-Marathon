package server.service;

import com.zada.hackathon.client.ClientException;
import com.zada.hackathon.client.DataClients;
import com.zada.hackathon.gen.Keyword;
import com.zada.hackathon.gen.KeywordRequest;
import com.zada.hackathon.gen.KeywordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshuai on 15-10-29.
 */
@Service
public class KeywordService {
    private static Logger logger = LoggerFactory.getLogger(KeywordService.class);

    static DataClients dataClients = DataClientService.keyWordClient;

    public List<Keyword> getPromptList(String q, int limit) {

        List<Keyword> words;

        KeywordRequest keywordRequest = new KeywordRequest();
        keywordRequest.setWord(q);
        keywordRequest.setNumber(limit);


        try {
            KeywordResponse response = dataClients.searchKeyword(keywordRequest);
            words = response.getWords();
        } catch (ClientException e) {
            logger.warn(e.getMessage(), e);
            words = new ArrayList<Keyword>();
        }
        return words;
    }
}
