package payload.Request;

public class UpdatePasswordRequest {

    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
    private Integer userId;

    public UpdatePasswordRequest() { }

    public UpdatePasswordRequest(String newPassword, String currentPassword, String confirmationPassword, Integer userId) {
        this.newPassword = newPassword;
        this.currentPassword = currentPassword;
        this.confirmationPassword = confirmationPassword;
        this.userId = userId;
    }

    public String getNewPassword() { return newPassword; }

    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

    public String getCurrentPassword() { return currentPassword; }

    public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }

    public String getConfirmationPassword() { return confirmationPassword; }

    public void setConfirmationPassword(String confirmationPassword) { this.confirmationPassword = confirmationPassword; }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }
}
