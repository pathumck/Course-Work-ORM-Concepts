package lk.ijse.bookworm.dao.custom;

import lk.ijse.bookworm.dto.BookDTO;
import lk.ijse.bookworm.entity.Book;

public interface BookDAO {
    String generateNextId();
    boolean save(Book book);
}
