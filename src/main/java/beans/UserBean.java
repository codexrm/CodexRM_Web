package beans;

import entity.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ManagedBean
@SessionScoped
public class UserBean {

    private static String username = "";
    private static String email = "";
    private static Set<String> rolesList = new HashSet<>();
    private static String password = "";
    private static String name = "";
    private static String lastName = "";
    private static boolean enabled = false;

    private static User user = new User();

    public String getUsername() { return username; }

    public void setUsername(String username) { UserBean.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { UserBean.email = email; }

    public Set<String> getRolesList() { return rolesList; }

    public void setRolesList(Set<String> rolesList) { UserBean.rolesList = rolesList; }

    public String getPassword() { return password; }

    public void setPassword(String password) { UserBean.password = password; }

    public String getName() { return name; }

    public void setName(String name) { UserBean.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { UserBean.lastName = lastName; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { UserBean.enabled = enabled; }

    public User getUser() { return user; }

    public void setUser(User user) { UserBean.user = user; }

    public void cleanVariables(){
        username = "";
        email = "";
        rolesList.clear();
        password = "";
        name = "";
        lastName = "";
        enabled = false;
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
