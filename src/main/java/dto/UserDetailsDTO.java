package dto;

import java.util.List;

public class UserDetailsDTO {

    private Integer id;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private boolean enabled;
    private List<String> roles;

    public UserDetailsDTO() {}

    public UserDetailsDTO(Integer id, String username, String name, String lastName, String email, boolean enabled) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public List<String> getRoles() { return roles; }

    public void setRoles(List<String> roles) { this.roles = roles; }

    public void setRol(String rol) { this.roles.add(rol); }
}
