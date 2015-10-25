import com.zada.hackathon.client.ClientException;
import com.zada.hackathon.client.DataClients;
import com.zada.hackathon.gen.Keyword;
import com.zada.hackathon.gen.KeywordRequest;
import com.zada.hackathon.gen.KeywordResponse;

import java.util.List;

/**
 * Created by OmarTech on 15-10-24.
 */
public class TestKeyWordSearch {
    public static void main(String[] args) {

        DataClients dataClients = new DataClients("127.0.0.1:9090,127.0.0.1:9090");
        KeywordRequest req = new KeywordRequest();
        req.setWord("appr");
        req.setNumber(20);
        try {
            KeywordResponse response = dataClients.searchKeyword(req);
            List<Keyword> words = response.getWords();
            for (Keyword keyword : words) {
                System.out.println(keyword);
            }
            System.out.println("words size:" +words.size());
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }
}
