package beans;


import entity.*;
import enums.Format;
import model.ExportR;
import model.ImportR;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import org.primefaces.PrimeFaces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ReferenceBean {

    private static List<Reference> referenceList = new ArrayList<Reference>();
    private static Reference reference = new Reference();
    private static List<Reference> selectReferenceList = new ArrayList<Reference>();

    private static ArticleReferenceBean articleReferenceBean = new ArticleReferenceBean();
    private static BookReferenceBean bookReferenceBean = new BookReferenceBean();
    private static BookSectionReferenceBean bookSectionReferenceBean = new BookSectionReferenceBean();
    private static BookLetReferenceBean bookLetReferenceBean  = new BookLetReferenceBean();
    private static ConferenceProceedingsReferenceBean conferenceProceedingsReferenceBean = new ConferenceProceedingsReferenceBean();
    private static ThesisReferenceBean thesisReferenceBean = new ThesisReferenceBean();
    private static ConferencePaperReferenceBean conferencePaperReferenceBean = new ConferencePaperReferenceBean();
    private static WebPageReferenceBean webPageReferenceBean = new WebPageReferenceBean();

    private static ExportR exportR = new ExportR();
    private static ImportR importR = new ImportR();

    private static File exportFile = new File(System.getProperty("user.home") + File. separator +"Downloads" + File. separator + "exported References.txt" );
    private static String path = "";
    private static String format = "";

    public List<Reference> getReferenceList() { return referenceList; }

    public void setReferenceList(List<Reference> referenceList) { ReferenceBean.referenceList = referenceList; }

    public Reference getReference() { return reference; }

    public void setReference(Reference reference) { ReferenceBean.reference = reference; }

    public List<Reference> getSelectReferenceList() { return selectReferenceList; }

    public void setSelectReferenceList(List<Reference> selectReferenceList) { ReferenceBean.selectReferenceList = selectReferenceList; }

    public ArticleReferenceBean getArticleReferenceBean() { return articleReferenceBean; }

    public void setArticleReferenceBean(ArticleReferenceBean articleReferenceBean) { ReferenceBean.articleReferenceBean = articleReferenceBean; }

    public BookReferenceBean getBookReferenceBean() { return bookReferenceBean; }

    public void setBookReferenceBean(BookReferenceBean bookReferenceBean) { ReferenceBean.bookReferenceBean = bookReferenceBean; }

    public BookSectionReferenceBean getBookSectionReferenceBean() { return bookSectionReferenceBean; }

    public void setBookSectionReferenceBean(BookSectionReferenceBean bookSectionReferenceBean) { ReferenceBean.bookSectionReferenceBean = bookSectionReferenceBean; }

    public BookLetReferenceBean getBookLetReferenceBean() { return bookLetReferenceBean; }

    public void setBookLetReferenceBean(BookLetReferenceBean bookLetReferenceBean) { ReferenceBean.bookLetReferenceBean = bookLetReferenceBean; }

    public ConferenceProceedingsReferenceBean getConferenceProceedingsReferenceBean() { return conferenceProceedingsReferenceBean; }

    public void setConferenceProceedingsReferenceBean(ConferenceProceedingsReferenceBean conferenceProceedingsReferenceBean) { ReferenceBean.conferenceProceedingsReferenceBean = conferenceProceedingsReferenceBean; }

    public ThesisReferenceBean getThesisReferenceBean() { return thesisReferenceBean; }

    public void setThesisReferenceBean(ThesisReferenceBean thesisReferenceBean) { ReferenceBean.thesisReferenceBean = thesisReferenceBean; }

    public ConferencePaperReferenceBean getConferencePaperReferenceBean() { return conferencePaperReferenceBean; }

    public void setConferencePaperReferenceBean(ConferencePaperReferenceBean conferencePaperReferenceBean) { ReferenceBean.conferencePaperReferenceBean = conferencePaperReferenceBean; }

    public WebPageReferenceBean getWebPageReferenceBean() { return webPageReferenceBean; }

    public void setWebPageReferenceBean(WebPageReferenceBean webPageReferenceBean) { ReferenceBean.webPageReferenceBean = webPageReferenceBean; }

    public ExportR getExportR() { return exportR; }

    public void setExportR(ExportR exportR) { ReferenceBean.exportR = exportR; }

    public ImportR getImportR() { return importR; }

    public void setImportR(ImportR importR) { ReferenceBean.importR = importR; }

    public File getExportFile() { return exportFile; }

    public void setExportFile(File exportFile) { ReferenceBean.exportFile = exportFile; }

    public String getPath() { return path; }

    public void setPath(String path) { ReferenceBean.path = path; }

    public String getFormat() { return format; }

    public void setFormat(String format) { ReferenceBean.format = format; }

    public void cleanVariables(){
        path = "";
        format = "";
    }

    // Create
    public Reference createArticleReference() { return articleReferenceBean.create(); }

    public Reference createBookReference() { return bookReferenceBean.create(); }

    public Reference createBookSectionReference() { return bookSectionReferenceBean.create(); }

    public Reference createBookLetReference() { return bookLetReferenceBean.create(); }

    public Reference createThesisReference() { return thesisReferenceBean.create(); }

    public Reference createConferenceProceedingsReference() { return conferenceProceedingsReferenceBean.create(); }

    public Reference createConferencePaperReference() { return conferencePaperReferenceBean.create(); }

    public Reference createWebPageReference() { return webPageReferenceBean.create(); }

    //Info
    public void info(Reference selectReference) {

        if (selectReference != null) {
            if (selectReference.getClass() == BookReference.class) {
                PrimeFaces.current().ajax().update("form:info-bookReference-content");
                bookReferenceBean.setBookReference((BookReference) selectReference);
                PrimeFaces.current().executeScript("PF('infoBookReferenceDialog').show()");

            } else {
                if (selectReference.getClass() == ArticleReference.class) {
                    PrimeFaces.current().ajax().update("form:info-articleReference-content");
                    articleReferenceBean.copyEdit((ArticleReference) selectReference);
                    PrimeFaces.current().executeScript("PF('infoArticleReferenceDialog').show()");
                } else {
                    if (selectReference.getClass() == BookSectionReference.class) {
                        PrimeFaces.current().ajax().update("form:info-bookSectionReference-content");
                        bookSectionReferenceBean.copyEdit((BookSectionReference) selectReference);
                        PrimeFaces.current().executeScript("PF('infoBookSectionReferenceDialog').show()");
                    } else {
                        if (selectReference.getClass() == BookLetReference.class) {
                            PrimeFaces.current().ajax().update("form:info-bookLetReference-content");
                            bookLetReferenceBean.copyEdit((BookLetReference) selectReference);
                            PrimeFaces.current().executeScript("PF('infoBookLetReferenceDialog').show()");

                        } else {
                            if (selectReference.getClass() == ConferenceProceedingReference.class) {
                                PrimeFaces.current().ajax().update("form:info-conferenceProceedingsReference-content");
                                conferenceProceedingsReferenceBean.copyEdit((ConferenceProceedingReference) selectReference);
                                PrimeFaces.current().executeScript("PF('infoConferenceProceedingsReferenceDialog').show()");

                            } else {
                                if (selectReference.getClass() == ThesisReference.class) {
                                    PrimeFaces.current().ajax().update("form:info-thesisReference-content");
                                    thesisReferenceBean.copyEdit((ThesisReference) selectReference);
                                    PrimeFaces.current().executeScript("PF('infoThesisReferenceDialog').show()");
                                }
                                else {
                                    if (selectReference.getClass() == ConferencePaperReference.class) {
                                        PrimeFaces.current().ajax().update("form:info-conferencePaperReference-content");
                                        conferencePaperReferenceBean.copyEdit((ConferencePaperReference) selectReference);
                                        PrimeFaces.current().executeScript("PF('infoConferencePaperReferenceDialog').show()");
                                    }
                                    else {
                                        if (selectReference.getClass() == WebPageReference.class) {
                                            PrimeFaces.current().ajax().update("form:info-webPageReference-content");
                                            webPageReferenceBean.copyEdit((WebPageReference) selectReference);
                                            PrimeFaces.current().executeScript("PF('infoWebPageReferenceDialog').show()");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        PrimeFaces.current().ajax().update("form:messages");
    }

    public String infoMonth(String mth) {
        switch (mth){
            case "jan":
                return "Enero";
            case "feb":
                return "Febrero";
            case "mar":
                return "Marzo";
            case "apr":
                return "Abril";
            case "may":
                return "Mayo";
            case "jun":
                return "Junio";
            case "jul":
                return "Julio";
            case "aug":
                return "Agosto";
            case "sep":
                return "Septiembre";
            case "oct":
                return "Octubre";
            case "nov":
                return "Noviembre";
            case "dec":
                return "Diciembre";
            default:
                return "";
        }
    }

    //Edit
    public void edit(Reference selectReference) {
        if (selectReference != null) {

            if (selectReference.getClass() == BookReference.class) {
                PrimeFaces.current().ajax().update("form:edit-bookReference-content");
                bookReferenceBean.copyEdit((BookReference) selectReference);
                PrimeFaces.current().executeScript("PF('editBookReferenceDialog').show()");
            } else {
                if (selectReference.getClass() == ArticleReference.class) {
                    PrimeFaces.current().ajax().update("form:edit-articleReference-content");
                    articleReferenceBean.copyEdit((ArticleReference) selectReference);
                    PrimeFaces.current().executeScript("PF('editArticleReferenceDialog').show()");
                } else {
                    if (selectReference.getClass() == BookSectionReference.class) {
                        PrimeFaces.current().executeScript("PF('editBookSectionReferenceDialog').show()");
                        bookSectionReferenceBean.copyEdit((BookSectionReference) selectReference);
                        PrimeFaces.current().ajax().update("form:edit-bookSectionReference-content");
                    } else {
                        if (selectReference.getClass() == BookLetReference.class) {
                            PrimeFaces.current().ajax().update("form:edit-bookLetReference-content");
                            bookLetReferenceBean.copyEdit((BookLetReference) selectReference);
                            PrimeFaces.current().executeScript("PF('editBookLetReferenceDialog').show()");

                        } else {
                            if (selectReference.getClass() == ConferenceProceedingReference.class) {
                                PrimeFaces.current().ajax().update("form:edit-conferenceProceedingsReference-content");
                                conferenceProceedingsReferenceBean.copyEdit((ConferenceProceedingReference) selectReference);
                                PrimeFaces.current().executeScript("PF('editConferenceProceedingsReferenceDialog').show()");

                            } else {
                                if (selectReference.getClass() == ThesisReference.class) {
                                    PrimeFaces.current().ajax().update("form:edit-thesisReference-content");
                                    thesisReferenceBean.copyEdit((ThesisReference) selectReference);
                                    PrimeFaces.current().executeScript("PF('editThesisReferenceDialog').show()");

                                }else{
                                    if(selectReference.getClass() == ConferencePaperReference.class){
                                        PrimeFaces.current().ajax().update("form:edit-conferencePaperReference-content");
                                        conferencePaperReferenceBean.copyEdit((ConferencePaperReference) selectReference);
                                        PrimeFaces.current().executeScript("PF('editConferencePaperReferenceDialog').show()");

                                    } else{
                                        if(selectReference.getClass() == WebPageReference.class){
                                            PrimeFaces.current().ajax().update("form:edit-webPageReference-content");
                                            webPageReferenceBean.copyEdit((WebPageReference) selectReference);
                                            PrimeFaces.current().executeScript("PF('editWebPageReferenceDialog').show()");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Export
    public void exportReferenceList(Format format) throws IOException { exportR.exportReferenceList(exportFile, (ArrayList<Reference>) selectReferenceList, format); }

    //Import
    public ArrayList<Reference> importReferences() throws IOException, TokenMgrException, ParseException {

        File importFile = new File(path);
        if (importFile.exists() && importFile.isFile()) {
            return importR.importReferences(path, format);
        }
        else{return null;}
    }
}
