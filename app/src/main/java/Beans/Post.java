package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post implements Serializable {

    public int id;
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
    public String postDate;
    public Landlord landlord;
    ArrayList < Object > rules = new ArrayList < Object > ();
    public boolean flag;

    public Post(int id, String title, String description, String address, String province, String district, String photo, String department, float price, int roomQuantity, int bathroomQuantity, String postDate, Landlord landlord,boolean flag) {
        this.id = id;
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
        this.postDate = postDate;
        this.landlord = landlord;
        this.flag=flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPrice(int price) {
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

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }
}
