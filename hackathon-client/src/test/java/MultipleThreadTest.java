import com.zada.hackathon.client.ClientException;
import com.zada.hackathon.client.DataClients;
import com.zada.hackathon.gen.Keyword;
import com.zada.hackathon.gen.KeywordRequest;
import com.zada.hackathon.gen.KeywordResponse;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by omar on 15/10/29.
 */
public class MultipleThreadTest {
    public static void main(String[] args) {
        MultipleThreadTest mtt = new MultipleThreadTest();
        mtt.runTest();
    }

    AtomicInteger succ = new AtomicInteger();
    AtomicInteger err = new AtomicInteger();

    static DataClients dataClients = new DataClients("127.0.0.1:9090,127.0.0.1:9090");

    public void runTest() {

        String word = "hello";
        int count = 5;

        for (int i = 0; i < 100; i++) {
            Worker worker = new Worker(word, count, dataClients);
            new Thread(worker).start();
        }

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 8, 1, TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(16), new ThreadPoolExecutor.CallerRunsPolicy());

//        while (true) {
//            Worker worker = new Worker(word, count, dataClients);
//            executor.submit(worker);
//        }

    }

    class Worker implements Runnable {
        String word;
        int count;
        DataClients dataClients;

        public Worker(String word, int count, DataClients dataClients) {
            this.word = word;
            this.count = count;
            this.dataClients = dataClients;
        }

        @Override
        public void run() {
            KeywordRequest keywordRequest = new KeywordRequest();
            keywordRequest.setWord(word);
            keywordRequest.setNumber(count);


            try {
                KeywordResponse keywordResponse = dataClients.searchKeyword(keywordRequest);
                List<Keyword> words = keywordResponse.getWords();
                succ.incrementAndGet();
            } catch (ClientException e) {
                err.incrementAndGet();
            }
        }
    }

}
