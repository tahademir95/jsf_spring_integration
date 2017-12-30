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
    List<MyUser> locationList;

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

    public UserManagedBean() {
        simpleModel = new DefaultMapModel();
    }

    //This method adds a new member.
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
            reset();

            return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    //This method deletes the members
    public String deleteUser (MyUser mystudent){
        try{

            userService.deleteUser(mystudent);
            return SUCCESS;

        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    //This method resets the input values.
    public void reset() {
        this.setName("");
        this.setSurname("");
        this.setPassword("");
        this.setSchool("");
        this.setLatitude(null);
        this.setLongitude(null);
    }

    //This method gets the users and returns as a list.
    public List<MyUser> getMyUserList() {
        myUserList = new ArrayList<MyUser>();
        myUserList.addAll(getUserService().getUsers());
        return myUserList;
    }

    //This method gets the names of the universities and returns as a list.
    public List<MyUser> getSchoolList() {
        schoolList = new ArrayList<MyUser>();
        schoolList.addAll(getUserService().getSchoolInfo());
        return schoolList;
    }

    //This method gets the latitudes of the universities and returns as a list.
    public List<MyUser> getLatitudeList() {
        latitudeList = new ArrayList<MyUser>();
        latitudeList.addAll(getUserService().getSchoolLatitudeList());
        return latitudeList;
    }

    //This method gets the longitudes of the universities and returns as a list.
    public List<MyUser>  getLongitudeList(){
        longitudeList = new ArrayList<MyUser>();
        longitudeList.addAll(getUserService().getSchoolLongitudeList());
        return longitudeList;
    }

    //This method gets the location of the universities and returns as a list.
    public List<MyUser> getLocationList() {
        locationList = new ArrayList<MyUser>();
        locationList.addAll(getUserService().markSchoolLocationList());
        return locationList;
    }

    //This method shows the universities with their locations as a tree in treetable.
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

    //This method shows the location of the universities if they are added to the system.
    public void showSchoolOnMap(){
        LatLng[] coords = new LatLng[latitudeList.size()];
        Double[] latitudes = new Double[latitudeList.size()];
        latitudeList.toArray(latitudes);

        Double[] longitudes = new Double[longitudeList.size()];
        longitudeList.toArray(longitudes);

        String[] schoolName = new String[schoolList.size()];
        schoolList.toArray(schoolName);

        for (int i = 0; i < locationList.size(); i++){
            coords[i] = new LatLng(latitudes[i], longitudes[i]);
            simpleModel.addOverlay(new Marker(coords[i], schoolName[i]));
        }
    }

    @PostConstruct
    public void init() {

        getSchoolList();
        getLatitudeList();
        getLongitudeList();
        getLocationList();
        showSchoolOnMap();

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

    public TreeNode getLng_node() {
        return lng_node;
    }
    public void setLng_node(TreeNode lng_node) {
        this.lng_node = lng_node;
    }

    public void setLocationList(List<MyUser> locationList) {
        this.locationList = locationList;
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

