import com.zada.hackathon.client.ClientException;
import com.zada.hackathon.client.DataClients;
import com.zada.hackathon.gen.Keyword;
import com.zada.hackathon.gen.KeywordRequest;
import com.zada.hackathon.gen.KeywordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by omar on 15/10/29.
 */
public class MultipleThreadTest {
    static Logger logger = LoggerFactory.getLogger(MultipleThreadTest.class);

    public static void main(String[] args) throws IOException {
        MultipleThreadTest mtt = new MultipleThreadTest();
        mtt.runTest();
    }

    AtomicInteger succ = new AtomicInteger();
    AtomicInteger err = new AtomicInteger();

    static DataClients dataClients = new DataClients("127.0.0.1:9090,127.0.0.1:9090");

    public void runTest() throws IOException {

        String word = "hello";
        int count = 5;

        for (int i = 0; i < 100; i++) {
            Worker worker = new Worker(word, count, dataClients);
            new Thread(worker).start();
        }

        int cpu = 32;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(cpu, cpu, 1, TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(cpu * 2), new ThreadPoolExecutor.CallerRunsPolicy());
//
        try (BufferedReader br = new BufferedReader(new FileReader(new File("sentences")));) {
            String line = br.readLine();
            while (line != null) {
                if (line.length() > 3) {
                    word = line.substring(0, 3);
                } else {
                    word = line;
                }
                Worker worker = new Worker(word, count, dataClients);
                executor.submit(worker);
                line = br.readLine();
            }
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
                logger.info("req : {}, return {} results", word, words.size());
                succ.incrementAndGet();
            } catch (ClientException e) {
                err.incrementAndGet();
            }
        }
    }

}
