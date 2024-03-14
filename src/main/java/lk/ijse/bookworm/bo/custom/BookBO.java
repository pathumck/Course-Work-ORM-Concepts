package lk.ijse.bookworm.bo.custom;

import lk.ijse.bookworm.dto.BookDTO;
import lk.ijse.bookworm.dto.BranchDTO;
import lk.ijse.bookworm.entity.Book;

import java.util.List;

public interface BookBO {
    String generateNextBookId ();
    boolean saveBook(BookDTO bookDTO);
    List<BookDTO> getAllBooks();
    boolean deleteBook(String id);

    boolean updateBook(BookDTO bookDTO);

    List<BranchDTO> getAllBranches();
}
