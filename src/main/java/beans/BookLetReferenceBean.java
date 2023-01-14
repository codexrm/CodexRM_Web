package beans;

import entity.BookLetReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDate;

@ManagedBean
@SessionScoped
public class BookLetReferenceBean {

    private static BookLetReference bookLetReference = new BookLetReference();
    private static String author = "";
    private static LocalDate date = null;
    private static String note = "";
    private static String title = "";
    private static String howpublished = "";
    private static String address = "";

    public BookLetReference getBookLetReference() { return bookLetReference; }

    public void setBookLetReference(BookLetReference bookLetReference) { BookLetReferenceBean.bookLetReference = bookLetReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { BookLetReferenceBean.author = author; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { BookLetReferenceBean.date = date; }

    public String getNote() { return note; }

    public void setNote(String note) { BookLetReferenceBean.note = note; }

    public String getTitle() { return title; }

    public void setTitle(String title) { BookLetReferenceBean.title = title; }

    public String getHowpublished() { return howpublished; }

    public void setHowpublished(String howpublished) { BookLetReferenceBean.howpublished = howpublished; }

    public String getAddress() { return address; }

    public void setAddress(String address) { BookLetReferenceBean.address = address; }

    public void cleanVariables(){
        author = "";
        date = null;
        note = "";
        title = "";
        howpublished = "";
        address = "";
    }

    public void copyEdit(BookLetReference bookLet){
        if(bookLet != null)
            bookLetReference = new BookLetReference(bookLet.getId(), bookLet.getAuthor(), bookLet.getDate(),
                    bookLet.getNote(), bookLet.getTitle(), bookLet.getUser(), bookLet.getAddress(),
                    bookLet.getHowpublished());
    }
}
