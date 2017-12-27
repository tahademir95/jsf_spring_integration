package com.taha.spring.service;

import com.taha.spring.model.MyUniversity;

import java.util.List;

public interface IUniversityService {

    public void addUniversity(MyUniversity myUniversity);
    public void deleteUniversity(MyUniversity myUniversity);
    public MyUniversity getUniversityByName(String name);
    public List<MyUniversity> getUniversities();

    }
