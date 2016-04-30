/**
 *
 * Author:      Patrick Bartholomew
 * Date:        1/29/2016
 * Description: Form that allows new users to sign up for Unite.
 *
 */
package unite.login.beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import secure.unite.beans.SessionState;
import unite.DAO.SignupDAO;

@ManagedBean(name="signup")
@ViewScoped
public class Signup implements Serializable{

    // user input variables
    private String firstname;
    private String lastname;
    private String password;
    private String checkPassword;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;

    // Getters -- Setters
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public boolean checkPW() {
        
        boolean match = password.equals(checkPassword);
        
        if (password.isEmpty()) {
            // password not allowed to be empty
            System.out.println("password attempt was empty");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password field cannot be empty."));
            return false;
        } else {
            
            return match;
        }
    }

    // get data from signup page.
    public String setupNewVolunteer() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean pwvalid = checkPW();
        boolean valid = SignupDAO.CreateUser(firstname, lastname, password, email, phone, address, city, state, zip);

        if (pwvalid) {

            if (valid) {                
                // let user know their account was created.                
                context.addMessage(null, new FacesMessage("Account Created", "You can now log in from the Login page"));                
                return "/index.xhtml?faces-redirect=true";
            } else {
                // don't redirect.  show the user an error message.
                System.out.println("Passwords didn't match");
                return "failure";
            }

        } else {
            // password check failed
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Passwords do not match."));
            System.out.println("password mismatch");
            return "mismatch";
        }
    }

    public String Cancel() {
        System.out.println("admin was logged out from dashboard");
        return "/index.xhtml?faces-redirect=true";
    }

}
