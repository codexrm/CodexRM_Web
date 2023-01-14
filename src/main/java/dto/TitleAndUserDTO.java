package dto;

import entity.User;

public class TitleAndUserDTO {

    private String title;
    private User user;

    public TitleAndUserDTO() { }

    public TitleAndUserDTO(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
