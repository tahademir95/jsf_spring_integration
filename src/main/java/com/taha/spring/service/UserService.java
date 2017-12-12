package com.taha.spring.service;

import java.util.List;

import com.taha.spring.model.MyUser;
import org.springframework.transaction.annotation.Transactional;
import com.taha.spring.dao.IUserDAO;

@Transactional(readOnly = true)
public class UserService implements IUserService {
    IUserDAO userDAO;

    @Transactional(readOnly = false)
    @Override
    public void addUser(MyUser myUser) {
        getUserDAO().addUser(myUser);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteUser(MyUser myUser) {
        getUserDAO().deleteUser(myUser);
    }

    @Transactional(readOnly = false)
    @Override
    public void updateUser(MyUser myUser) {
        getUserDAO().updateUser(myUser);
    }

    @Override
    public MyUser getUserById(int id) {
        return getUserDAO().getUserById(id);
    }

    @Override
    public List<MyUser> getUsers() {
        return getUserDAO().getUsers();
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
