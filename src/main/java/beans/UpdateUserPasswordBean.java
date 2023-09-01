package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UpdateUserPasswordBean {

    private static String currentPassword;
    private static String newPassword;
    private static String confirmationPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        UpdateUserPasswordBean.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        UpdateUserPasswordBean.newPassword = newPassword;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) { UpdateUserPasswordBean.confirmationPassword = confirmationPassword; }

    public void cleanVariables() {
        currentPassword = "";
        newPassword = "";
        confirmationPassword = "";
    }
}
