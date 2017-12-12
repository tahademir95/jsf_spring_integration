package com.taha.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="myUser")
public class MyUser {
    private int id;
    private String name;
    private String surname;
    @Id
    @Column(name="id", unique = true, nullable = false)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Column(name="name", unique = true, nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(name="surname", unique = true, nullable = false)
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("id : ").append(getId());
        strBuff.append(", name : ").append(getName());
        strBuff.append(", surname : ").append(getSurname());
        return strBuff.toString();
    }
}
