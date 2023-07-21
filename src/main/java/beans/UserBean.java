package beans;

import entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.*;

@ManagedBean
@SessionScoped
public class UserBean {

    private static List<User> userList = new ArrayList<User>();
    private static User user = new User();
    private static List<User> selectUserList = new ArrayList<User>();

    private static String username = "";
    private static String email = "";
    private static List<String> roles = new ArrayList<>();
    private static String password = "";
    private static String name = "";
    private static String lastName = "";
    private static boolean enabled = false;

    public List<User> getUserList() { return userList; }

    public void setUserList(List<User> userList) { UserBean.userList = userList; }

    public User getUser() { return user; }

    public void setUser(User user) { UserBean.user = user; }

    public List<User> getSelectUserList() { return selectUserList; }

    public void setSelectUserList(List<User> selectUserList) { UserBean.selectUserList = selectUserList; }

    public String getUsername() { return username; }

    public void setUsername(String username) { UserBean.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { UserBean.email = email; }

    public List<String> getRoles() { return roles; }

    public void setRoles(List<String> roles) { UserBean.roles = roles; }

    public String getPassword() { return password; }

    public void setPassword(String password) { UserBean.password = password; }

    public String getName(){ return name; }

    public void setName(String name) { UserBean.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { UserBean.lastName = lastName; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { UserBean.enabled = enabled; }

    public void cleanVariables(){
        username = "";
        email = "";
        roles.clear();
        password = "";
        name = "";
        lastName = "";
        enabled = false;
    }

    public void copyEdit(User userCodex){
        if(userCodex != null)
            user = new User(userCodex.getId(), userCodex.getUsername(), userCodex.getName(), userCodex.getLastName(), userCodex.getEmail(), userCodex.isEnabled(), userCodex.getPassword(), userCodex.getRoles());
    }

    public String translateEstado(boolean text){ return (text)? "habilitado" : "deshabilitado"; }

    public String translateColorEstado(boolean text){ return (text)? "green" : "red"; }

    public List<String> roleInfo(){

        List<String> RoleInfo = new ArrayList<>();
        for(String role: user.getRoles()){
            switch (role) {
                case "ROLE_ADMIN":
                    RoleInfo.add("Administrador");
                    break;

                case "ROLE_MANAGER":
                    RoleInfo.add("Gerente");
                    break;

                case "ROLE_AUDITOR":
                    RoleInfo.add("Auditor");
                    break;

                default:
                    RoleInfo.add("Usuario");
            }
        }
        return RoleInfo;
    }

    public Set<String> assignRole(){
        Set<String> rolesAdd = new HashSet<>();

        for(String role: roles){
            switch (role) {
                case "ROLE_ADMIN":
                    rolesAdd.add("admin");
                    break;

                case "ROLE_MANAGER":
                    rolesAdd.add("manager");
                    break;

                case "ROLE_AUDITOR":
                    rolesAdd.add("auditor");
                    break;

                default:
                    rolesAdd.add("user");
            }
        }
        return rolesAdd;
    }
}
