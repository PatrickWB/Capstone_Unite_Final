/**
 *
 * @author Patrick
 */
package unite.login.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;
import unite.login.beans.DataConnect;
import secure.unite.entities.Group;

@ManagedBean(name = "publicSearchService")
@ApplicationScoped
public class PublicSearch implements Serializable {

    @NotNull
    private String searchInput;
    @NotNull
    private int zip;

    public int getZip() {

        return zip;
    }

    public void setZip(int zipcode) {
        this.zip = zipcode;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String input) {
        this.searchInput = input;
    }

    // search group results
    public List<Group> searchGroups() {

        List<Group> list = new ArrayList<Group>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DataConnect.getConnection();
            // get the admin tasks for the admin to display on their dashboard
            String query = "SELECT * FROM  VolunteeringGroupInformationTable WHERE V_Group_Name LIKE ? AND V_Zip = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, searchInput);
            ps.setInt(2, zip);

            rs = ps.executeQuery();

            while (rs.next()) {
                Group group = new Group();
                group.setG_id(rs.getInt("V_Group_ID"));
                group.setG_name(rs.getString("V_Group_Name"));
                group.setG_active(rs.getInt("V_isActive"));
                group.setG_description(rs.getString("V_Desc"));
                group.setG_address(rs.getString("V_Address"));
                group.setG_city(rs.getString("V_City"));
                group.setG_state(rs.getString("V_State"));
                group.setG_zip(rs.getInt("V_Zip"));
                group.setG_category(rs.getString("V_Group_Category"));
                group.setCreatedDate(rs.getTimestamp("CreatedDate"));
                list.add(group);
            }

        } catch (Exception ex) {
            System.out.println("search Error (Search Bean) --> " + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return list;
    }

    public String publicSearch() {
        return "search";
    }

}
