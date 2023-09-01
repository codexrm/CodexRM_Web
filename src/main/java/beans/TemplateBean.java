package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class TemplateBean {

    private static List<String> roles = new ArrayList<>();

    private boolean role_admin;
    private boolean role_user;
    private boolean role_manager;
    private boolean role_auditor;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        TemplateBean.roles = roles;
    }

    public boolean isRole_admin() {
        return role_admin;
    }

    public void setRole_admin(boolean role_admin) {
        this.role_admin = role_admin;
    }

    public boolean isRole_user() {
        return role_user;
    }

    public void setRole_user(boolean role_user) {
        this.role_user = role_user;
    }

    public boolean isRole_manager() {
        return role_manager;
    }

    public void setRole_manager(boolean role_manager) {
        this.role_manager = role_manager;
    }

    public boolean isRole_auditor() {
        return role_auditor;
    }

    public void setRole_auditor(boolean role_auditor) {
        this.role_auditor = role_auditor;
    }

    public void init(List<String> roleList) {
        cleanVariable();
        setRoles(roleList);
        verifyRol();
    }

    private void cleanVariable() {
        roles.clear();
        role_admin = false;
        role_auditor = false;
        role_manager = false;
        role_user = false;
    }

    private void verifyRol() {
        for (String r : roles) {
            switch (r) {
                case "ROLE_ADMIN":
                    role_admin = true;
                    break;
                case "ROLE_MANAGER":
                    role_manager = true;
                    break;
                case "ROLE_AUDITOR":
                    role_auditor = true;
                    break;
                case "ROLE_USER":
                    role_user = true;
            }
        }
    }

    public boolean translateRole_Boolean(boolean... r) {
        boolean t = false;
        for (boolean b : r) {
            t = t || b;
        }
        return t;
    }
}
