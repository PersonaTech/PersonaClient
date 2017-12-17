package ModulesPackage;

import java.io.Serializable;

/**
 * Created by benshmuel on 03/12/2017.
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 1113L;

    private String uId;

    private String userType;
    private String name;
    private String id;
    private int age;
    private String address;
    private String phoneNumber;
    private String county;


    public Employee() {
    }

    public Employee(String id, String userType, int age, String name , String address , String phoneNumber , String county) {
        this.uId = null;
        this.id = id;
        this.userType = userType;
        this.age = age;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.county = county;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {

        return getClass().getName() + " Employee name = " + this.getName() + " Employee UserType = "
                + this.getUserType() + " Employee id = " + this.getId() + " Age = " + this.getAge()
                + " Address = " + this.getAddress() + " Phone number = " + this.getPhoneNumber()
                + " County = " + this.getCounty()
                ;

    }
}
