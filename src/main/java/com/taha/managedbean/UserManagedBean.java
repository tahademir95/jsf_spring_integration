package com.taha.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.taha.spring.model.MyUser;
import org.springframework.dao.DataAccessException;

import com.taha.spring.service.IUserService;


@ManagedBean(name="userMB")
@RequestScoped
public class UserManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";

    @ManagedProperty(value="#{UserService}")
    IUserService userService;
    List<MyUser> myUserList;

    public int id;
    public String name;
    public String surname;
    public String password;



    public String addUser() {
        try {
            MyUser myUser = new MyUser();
            myUser.setId(getId());
            myUser.setName(getName());
            myUser.setSurname(getSurname());
            myUser.setPassword(getPassword());
            getUserService().addUser(myUser);
            return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return ERROR;
    }
    public String deleteUser(){
        try{
            MyUser user = new MyUser();
            user.setId(getId());
            user.setName(getName());
            user.setSurname(getSurname());
            user.setPassword(getPassword());
            getUserService().addUser(user);
            getUserService().deleteUser(user);
            return SUCCESS;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    public void reset() {
        this.setId(0);
        this.setName("");
        this.setSurname("");
    }
    public List<MyUser> getMyUserList() {
        myUserList = new ArrayList<MyUser>();
        myUserList.addAll(getUserService().getUsers());
        return myUserList;
    }
    public IUserService getUserService() {
        return userService;
    }
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    public void setMyUserList(List<MyUser> myUserList) {
        this.myUserList = myUserList;
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
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

