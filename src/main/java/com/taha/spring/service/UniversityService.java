package com.taha.spring.service;

import com.taha.spring.dao.IUniversityDAO;
import com.taha.spring.model.MyUniversity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("uniService")
public class UniversityService implements IUniversityService {

    @Autowired
    IUniversityDAO universityDAO;

    public void addUniversity(MyUniversity myUniversity){
        getUniversityDAO().addUniversity(myUniversity);
    }

    public void deleteUniversity(MyUniversity myUniversity){
        getUniversityDAO().deleteUniversity(myUniversity);
    }

    public MyUniversity getUniversityByName(String name){
        return getUniversityDAO().getUniversityByName(name);
    }

    public List<MyUniversity> getUniversities(){
        return getUniversityDAO().getUniversities();
    }

    public IUniversityDAO getUniversityDAO() {
        return universityDAO;
    }


    public void setUniversityDAO(IUniversityDAO universityDAO) {
        this.universityDAO = universityDAO;
    }
}
