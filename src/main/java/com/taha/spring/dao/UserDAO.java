package com.taha.spring.dao;

import java.util.List;

import com.taha.spring.model.MyUser;
import org.hibernate.SessionFactory;

import javax.swing.tree.TreeNode;

public class UserDAO implements IUserDAO {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(MyUser myUser) {
        getSessionFactory().getCurrentSession().save(myUser);
    }

    @Override
    public void deleteUser(MyUser myUser) {
        getSessionFactory().getCurrentSession().delete(myUser);
    }

    @Override
    public void updateUser(MyUser myUser) {
        getSessionFactory().getCurrentSession().update(myUser);
    }

    @Override
    public MyUser getUserById(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from MyUser where id=?").list();
        return (MyUser)list.get(0);
    }

    @Override
    public MyUser getSchool(String school) {
        List list = getSessionFactory().getCurrentSession().createQuery("select st_school from MyUser where st_school=?").list();
        return (MyUser)list.get(4);
    }

    @Override
    public List<MyUser> getSchoolInfo() {
        List list = getSessionFactory().getCurrentSession().createQuery("select school from MyUser where id is not null").list();
        return list;
    }

    @Override
    public MyUser getSchoolLatitude(Double latitude) {
        List list = getSessionFactory().getCurrentSession().createQuery("select st_schoolLatitude from MyUser where st_schoolLatitude=?").list();
        return (MyUser)list.get(5);
    }

    @Override
    public List<MyUser> getSchoolLatitudeList() {
        List list = getSessionFactory().getCurrentSession().createQuery("select latitude from MyUser where id is not null").list();
        return list;
    }

    @Override
    public MyUser getSchoolLongitude(Double longitude) {
        List list = getSessionFactory().getCurrentSession().createQuery("select sc_schoolLongitude from MyUser where st_schoolLongitude=?").list();
        return (MyUser)list.get(6);
    }

    @Override
    public List<MyUser> getSchoolLongitudeList() {
        List list = getSessionFactory().getCurrentSession().createQuery("select longitude from MyUser where id is not null").list();
        return list;
    }

    @Override
    public List<MyUser> getUsers() {
        List list = getSessionFactory().getCurrentSession().createQuery("from MyUser").list();

        return list;
    }
}
