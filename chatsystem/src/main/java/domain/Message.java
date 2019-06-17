package domain;

import java.util.Date;

public class Message {

    private int id;
    private User userSent;
    private String content;
    private String video;
    private String image;
    private Date sent;

    public Message(User u, String content){
        this.userSent = u;
        this.content = content;
        this.sent = new Date();
    }

    public User getUserSent() {
        return userSent;
    }

    public void setUserSent(User userSent) {
        this.userSent = userSent;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
