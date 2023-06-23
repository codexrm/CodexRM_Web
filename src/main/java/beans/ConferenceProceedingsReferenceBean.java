package beans;

import entity.ConferenceProceedingReference;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ConferenceProceedingsReferenceBean {

    private static ConferenceProceedingReference conferenceProceedingsReference = new ConferenceProceedingReference();
    private static String title = "";
    private static String year = "";
    private static String editor = "";
    private static String volume = "";
    private static String number = "";
    private static String series = "";
    private static String address = "";
    private static String publisher = "";
    private static String month = "";
    private static String organization = "";
    private static String isbn = "";
    private static String note = "";

    public ConferenceProceedingReference getConferenceProceedingsReference() { return conferenceProceedingsReference; }

    public void setConferenceProceedingsReference(ConferenceProceedingReference conferenceProceedingsReference) { ConferenceProceedingsReferenceBean.conferenceProceedingsReference = conferenceProceedingsReference; }

    public String getTitle() { return title; }

    public void setTitle(String title) { ConferenceProceedingsReferenceBean.title = title; }

    public String getYear() { return year; }

    public void setYear(String year) { ConferenceProceedingsReferenceBean.year = year; }

    public String getEditor() { return editor; }

    public void setEditor(String editor) { ConferenceProceedingsReferenceBean.editor = editor; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { ConferenceProceedingsReferenceBean.volume = volume; }

    public String getNumber() { return number; }

    public void setNumber(String number) { ConferenceProceedingsReferenceBean.number = number; }

    public String getSeries() { return series; }

    public void setSeries(String series) { ConferenceProceedingsReferenceBean.series = series; }

    public String getAddress() { return address; }

    public void setAddress(String address) { ConferenceProceedingsReferenceBean.address = address; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { ConferenceProceedingsReferenceBean.publisher = publisher; }

    public String getMonth() { return month; }

    public void setMonth(String month) { ConferenceProceedingsReferenceBean.month = month; }

    public String getOrganization() { return organization; }

    public void setOrganization(String organization) { ConferenceProceedingsReferenceBean.organization = organization; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { ConferenceProceedingsReferenceBean.isbn = isbn; }

    public String getNote() { return note; }

    public void setNote(String note) { ConferenceProceedingsReferenceBean.note = note; }

    public void cleanVariables(){
        title = "";
        year = "";
        editor = "";
        volume = "";
        number = "";
        series = "";
        address = "";
        publisher = "";
        month = "";
        organization = "";
        isbn = "";
        note = "";

    }

    public void copyEdit(ConferenceProceedingReference proceedings){
        if(proceedings != null)
            conferenceProceedingsReference = new ConferenceProceedingReference(proceedings.getTitle(), proceedings.getYear(), proceedings.getMonth(), proceedings.getNote(), proceedings.getUser(), proceedings.getEditor(), proceedings.getVolume(),
                    proceedings.getNumber(), proceedings.getSeries(), proceedings.getAddress(), proceedings.getPublisher(), proceedings.getIsbn(), proceedings.getOrganization());
    }
}
