package dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class EmployeeDTO {
    private String name;
    private String company;
    private String eMail;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String company, String eMail) {
        this.name = name;
        this.company = company;
        this.eMail = eMail;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}
