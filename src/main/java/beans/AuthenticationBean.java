package beans;

import payload.Request.UserLoginRequest;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AuthenticationBean {

    private static String username = "";
    private static String password = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        AuthenticationBean.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        AuthenticationBean.password = password;
    }

    public void cleanVariables() {
        username = "";
        password = "";
    }

    public UserLoginRequest createUserLogin() {
        return new UserLoginRequest(username, password);
    }
}
