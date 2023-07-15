package Auth;

import java.util.Set;

public class SignupRequest {


    private String username;
    private String email;
    private Set<String> role;
    private String password;
    private String name;
    private String lastName;
    private boolean enabled;

    public SignupRequest(String username, String email, Set<String> role, String password, String name, String lastName, boolean enabled) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
