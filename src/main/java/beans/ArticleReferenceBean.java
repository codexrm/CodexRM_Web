package beans;

import entity.ArticleReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ArticleReferenceBean {

    private static ArticleReference articleReference = new ArticleReference();
    private static String author = "";
    private static String title = "";
    private static String journal = "";
    private static String year = "";
    private static String volume = "";
    private static String number = "";
    private static String pages = "";
    private static String month = "";
    private static String issn = "";
    private static String note = "";

    public ArticleReferenceBean() {}

    public ArticleReference getArticleReference() { return articleReference; }

    public void setArticleReference(ArticleReference articleReference) { ArticleReferenceBean.articleReference = articleReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { ArticleReferenceBean.author = author; }

    public String getTitle() { return title; }

    public void setTitle(String title) { ArticleReferenceBean.title = title; }

    public String getJournal() { return journal; }

    public void setJournal(String journal) { ArticleReferenceBean.journal = journal; }

    public String getYear() { return year; }

    public void setYear(String year) { ArticleReferenceBean.year = year; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { ArticleReferenceBean.volume = volume; }

    public String getNumber() { return number; }

    public void setNumber(String number) { ArticleReferenceBean.number = number; }

    public String getPages() { return pages; }

    public void setPages(String pages) { ArticleReferenceBean.pages = pages; }

    public String getMonth() { return month; }

    public void setMonth(String month) { ArticleReferenceBean.month = month; }

    public String getIssn() { return issn; }

    public void setIssn(String issn) { ArticleReferenceBean.issn = issn; }

    public String getNote() { return note; }

    public void setNote(String note) { ArticleReferenceBean.note = note; }

    public void cleanVariables() {
        author = "";
        title = "";
        journal = "";
        year = "";
        volume = "";
        number = "";
        pages = "";
        month = "";
        issn = "";
        note = "";
    }

    public void copyEdit(ArticleReference article) {
        if (article != null)
            articleReference = new ArticleReference(article.getId(), article.getTitle(), article.getYear(), article.getMonth(), article.getNote(), article.getAuthor(), article.getJournal(), article.getVolume(), article.getNumber(), article.getPages(),
                    article.getIssn());
    }

    public ArticleReference create() {
        return new ArticleReference(title, year, month, note, author, journal, volume, number, pages, issn);
    }
}
