package ua.com.bukvashops.pocupon.Entities;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Igor on 18-Mar-16.
 */
public class Certificate implements BaseEntity {
    private int id;
    private String code;
    private boolean isActive;
    private Date dateOfApplication;
    private String comment;
    private String usedBy;

    public Certificate() {
    }

    public Certificate(int id, String code, boolean isActive, Date dateOfApplication, String comment, String usedBy) {
        this.id = id;
        this.code = code;
        this.isActive = isActive;
        this.dateOfApplication = dateOfApplication;
        this.comment = comment;
        this.usedBy = usedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getDateOfApplication() {
        return dateOfApplication;
    }

    public void setDateOfApplication(Date dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(String usedBy) {

        this.usedBy = usedBy;
    }

    @Override
    public String toString(){
        return "id=" + this.id +
                ", name=" + this.code +
                ", isActive=" + this.isActive +
                ", dateOfApplication=" + this.dateOfApplication +
                ", comment=" + this.comment +
                ", usedByUser" + this.usedBy;
    }
}
