package lk.ijse.bookworm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String genre;
    private String status;
    @ManyToOne
    private Branch branch;
    @OneToMany(mappedBy = "book")
    private List<ReturnDetail> returnDetailList;
    @OneToMany(mappedBy = "book")
    private List<BorrowDetail> borrowDetailList;

    public Book() {
    }

    public Book(String id, String title, String author, String genre, String status, Branch branch) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.branch = branch;
    }

    public Book(String id, String title, String author, String genre, String status, Branch branch, List<ReturnDetail> returnDetailList, List<BorrowDetail> borrowDetailList) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.branch = branch;
        this.returnDetailList = returnDetailList;
        this.borrowDetailList = borrowDetailList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<ReturnDetail> getReturnDetailList() {
        return returnDetailList;
    }

    public void setReturnDetailList(List<ReturnDetail> returnDetailList) {
        this.returnDetailList = returnDetailList;
    }

    public List<BorrowDetail> getBorrowDetailList() {
        return borrowDetailList;
    }

    public void setBorrowDetailList(List<BorrowDetail> borrowDetailList) {
        this.borrowDetailList = borrowDetailList;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", status='" + status + '\'' +
                ", branch=" + branch +
                ", returnDetailList=" + returnDetailList +
                ", borrowDetailList=" + borrowDetailList +
                '}';
    }
}
