package backend.Classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({"id","revision"})

public class groups {
    @JsonProperty("_id")
    public String id;

    @JsonProperty("_rev")
    public String revision;
    public String GroupName;
    public String GroupCategory;
    public int GroupSelected;

    public groups() {}

    public groups(String pGroupName, String pGroupCategory, int pGroupSelected){
        this.GroupName = pGroupName;
        this.GroupCategory = pGroupCategory;
        this.GroupSelected = pGroupSelected;
    }

    public void setId(String pID){id = pID;}
    public String getId(){return id;}

    public void setRevision(String pRevision){revision = pRevision;}
    public String getRevision(){return revision;}

    public void setGroupName(String pGroupName){
        GroupName = pGroupName;
    }

    public void setGroupCategory(String pGroupCategory){
        GroupCategory = pGroupCategory;
    }

    public void setGroupSelected(int pGroupSelected){
        GroupSelected = pGroupSelected;
    }

    public String getGroupName(){
        return GroupName;
    }

    public String getGroupCategory(){
        return GroupCategory;
    }

    public int getGroupSelected(){
        return  GroupSelected;
    }
}
