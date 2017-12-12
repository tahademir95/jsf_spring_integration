package com.taha.spring.dao;

import java.util.List;

import com.taha.spring.model.MyUser;
import org.hibernate.SessionFactory;

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
                .createQuery("from MyUser where id=?")
                .setParameter(0, id).list();
        return (MyUser)list.get(0);
    }

    @Override
    public List<MyUser> getUsers() {
        List list = getSessionFactory().getCurrentSession().createQuery("from MyUser").list();

        return list;
    }
}
