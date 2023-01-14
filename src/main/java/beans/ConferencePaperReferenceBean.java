package beans;

import entity.ConferencePaperReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDate;

@ManagedBean
@SessionScoped
public class ConferencePaperReferenceBean {

    private static ConferencePaperReference conferencePaperReference = new ConferencePaperReference();
    private static String author = "";
    private static LocalDate date = null;
    private static String note = "";
    private static String title = "";
    private static String publisher = "";
    private static String volume = "";
    private static String address = "";
    private static String pages = "";

    public ConferencePaperReference getConferencePaperReference() { return conferencePaperReference; }

    public void setConferencePaperReference(ConferencePaperReference conferencePaperReference) { ConferencePaperReferenceBean.conferencePaperReference = conferencePaperReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { ConferencePaperReferenceBean.author = author; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { ConferencePaperReferenceBean.date = date; }

    public String getNote() { return note; }

    public void setNote(String note) { ConferencePaperReferenceBean.note = note; }

    public String getTitle() { return title; }

    public void setTitle(String title) { ConferencePaperReferenceBean.title = title; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { ConferencePaperReferenceBean.publisher = publisher; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { ConferencePaperReferenceBean.volume = volume; }

    public String getAddress() { return address; }

    public void setAddress(String address) { ConferencePaperReferenceBean.address = address; }

    public String getPages() { return pages; }

    public void setPages(String pages) { ConferencePaperReferenceBean.pages = pages; }

    public void cleanVariables(){
        author = "";
        date = null;
        note = "";
        title = "";
        publisher = "";
        volume = "";
        address = "";
        pages = "";
    }

    public void copyEdit(ConferencePaperReference paper){
        if(paper != null)
            conferencePaperReference = new ConferencePaperReference(paper.getId(), paper.getAuthor(),
                    paper.getDate(), paper.getNote(), paper.getTitle(), paper.getUser(), paper.getAddress(),
                    paper.getPages(), paper.getPublisher(), paper.getVolume());
    }
}
