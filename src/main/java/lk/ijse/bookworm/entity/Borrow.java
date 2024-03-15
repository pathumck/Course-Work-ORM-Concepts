package lk.ijse.bookworm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Borrow {
    @Id
    private String id;
    private String date;
    private String dueDate;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "borrow")
    private List<BorrowDetail> borrowDetails;

    public Borrow() {
    }

    public Borrow(String id, String date, String dueDate, User user, List<BorrowDetail> borrowDetails) {
        this.id = id;
        this.date = date;
        this.dueDate = dueDate;
        this.user = user;
        this.borrowDetails = borrowDetails;
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BorrowDetail> getBorrowDetails() {
        return borrowDetails;
    }

    public void setBorrowDetails(List<BorrowDetail> borrowDetails) {
        this.borrowDetails = borrowDetails;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", user=" + user +
                ", borrowDetails=" + borrowDetails +
                '}';
    }
}

