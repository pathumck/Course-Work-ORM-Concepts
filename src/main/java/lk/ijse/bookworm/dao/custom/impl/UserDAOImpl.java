package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.dao.custom.UserDAO;
import lk.ijse.bookworm.entity.User;
import lk.ijse.bookworm.util.HbFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAOImpl implements UserDAO {

    @Override
    public String generateNextId() {
        Session session = HbFactoryConfiguration.getInstance().getSession();
        String prefix = "u00";
        Query<String> query = session.createQuery(
                "SELECT u.id FROM User u WHERE u.id LIKE :prefix ORDER BY CAST(SUBSTRING(u.id, :prefixLength + 1) AS integer) DESC",
                String.class
        );
        query.setParameter("prefix", prefix + "%");
        query.setParameter("prefixLength", prefix.length());
        query.setMaxResults(1);
        String nextUserId = query.uniqueResult();
        session.close();
        return nextUserId;
    }

    @Override
    public boolean save(User user) {
        Session session = HbFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
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
    public boolean update(User user) {
        Session session = HbFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
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
