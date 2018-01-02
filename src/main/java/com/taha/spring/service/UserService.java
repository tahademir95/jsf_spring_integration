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
    public MyUser getSchool(String school) {
        return getUserDAO().getSchool(school);
    }

    @Override
    public List<MyUser> getSchoolInfo() {
        return getUserDAO().getSchoolInfo();
    }

    @Override
    public List<MyUser> getUsers() {
        return getUserDAO().getUsers();
    }

    @Override
    public MyUser getSchoolLatitude(Double latitude) {
        return getUserDAO().getSchoolLatitude(latitude);
    }

    @Override
    public List<MyUser> getSchoolLatitudeList() {
        return getUserDAO().getSchoolLatitudeList();
    }

    @Override
    public MyUser getSchoolLongitude(Double longitude) {
        return getUserDAO().getSchoolLongitude(longitude);
    }

    @Override
    public List<MyUser> getSchoolLongitudeList() {
        return getUserDAO().getSchoolLongitudeList();
    }

    @Override
    public MyUser getSchoolLocation(Double latitude, Double longitude) {
        return getUserDAO().getSchoolLocation(latitude, longitude);
    }

    @Override
    public List<MyUser> markSchoolLocationList() {
        return getUserDAO().markSchoolLocationList();
    }


    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
