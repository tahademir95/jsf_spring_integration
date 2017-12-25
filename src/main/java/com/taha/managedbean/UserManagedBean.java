package com.taha.managedbean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import com.taha.spring.model.MyUser;
import org.springframework.dao.DataAccessException;
import com.taha.spring.service.IUserService;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean(name="userMB")
@RequestScoped
public class UserManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";

    @ManagedProperty(value="#{UserService}")
    IUserService userService;
    List<MyUser> myUserList;

    public int id;
    public String name;
    public String surname;
    public String password;
    private MapModel simpleModel;
    private Marker marker;

    private String school;

    public String addUser() {
        try {
            MyUser myUser = new MyUser();
            myUser.setId(getId());
            myUser.setName(getName());
            myUser.setSurname(getSurname());
            myUser.setPassword(getPassword());
            myUser.setSchool(getSchool());

            getUserService().addUser(myUser);
            return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public String deleteUser (MyUser mystudent){
        try{

            userService.deleteUser(mystudent);
            return SUCCESS;

        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    public void reset() {
        this.setId(0);
        this.setName("");
        this.setSurname("");
    }
    public List<MyUser> getMyUserList() {
        myUserList = new ArrayList<MyUser>();
        myUserList.addAll(getUserService().getUsers());
        return myUserList;
    }

    @PostConstruct
    public void init() {

        simpleModel = new DefaultMapModel();

        //Shared coordinates
        LatLng coord1 = new LatLng(41.10548, 29.02365);
        LatLng coord2 = new LatLng(41.07991, 29.05047);
        LatLng coord3 = new LatLng(41.04217, 29.00770);
        LatLng coord4 = new LatLng(41.02418, 28.96106);
        LatLng coord5 = new LatLng(41.03449, 29.25642);

        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Istanbul Technical University"));
        simpleModel.addOverlay(new Marker(coord2, "Bogazici University"));
        simpleModel.addOverlay(new Marker(coord3, "Bahcesehir University"));
        simpleModel.addOverlay(new Marker(coord4, "Kadir Has University"));
        simpleModel.addOverlay(new Marker(coord5, "Ozyegin University"));

    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));
    }

    public Marker getMarker() {
        return marker;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    public void setMyUserList(List<MyUser> myUserList) {
        this.myUserList = myUserList;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

}

