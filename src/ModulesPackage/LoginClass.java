package ModulesPackage;

import java.io.Serializable;

/**
 * Created by benshmuel on 23/11/2017.
 */public class LoginClass implements Serializable{

    private static final long serialVersionUID = 321L;


    private String userEmail;
    private String password;

    public LoginClass() {
    }

    public LoginClass(String username, String password) {
        this.userEmail = username;
        this.password = password;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){

        return getClass().getName() + " Username = " + this.getUserEmail() + " Password = "
                + this.getPassword() ;




    }
}