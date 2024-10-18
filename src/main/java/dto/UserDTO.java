package dto;


public class UserDTO {

    private String email;
    private String password;
    private String type; // Admin/Employee
    private String empId;

    public UserDTO() {
    }

    public UserDTO(String email, String password, String type, String empId) {
        this.email = email;
        this.password = password;
        this.type = type;
        this.empId = empId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", empId='" + empId + '\'' +
                '}';
    }
}
