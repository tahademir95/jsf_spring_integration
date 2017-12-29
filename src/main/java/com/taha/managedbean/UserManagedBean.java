package com.taha.managedbean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import com.taha.spring.model.MyUser;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
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
    List<MyUser> schoolList;
    List<MyUser> latitudeList;
    List<MyUser> longitudeList;

    public int id;
    public String name;
    public String surname;
    public String password;
    private MapModel simpleModel;
    private Marker marker;
    private String school;
    public Double latitude;
    public Double longitude;

    private TreeNode root;
    private TreeNode lat_node;
    private TreeNode sch_node;
    private TreeNode lng_node;

    public TreeNode getLng_node() {
        return lng_node;
    }

    public void setLng_node(TreeNode lng_node) {
        this.lng_node = lng_node;
    }

    public UserManagedBean() {
        simpleModel = new DefaultMapModel();
    }

    public String addUser() {
        try {
            MyUser myUser = new MyUser();

            myUser.setId(getId());
            myUser.setName(getName());
            myUser.setSurname(getSurname());
            myUser.setPassword(getPassword());
            myUser.setSchool(getSchool());
            myUser.setLatitude(getLatitude());
            myUser.setLongitude(getLongitude());

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

    public List<MyUser> getSchoolList() {
        schoolList = new ArrayList<MyUser>();
        schoolList.addAll(getUserService().getSchoolInfo());
        return schoolList;
    }

    public List<MyUser> getLatitudeList() {
        latitudeList = new ArrayList<MyUser>();
        latitudeList.addAll(getUserService().getSchoolLatitudeList());
        return latitudeList;
    }

    public List<MyUser>  getLongitudeList(){
        longitudeList = new ArrayList<MyUser>();
        longitudeList.addAll(getUserService().getSchoolLongitudeList());
        return longitudeList;
    }

    public TreeNode getRoot() {

        if (root == null) {

            root = new DefaultTreeNode("university","node", null);
            sch_node = new DefaultTreeNode("sch", "Universities", root);

            TreeNode[] schoolName =new TreeNode[schoolList.size()];
            TreeNode[] schoolLat = new TreeNode[latitudeList.size()];
            TreeNode[] schoolLong = new TreeNode[longitudeList.size()];

            // Gets the schools' longitudes and assign them to node
            for (int k = 0; k < longitudeList.size(); k++){
                schoolLong[k] = new DefaultTreeNode(longitudeList.get(k), lng_node);
                schoolLong[k].getChildren();
            }

            // Gets the schools' latitudes and assign them to node
            for (int j = 0; j < latitudeList.size(); j++){
                schoolLat[j] = new DefaultTreeNode(latitudeList.get(j), lat_node);
                schoolLat[j].getChildren();
            }

            // Gets the schools' names and assign them to node and also gets the latitudes and longitudes as a child node
            for (int i = 0; i < schoolList.size(); i++) {
                schoolName[i]=new DefaultTreeNode(schoolList.get(i), sch_node);

                lat_node = new DefaultTreeNode("Latitude", schoolName[i]);
                lat_node.getChildren().add(new DefaultTreeNode(schoolLat[i].getData()));

                lng_node = new DefaultTreeNode("Longitude", schoolName[i]);
                lng_node.getChildren().add(new DefaultTreeNode(schoolLong[i].getData()));
                
            }
        }
        return root;
    }

    @PostConstruct
    public void init() {

        getSchoolList();
        getLatitudeList();
        getLongitudeList();

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

    public void setLongitudeList(List<MyUser> longitudeList) {
        this.longitudeList = longitudeList;
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

//    public void setSchoolList(List<MyUser> schoolList) {
//        this.schoolList = schoolList;
//    }
//
//    public void setLatitudeList(List<MyUser> latitudeList) {
//        this.latitudeList = latitudeList;
//    }
//
//    public void setSch_node(TreeNode sch_node) {
//        this.sch_node = sch_node;
//    }
//    public TreeNode getSch_node() {
//        return sch_node;
//    }
//
//    public void setLat_node(TreeNode lat_node) {
//        this.lat_node = lat_node;
//    }
//    public TreeNode getLat_node() {
//
//        return lat_node;
//    }

}

