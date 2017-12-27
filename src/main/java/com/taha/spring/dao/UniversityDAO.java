package com.taha.spring.dao;

import com.taha.spring.model.MyUniversity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UniversityDAO implements IUniversityDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    public void addUniversity(MyUniversity myUniversity){
        getSessionFactory().getCurrentSession().save(myUniversity);
    }

    @Transactional
    public void deleteUniversity(MyUniversity myUniversity){
        getSessionFactory().getCurrentSession().delete(myUniversity);
    }

    @Transactional
    public MyUniversity getUniversityByName(String name){
        List list = getSessionFactory().getCurrentSession().createQuery("from MyUniversity u MyUser us where u.name=? and us.id = u.id ").list();
        return (MyUniversity)list.get(0);
    }

    @Transactional
    public List<MyUniversity> getUniversities(){
        List<MyUniversity> universityList = getSessionFactory().getCurrentSession().createQuery("from MyUniversity").list();
        return universityList;
    }


}
