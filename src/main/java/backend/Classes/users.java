package backend.Classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"id","revision"})

public class users {
    @JsonProperty("_id")
    public String id;

    @JsonProperty("_rev")
    public String revision;

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

    public void setId(String pID){id = pID;}
    public String getId(){return id;}

    public void setRevision(String pRevision){revision = pRevision;}
    public String getRevision(){return revision;}
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
