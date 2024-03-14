package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.dao.custom.BookDAO;
import lk.ijse.bookworm.dao.custom.impl.BookDAOImpl;

public class BookBOImpl implements BookBO {
    BookDAO bookDAO = new BookDAOImpl();
    @Override
    public String generateNextBookId () {
        return bookDAO.generateNextId();
    }
}
