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
public class Feedback {
    private int  feedback_id;
    private int  userid;
    private int  staff_id;
    private String title;
    private String content;
    private Timestamp date_posted;
    private String staff_name;

    public Feedback() {
    }

    public Feedback(int feedback_id, int userid, int staff_id, String title, String content, Timestamp date_posted, String staff_name) {
        this.feedback_id = feedback_id;
        this.userid = userid;
        this.staff_id = staff_id;
        this.title = title;
        this.content = content;
        this.date_posted = date_posted;
        this.staff_name = staff_name;
    }
    public Feedback(int feedback_id, int userid, int staff_id, String title, String content, Timestamp date_posted) {
        this.feedback_id = feedback_id;
        this.userid = userid;
        this.staff_id = staff_id;
        this.title = title;
        this.content = content;
        this.date_posted = date_posted;
        this.staff_name = staff_name;
    }



    public Feedback(int userid, int staff_id, String title, String content, Timestamp date_posted) {
        this.userid = userid;
        this.staff_id = staff_id;
        this.title = title;
        this.content = content;
        this.date_posted = date_posted;
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
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

    public Timestamp getDate_posted() {
        return date_posted;
    }

    public void setDate_posted(Timestamp date_posted) {
        this.date_posted = date_posted;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    @Override
    public String toString() {
        return "Feddback{" + "feedback_id=" + feedback_id + ", userid=" + userid + ", staff_id=" + staff_id + ", title=" + title + ", content=" + content + ", date_posted=" + date_posted + ", staff_name=" + staff_name + '}';
    }



}
