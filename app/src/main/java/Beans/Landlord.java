package Beans;

import java.io.Serializable;

public class Landlord implements Serializable {

    public int id;
    public String name;
    public String lastName;
    public String cellPhone;
    public String idCard;
    public String description;
    public String birthday;
    public String department;
    public String province;
    public String district;
    public String profilePicture;
    public String plan = null;
    public String startSubscription;
    public String endSubsciption;

    public Landlord(int id, String name, String lastName, String cellPhone, String idCard, String description, String birthday, String department, String province, String district, String profilePicture, String startSubscription, String endSubsciption) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cellPhone = cellPhone;
        this.idCard = idCard;
        this.description = description;
        this.birthday = birthday;
        this.department = department;
        this.province = province;
        this.district = district;
        this.profilePicture = profilePicture;
        this.startSubscription = startSubscription;
        this.endSubsciption = endSubsciption;
    }

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getDescription() {
        return description;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDepartment() {
        return department;
    }

    public String getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getPlan() {
        return plan;
    }

    public String getStartSubscription() {
        return startSubscription;
    }

    public String getEndSubsciption() {
        return endSubsciption;
    }

    // Setter Methods

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setStartSubscription(String startSubscription) {
        this.startSubscription = startSubscription;
    }

    public void setEndSubsciption(String endSubsciption) {
        this.endSubsciption = endSubsciption;
    }


}
