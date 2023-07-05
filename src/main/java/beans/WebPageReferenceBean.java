package beans;

import entity.WebPageReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class WebPageReferenceBean {

    private static WebPageReference webPageReference = new WebPageReference();
    private static String author = "";
    private static String title = "";
    private static String url = "";
    private static String month = "";
    private static String year = "";
    private static String note = "";

    public WebPageReference getWebPageReference() { return webPageReference; }

    public void setWebPageReference(WebPageReference webPageReference) { WebPageReferenceBean.webPageReference = webPageReference; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { WebPageReferenceBean.author = author; }

    public String getTitle() { return title; }

    public void setTitle(String title) { WebPageReferenceBean.title = title; }

    public String getUrl() { return url; }

    public void setUrl(String url) { WebPageReferenceBean.url = url; }

    public String getMonth() { return month; }

    public void setMonth(String month) { WebPageReferenceBean.month = month; }

    public String getYear() { return year; }

    public void setYear(String year) { WebPageReferenceBean.year = year; }

    public String getNote() { return note; }

    public void setNote(String note) { WebPageReferenceBean.note = note; }

    public void cleanVariables(){
        author = "";
        title = "";
        url = "";
        month = "";
        year = "";
        note = "";
    }

    public void copyEdit(WebPageReference webPage){
        if(webPage != null)
            webPageReference = new WebPageReference(webPage.getId(), webPage.getTitle(), webPage.getYear(), webPage.getMonth(), webPage.getNote(), webPage.getAuthor(), webPage.getUrl());
    }
}
