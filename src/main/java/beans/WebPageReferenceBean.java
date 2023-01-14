package beans;

import entity.WebPageReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDate;

@ManagedBean
@SessionScoped
public class WebPageReferenceBean {

    private static WebPageReference webPageReference = new WebPageReference();
    private static String author = "";
    private static LocalDate date = null;
    private static String note = "";
    private static String title = "";
    private static String url = "";
    private static LocalDate accessDate = null;

    public WebPageReference getWebPageReference() { return webPageReference; }

    public void setWebPageReference(WebPageReference webPageReference) { WebPageReferenceBean.webPageReference = webPageReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { WebPageReferenceBean.author = author; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { WebPageReferenceBean.date = date; }

    public String getNote() { return note; }

    public void setNote(String note) { WebPageReferenceBean.note = note; }

    public String getTitle() { return title; }

    public void setTitle(String title) { WebPageReferenceBean.title = title; }

    public String getUrl() { return url; }

    public void setUrl(String url) { WebPageReferenceBean.url = url; }

    public LocalDate getAccessDate() { return accessDate; }

    public void setAccessDate(LocalDate accessDate) { WebPageReferenceBean.accessDate = accessDate; }

    public void cleanVariables(){
        author = "";
        date = null;
        note = "";
        title = "";
        url = "";
        accessDate = null;
    }

    public void copyEdit(WebPageReference webPage){
        if(webPage != null)
            webPageReference = new WebPageReference(webPage.getId(), webPage.getAuthor(), webPage.getDate(),
                    webPage.getNote(), webPage.getTitle(), webPage.getUser(), webPage.getAccessDate(),
                    webPage.getUrl());
    }
}
