package beans;

import entity.BookLetReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class BookLetReferenceBean {

    private static BookLetReference bookLetReference = new BookLetReference();
    private static String title = "";
    private static String author = "";
    private static String howpublished = "";
    private static String address = "";
    private static String month = "";
    private static String year = "";
    private static String note = "";

    public BookLetReference getBookLetReference() { return bookLetReference; }

    public void setBookLetReference(BookLetReference bookLetReference) { BookLetReferenceBean.bookLetReference = bookLetReference; }

    public String getTitle() { return title; }

    public void setTitle(String title) { BookLetReferenceBean.title = title; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { BookLetReferenceBean.author = author; }

    public String getHowpublished() { return howpublished; }

    public void setHowpublished(String howpublished) { BookLetReferenceBean.howpublished = howpublished; }

    public String getAddress() { return address; }

    public void setAddress(String address) { BookLetReferenceBean.address = address; }

    public String getMonth() { return month; }

    public void setMonth(String month) { BookLetReferenceBean.month = month; }

    public String getYear() { return year; }

    public void setYear(String year) { BookLetReferenceBean.year = year; }

    public String getNote() { return note; }

    public void setNote(String note) { BookLetReferenceBean.note = note; }

    public void cleanVariables(){
        title = "";
        author = "";
        howpublished = "";
        address = "";
        month = "";
        year = "";
        note = "";
    }

    public void copyEdit(BookLetReference bookLet){
        if(bookLet != null)
            bookLetReference = new BookLetReference(bookLet.getTitle(), bookLet.getYear(), bookLet.getMonth(), bookLet.getNote(), bookLet.getUser(), bookLet.getAuthor(), bookLet.getHowpublished(), bookLet.getAddress());
    }
}
