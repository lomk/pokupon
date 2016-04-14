package ua.com.bukvashops.pocupon.Entities;

import java.io.Serializable;

/**
 * Created by mater on 18-Mar-16.
 */
public class Shop implements BaseEntity {
    private int id;
    private String name;

    public Shop() {
    }
    public Shop(String name) {
        this.name = name;
    }
    public Shop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "id=" + this.id + ", name=" + this.name;
    }
}
