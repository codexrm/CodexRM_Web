package beans;

import entity.ThesisReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ThesisReferenceBean {

    private static ThesisReference thesisReference = new ThesisReference();
    private static String author = "";
    private static String title = "";
    private static String school = "";
    private static String year = "";
    private static String type = "";
    private static String address = "";
    private static String month = "";
    private static String note = "";

    public ThesisReference getThesisReference() { return thesisReference; }

    public void setThesisReference(ThesisReference thesisReference) { ThesisReferenceBean.thesisReference = thesisReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { ThesisReferenceBean.author = author; }

    public String getTitle() { return title; }

    public void setTitle(String title) { ThesisReferenceBean.title = title; }

    public String getSchool() { return school; }

    public void setSchool(String school) { ThesisReferenceBean.school = school; }

    public String getYear() { return year; }

    public void setYear(String year) { ThesisReferenceBean.year = year; }

    public String getType() { return type; }

    public void setType(String type) { ThesisReferenceBean.type = type; }

    public  String getAddress() { return address; }

    public void setAddress(String address) { ThesisReferenceBean.address = address; }

    public String getMonth() { return month; }

    public void setMonth(String month) { ThesisReferenceBean.month = month; }

    public String getNote() { return note; }

    public void setNote(String note) { ThesisReferenceBean.note = note; }

    public void cleanVariables(){
        author = "";
        title = "";
        school = "";
        year = "";
        type = "";
        address = "";
        month = "";
        note = "";
    }

    public void copyEdit(ThesisReference thesis){
        if(thesis != null)
            thesisReference = new ThesisReference(thesis.getTitle(), thesis.getYear(), thesis.getMonth(), thesis.getNote(), thesis.getUser(), thesis.getAuthor(), thesis.getSchool(), thesis.getType(), thesis.getAddress());
    }
}
