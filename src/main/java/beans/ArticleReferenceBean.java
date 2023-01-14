package beans;

import entity.ArticleReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDate;

@ManagedBean
@SessionScoped
public class ArticleReferenceBean {

    private static ArticleReference articleReference = new ArticleReference();
    private static String author = "";
    private static LocalDate date = null;
    private static String note = "";
    private static String title = "";
    private static String journal = "";
    private static String volume = "";
    private static String number = "";
    private static String pages = "";

    public ArticleReference getArticleReference() { return articleReference; }

    public void setArticleReference(ArticleReference articleReference) { ArticleReferenceBean.articleReference = articleReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { ArticleReferenceBean.author = author; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { ArticleReferenceBean.date = date; }

    public String getNote() { return note; }

    public void setNote(String note) { ArticleReferenceBean.note = note; }

    public String getTitle() { return title; }

    public void setTitle(String title) { ArticleReferenceBean.title = title; }

    public String getJournal() { return journal; }

    public void setJournal(String journal) { ArticleReferenceBean.journal = journal; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { ArticleReferenceBean.volume = volume; }

    public String getNumber() { return number; }

    public void setNumber(String number) { ArticleReferenceBean.number = number; }

    public String getPages() { return pages; }

    public void setPages(String pages) { ArticleReferenceBean.pages = pages; }

    public void cleanVariables(){
        author = "";
        date = null;
        note = "";
        title = "";
        journal = "";
        volume = "";
        number = "";
        pages = "";
    }

    public void copyEdit(ArticleReference article){
        if(article != null)
            articleReference = new ArticleReference(article.getId(), article.getAuthor(), article.getDate(),
                    article.getNote(), article.getTitle(), article.getUser(), article.getJournal(),
                    article.getNumber(), article.getPages(), article.getVolume());
    }
}
