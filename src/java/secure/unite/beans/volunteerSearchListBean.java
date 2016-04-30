
/*
 * Author:      Patrick Bartholomew
 * Date:        2/24/2016
 * Modified:    2/27/2016
 * Description: This bean is repsonsible for getters/setters on the Adminstrator's task list.
 *
 */

package secure.unite.beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import secure.unite.entities.Group;

@ManagedBean(name = "searchBean")
@ViewScoped
public class volunteerSearchListBean implements Serializable {
    
    private List<Group> searchList;
    private Group group;    
    
    @ManagedProperty("#{searchService}")
    private Search search;    

    @PostConstruct
    public void init() {
        // generate the list for the xhtml
        searchList = search.searchGroups();
    }  
    
    public void updateList(){
        searchList = search.searchGroups();
    }
    
    public List<Group> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Group> searchList) {
        this.searchList = searchList;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setSearch(Search search) {
        this.search = search;
    }  
    
    
}
