package dto;

import entity.User;

import java.time.LocalDate;

public class ThesisReferenceDTO extends ReferenceDTO{

    private String school;
    private String type;
    private String address;

    public ThesisReferenceDTO() {}

    public ThesisReferenceDTO(String author, String title, LocalDate date, String note, Integer id, User user, String school, String type, String address) {
        super(author, title, date, note, id, user);
        this.school = school;
        this.type = type;
        this.address = address;
    }

    public ThesisReferenceDTO(String author, String title, LocalDate date, String note, User user, String school, String type, String address) {
        super(author, title, date, note, user);
        this.school = school;
        this.type = type;
        this.address = address;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
