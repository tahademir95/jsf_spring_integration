package com.taha.spring.service;

import java.util.List;

import com.taha.spring.model.MyUser;

public interface IUserService {

    public void addUser(MyUser myUser);
    public void updateUser(MyUser myUser);
    public void deleteUser(MyUser myUser);
    public MyUser getUserById(int id);
    public List<MyUser> getUsers();

}
