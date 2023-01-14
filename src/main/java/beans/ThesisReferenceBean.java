package beans;

import entity.ThesisReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDate;

@ManagedBean
@SessionScoped
public class ThesisReferenceBean {

    private static ThesisReference thesisReference = new ThesisReference();
    private static String author = "";
    private static LocalDate date = null;
    private static String note = "";
    private static String title = "";
    private static String school = "";
    private static String type = "";
    private static String address = "";

    public ThesisReference getThesisReference() { return thesisReference; }

    public void setThesisReference(ThesisReference thesisReference) { ThesisReferenceBean.thesisReference = thesisReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { ThesisReferenceBean.author = author; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { ThesisReferenceBean.date = date; }

    public String getNote() { return note; }

    public void setNote(String note) { ThesisReferenceBean.note = note; }

    public String getTitle() { return title; }

    public void setTitle(String title) { ThesisReferenceBean.title = title; }

    public String getSchool() { return school; }

    public void setSchool(String school) { ThesisReferenceBean.school = school; }

    public String getType() { return type; }

    public void setType(String type) { ThesisReferenceBean.type = type; }

    public String getAddress() { return address; }

    public void setAddress(String address) { ThesisReferenceBean.address = address; }

    public void cleanVariables(){
        author = "";
        date = null;
        note = "";
        title = "";
        school = "";
        type = "";
        address = "";
    }

    public void copyEdit(ThesisReference thesis){
        if(thesis != null)
            thesisReference = new ThesisReference(thesis.getId(), thesis.getAuthor(), thesis.getDate(),
                    thesis.getNote(), thesis.getTitle(), thesis.getUser(), thesis.getAddress(),
                    thesis.getSchool(), thesis.getType());
    }
}
