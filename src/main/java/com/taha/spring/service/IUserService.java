package com.taha.spring.service;

import java.util.List;

import com.taha.spring.model.MyUser;

public interface IUserService {

    public void addUser(MyUser myUser);
    public void updateUser(MyUser myUser);
    public void deleteUser(MyUser myUser);
    public MyUser getUserById(int id);
    public List<MyUser> getUsers();

    public MyUser getSchool(String school);
    public List<MyUser> getSchoolInfo();

    public MyUser getSchoolLatitude(Double latitude);
    public List<MyUser> getSchoolLatitudeList();

    public MyUser getSchoolLongitude(Double longitude);
    public List<MyUser> getSchoolLongitudeList();

    public MyUser getSchoolLocation(Double latitude, Double longitude);
    public List<MyUser> markSchoolLocationList();
}
