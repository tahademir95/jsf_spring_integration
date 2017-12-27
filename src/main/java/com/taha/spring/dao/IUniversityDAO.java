package com.taha.spring.dao;

import com.taha.spring.model.MyUniversity;

import java.util.List;

public interface IUniversityDAO {

    public void addUniversity(MyUniversity myUniversity);
    public void deleteUniversity(MyUniversity myUniversity);
    public MyUniversity getUniversityByName(String name);
    public List<MyUniversity> getUniversities();


}
