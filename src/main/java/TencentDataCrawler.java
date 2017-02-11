import Model.Job;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by ruixie on 11/02/2017.
 */
public class TencentDataCrawler extends WebCrawler {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TencentDataCrawler.class);

    private static final Pattern FILTERS = Pattern.compile(
            ".*(\\.(css|js|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" +
                    "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    private CrawlStat myCrawlStat;
    private File outputFile;

    public TencentDataCrawler(File file) {
        myCrawlStat = new CrawlStat();
        outputFile = file;
    }

    @Override
    public boolean shouldVisit(Page page, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && href.startsWith("http://hr.tencent.com/position");
    }

    @Override
    public void visit(Page page) {
        logger.info("Visited: {}", page.getWebURL().getURL());
        myCrawlStat.incProcessedPages();
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData parseData = (HtmlParseData) page.getParseData();
            myCrawlStat.getDataQueue().offer(new Job(parseData.getTitle(), parseData.getText(), page.getWebURL().getURL()));
            Set<WebURL> links = parseData.getOutgoingUrls();
            myCrawlStat.incTotalLinks(links.size());
            try {
                myCrawlStat.incTotalTextSize(parseData.getText().getBytes("UTF-8").length);
            } catch (UnsupportedEncodingException ignored) {
                logger.info("Crawler {} ignores {}", getMyId(), ignored.getMessage());
            }
        }

        if ((myCrawlStat.getTotalProcessedPages() % 10) == 0) {
            logger.info("Crawler {} > write to file {}", getMyId(), outputFile.getAbsolutePath());
            dumpMyData();
        }

    }

    @Override
    public Object getMyLocalData() {
        return myCrawlStat;
    }

    @Override
    public void onBeforeExit() {
        logger.info("Crawler {} > write to file {}", getMyId(), outputFile.getAbsolutePath());
        dumpMyData();
    }

    public String getDescription(String html) {
        return null;
    }

    public void dumpMyData() {
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile, true)));
            while (!myCrawlStat.getDataQueue().isEmpty()) {
                Job job = myCrawlStat.getDataQueue().poll();
                output.write("=========================================\n");
                output.write(job.getURL() + "\n");
                output.write(job.getTitle() + "\n");
                output.write(job.getContent() + "\n");
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
