package server.action;

import com.zada.hackathon.client.ClientException;
import com.zada.hackathon.client.DataClients;
import com.zada.hackathon.gen.Keyword;
import com.zada.hackathon.gen.KeywordRequest;
import com.zada.hackathon.gen.KeywordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import server.service.KeywordService;

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

    @Autowired
    private KeywordService keywordService;

    @RequestMapping("")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public String index2() {
        return "index";
    }



    @RequestMapping(value = "/getName")
    @ResponseBody
    public List<Map<String, String>> getName(String q, int limit) {
        logger.info("q:{}, limit:{}", q, limit);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        if (limit <= 0  || q == null || "".equals(q)) {
            return list;
        }


        List<Keyword> words = keywordService.getPromptList(q,limit);
        if (words == null || words.size() == 0){
            return list;
        }

        for (Keyword keyword : words) {
            String word = keyword.getWord();
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", word);
            map.put("to", keyword.getCount() + "");
            list.add(map);
        }
        return list;
    }
}
