package beans;

import Auth.*;
import entity.*;
import enums.Format;
import model.ExportR;
import model.ImportR;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import org.primefaces.PrimeFaces;
import rest.Service;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    private static AuthenticationBean authenticationBean = new AuthenticationBean();
    private static UserBean userBean = new UserBean();

    private static ExportR exportR = new ExportR();
    private static ImportR importR = new ImportR();

    private static AuthenticationData authenticationData = new AuthenticationData();
    private static Service service = new Service();

    private static File exportFile = new File(System.getProperty("user.home") + File. separator +"Downloads" + File. separator + "exported References.txt" );
    private static String path = "";
    private static String format = "";
    private static LocalDate ActualDate = LocalDate.now();

    public void init() {
        verificateExpiationDate();
        referenceList.clear();
        referenceList = service.getAllReference(authenticationData);
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

    public AuthenticationBean getAuthenticationBean() { return authenticationBean; }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) { ReferenceManagerBean.authenticationBean = authenticationBean; }

    public UserBean getUserBean() { return userBean; }

    public void setUserBean(UserBean userBean) { ReferenceManagerBean.userBean = userBean; }

    public AuthenticationData getAuthenticationData() { return authenticationData; }

    public void setAuthenticationData(AuthenticationData authenticationData) { ReferenceManagerBean.authenticationData = authenticationData; }

    public Service getService() { return service; }

    public void setService(Service service) { ReferenceManagerBean.service = service; }

    public File getExportFile() { return exportFile; }

    public void setExportFile(File exportFile) { ReferenceManagerBean.exportFile = exportFile; }

    public String getFormat() { return format; }

    public void setFormat(String format) { ReferenceManagerBean.format = format; }

    public String getPath() { return path; }

    public void setPath(String path) { ReferenceManagerBean.path = path; }

    public ExportR getExportR() { return exportR; }

    public void setExportR(ExportR exportR) { ReferenceManagerBean.exportR = exportR; }

    public ImportR getImportR() { return importR; }

    public void setImportR(ImportR importR) { ReferenceManagerBean.importR = importR; }

    public LocalDate getActualDate() { return ActualDate; }

    public void setActualDate(LocalDate actualDate) { ActualDate = actualDate; }


    // User
    public String singin() {
      authenticationData = service.login(new UserLogin(authenticationBean.getUsername(), authenticationBean.getPassword()));
      if(authenticationData == null){
          addMessage(FacesMessage.SEVERITY_ERROR, "Usuario no autorizado", "Verifique ususario y contraseña nuevamente");
          PrimeFaces.current().ajax().update("form:messages", "form-log");
          return null;
      }else{
          authenticationBean.cleanVariables();
          return "exito";
      }
    }

    public void singout() {
        try {
            service.logout(authenticationData.getToken());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void singup() {
        userBean.getRolesList().add("user");
        SignupRequest user = new SignupRequest(userBean.getUsername(), userBean.getEmail(), userBean.getRolesList(), userBean. getPassword(), userBean.getName(), userBean.getLastName(), true);
        String message = service.registerUser(user);
        userBean.cleanVariables();

        switch (message){
            case "Error: Username is already taken!":
                addMessage(FacesMessage.SEVERITY_ERROR, "El nombre de ususario ya esta tomado", "");
                break;
            case "Error: Email is already in use!":
                addMessage(FacesMessage.SEVERITY_ERROR, "El E-mail ya esta en uso ", "");
                break;
            case "User registered successfully!":
                addMessage(FacesMessage.SEVERITY_INFO, "Usuario registrado satisfactoriamente", "");
                break;
        }
        PrimeFaces.current().ajax().update("form:messages", "form-register");

    }



    // References
    public void createArticleReference() {

        ArticleReference article = new ArticleReference(articleReferenceBean.getTitle(), articleReferenceBean.getYear(), articleReferenceBean.getMonth(), articleReferenceBean.getNote(), articleReferenceBean.getAuthor(), articleReferenceBean.getJournal(),
                articleReferenceBean.getVolume(), articleReferenceBean.getNumber(), articleReferenceBean.getPages(), articleReferenceBean.getIssn());

        verificateExpiationDate();

        if(service.addReference(authenticationData.getId(), article, authenticationData.getToken())){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Artículo adicionada", "");

        }else{
            addMessage( FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addArticleReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createBookReference() {

        BookReference book = new BookReference(bookReferenceBean.getTitle(), bookReferenceBean.getYear(), bookReferenceBean.getMonth(), bookReferenceBean.getNote(), bookReferenceBean.getAuthor(), bookReferenceBean.getEditor(), bookReferenceBean.getPublisher(),
                bookReferenceBean.getVolume(), bookReferenceBean.getNumber(), bookReferenceBean.getSeries(), bookReferenceBean.getAddress(), bookReferenceBean.getEdition(), bookReferenceBean.getIsbn());

        verificateExpiationDate();

        if (service.addReference(authenticationData.getId(), book, authenticationData.getToken())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia de Libro adicionada", "");

        } else {
           addMessage(FacesMessage.SEVERITY_ERROR, "Existe error en el formulario", "");
        }

        init();
        PrimeFaces.current().executeScript("PF('addBookReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createBookSectionReference() {

        BookSectionReference bookSection = new BookSectionReference(bookSectionReferenceBean.getTitle(), bookSectionReferenceBean.getYear(), bookSectionReferenceBean.getMonth(), bookSectionReferenceBean.getNote(), bookSectionReferenceBean.getAuthor(),
                bookSectionReferenceBean.getEditor(), bookSectionReferenceBean.getPublisher(), bookSectionReferenceBean.getVolume(), bookSectionReferenceBean.getNumber(), bookSectionReferenceBean.getSeries(), bookSectionReferenceBean.getAddress(),
                bookSectionReferenceBean.getEdition(), bookSectionReferenceBean.getIsbn(), bookSectionReferenceBean.getChapter(), bookSectionReferenceBean.getPages(), bookSectionReferenceBean.getType());

        verificateExpiationDate();

        if(service.addReference(authenticationData.getId(), bookSection, authenticationData.getToken())){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia de la Sección de libro adicionada", "");

        }else{
          addMessage(FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addBookSectionReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createBookLetReference() {

        BookLetReference bookLet = new BookLetReference(bookLetReferenceBean.getTitle(), bookLetReferenceBean.getYear(), bookLetReferenceBean.getMonth(), bookLetReferenceBean.getNote(), bookLetReferenceBean.getAuthor(), bookLetReferenceBean.getHowpublished(),
                bookLetReferenceBean.getAddress());

        verificateExpiationDate();

        if(service.addReference(authenticationData.getId(), bookLet, authenticationData.getToken())){
           addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Folleto adicionada", "");

        }else{
           addMessage(FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addBookLetReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createThesisReference() {

        ThesisReference thesis = new ThesisReference(thesisReferenceBean.getTitle(), thesisReferenceBean.getYear(), thesisReferenceBean.getMonth(), thesisReferenceBean.getNote(), thesisReferenceBean.getAuthor(), thesisReferenceBean.getSchool(),
                thesisReferenceBean.getType(), thesisReferenceBean.getAddress());

        verificateExpiationDate();

        if(service.addReference(authenticationData.getId(), thesis, authenticationData.getToken())){
           addMessage(FacesMessage.SEVERITY_INFO, "Referencia de la Tesis adicionada", "");

        }else{
            addMessage(FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addThesisReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createConferenceProceedingsReference() {

        ConferenceProceedingReference proceedings = new ConferenceProceedingReference(conferenceProceedingsReferenceBean.getTitle(), conferenceProceedingsReferenceBean.getYear(), conferenceProceedingsReferenceBean.getMonth(),
                conferenceProceedingsReferenceBean.getNote(), conferenceProceedingsReferenceBean.getEditor(), conferenceProceedingsReferenceBean.getVolume(), conferenceProceedingsReferenceBean.getNumber(), conferenceProceedingsReferenceBean.getSeries(),
                conferenceProceedingsReferenceBean.getAddress(), conferenceProceedingsReferenceBean.getPublisher(), conferenceProceedingsReferenceBean.getIsbn(), conferenceProceedingsReferenceBean.getOrganization());

        verificateExpiationDate();

        if(service.addReference(authenticationData.getId(), proceedings, authenticationData.getToken())){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Acta de Congreso adicionada", "");

        }else{
            addMessage(FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addConferenceProceedingsReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createConferencePaperReference() {

        ConferencePaperReference paper = new ConferencePaperReference(conferencePaperReferenceBean.getTitle(), conferencePaperReferenceBean.getYear(), conferencePaperReferenceBean.getMonth(), conferencePaperReferenceBean.getNote(),
                conferencePaperReferenceBean.getAuthor(), conferencePaperReferenceBean.getBookTitle(), conferencePaperReferenceBean.getEditor(), conferencePaperReferenceBean.getNumber(), conferencePaperReferenceBean.getSeries(),
                conferencePaperReferenceBean.getPublisher(), conferencePaperReferenceBean.getVolume(), conferencePaperReferenceBean.getAddress(), conferencePaperReferenceBean.getPages(), conferencePaperReferenceBean.getOrganization());

        verificateExpiationDate();

        if(service.addReference(authenticationData.getId(), paper, authenticationData.getToken())){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia del Documento de Sesión adicionada", "");

        }else{
           addMessage(FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }

        init();
        PrimeFaces.current().executeScript("PF('addConferencePaperReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createWebPageReference() {

        WebPageReference webPage = new WebPageReference(webPageReferenceBean.getTitle(), webPageReferenceBean.getYear(), webPageReferenceBean.getMonth(), webPageReferenceBean.getNote(), webPageReferenceBean.getAuthor(), webPageReferenceBean.getUrl());

        verificateExpiationDate();

        if(service.addReference(authenticationData.getId(), webPage, authenticationData.getToken())){
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

    public String infoThesisType(String type) {
        switch (type){
            case "MASTERS":
                return "Maestría";
            case "PHD":
                return "Doctorado";
            default:
                return "";
        }
    }

    public String infoBookSectionType(String type) {
        switch (type){
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

    public void delete() {

        verificateExpiationDate();

        if (reference!= null && service.deleteReference(reference.getId(), authenticationData.getToken())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia eliminada", "");
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Existen errores al eliminar la referencia", ""));
        }

        init();
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void deleteSelectedReference() {

        ArrayList<Integer> idList = new ArrayList<>();
        for(Reference reference: selectReferenceList){
            idList.add(reference.getId());
        }

        verificateExpiationDate();

        if(idList.size() != 0 && service.deleteGroupReference(idList, authenticationData.getToken())){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencias seleccionadas eliminadas", "");

        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al eliminar la referencia", ""));
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

    public void editArticleReference() {

        verificateExpiationDate();

        ArticleReference articleFinded = (ArticleReference) service.getReferenceById(articleReferenceBean.getArticleReference().getId(), authenticationData.getToken());

        if (articleFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
            if(!service.updateReference(authenticationData.getId(), articleReferenceBean.getArticleReference(), authenticationData.getToken())){
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

        verificateExpiationDate();

       BookReference bookFinded = (BookReference) service.getReferenceById(bookReferenceBean.getBookReference().getId(), authenticationData.getToken());
        if (bookFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
           if (!service.updateReference(authenticationData.getId(), bookReferenceBean.getBookReference(), authenticationData.getToken())){
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

        verificateExpiationDate();

        BookLetReference bookLetFinded = (BookLetReference) service.getReferenceById(bookLetReferenceBean.getBookLetReference().getId(), authenticationData.getToken());
        if (bookLetFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");

        }
        else{
            if (!service.updateReference(authenticationData.getId(), bookLetReferenceBean.getBookLetReference(), authenticationData.getToken())){
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

        verificateExpiationDate();

        BookSectionReference sectionFinded = (BookSectionReference) service.getReferenceById(bookSectionReferenceBean.getBookSectionReference().getId(),authenticationData.getToken());
        if (sectionFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");

        }
        else{
            if(!service.updateReference(authenticationData.getId(), bookSectionReferenceBean.getBookSectionReference(), authenticationData.getToken())){
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

        verificateExpiationDate();

        ConferencePaperReference paperFinded = (ConferencePaperReference) service.getReferenceById(conferencePaperReferenceBean.getConferencePaperReference().getId(), authenticationData.getToken());

        if (paperFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
            if(!service.updateReference(authenticationData.getId(), conferencePaperReferenceBean.getConferencePaperReference(), authenticationData.getToken())){
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

        verificateExpiationDate();

        ConferenceProceedingReference proceedingsFinded = (ConferenceProceedingReference) service.getReferenceById(conferenceProceedingsReferenceBean.getConferenceProceedingsReference().getId(), authenticationData.getToken());

        if (proceedingsFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
            if(!service.updateReference(authenticationData.getId(), conferenceProceedingsReferenceBean.getConferenceProceedingsReference(), authenticationData.getToken())){
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

        verificateExpiationDate();

        ThesisReference thesisFinded = (ThesisReference) service.getReferenceById(thesisReferenceBean.getThesisReference().getId(), authenticationData.getToken());

        if (thesisFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
            if(!service.updateReference(authenticationData.getId(), thesisReferenceBean.getThesisReference(), authenticationData.getToken())){
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

        verificateExpiationDate();

        WebPageReference webPageFinded = (WebPageReference) service.getReferenceById(webPageReferenceBean.getWebPageReference().getId(), authenticationData.getToken());
        if (webPageFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
            if(!service.updateReference(authenticationData.getId(), webPageReferenceBean.getWebPageReference(), authenticationData.getToken())){
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

    public void exportRis() throws IOException {
        exportReferenceList(Format.RIS);
    }

    public void exportBibTex() throws IOException {
        exportReferenceList(Format.BIBTEX);
    }

   private void exportReferenceList(Format format) throws IOException {

        exportR.exportReferenceList(exportFile, (ArrayList<Reference>) selectReferenceList, format);
        addMessage(FacesMessage.SEVERITY_INFO, "Las Referencias seleccionadas han sido exportadas", "");

        PrimeFaces.current().ajax().update("form:messages");
    }

    public void importReferences() throws IOException, TokenMgrException, ParseException {

        File importFile = new File(path);
        if(importFile.exists() && importFile.isFile()){
            ArrayList<Reference> importerReferenceList = importR.importReferences(path,format);

            verificateExpiationDate();

            if(service.addReferenceGroup(authenticationData.getId(), importerReferenceList, authenticationData.getToken())){
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

    private void verificateExpiationDate() {
        if(authenticationData.getTokenExpirationDate().before(new Date())){
            try {
                refreshToken();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void refreshToken() throws IOException {
        TokenRefreshResponse response = service.refreshToken(new TokenRefreshRequest(authenticationData.getRefreshToken()));
        authenticationData.setToken(response.getTokenType()  + " " + response.getAccessToken());
        authenticationData.setRefreshToken(response.getRefreshToken());
        authenticationData.setTokenExpirationDate(response.getTokenExpirationDate());
    }

}

