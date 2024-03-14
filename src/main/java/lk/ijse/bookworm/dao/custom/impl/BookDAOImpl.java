package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.dao.custom.BookDAO;
import lk.ijse.bookworm.entity.Book;
import lk.ijse.bookworm.util.HbFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public String generateNextId() {
        Session session = HbFactoryConfiguration.getInstance().getSession();
        String prefix = "b00";
        Query<String> query = session.createQuery(
                "SELECT b.id FROM Book b WHERE b.id LIKE :prefix ORDER BY CAST(SUBSTRING(b.id, :prefixLength + 1) AS integer) DESC",
                String.class
        );
        query.setParameter("prefix", prefix + "%");
        query.setParameter("prefixLength", prefix.length());
        query.setMaxResults(1);
        String nextBookId = query.uniqueResult();
        session.close();
        return nextBookId;
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

    @Override
    public List<Book> getAll() {
        Session session = HbFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Book> books = null;
        Query<Book> query = session.createQuery("from Book", Book.class);
        books = query.list();
        transaction.commit();
        return books;
    }

    @Override
    public boolean delete(String id) {
        Session session = HbFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE FROM Book WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }
}
