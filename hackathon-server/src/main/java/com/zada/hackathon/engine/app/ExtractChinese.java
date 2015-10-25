package com.zada.hackathon.engine.app;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by OmarTech on 15-10-24.
 */
public class ExtractChinese {


    public void extract() throws IOException {
        File file = new File("news.txt");

        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 8, 1, TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(16), new ThreadPoolExecutor.CallerRunsPolicy());
        File pname = new File("chinese.txt");
        if (pname.exists()) {
            pname.delete();
        }
        try (FileWriter fileWriter = new FileWriter(pname);
             BufferedReader br = new BufferedReader(new FileReader(file));) {
            String line = br.readLine();
            int i = 0;
            while (line != null) {
//                System.out.println(line);
                executor.submit(new LineWorker(line, fileWriter));
                i++;
                if (i % 10000 == 0) {
                    System.out.println(i + " lines over");
                }
                if (i > 300000) {
                    break;
                }
                line = br.readLine();
            }
            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class LineWorker implements Runnable {
        public LineWorker(String line, FileWriter writer) {
            this.line = line;
            this.writer = writer;
        }

        FileWriter writer;
        String line;

        @Override
        public void run() {
            if (!line.contains("\t")) {
                return;
            }
            try {
                String[] split = line.split("\t");
                if (split.length == 2) {

                    String title = split[0];
                    title = title.trim();
                    writer.write(title+"\n");

                    String content = split[1];
                    if (!StringUtils.isEmpty(content)) {
                        List<Term> terms = ToAnalysis.parse(content);
                        Set<String> set = new HashSet<>();
                        for (Term term : terms) {
                            String name = term.getName();
                            if (name.length() > 2 && isNotWholeNumber(name)) {
                                set.add(name);
                            }
                        }
                        for (String tmp : set) {
                            writer.write(tmp+"\n");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static Pattern pattern = Pattern.compile("\\d+");

    static boolean isNotWholeNumber(String string) {
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return false;
        } else {
            return true;
        }
    }


    public static void main(String[] args) {
        ExtractChinese en = new ExtractChinese();
        try {
            en.extract();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String str = "hahah!adfasdf,asd.asdeeee?asdf";
//        String[] split = str.split("[!?.]");
//        for(String tmp : split){
//            System.out.println(tmp);
//        }
    }
}
