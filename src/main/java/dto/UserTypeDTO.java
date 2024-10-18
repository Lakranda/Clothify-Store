package dto;

public class UserTypeDTO {
    private String userType;
    private Long empId;

    public UserTypeDTO(String userType, Long empId) {
        this.userType = userType;
        this.empId = empId;
    }

    // Getters and Setters
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "UserTypeDTO{" +
                "userType='" + userType + '\'' +
                ", empId=" + empId +
                '}';
    }
}
