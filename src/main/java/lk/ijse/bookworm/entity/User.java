package lk.ijse.bookworm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String address;
    private String tp;
    private String bDay;
    @OneToMany(mappedBy = "user")
    private List<Borrow> borrowList;
    @OneToMany(mappedBy = "user")
    private List<Returns> returnList;

    public User() {
    }

    public User(String id, String name, String address, String tp, String bDay) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tp = tp;
        this.bDay = bDay;
    }

    public User(String id, String name, String address, String tp, String bDay, List<Borrow> borrowList, List<Returns> returnList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tp = tp;
        this.bDay = bDay;
        this.borrowList = borrowList;
        this.returnList = returnList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getbDay() {
        return bDay;
    }

    public void setbDay(String bDay) {
        this.bDay = bDay;
    }

    public List<Borrow> getBorrowList() {
        return borrowList;
    }

    public void setBorrowList(List<Borrow> borrowList) {
        this.borrowList = borrowList;
    }

    public List<Returns> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<Returns> returnList) {
        this.returnList = returnList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tp='" + tp + '\'' +
                ", bDay='" + bDay + '\'' +
                ", borrowList=" + borrowList +
                ", returnList=" + returnList +
                '}';
    }
}
