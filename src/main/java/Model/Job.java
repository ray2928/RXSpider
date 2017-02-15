package Model;

/**
 * Created by ruixie on 11/02/2017.
 */
public class Job {
    private String title;
    private String content;
    private String URL;


    public Job(String title, String content, String URL) {
        setTitle(title);
        setURL(URL);
        setContent(content);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
