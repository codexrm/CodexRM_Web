package beans;

import entity.ConferenceProceedingsReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDate;

@ManagedBean
@SessionScoped
public class ConferenceProceedingsReferenceBean {

    private static ConferenceProceedingsReference conferenceProceedingsReference = new ConferenceProceedingsReference();
    private static String author = "";
    private static LocalDate date = null;
    private static String note = "";
    private static String title = "";
    private static String volume = "";
    private static String series = "";
    private static String address = "";

    public ConferenceProceedingsReference getConferenceProceedingsReference() { return conferenceProceedingsReference; }

    public void setConferenceProceedingsReference(ConferenceProceedingsReference conferenceProceedingsReference) { ConferenceProceedingsReferenceBean.conferenceProceedingsReference = conferenceProceedingsReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { ConferenceProceedingsReferenceBean.author = author; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { ConferenceProceedingsReferenceBean.date = date; }

    public String getNote() { return note; }

    public void setNote(String note) { ConferenceProceedingsReferenceBean.note = note; }

    public String getTitle() { return title; }

    public void setTitle(String title) { ConferenceProceedingsReferenceBean.title = title; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { ConferenceProceedingsReferenceBean.volume = volume; }

    public String getSeries() { return series; }

    public void setSeries(String series) { ConferenceProceedingsReferenceBean.series = series; }

    public String getAddress() { return address; }

    public void setAddress(String address) { ConferenceProceedingsReferenceBean.address = address; }

    public void cleanVariables(){
        author = "";
        date = null;
        note = "";
        title = "";
        volume = "";
        series = "";
        address = "";
    }

    public void copyEdit(ConferenceProceedingsReference proceedings){
        if(proceedings != null)
            conferenceProceedingsReference = new ConferenceProceedingsReference(proceedings.getId(),
                    proceedings.getAuthor(), proceedings.getDate(), proceedings.getNote(),
                    proceedings.getTitle(), proceedings.getUser(), proceedings.getAddress(),
                    proceedings.getSeries(), proceedings.getVolume());
    }
}
