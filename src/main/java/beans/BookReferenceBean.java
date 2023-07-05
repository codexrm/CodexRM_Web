package beans;

import entity.BookReference;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class BookReferenceBean {

    private static BookReference bookReference = new BookReference();
    private static String author = "";
    private static String editor = "";
    private static String title = "";
    private static String publisher = "";
    private static String year = "";
    private static String volume = "";
    private static String number = "";
    private static String series = "";
    private static String address = "";
    private static String edition = "";
    private static String month = "";
    private static String isbn = "";
    private static String note = "";

    public BookReference getBookReference() { return bookReference; }

    public void setBookReference(BookReference bookReference) { BookReferenceBean.bookReference = bookReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { BookReferenceBean.author = author; }

    public String getEditor() { return editor; }

    public void setEditor(String editor) { BookReferenceBean.editor = editor; }

    public String getTitle() { return title; }

    public void setTitle(String title) { BookReferenceBean.title = title; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { BookReferenceBean.publisher = publisher; }

    public String getYear() { return year; }

    public void setYear(String year) { BookReferenceBean.year = year; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { BookReferenceBean.volume = volume; }

    public String getNumber() { return number; }

    public void setNumber(String number) { BookReferenceBean.number = number; }

    public String getSeries() { return series; }

    public void setSeries(String series) { BookReferenceBean.series = series; }

    public String getAddress() { return address; }

    public void setAddress(String address) { BookReferenceBean.address = address; }

    public String getEdition() { return edition; }

    public void setEdition(String edition) { BookReferenceBean.edition = edition; }

    public String getMonth() { return month; }

    public void setMonth(String month) { BookReferenceBean.month = month; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { BookReferenceBean.isbn = isbn; }

    public String getNote() { return note; }

    public void setNote(String note) { BookReferenceBean.note = note; }

    public void cleanVariables() {
        author = "";
        editor = "";
        title = "";
        publisher = "";
        year = "";
        volume = "";
        number = "";
        series = "";
        address = "";
        edition = "";
        month = "";
        isbn = "";
        note = "";

    }

    public void copyEdit(BookReference book){
        if(book != null)
            bookReference = new BookReference(book.getId(),  book.getTitle(), book.getYear(), book.getMonth(), book.getNote(), book.getAuthor(), book.getEditor(), book.getPublisher(), book.getVolume(), book.getNumber(), book.getSeries(), book.getAddress(),
                    book.getEdition(), book.getIsbn());
    }
}

