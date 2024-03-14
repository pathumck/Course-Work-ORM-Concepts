package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.dao.custom.BookDAO;
import lk.ijse.bookworm.entity.Book;
import lk.ijse.bookworm.util.HbFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookDAOImpl implements BookDAO {
    @Override
    public String generateNextId() {
        return null ;
    }

    @Override
    public boolean save(Book book) {
        Session session = HbFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(book);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
    }
}
