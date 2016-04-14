package ua.com.bukvashops.pocupon.Entities;

import java.io.Serializable;

/**
 * Created by Igor on 2/16/2016.
 */
public class User implements BaseEntity {
    private static final long serialVersionUID = 6297385302078200511L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private boolean isActive;
    private boolean isAdmin;
    private String shop;



    public User(){}

    public User(int id, String firstName, String lastName, String login, String password, Boolean isActive, Boolean isAdmin, String shop) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        this.shop = shop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {

        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }



    @Override
    public String toString(){
        return "id=" + this.id +
                ", firstName=" + this.firstName +
                ", lastName=" + this.lastName +
                ", login=" + this.login +
                ", password=" + this.password +
                ", isActive=" + this.isActive +
                ", shop=" + this.shop +
                ", isAdmin" + this.isAdmin;

    }
}
