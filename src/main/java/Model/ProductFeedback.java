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
public class ProductFeedback {

    private int feelback_id;
    
    private String title;
    private String content;
    private int author_id;
    private Timestamp date_posted;
    private int product_id;
    private int star;
    private String authorName;

    public ProductFeedback() {
    }

    public ProductFeedback(int feelback_id, String title, String content ,int author_id, Timestamp date_posted, int product_id, int star, String authorName) {
        this.feelback_id = feelback_id;
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.date_posted = date_posted;
        this.product_id = product_id;
        this.star = star;
        this.authorName = authorName;
    }

    public ProductFeedback(String title,String content, int author_id, Timestamp date_posted, int product_id, int star) {
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.date_posted = date_posted;
        this.product_id = product_id;
        this.star = star;

    }

    public int getFeelback_id() {
        return feelback_id;
    }

    public void setFeelback_id(int feelback_id) {
        this.feelback_id = feelback_id;
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ProductFeedback{" + "feelback_id=" + feelback_id + ", title=" + title + ", content=" + content + ", author_id=" + author_id + ", date_posted=" + date_posted + ", product_id=" + product_id + ", star=" + star + ", authorName=" + authorName + '}';
    }

  
}
