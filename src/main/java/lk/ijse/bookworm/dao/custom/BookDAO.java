package lk.ijse.bookworm.dao.custom;

import lk.ijse.bookworm.entity.Book;

import java.util.List;

public interface BookDAO {
    String generateNextId();
    boolean save(Book book);
    List<Book> getAll();
    boolean delete(String id);
    boolean update(Book book);
}
