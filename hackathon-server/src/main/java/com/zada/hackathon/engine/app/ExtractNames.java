package com.zada.hackathon.engine.app;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by OmarTech on 15-10-24.
 */
public class ExtractNames {


    public void extract() throws IOException {
        File file = new File("/Users/omar/data/opinion_spam/amazon_liubing/real/reviewsNew.txt");

        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 8, 1, TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(16), new ThreadPoolExecutor.CallerRunsPolicy());
        File pname = new File("pnames");
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
            String[] split = line.split("\t");
            if (split.length == 8) {
                String content = split[7].trim();
                String[] strings = content.split("[.!?]");
                try {
                    for (String str : strings) {
                        String[] split1 = str.split(" ");
                        if (split1.length >= 5) {
                            StringBuffer sb = new StringBuffer();
                            for (int i = 0; i < 4; i++) {
                                sb.append(split1[i] + " ");
                            }
                            str = sb.toString();
                        }
                        str = str.replaceAll("[\")(]", "");
                        str = str.trim();
                        str = str.toLowerCase();
                        if (!StringUtils.isEmpty(str) && str.length() > 2) {
                            writer.write(str + "\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ExtractNames en = new ExtractNames();
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
