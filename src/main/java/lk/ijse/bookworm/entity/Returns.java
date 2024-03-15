package lk.ijse.bookworm.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Returns {
    @Id
    private String id;
    private String date;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "returns")
    private List<ReturnDetail> returnDetails;

    public Returns() {
    }

    public Returns(String id, String date, List<ReturnDetail> returnDetails, User user) {
        this.id = id;
        this.date = date;
        this.returnDetails = returnDetails;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ReturnDetail> getReturnDetails() {
        return returnDetails;
    }

    public void setReturnDetails(List<ReturnDetail> returnDetails) {
        this.returnDetails = returnDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Returns{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", returnDetails=" + returnDetails +
                ", user=" + user +
                '}';
    }
}
