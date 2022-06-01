package Beans;

import java.io.Serializable;

public class Review implements Serializable {

    public int id;
    public String content;
    public Leaseholder leaseholder;
    public Post post;

    public Review(int id, String content, Leaseholder leaseholder, Post post) {
        this.id = id;
        this.content = content;
        this.leaseholder = leaseholder;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Leaseholder getLeaseholder() {
        return leaseholder;
    }

    public void setLeaseholder(Leaseholder author) {
        this.leaseholder = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
