package Beans;

public class Rule {
    private int id;
    private String title;
    private String description;
    private int postId;

    public Rule(int id, String title, String description, int postId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
