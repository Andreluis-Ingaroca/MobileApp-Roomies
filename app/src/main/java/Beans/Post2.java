package Beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Post2 implements Serializable {

    public String title;
    public String description;
    public String address;
    public String province;
    public String district;
    public String photo;
    public String department;
    public float price;
    public int roomQuantity;
    public int bathroomQuantity;


    public Post2(String title, String description, String address, String province, String district, String photo, String department, float price, int roomQuantity, int bathroomQuantity) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.province = province;
        this.district = district;
        this.photo = photo;
        this.department = department;
        this.price = price;
        this.roomQuantity = roomQuantity;
        this.bathroomQuantity = bathroomQuantity;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public int getBathroomQuantity() {
        return bathroomQuantity;
    }

    public void setBathroomQuantity(int bathroomQuantity) {
        this.bathroomQuantity = bathroomQuantity;
    }

}
