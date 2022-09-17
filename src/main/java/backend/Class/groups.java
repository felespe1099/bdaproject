package backend.Class;

public class groups {
    public String GroupName;
    public String GroupCategory;
    public int GroupSelected;

    public groups(String pGroupName, String pGroupCategory, int pGroupSelected){
        this.GroupName = pGroupName;
        this.GroupCategory = pGroupCategory;
        this.GroupSelected = pGroupSelected;
    }

    public void setGroupName(String pGroupName){
        this.GroupName = pGroupName;
    }

    public void setGroupCategory(String pGroupCategory){
        this.GroupCategory = pGroupCategory;
    }

    public void setGroupSelected(int pGroupSelected){
        this.GroupSelected = pGroupSelected;
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
