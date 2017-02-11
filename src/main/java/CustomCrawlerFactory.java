import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

import java.io.File;

/**
 * Created by ruixie on 11/02/2017.
 */
public class CustomCrawlerFactory implements CrawlController.WebCrawlerFactory {
    File outputFile;

    public CustomCrawlerFactory(File file) {
        this.outputFile = file;
    }

    public WebCrawler newInstance() throws Exception {
        return new TencentDataCrawler(outputFile);
    }
}
