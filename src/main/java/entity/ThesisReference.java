package entity;

import utils.FieldValidations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "thesisreference")
public class ThesisReference extends Reference{

    @Column(name = "author")
    private String author;

    @Column(name = "school")
    private String school;

    @Column(name = "type")
    private String type;

    @Column(name = "address")
    private String address;

    private final FieldValidations validations = new FieldValidations();

    public ThesisReference() {}

    public ThesisReference(String title, String year, String month, String note, String author, String school, String type, String address) {
        super(title, year, month, note);
        this.school = school;
        this.type = type;

        if(validations.validateAuthorOrEditor(author))
        this.author = author;

        if(validations.validateAddress(address))
        this.address = address;
    }

    public ThesisReference(Integer id, String title, String year, String month, String note, String author, String school, String type, String address) {
        super(id, title, year, month, note);
        this.school = school;
        this.type = type;

        if(validations.validateAuthorOrEditor(author))
            this.author = author;

        if(validations.validateAddress(address))
            this.address = address;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }

    public String getSchool() { return school; }

    public void setSchool(String school) { this.school = school; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        if(validations.validateAddress(address))
        this.address = address;
    }
}