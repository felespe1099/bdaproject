package backend.Class;

public class users {
    public String name;
    public String group;
    public String username;
    public String password;
    public String role;

    public users(String pName, String pGroup, String pUsername, String pPassword, String pRole) {
        this.name = pName;
        this.group = pGroup;
        this.username = pUsername;
        this.password = pPassword;
        this.role = pRole;
    }

    public void setName(String pName) {
        this.name = pName;
    }

    public void setGroup(String pGroup) {
        this.group = pGroup;
    }

    public void setUsername(String pUsername) {
        this.username = pUsername;
    }

    public void setPassword(String pPassword) {
        this.password = pPassword;
    }

    public void setRole(String pRole) {
        this.group = pRole;
    }

    public String getName(){
        return name;
    }

    public String getGroup(){
        return group;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public  String getRole(){
        return role;
    }
}
