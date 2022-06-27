package Beans;

public class SaveProfile {
    private String name;
    private String lastName;
    private String cellPhone;
    private String idCard;
    private String description;
    private String birthday;
    private String department;
    private String province;
    private String district;
    private String address;
    private String profilePicture;

    public SaveProfile(String name, String lastName, String cellPhone, String idCard, String description, String birthday,
                       String department, String province, String district, String address, String profilePicture) {
        this.name = name;
        this.lastName = lastName;
        this.cellPhone = cellPhone;
        this.idCard = idCard;
        this.description = description;
        this.birthday = birthday;
        this.department = department;
        this.province = province;
        this.district = district;
        this.address = address;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
