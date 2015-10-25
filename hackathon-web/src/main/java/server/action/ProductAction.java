package server.action;

import com.zada.hackathon.client.ClientException;
import com.zada.hackathon.client.DataClients;
import com.zada.hackathon.gen.Keyword;
import com.zada.hackathon.gen.KeywordRequest;
import com.zada.hackathon.gen.KeywordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by wangshuai on 2015/10/24 0024.
 */

@Controller
public class ProductAction {
    static Logger logger = LoggerFactory.getLogger(ProductAction.class);

    @RequestMapping("")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public String index2() {
        return "index";
    }

    static DataClients dataClients = new DataClients("127.0.0.1:9090,127.0.0.1:9090");

    @RequestMapping(value = "/getName")
    @ResponseBody
    public List<Map<String, String>> getName(String q, int limit) {

        logger.info("q:{}, limit:{}", q, limit);

        KeywordRequest keywordRequest = new KeywordRequest();
        keywordRequest.setWord(q);
        keywordRequest.setNumber(limit);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        try {
            KeywordResponse response = dataClients.searchKeyword(keywordRequest);
            List<Keyword> words = response.getWords();
            for (Keyword keyword : words) {
                String word = keyword.getWord();
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", word);
                map.put("to", keyword.getCount() + "");
                list.add(map);
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return list;
    }
}
