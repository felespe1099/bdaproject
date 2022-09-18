package backend.Classes;

public class users {
    public String name;
    public String group;
    public String username;
    public String password;
    public String role;

    public users(){}

    public users(String pName, String pGroup, String pUsername, String pPassword, String pRole) {
        this.name = pName;
        this.group = pGroup;
        this.username = pUsername;
        this.password = pPassword;
        this.role = pRole;
    }
    public void setName(String pName) {
        name = pName;
    }

    public void setGroup(String pGroup) {
        group = pGroup;
    }

    public void setUsername(String pUsername) {
        username = pUsername;
    }

    public void setPassword(String pPassword) {
        password = pPassword;
    }

    public void setRole(String pRole) {
        role = pRole;
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
