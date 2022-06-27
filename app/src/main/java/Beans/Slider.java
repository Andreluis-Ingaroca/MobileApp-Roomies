package Beans;

public class Slider {

    private  String imagrUrl;
    private  String name;
    private  String description;

    public Slider(String imagrUrl, String name, String description) {
        this.imagrUrl = imagrUrl;
        this.name = name;
        this.description = description;
    }

    public String getImagrUrl() {
        return imagrUrl;
    }

    public void setImagrUrl(String imagrUrl) {
        this.imagrUrl = imagrUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
