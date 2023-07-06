/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class BlogComment {

    private int comment_id;
    private String content;
    private int author_id;
    private Timestamp date_posted;
    private int blog_id;
    private String authorName;

    public BlogComment() {
    }

    public BlogComment(int comment_id, String content, int author_id, Timestamp date_posted, int blog_id, String authorName) {
        this.comment_id = comment_id;
        this.content = content;
        this.author_id = author_id;
        this.date_posted = date_posted;
        this.blog_id = blog_id;
        this.authorName = authorName;
    }
    public BlogComment( String content, int author_id, Timestamp date_posted, int blog_id) {
    
        this.content = content;
        this.author_id = author_id;
        this.date_posted = date_posted;
        this.blog_id = blog_id;

    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
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

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "BlogComment{" + "comment_id=" + comment_id + ", content=" + content + ", author_id=" + author_id + ", date_posted=" + date_posted + ", blog_id=" + blog_id + ", authorName=" + authorName + '}';
    }
    
}
