package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.dao.custom.BookDAO;
import lk.ijse.bookworm.dao.custom.BranchDAO;
import lk.ijse.bookworm.dao.custom.impl.BookDAOImpl;
import lk.ijse.bookworm.dao.custom.impl.BranchDAOImpl;
import lk.ijse.bookworm.dto.BookDTO;
import lk.ijse.bookworm.entity.Book;
import lk.ijse.bookworm.entity.Branch;

public class BookBOImpl implements BookBO {
    BookDAO bookDAO = new BookDAOImpl();
    BranchDAO branchDAO = new BranchDAOImpl();
    @Override
    public String generateNextBookId () {
        return bookDAO.generateNextId();
    }

    @Override
    public boolean saveBook(BookDTO bookDTO) {
        return bookDAO.save(new Book(bookDTO.getBookId(),bookDTO.getBookTitle(),bookDTO.getBookAuthor(),bookDTO.getBookGenre(),bookDTO.getStatus(),branchDAO.search(bookDTO.getBranchId())));
    }


}
