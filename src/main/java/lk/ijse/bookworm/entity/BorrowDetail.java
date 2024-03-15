package lk.ijse.bookworm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class BorrowDetail {
    @Id
    private String id;
    @ManyToOne
    private Borrow borrow;
    @ManyToOne
    private Book book;

    public BorrowDetail() {
    }

    public BorrowDetail(String id, Borrow borrow, Book book) {
        this.id = id;
        this.borrow = borrow;
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BorrowDetail{" +
                "id=" + id +
                ", borrow=" + borrow +
                ", book=" + book +
                '}';
    }
}
