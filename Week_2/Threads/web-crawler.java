package Week_2.Threads;

import java.util.concurrent.*;
import java.util.Random;

class CrawlerTask implements Runnable {
    private String url;
    private ConcurrentHashMap<String, Boolean> crawledUrls;

    public CrawlerTask(String url, ConcurrentHashMap<String, Boolean> crawledUrls) {
        this.url = url;
        this.crawledUrls = crawledUrls;
    }

    public void run() {
        System.out.println("Crawling: " + url);
        // Simulate crawling
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        crawledUrls.put(url, true);
        System.out.println("Finished crawling: " + url);
    }
}

public class WebCrawler {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        ConcurrentHashMap<String, Boolean> crawledUrls = new ConcurrentHashMap<>();

        String[] urls = {
            "http://example.com",
            "http://example.org",
            "http://example.net",
            "http://example.edu",
            "http://example.io"
        };

        for (String url : urls) {
            executor.submit(new CrawlerTask(url, crawledUrls));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Crawling completed. Crawled URLs:");
        for (String url : crawledUrls.keySet()) {
            System.out.println(url);
        }
    }
}
