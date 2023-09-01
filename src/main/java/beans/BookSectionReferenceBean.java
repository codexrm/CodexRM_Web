package beans;

import entity.BookSectionReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class BookSectionReferenceBean {

    private static BookSectionReference bookSectionReference = new BookSectionReference();
    private static String chapter = "";
    private static String pages = "";
    private static String author = "";
    private static String editor = "";
    private static String title = "";
    private static String publisher = "";
    private static String year = "";
    private static String volume = "";
    private static String number = "";
    private static String series = "";
    private static String type = "";
    private static String address = "";
    private static String edition = "";
    private static String month = "";
    private static String isbn = "";
    private static String note = "";

    public BookSectionReference getBookSectionReference() { return bookSectionReference; }

    public void setBookSectionReference(BookSectionReference bookSectionReference) { BookSectionReferenceBean.bookSectionReference = bookSectionReference; }

    public String getChapter() { return chapter; }

    public void setChapter(String chapter) { BookSectionReferenceBean.chapter = chapter; }

    public String getPages() { return pages; }

    public void setPages(String pages) { BookSectionReferenceBean.pages = pages; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { BookSectionReferenceBean.author = author; }

    public String getEditor() { return editor; }

    public void setEditor(String editor) { BookSectionReferenceBean.editor = editor; }

    public String getTitle() { return title; }

    public void setTitle(String title) { BookSectionReferenceBean.title = title; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { BookSectionReferenceBean.publisher = publisher; }

    public String getYear() { return year; }

    public void setYear(String year) { BookSectionReferenceBean.year = year; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { BookSectionReferenceBean.volume = volume; }

    public String getNumber() { return number; }

    public void setNumber(String number) { BookSectionReferenceBean.number = number; }

    public String getSeries() { return series; }

    public void setSeries(String series) { BookSectionReferenceBean.series = series; }

    public String getType() { return type; }

    public void setType(String type) { BookSectionReferenceBean.type = type; }

    public String getAddress() { return address; }

    public void setAddress(String address) { BookSectionReferenceBean.address = address; }

    public String getEdition() { return edition; }

    public void setEdition(String edition) { BookSectionReferenceBean.edition = edition; }

    public String getMonth() { return month; }

    public void setMonth(String month) { BookSectionReferenceBean.month = month; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { BookSectionReferenceBean.isbn = isbn; }

    public String getNote() { return note; }

    public void setNote(String note) { BookSectionReferenceBean.note = note; }

    public void cleanVariables() {
        chapter = "";
        pages = "";
        author = "";
        editor = "";
        title = "";
        publisher = "";
        year = "";
        volume = "";
        number = "";
        series = "";
        type = "";
        address = "";
        edition = "";
        month = "";
        isbn = "";
        note = "";
    }

    public void copyEdit(BookSectionReference section) {
        if (section != null)
            bookSectionReference = new BookSectionReference(section.getId(), section.getTitle(), section.getYear(), section.getMonth(), section.getNote(), section.getAuthor(), section.getEditor(), section.getPublisher(), section.getVolume(), section.getNumber(),
                    section.getSeries(), section.getAddress(), section.getEdition(), section.getIsbn(), section.getChapter(), section.getPages(), section.getType());
    }

    public BookSectionReference create() {
        return new BookSectionReference(title, year, month, note, author, editor, publisher, volume, number, series, address, edition, isbn, chapter, pages, type);
    }

    public String infoBookSectionType(String type) {
        switch (type) {
            case "MATHESIS":
                return "Tesis de Maestría";
            case "PHDTHESIS":
                return "Tesis de Doctorado";
            case "CANDTHESIS":
                return "Tesis Candidata";
            case "TECHREPORT":
                return "Reporte Técnico";
            case "RESREPORT":
                return "Informe de investigación";
            case "SOFTWARE":
                return "Software";
            case "AUDIOCD":
                return "CD de audio";
            case "DataCD":
                return "CD de datos";
            default:
                return "";
        }
    }
}
