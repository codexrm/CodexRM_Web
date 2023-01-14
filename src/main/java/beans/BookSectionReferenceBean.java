package beans;

import entity.BookSectionReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDate;

@ManagedBean
@SessionScoped
public class BookSectionReferenceBean {

    private static BookSectionReference bookSectionReference = new BookSectionReference();
    private static String author = "";
    private static LocalDate date = null;
    private static String note = "";
    private static String title = "";
    private static String publisher = "";
    private static String address = "";
    private static String edition = "";
    private static String series = "";
    private static String volume = "";
    private static String chapter = "";
    private static String pages = "";

    public BookSectionReference getBookSectionReference() { return bookSectionReference; }

    public void setBookSectionReference(BookSectionReference bookSectionReference) { BookSectionReferenceBean.bookSectionReference = bookSectionReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { BookSectionReferenceBean.author = author; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { BookSectionReferenceBean.date = date; }

    public String getNote() { return note; }

    public void setNote(String note) { BookSectionReferenceBean.note = note; }

    public String getTitle() { return title; }

    public void setTitle(String title) { BookSectionReferenceBean.title = title; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { BookSectionReferenceBean.publisher = publisher; }

    public String getAddress() { return address; }

    public void setAddress(String address) { BookSectionReferenceBean.address = address; }

    public String getEdition() { return edition; }

    public void setEdition(String edition) { BookSectionReferenceBean.edition = edition; }

    public String getSeries() { return series; }

    public void setSeries(String series) { BookSectionReferenceBean.series = series; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { BookSectionReferenceBean.volume = volume; }

    public String getChapter() { return chapter; }

    public void setChapter(String chapter) { BookSectionReferenceBean.chapter = chapter; }

    public String getPages() { return pages; }

    public void setPages(String pages) { BookSectionReferenceBean.pages = pages; }

    public void cleanVariables(){
        author = "";
        date = null;
        note = "";
        title = "";
        publisher = "";
        address = "";
        edition = "";
        series = "";
        volume = "";
        chapter = "";
        pages = "";
    }

    public void copyEdit(BookSectionReference section){
        if(section != null)
            bookSectionReference = new BookSectionReference(section.getId(), section.getAuthor(),
                    section.getDate(), section.getNote(), section.getTitle(), section.getUser(),
                    section.getAddress(), section.getEdition(), section.getPublisher(), section.getSeries(),
                    section.getVolume(), section.getChapter(), section.getPages());
    }
}
