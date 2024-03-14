package lk.ijse.bookworm.bo.custom;

import lk.ijse.bookworm.dto.BookDTO;

public interface BookBO {
    String generateNextBookId ();
    boolean saveBook(BookDTO bookDTO);
}
