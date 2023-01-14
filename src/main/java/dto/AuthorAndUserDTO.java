package dto;

import entity.User;

public class AuthorAndUserDTO {

    private String author;
    private User user;

    public AuthorAndUserDTO() { }

    public AuthorAndUserDTO(String author, User user) {
        this.author = author;
        this.user = user;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
