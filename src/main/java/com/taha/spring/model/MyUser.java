package com.taha.spring.model;

import javax.persistence.*;

@Entity
@Table(name="myUser")
public class MyUser {
    private int id;
    private String name;
    private String surname;
    private String password;
    private String school;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    @Column(name="st_name", unique = true, nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="st_lastname", unique = true, nullable = false)
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "st_password", unique = true, nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "st_school", unique = true, nullable = false)
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }


    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("id : ").append(getId());
        strBuff.append(", st_name : ").append(getName());
        strBuff.append(", st_lastname : ").append(getSurname());
        strBuff.append(", st_password : ").append(getSurname());
        strBuff.append(", st_school : ").append(getSchool());

        return strBuff.toString();
    }
}
