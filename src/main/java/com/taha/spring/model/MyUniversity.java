package com.taha.spring.model;

import javax.persistence.*;

@Entity
@Table(name =  "universityInfo")
public class MyUniversity {

    private String school;
    private Double latitude;
    private Double longitude;
    private MyUser myUser;

    @Id
    @Column(name = "st_school", unique = true, nullable = false)
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }

    @Column(name = "st_schoolLatitude", unique = true, nullable = false)
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name = "st_schoolLongitude", unique = true, nullable = false)
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append(", school : ").append(getSchool());
        strBuff.append(", st_schoolLatitude : ").append(getLatitude());
        strBuff.append(", st_schoolLongitude : ").append(getLongitude());


        return strBuff.toString();
    }
}
