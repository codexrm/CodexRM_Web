package beans;

import entity.User;
import payload.Request.AddUserRequest;
import payload.Request.SignupRequest;
import payload.Request.UpdatePasswordRequest;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class UserBean {

    private static List<User> userList = new ArrayList<>();
    private static User user = new User();
    private static List<User> selectUserList = new ArrayList<>();

    private static String username = "";
    private static String email = "";
    private static List<String> roles = new ArrayList<>();
    private static String password = "";
    private static String name = "";
    private static String lastName = "";
    private static boolean enabled = false;

    private static UpdateUserPasswordBean updateUserPasswordBean = new UpdateUserPasswordBean();

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

    public String getName() { return name; }

    public void setName(String name) { UserBean.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { UserBean.lastName = lastName; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { UserBean.enabled = enabled; }

    public UpdateUserPasswordBean getUpdateUserPasswordBean() { return updateUserPasswordBean; }

    public void setUpdateUserPasswordBean(UpdateUserPasswordBean updateUserPasswordBean) { UserBean.updateUserPasswordBean = updateUserPasswordBean; }

    public void cleanVariables() {
        username = "";
        email = "";
        roles.clear();
        password = "";
        name = "";
        lastName = "";
        enabled = false;
    }

    public void copyEdit(User userCodex) {
        if (userCodex != null)
            user = new User(userCodex.getId(), userCodex.getUsername(), userCodex.getName(), userCodex.getLastName(), userCodex.getEmail(), userCodex.isEnabled(), userCodex.getPassword(), userCodex.getRoles());
    }

    public SignupRequest singup() {
        return new SignupRequest(username, email, password, name, lastName, true);
    }

    public AddUserRequest createUser() { return new AddUserRequest(username, email, password, name, lastName, enabled, roles); }

    public UpdatePasswordRequest editUserPassword() { return new UpdatePasswordRequest(updateUserPasswordBean.getNewPassword(), updateUserPasswordBean.getCurrentPassword(), updateUserPasswordBean.getConfirmationPassword()); }

    public String translateEstado(boolean text) {
        return (text) ? "habilitado" : "deshabilitado";
    }

    public String translateColorEstado(boolean text) {
        return (text) ? "green" : "red";
    }

    public String roleInfo() {

        List<String> roleList = new ArrayList<>();
        for (String role : user.getRoles()) {
            switch (role) {
                case "ROLE_ADMIN":
                    roleList.add("Administrador");
                    break;

                case "ROLE_MANAGER":
                    roleList.add("Directivo");
                    break;

                case "ROLE_AUDITOR":
                    roleList.add("Auditor");
                    break;

                case "ROLE_USER":
                    roleList.add("Usuario");
                    break;

                default:
            }
        }
        StringBuilder roleInfo = new StringBuilder();
        for (int i = 0; i < roleList.size(); i++) {

            if (i != 0) {
                roleInfo.append(",");
            }
            roleInfo.append(roleList.get(i));
        }
        return roleInfo.toString();
    }
}
