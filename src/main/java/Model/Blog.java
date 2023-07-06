package Model;

import java.sql.Timestamp;

public class Blog {

    private int blog_id;
    private String title;
    private String content;
    private int author_id;
    private Timestamp date_posted;
    private String image;
    private String authorName;

    public Blog() {
        super();
    }

    public Blog(int blog_id, String title, String content, int author_id, Timestamp date_posted, String image, String authorName) {
        super();
        this.blog_id = blog_id;
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.date_posted = date_posted;
        this.image = image;
        this.authorName = authorName;
    }

    public Blog(String title, String content, int author_id, Timestamp date_posted, String image) {
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.date_posted = date_posted;
        this.image = image;

    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
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

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public Timestamp getDate_posted() {
        return date_posted;
    }

    public void setDate_posted(Timestamp date_posted) {
        this.date_posted = date_posted;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Blog [blog_id=" + blog_id + ", title=" + title + ", content=" + content + ", author_id=" + author_id
                + ", date_posted=" + date_posted + ", image=" + image + ", authorName=" + authorName + "]";
    }

}
