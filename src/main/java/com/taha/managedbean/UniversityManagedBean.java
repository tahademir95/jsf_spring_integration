package com.taha.managedbean;

import com.taha.spring.model.MyUniversity;
import com.taha.spring.service.IUniversityService;
import org.primefaces.component.tree.Tree;
import org.springframework.dao.DataAccessException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Tree node için gerekli importlar
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name="universityMB")
@RequestScoped
public class UniversityManagedBean implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";

    @ManagedProperty(value = "#{UniversityService}")
    IUniversityService universityService;
    List<MyUniversity> myUniversityList;




    public String school;
    public Double latitude;
    public Double longitude;




    public String addUniversity(){

        try{
            MyUniversity myUniversity = new MyUniversity();

            myUniversity.setSchool(getSchool());
            myUniversity.setLatitude(getLatitude());
            myUniversity.setLongitude(getLongitude());

            getUniversityService().addUniversity(myUniversity);

            return SUCCESS;
        }catch (DataAccessException e) {
            e.printStackTrace();
        }
        return ERROR;
    }


    public String deleteUniversity(MyUniversity myUniversity){
        try{
            universityService.deleteUniversity(myUniversity);
            return SUCCESS;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    public IUniversityService getUniversityService() {
        return universityService;
    }
    public void setUniversityService(IUniversityService universityService) {
        this.universityService = universityService;
    }

    public List<MyUniversity> getMyUniversityList() {
        myUniversityList = new ArrayList<MyUniversity>();
        myUniversityList.addAll(getUniversityService().getUniversities());
        return myUniversityList;
    }
    public void setMyUniversityList(List<MyUniversity> myUniversityList) {
        this.myUniversityList = myUniversityList;
    }

    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }

    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


}
