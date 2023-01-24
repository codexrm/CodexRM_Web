package beans;

import entity.*;
import enums.Format;
import model.Export;
import model.ExportFactory;
import model.Import;
import model.ImportFactory;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import org.primefaces.PrimeFaces;
import rest.RestReference;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ReferenceManagerBean {

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
    private static UserBean userBean = new UserBean();
    private static RestReference restReference = new RestReference();
    private static ExportFactory exportFactory = new ExportFactory();
    private static ImportFactory importFactory = new ImportFactory();
    private static File exportFile = new File(System.getProperty("user.home") + File. separator +"Desktop" + File. separator + "exported References.txt" );
    private static String path = "";
    private static String format = "";
    private static LocalDate ActualDate = LocalDate.now();

    public void init() {
        referenceList.clear();
        userBean.setUser(new User("mary","mary"));
        referenceList = restReference.getAllReference(userBean.getUser());
    }
    public void cleanVariables(){
        path = "";
        format = "";
    }

    public List<Reference> getReferenceList() { return referenceList; }

    public void setReferenceList(List<Reference> referenceList) { ReferenceManagerBean.referenceList = referenceList; }

    public Reference getReference() { return reference; }

    public void setReference(Reference reference) { ReferenceManagerBean.reference = reference; }

    public List<Reference> getSelectReferenceList() { return selectReferenceList; }

    public void setSelectReferenceList(List<Reference> selectReferenceList) { ReferenceManagerBean.selectReferenceList = selectReferenceList; }

    public ArticleReferenceBean getArticleReferenceBean() { return articleReferenceBean; }

    public void setArticleReferenceBean(ArticleReferenceBean articleReferenceBean) { ReferenceManagerBean.articleReferenceBean = articleReferenceBean; }

    public BookReferenceBean getBookReferenceBean() { return bookReferenceBean; }

    public void setBookReferenceBean(BookReferenceBean bookReferenceBean) { ReferenceManagerBean.bookReferenceBean = bookReferenceBean; }

    public BookSectionReferenceBean getBookSectionReferenceBean() { return bookSectionReferenceBean; }

    public void setBookSectionReferenceBean(BookSectionReferenceBean bookSectionReferenceBean) { ReferenceManagerBean.bookSectionReferenceBean = bookSectionReferenceBean; }

    public BookLetReferenceBean getBookLetReferenceBean() { return bookLetReferenceBean; }

    public void setBookLetReferenceBean(BookLetReferenceBean bookLetReferenceBean) { ReferenceManagerBean.bookLetReferenceBean = bookLetReferenceBean; }

    public ConferenceProceedingsReferenceBean getConferenceProceedingsReferenceBean() { return conferenceProceedingsReferenceBean; }

    public void setConferenceProceedingsReferenceBean(ConferenceProceedingsReferenceBean conferenceProceedingsReferenceBean) { ReferenceManagerBean.conferenceProceedingsReferenceBean = conferenceProceedingsReferenceBean; }

    public ThesisReferenceBean getThesisReferenceBean() { return thesisReferenceBean; }

    public void setThesisReferenceBean(ThesisReferenceBean thesisReferenceBean) { ReferenceManagerBean.thesisReferenceBean = thesisReferenceBean; }

    public ConferencePaperReferenceBean getConferencePaperReferenceBean() { return conferencePaperReferenceBean; }

    public void setConferencePaperReferenceBean(ConferencePaperReferenceBean conferencePaperReferenceBean) { ReferenceManagerBean.conferencePaperReferenceBean = conferencePaperReferenceBean; }

    public WebPageReferenceBean getWebPageReferenceBean() { return webPageReferenceBean; }

    public void setWebPageReferenceBean(WebPageReferenceBean webPageReferenceBean) { ReferenceManagerBean.webPageReferenceBean = webPageReferenceBean; }

    public UserBean getUserBean() { return userBean; }

    public void setUserBean(UserBean userBean) { ReferenceManagerBean.userBean = userBean; }

    public RestReference getRestReference() { return restReference; }

    public void setRestReference(RestReference restReference) { ReferenceManagerBean.restReference = restReference; }

    public ExportFactory getExportFactory() { return exportFactory; }

    public void setExportFactory(ExportFactory exportFactory) { ReferenceManagerBean.exportFactory = exportFactory; }

    public ImportFactory getImportFactory() { return importFactory; }

    public void setImportFactory(ImportFactory importFactory) { ReferenceManagerBean.importFactory = importFactory; }

    public File getExportFile() { return exportFile; }

    public void setExportFile(File exportFile) { ReferenceManagerBean.exportFile = exportFile; }

    public String getFormat() { return format; }

    public void setFormat(String format) { ReferenceManagerBean.format = format; }

    public String getPath() { return path; }

    public void setPath(String path) { ReferenceManagerBean.path = path; }

    public LocalDate getActualDate() { return ActualDate; }

    public void setActualDate(LocalDate actualDate) { ActualDate = actualDate; }

    public void createArticleReference() {

        ArticleReference article = new ArticleReference(articleReferenceBean.getAuthor(),
                articleReferenceBean.getTitle(), articleReferenceBean.getDate(), articleReferenceBean.getNote(),
                userBean.getUser(), articleReferenceBean.getJournal(), articleReferenceBean.getNumber(),
                articleReferenceBean.getPages(), articleReferenceBean.getVolume());

        if(restReference.addReference(article)){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Artículo adicionada", "");

        }else{
            addMessage( FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addArticleReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createBookReference() {

        BookReference book = new BookReference(bookReferenceBean.getAuthor(), bookReferenceBean.getTitle(),
                bookReferenceBean.getDate(), bookReferenceBean.getNote(), userBean.getUser(),
                bookReferenceBean.getAddress(), bookReferenceBean.getEdition(), bookReferenceBean.getPublisher(),
                bookReferenceBean.getSeries(), bookReferenceBean.getVolume());

        if (restReference.addReference(book)) {
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia de Libro adicionada", "");

        } else {
           addMessage(FacesMessage.SEVERITY_ERROR, "Existe error en el formulario", "");
        }

        init();
        PrimeFaces.current().executeScript("PF('addBookReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createBookSectionReference() {

        BookSectionReference bookSection = new BookSectionReference(bookSectionReferenceBean.getAuthor(),
                bookSectionReferenceBean.getTitle(), bookSectionReferenceBean.getDate(),
                bookSectionReferenceBean.getNote(), userBean.getUser(), bookSectionReferenceBean.getAddress(),
                bookSectionReferenceBean.getEdition(), bookSectionReferenceBean.getPublisher(),
                bookSectionReferenceBean.getSeries(), bookSectionReferenceBean.getVolume(),
                bookSectionReferenceBean.getChapter(), bookSectionReferenceBean.getPages());

        if(restReference.addReference(bookSection)){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia de la Sección de libro adicionada", "");

        }else{
          addMessage(FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addBookSectionReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createBookLetReference() {

        BookLetReference bookLet = new BookLetReference(bookLetReferenceBean.getAuthor(),
                bookLetReferenceBean.getTitle(), bookLetReferenceBean.getDate(), bookLetReferenceBean.getNote(),
                userBean.getUser(), bookLetReferenceBean.getAddress(), bookLetReferenceBean.getHowpublished());

        if(restReference.addReference(bookLet)){
           addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Folleto adicionada", "");

        }else{
           addMessage(FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addBookLetReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createThesisReference() {

        ThesisReference thesis = new ThesisReference(thesisReferenceBean.getAuthor(),
                thesisReferenceBean.getTitle(), thesisReferenceBean.getDate(), thesisReferenceBean.getNote(),
                userBean.getUser(), thesisReferenceBean.getAddress(), thesisReferenceBean.getSchool(),
                thesisReferenceBean.getType());

        if(restReference.addReference(thesis)){
           addMessage(FacesMessage.SEVERITY_INFO, "Referencia de la Tesis adicionada", "");

        }else{
            addMessage(FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addThesisReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createConferenceProceedingsReference() {

        ConferenceProceedingsReference proceedings = new ConferenceProceedingsReference(
                conferenceProceedingsReferenceBean.getAuthor(), conferenceProceedingsReferenceBean.getTitle(),
                conferenceProceedingsReferenceBean.getDate(), conferenceProceedingsReferenceBean.getNote(),
                userBean.getUser(), conferenceProceedingsReferenceBean.getAddress(),
                conferenceProceedingsReferenceBean.getSeries(), conferenceProceedingsReferenceBean.getVolume());

        if(restReference.addReference(proceedings)){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Acta de Congreso adicionada", "");

        }else{
            addMessage(FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addConferenceProceedingsReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createConferencePaperReference() {

        ConferencePaperReference paper = new ConferencePaperReference(conferencePaperReferenceBean.getAuthor(),
                conferencePaperReferenceBean.getTitle(), conferencePaperReferenceBean.getDate(),
                conferencePaperReferenceBean.getNote(), userBean.getUser(),
                conferencePaperReferenceBean.getAddress(), conferencePaperReferenceBean.getPages(),
                conferencePaperReferenceBean.getPublisher(), conferencePaperReferenceBean.getVolume());

        if(restReference.addReference(paper)){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Documento de Sesión adicionada", "");

        }else{
           addMessage(FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addConferencePaperReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createWebPageReference() {

        WebPageReference webPage = new WebPageReference(webPageReferenceBean.getAuthor(),
                webPageReferenceBean.getTitle(), webPageReferenceBean.getDate(), webPageReferenceBean.getNote(),
                userBean.getUser(), webPageReferenceBean.getAccessDate(), webPageReferenceBean.getUrl());

        if(restReference.addReference(webPage)){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia de la Página Web adicionada", "");

        }else{
            addMessage(FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addWebPageReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

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
                            if (selectReference.getClass() == ConferenceProceedingsReference.class) {
                                PrimeFaces.current().ajax().update("form:info-conferenceProceedingsReference-content");
                                conferenceProceedingsReferenceBean.copyEdit((ConferenceProceedingsReference) selectReference);
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

    public void delete() {

        if (reference!= null && restReference.deleteReference(reference.getId())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia eliminada", "");
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Existen errores al eliminar la referencia", ""));
        }

        init();
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

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
                            if (selectReference.getClass() == ConferenceProceedingsReference.class) {
                                PrimeFaces.current().ajax().update("form:edit-conferenceProceedingsReference-content");
                                conferenceProceedingsReferenceBean.copyEdit((ConferenceProceedingsReference) selectReference);
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

    public void editArticleReference() {

        ArticleReference articleFinded = (ArticleReference) restReference.getReferenceById(articleReferenceBean.getArticleReference().getId());

        if (articleFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
            if(!restReference.updateReference(articleReferenceBean.getArticleReference())){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Error en el formulario"));
            }
            else{
                addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Artículo actualizada", "");
            }
        }

        init();
        PrimeFaces.current().executeScript("PF('editArticleReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editBookReference() {

       BookReference bookFinded = (BookReference) restReference.getReferenceById(bookReferenceBean.getBookReference().getId());
        if (bookFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
           if (!restReference.updateReference(bookReferenceBean.getBookReference())){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Error en el formulario"));
           }
           else{
               addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Libro actualizada", "");
           }
        }

        init();
        PrimeFaces.current().executeScript("PF('editBookReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editBookLetReference() {

        BookLetReference bookLetFinded = (BookLetReference) restReference.getReferenceById(bookLetReferenceBean.getBookLetReference().getId());
        if (bookLetFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");

        }
        else{
            if (!restReference.updateReference(bookLetReferenceBean.getBookLetReference())){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Error en el formulario"));
            }
            else{
                addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Folleto actualizada", "");
            }
        }

        init();
        PrimeFaces.current().executeScript("PF('editBookLetReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editBookSectionReference() {

        BookSectionReference sectionFinded = (BookSectionReference) restReference.getReferenceById(bookSectionReferenceBean.getBookSectionReference().getId());
        if (sectionFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");

        }
        else{
            if(!restReference.updateReference(bookSectionReferenceBean.getBookSectionReference())){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Error en el formulario"));
            }
            else{
                addMessage(FacesMessage.SEVERITY_INFO, "Referencia de la Sección de libro actualizada", "");
            }
        }

        init();
        PrimeFaces.current().executeScript("PF('editBookSectionReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editConferencePaperReference() {

        ConferencePaperReference paperFinded = (ConferencePaperReference) restReference.getReferenceById(conferencePaperReferenceBean.getConferencePaperReference().getId());

        if (paperFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
            if(!restReference.updateReference(conferencePaperReferenceBean.getConferencePaperReference())){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Error en el formulario"));
            }
            else{
                addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Documento de Sesión actualizada", "");
            }
        }

        init();
        PrimeFaces.current().executeScript("PF('editConferencePaperReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editConferenceProceedingsReference() {

        ConferenceProceedingsReference proceedingsFinded = (ConferenceProceedingsReference) restReference.getReferenceById(conferenceProceedingsReferenceBean.getConferenceProceedingsReference().getId());

        if (proceedingsFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
            if(!restReference.updateReference(conferenceProceedingsReferenceBean.getConferenceProceedingsReference())){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Error en el formulario"));
            }
            else{
                addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Acta de Congreso actualizada", "");
            }
        }

        init();
        PrimeFaces.current().executeScript("PF('editConferenceProceedingsReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editThesisReference() {

        ThesisReference thesisFinded = (ThesisReference) restReference.getReferenceById(thesisReferenceBean.getThesisReference().getId());

        if (thesisFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
            if(!restReference.updateReference(thesisReferenceBean.getThesisReference())){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Error en el formulario"));
            }
            else{
                addMessage(FacesMessage.SEVERITY_INFO, "Referencia de la Tesis actualizada", "");
            }
        }

        init();
        PrimeFaces.current().executeScript("PF('editThesisReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editWebPageReference() {

        WebPageReference webPageFinded = (WebPageReference) restReference.getReferenceById(webPageReferenceBean.getWebPageReference().getId());
        if (webPageFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
            if(!restReference.updateReference(webPageReferenceBean.getWebPageReference())){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Error en el formulario"));
            }
            else {
                addMessage(FacesMessage.SEVERITY_INFO, "Referencia de la Página Web actualizada", "");
            }
        }

        init();
        PrimeFaces.current().executeScript("PF('editWebPageReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void deleteSelectedReference() {

        ArrayList<Integer> idList = new ArrayList<>();
        for(Reference reference: selectReferenceList){
            idList.add(reference.getId());
        }

        if(idList.size() != 0 && restReference.deleteGroupReference(idList)){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencias seleccionadas eliminadas", "");

        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al eliminar la referencia", ""));
        }

        init();
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void exportRis() throws IOException { exportReferenceList(Format.RIS); }

    public void exportBibTex() throws IOException { exportReferenceList(Format.BIBTEX); }

    private void exportReferenceList(Format format) throws IOException {

        Export export = exportFactory.getExport(format);
        export.writeValue((ArrayList<Reference>) selectReferenceList, exportFile.getPath());
        addMessage(FacesMessage.SEVERITY_INFO, "Las Referencias seleccionadas han sido exportadas", "");

        PrimeFaces.current().ajax().update("form:messages");
    }

    public void importReferences() throws IOException, TokenMgrException, ParseException {

        File importFile = new File(path);
        Import importer;

        if(importFile.exists() && importFile.isFile()){
            if(format.equals("Ris")){
                importer = importFactory.getImport(Format.RIS);
            }else{
                importer = importFactory.getImport(Format.BIBTEX);
            }

            ArrayList<Reference> importerReferenceList = importer.readFile(importFile.getPath());
            for (Reference reference: importerReferenceList ) {
                reference.setUser(userBean.getUser());
            }

            if(restReference.addReferenceGroup(importerReferenceList)){
                addMessage(FacesMessage.SEVERITY_INFO, "Referencias Importadas", "");
            }
        }
        else{
            addMessage(FacesMessage.SEVERITY_ERROR, "El fichero seleccionado no es valido", "Debe seleccionar un fichero *.txt");
        }

        init();
        PrimeFaces.current().executeScript("PF('importReferencesDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}

