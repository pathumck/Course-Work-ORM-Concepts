package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.dao.custom.BookDAO;
import lk.ijse.bookworm.dao.custom.BranchDAO;
import lk.ijse.bookworm.dao.custom.impl.BookDAOImpl;
import lk.ijse.bookworm.dao.custom.impl.BranchDAOImpl;
import lk.ijse.bookworm.dto.BookDTO;
import lk.ijse.bookworm.dto.BranchDTO;
import lk.ijse.bookworm.entity.Book;
import lk.ijse.bookworm.entity.Branch;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookDAO.getAll();
        List<BookDTO> dtos = new ArrayList<>();
        for (Book book : books) {
            dtos.add(new BookDTO(book.getId(),book.getTitle(),book.getAuthor(),book.getGenre(),book.getBranch().getId(),book.getStatus()));
        }
        return dtos;
    }

    @Override
    public boolean deleteBook(String id) {
        return bookDAO.delete(id);
    }

    @Override
    public boolean updateBook(BookDTO bookDTO) {
        return bookDAO.update(new Book(bookDTO.getBookId(),bookDTO.getBookTitle(),bookDTO.getBookAuthor(),bookDTO.getBookGenre(),bookDTO.getStatus(),branchDAO.search(bookDTO.getBranchId())));
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        List<Branch> branches = branchDAO.getAll();
        List<BranchDTO> dtos = new ArrayList<>();
        for (Branch branch : branches) {
            dtos.add(new BranchDTO(branch.getId(),branch.getName(),branch.getAddress()));
        }
        return dtos;
    }
}
