import Model.Job;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by ruixie on 11/02/2017.
 */
public class CrawlStat {
    private int totalProcessedPages;
    private long totalLinks;
    private long totalTextSize;
    private Queue<Job> contentQueue;

    public CrawlStat() {
        contentQueue = new LinkedList<Job>();
    }

    public int getTotalProcessedPages() {
        return totalProcessedPages;
    }

    public void setTotalProcessedPages(int totalProcessedPages) {
        this.totalProcessedPages = totalProcessedPages;
    }

    public void incProcessedPages() {
        this.totalProcessedPages++;
    }

    public long getTotalLinks() {
        return totalLinks;
    }

    public void setTotalLinks(long totalLinks) {
        this.totalLinks = totalLinks;
    }

    public long getTotalTextSize() {
        return totalTextSize;
    }

    public void setTotalTextSize(long totalTextSize) {
        this.totalTextSize = totalTextSize;
    }

    public void incTotalLinks(int count) {
        this.totalLinks += count;
    }

    public void incTotalTextSize(int count) {
        this.totalTextSize += count;
    }

    public Queue<Job> getDataQueue() {
        return this.contentQueue;
    }
}
