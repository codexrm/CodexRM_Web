package beans;

import entity.BookReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDate;

@ManagedBean
@SessionScoped
public class BookReferenceBean {

    private static BookReference bookReference = new BookReference();
    private static String author = "";
    private static LocalDate date = null;
    private static String note = "";
    private static String title = "";
    private static String publisher = "";
    private static String address = "";
    private static String edition = "";
    private static String series = "";
    private static String volume = "";


    public BookReference getBookReference() { return bookReference; }

    public void setBookReference(BookReference bookReference) { BookReferenceBean.bookReference = bookReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { BookReferenceBean.author = author; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { BookReferenceBean.date = date; }

    public String getNote() { return note; }

    public void setNote(String note) { BookReferenceBean.note = note; }

    public String getTitle() { return title; }

    public void setTitle(String title) { BookReferenceBean.title = title; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { BookReferenceBean.publisher = publisher; }

    public String getAddress() { return address; }

    public void setAddress(String address) { BookReferenceBean.address = address; }

    public String getEdition() { return edition; }

    public void setEdition(String edition) { BookReferenceBean.edition = edition; }

    public String getSeries() { return series; }

    public void setSeries(String series) { BookReferenceBean.series = series; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { BookReferenceBean.volume = volume; }

    public void cleanVariables() {
        author = "";
        date = null;
        note = "";
        title = "";
        publisher = "";
        address = "";
        edition = "";
        series = "";
        volume = "";
    }

    public void copyEdit(BookReference book){
        if(book != null)
            bookReference = new BookReference( book.getId(), book.getAuthor(), book.getDate(), book.getNote(),
                    book.getTitle(), book.getUser(), book.getAddress(), book.getEdition(), book.getPublisher(),
                    book.getSeries(), book.getVolume());
    }
}

