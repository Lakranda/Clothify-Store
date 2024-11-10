package entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "returns")
public class Returns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long returnId;
    private LocalDate date;
    private String time;
    private Double total;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "returns", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ReturnItem> returnItems=new ArrayList<>();

    public Returns() {
    }


    public Returns(LocalDate date, String time, Double netTotal) {

        this.date = date;
        this.time = time;
        this.total = netTotal;

    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void addReturnsItem(ReturnItem returnItem) {
        returnItems.add(returnItem);
        returnItem.setOrder(this); // Ensure the order is set in OrderItem
    }

    public void removeReturnsItem(ReturnItem returnItem) {
        returnItems.remove(returnItem);
        returnItem.setOrder(null); // Nullify the reference
    }


    @Override
    public String toString() {
        return "Returns{" +
                "returnId=" + returnId +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", total=" + total +
                '}';
    }
}
