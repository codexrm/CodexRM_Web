package beans;

import entity.*;
import enums.Format;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import org.primefaces.PrimeFaces;
import payload.Request.TokenRefreshRequest;
import payload.Request.UpdatePasswordRequest;
import payload.Response.AuthenticationDataResponse;
import payload.Response.TokenRefreshResponse;
import rest.Service;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@ManagedBean
@SessionScoped
public class ManagerBean {

    private static UserBean userBean = new UserBean();
    private static ReferenceBean referenceBean = new ReferenceBean();
    private static AuthenticationBean authenticationBean = new AuthenticationBean();
    private static TemplateBean templateBean = new TemplateBean();
    private static AuthenticationDataResponse authenticationData = new AuthenticationDataResponse();
    private static Service service = new Service();

    public UserBean getUserBean() { return userBean; }

    public void setUserBean(UserBean userBean) { ManagerBean.userBean = userBean; }

    public ReferenceBean getReferenceBean() { return referenceBean; }

    public void setReferenceBean(ReferenceBean referenceBean) { ManagerBean.referenceBean = referenceBean; }

    public AuthenticationBean getAuthenticationBean() { return authenticationBean; }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) { ManagerBean.authenticationBean = authenticationBean; }

    public TemplateBean getTemplateBean() { return templateBean; }

    public void setTemplateBean(TemplateBean templateBean) { ManagerBean.templateBean = templateBean; }

    public AuthenticationDataResponse getAuthenticationData() { return authenticationData; }

    public void setAuthenticationData(AuthenticationDataResponse authenticationData) { ManagerBean.authenticationData = authenticationData; }

    public Service getService() { return service; }

    public void setService(Service service) { ManagerBean.service = service; }

    public void initReferenceTable() {
        verificateExpiationDate();
        referenceBean.getReferenceList().clear();
        referenceBean.setReferenceList(service.getAllReference(authenticationData));
    }

    public void initUserTable() {
        verificateExpiationDate();
        userBean.getUserList().clear();
        userBean.setUserList(service.getAllUser(authenticationData.getToken()));
    }

    public void initPreferences() {
        verificateExpiationDate();
        userBean.copyEdit(service.getUserById(authenticationData.getId(), authenticationData.getToken()));
    }

    public void initTemplate() {
        templateBean.init(authenticationData.getRoles());
    }

    // Auth
    public String singin() {
      authenticationData = service.login(authenticationBean.createUserLogin());
      if(authenticationData == null){
          addMessage(FacesMessage.SEVERITY_ERROR, "Usuario no autorizado", "Verifique nombre de usuario y contraseña nuevamente o es posible que el usuario se encuentre deshabilitado");
          PrimeFaces.current().ajax().update("form:messages", "form-log");
          return null;
      }else{
          authenticationBean.cleanVariables();
          initTemplate();
          return "exito";
      }
    }

    public String singout() {
        verificateExpiationDate();
        try {
            if(service.logout(authenticationData.getToken())){
                return "logout";
            } else{
                return null;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void singup() {
        registerUser(service.registerUser(userBean.singup()));
        PrimeFaces.current().ajax().update("form:messages", "form-register");
    }

    // User
    public void createUser() {
        registerUser(service.addUser(userBean.createUser(), authenticationData.getToken()));
        initUserTable();
        PrimeFaces.current().executeScript("PF('addUserDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-user");
    }

    public void deleteUser() {
        verificateExpiationDate();

        if (userBean.getUser() != null && service.deleteUser(userBean.getUser().getId(), authenticationData.getToken())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado", "");
        }
        else{
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al eliminar el usuario", "");
        }

        initUserTable();
        PrimeFaces.current().ajax().update("form:messages", "form:dt-user");
    }

    public void deleteSelectedUser() {
        ArrayList<Integer> idList = new ArrayList<>();
        for(User user: userBean.getSelectUserList()){
            idList.add(user.getId());
        }

        verificateExpiationDate();

        if(idList.size() != 0 && service.deleteUserGroup(idList, authenticationData.getToken())){
            addMessage(FacesMessage.SEVERITY_INFO, "Usuarios seleccionados eliminados", "");

        }else{
           addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al eliminar el usuario", "");
        }

        initUserTable();
        PrimeFaces.current().ajax().update("form:messages", "form:dt-user");
    }

    public void editUser() {
        updateUser();
        if (!service.updateUser(userBean.getUser(), authenticationData.getToken())) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar el usuario", "Error en el formulario");
        } else {
            addMessage(FacesMessage.SEVERITY_INFO, "Usuario actualizado", "");
        }

        initUserTable();
        PrimeFaces.current().executeScript("PF('editUserDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-user");
    }

    public void editPreferences() {
        updateUser();
        if (!service.updatePreferences(userBean.getUser(), authenticationData.getToken())) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar el usuario", "Error en el formulario");
        } else {
            addMessage(FacesMessage.SEVERITY_INFO, "Usuario actualizado", "");
        }
        PrimeFaces.current().ajax().update("form:messages", "form-preferences");
    }

    public void editUserPassword() {
        verificateExpiationDate();
       String message = service.updateUserPassword(userBean.editUserPassword(authenticationData.getId()), authenticationData.getToken());
        switch (message){
            case "Error: Current password incorrect!":
                addMessage(FacesMessage.SEVERITY_ERROR, "La contraseña actual es incorrecta", "");
                break;
            case "Error: New password and confirmation are different!":
                addMessage(FacesMessage.SEVERITY_ERROR, "La nueva contraseña y la confirmación son distintas ", "");
                break;
            case "Updated password!":
                addMessage(FacesMessage.SEVERITY_INFO, "Contraseña Actualizada", "");
                break;
        }
        PrimeFaces.current().executeScript("PF('editUserPasswordDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form-preferences");
    }

    // References
    private void createReference(Reference reference){
        verificateExpiationDate();

        if(service.addReference(authenticationData.getId(), reference, authenticationData.getToken())){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia adicionada", "");

        }else{
            addMessage( FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }
        initReferenceTable();
    }

    public void createArticleReference() {
       createReference(referenceBean.createArticleReference());
       PrimeFaces.current().executeScript("PF('addArticleReferenceDialog').hide()");
       PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createBookReference() {
        createReference(referenceBean.createBookReference());
        PrimeFaces.current().executeScript("PF('addBookReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createBookSectionReference() {
        createReference(referenceBean.createBookSectionReference());
        PrimeFaces.current().executeScript("PF('addBookSectionReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createBookLetReference() {
        createReference(referenceBean.createBookLetReference());
        PrimeFaces.current().executeScript("PF('addBookLetReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createThesisReference() {
        createReference(referenceBean.createThesisReference());
        PrimeFaces.current().executeScript("PF('addThesisReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createConferenceProceedingsReference() {
        createReference(referenceBean.createConferenceProceedingsReference());
        PrimeFaces.current().executeScript("PF('addConferenceProceedingsReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createConferencePaperReference() {
        createReference(referenceBean.createConferencePaperReference());
        PrimeFaces.current().executeScript("PF('addConferencePaperReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void createWebPageReference() {
        createReference(referenceBean.createWebPageReference());
        PrimeFaces.current().executeScript("PF('addWebPageReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void deleteReference() {
        verificateExpiationDate();

        if (referenceBean.getReference() != null && service.deleteReference(referenceBean.getReference().getId(), authenticationData.getToken())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Referencia eliminada", "");
        }
        else{
           addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al eliminar la referencia", "");
        }

        initReferenceTable();
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void deleteSelectedReference() {

        ArrayList<Integer> idList = new ArrayList<>();
        for(Reference reference: referenceBean.getSelectReferenceList()){
            idList.add(reference.getId());
        }

        verificateExpiationDate();

        if(idList.size() != 0 && service.deleteGroupReference(idList, authenticationData.getToken())){
            addMessage(FacesMessage.SEVERITY_INFO, "Referencias seleccionadas eliminadas", "");

        }else{
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al eliminar la referencia", "");
        }

        initReferenceTable();
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    private void editReference(Reference reference){
        verificateExpiationDate();

        Reference referenceFinded = service.getReferenceById(reference.getId(), authenticationData.getToken());

        if (referenceFinded == null){
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Referencia Inexistente");
        }
        else{
            if(!service.updateReference(authenticationData.getId(), reference, authenticationData.getToken())){
                addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar la referencia", "Error en el formulario");
            }
            else{
                addMessage(FacesMessage.SEVERITY_INFO, "Referencia actualizada", "");
            }
        }
        initReferenceTable();
    }

    public void editArticleReference() {
        editReference(referenceBean.getArticleReferenceBean().getArticleReference());
        PrimeFaces.current().executeScript("PF('editArticleReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editBookReference() {
        editReference(referenceBean.getBookReferenceBean().getBookReference());
        PrimeFaces.current().executeScript("PF('editBookReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editBookLetReference() {
        editReference(referenceBean.getBookLetReferenceBean().getBookLetReference());
        PrimeFaces.current().executeScript("PF('editBookLetReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editBookSectionReference() {
        editReference(referenceBean.getBookSectionReferenceBean().getBookSectionReference());
        PrimeFaces.current().executeScript("PF('editBookSectionReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editConferencePaperReference() {
        editReference(referenceBean.getConferencePaperReferenceBean().getConferencePaperReference());
        PrimeFaces.current().executeScript("PF('editConferencePaperReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editConferenceProceedingsReference() {
        editReference(referenceBean.getConferenceProceedingsReferenceBean().getConferenceProceedingsReference());
        PrimeFaces.current().executeScript("PF('editConferenceProceedingsReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editThesisReference() {
        editReference(referenceBean.getThesisReferenceBean().getThesisReference());
        PrimeFaces.current().executeScript("PF('editThesisReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void editWebPageReference() {
        editReference(referenceBean.getWebPageReferenceBean().getWebPageReference());
        PrimeFaces.current().executeScript("PF('editWebPageReferenceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void exportRis() throws IOException { exportReferenceList(Format.RIS); }

    public void exportBibTex() throws IOException { exportReferenceList(Format.BIBTEX); }

    private void exportReferenceList(Format format) throws IOException {
        referenceBean.exportReferenceList(format);
        addMessage(FacesMessage.SEVERITY_INFO, "Las Referencias seleccionadas han sido exportadas", "");
        PrimeFaces.current().ajax().update("form:messages");
    }

    public void importReferences() throws IOException, TokenMgrException, ParseException {
        ArrayList<Reference> importerReferenceList = referenceBean.importReferences();
        if(importerReferenceList != null){
            verificateExpiationDate();
            if(service.addReferenceGroup(authenticationData.getId(), importerReferenceList, authenticationData.getToken())){
                addMessage(FacesMessage.SEVERITY_INFO, "Referencias Importadas", "");
            }
        } else{
            addMessage(FacesMessage.SEVERITY_ERROR, "El fichero seleccionado no es valido", "Debe seleccionar un fichero *.txt");
        }

        initReferenceTable();
        PrimeFaces.current().executeScript("PF('importReferencesDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    private void registerUser(String message) {
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
    }

    private void updateUser() {

        verificateExpiationDate();

        User userFinded = service.getUserById(userBean.getUser().getId(), authenticationData.getToken());

        if (userFinded == null) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar el usuario", "Usuario Inexistente");
        }
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
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

