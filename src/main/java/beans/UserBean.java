package beans;

import entity.User;
import rest.RestUser;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UserBean {

    private static String username = "";
    private static String password = "";
    private static User user = new User();
    private static RestUser restUser = new RestUser();

    public String getUsername() { return username; }

    public void setUsername(String username) { UserBean.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { password = password; }

    public User getUser() { return user; }

    public void setUser(User user) { UserBean.user = user; }

    public RestUser getRestUser() { return restUser; }

    public void setRestUser(RestUser restUser) { UserBean.restUser = restUser; }

    public void cleanVariables(){
        username = "";
        password = "";
    }

    public void createUser() {

        User userCodex = new User( password,username);

        if(userCodex != null){

            addMessage(FacesMessage.SEVERITY_INFO, "usuario add", "");
        }else{
            addMessage( FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }
        //PrimeFaces.current().executeScript("PF('addArticleReferenceDialog').hide()");
        //PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void copyEdit(User UserCodex){
        if(UserCodex != null)
            user = new User( UserCodex.getPassword(), UserCodex.getUsername());
    }
    public void editUser() {
        if (user != null){
            //referenceList.remove(thesisReferenceBean.getThesisReference().getId());

           // referenceList.add(thesisReferenceBean.getThesisReference());
            addMessage(FacesMessage.SEVERITY_INFO, "user Update", "");
        }
        else{
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar el usuari", "User Inexistente");
        }

        //PrimeFaces.current().executeScript("PF('editThesisReferenceDialog').hide()");
       // PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void delete() {
        if (user != null) {
            //referenceList.remove(reference);
            addMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminadas", "");
        }
       // PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
