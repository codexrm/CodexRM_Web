package beans;

import entity.ConferencePaperReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ConferencePaperReferenceBean {

    private static ConferencePaperReference conferencePaperReference = new ConferencePaperReference();
    private static String author = "";
    private static String title = "";
    private static String bookTitle = "";
    private static String year = "";
    private static String editor = "";
    private static String volume = "";
    private static String number = "";
    private static String series = "";
    private static String pages = "";
    private static String address = "";
    private static String month = "";
    private static String organization = "";
    private static String publisher = "";
    private static String note = "";

    public ConferencePaperReference getConferencePaperReference() { return conferencePaperReference; }

    public void setConferencePaperReference(ConferencePaperReference conferencePaperReference) { ConferencePaperReferenceBean.conferencePaperReference = conferencePaperReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { ConferencePaperReferenceBean.author = author; }

    public String getTitle() { return title; }

    public void setTitle(String title) { ConferencePaperReferenceBean.title = title; }

    public String getBookTitle() { return bookTitle; }

    public void setBookTitle(String bookTitle) { ConferencePaperReferenceBean.bookTitle = bookTitle; }

    public String getYear() { return year; }

    public void setYear(String year) { ConferencePaperReferenceBean.year = year; }

    public String getEditor() { return editor; }

    public void setEditor(String editor) { ConferencePaperReferenceBean.editor = editor; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { ConferencePaperReferenceBean.volume = volume; }

    public String getNumber() { return number; }

    public void setNumber(String number) { ConferencePaperReferenceBean.number = number; }

    public String getSeries() { return series; }

    public void setSeries(String series) { ConferencePaperReferenceBean.series = series; }

    public String getPages() { return pages; }

    public void setPages(String pages) { ConferencePaperReferenceBean.pages = pages; }

    public String getAddress() { return address; }

    public void setAddress(String address) { ConferencePaperReferenceBean.address = address; }

    public String getMonth() { return month; }

    public void setMonth(String month) { ConferencePaperReferenceBean.month = month; }

    public String getOrganization() { return organization;}

    public void setOrganization(String organization) { ConferencePaperReferenceBean.organization = organization; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { ConferencePaperReferenceBean.publisher = publisher; }

    public String getNote() { return note; }

    public void setNote(String note) { ConferencePaperReferenceBean.note = note; }

    public void cleanVariables(){
        author = "";
        title = "";
        bookTitle = "";
        year = "";
        editor = "";
        volume = "";
        number = "";
        series = "";
        pages = "";
        address = "";
        month = "";
        organization = "";
        publisher = "";
        note = "";

    }

    public void copyEdit(ConferencePaperReference paper){
        if(paper != null)
            conferencePaperReference = new ConferencePaperReference(paper.getTitle(), paper.getYear(), paper.getMonth(), paper.getNote(), paper.getUser(), paper.getAuthor(), paper.getBookTitle(), paper.getEditor(), paper.getNumber(), paper.getSeries(),
                    paper.getPublisher(), paper.getVolume(), paper.getAddress(), paper.getPages(), paper.getOrganization());
    }
}
