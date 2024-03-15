package lk.ijse.bookworm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
public class ReturnDetail {
    @Id
    private String id;
    @ManyToOne
    private Returns returns;
    @ManyToOne
    private Book book;

    public ReturnDetail() {
    }

    public ReturnDetail(String id, Returns returns, Book book) {
        this.id = id;
        this.returns = returns;
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Returns getReturnn() {
        return returns;
    }

    public void setReturnn(Returns returns) {
        this.returns = returns;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "ReturnDetail{" +
                "id='" + id + '\'' +
                ", returns=" + returns +
                ", book=" + book +
                '}';
    }
}
