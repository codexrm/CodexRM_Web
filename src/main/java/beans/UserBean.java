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
    private static String name = "";
    private static String lastName = "";
    private static String email = "";
    private static boolean enabled = false;
    private static String password = "";
    private static User user = new User();
    private static RestUser restUser = new RestUser();


    public String getUsername() { return username; }

    public void setUsername(String username) { UserBean.username = username; }

    public String getName() { return name; }

    public void setName(String name) { UserBean.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { UserBean.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { UserBean.email = email; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { UserBean.enabled = enabled; }

    public String getPassword() { return password; }

    public void setPassword(String password) { UserBean.password = password; }

    public User getUser() { return user; }

    public void setUser(User user) { UserBean.user = user; }

    public RestUser getRestUser() { return restUser; }

    public void setRestUser(RestUser restUser) { UserBean.restUser = restUser; }

    public void cleanVariables(){
        username = "";
        name = "";
        lastName = "";
        email = "";
        enabled = false;
        password = "";
    }

    public void createUser() {

        User userCodex = new User( username, name, lastName, email, enabled, password);

        if(userCodex != null){

            addMessage(FacesMessage.SEVERITY_INFO, "Usuario adicionado", "");
        }else{
            addMessage( FacesMessage.SEVERITY_ERROR,"Existe error en el formulario","");
        }
        //PrimeFaces.current().executeScript("PF('addArticleReferenceDialog').hide()");
        //PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void copyEdit(User UserCodex){
        if(UserCodex != null)
            user = new User( UserCodex.getUsername(), UserCodex.getName(), UserCodex.getLastName(), UserCodex.getEmail(), UserCodex.isEnabled(), UserCodex.getPassword());
    }
    public void editUser() {
        if (user != null){
            //referenceList.remove(thesisReferenceBean.getThesisReference().getId());

           // referenceList.add(thesisReferenceBean.getThesisReference());
            addMessage(FacesMessage.SEVERITY_INFO, "Usuario Actualizado", "");
        }
        else{
            addMessage(FacesMessage.SEVERITY_ERROR, "Existen errores al editar el usuario", "Usuario Inexistente");
        }

        //PrimeFaces.current().executeScript("PF('editThesisReferenceDialog').hide()");
       // PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    public void delete() {
        if (user != null) {
            //referenceList.remove(reference);
            addMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado", "");
        }
       // PrimeFaces.current().ajax().update("form:messages", "form:dt-reference");
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
