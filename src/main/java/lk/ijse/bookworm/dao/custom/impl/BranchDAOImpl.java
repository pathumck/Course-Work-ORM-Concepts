package lk.ijse.bookworm.dao.custom.impl;

import lk.ijse.bookworm.dao.custom.BranchDAO;
import lk.ijse.bookworm.entity.Branch;
import lk.ijse.bookworm.util.HbFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BranchDAOImpl implements BranchDAO {
    @Override
    public String generateNextId() {
        Session session = HbFactoryConfiguration.getInstance().getSession();
            String prefix = "B00";
            Query<String> query = session.createQuery(
                    "SELECT b.id FROM Branch b WHERE b.id LIKE :prefix ORDER BY CAST(SUBSTRING(b.id, :prefixLength + 1) AS integer) DESC",
                    String.class
            );
            query.setParameter("prefix", prefix + "%");
            query.setParameter("prefixLength", prefix.length());
            query.setMaxResults(1);
            String nextBranchId = query.uniqueResult();
            session.close();
            return nextBranchId;
        }

    @Override
    public boolean save(Branch branch) {
        Session session = HbFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(branch);
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
    public boolean update(Branch branch) {
        Session session = HbFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(branch);
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
    public List<Branch> getAll() {
        Session session = HbFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Branch> branches = null;
        Query<Branch> query = session.createQuery("from Branch", Branch.class);
        branches = query.list();
        transaction.commit();
        return branches;
    }

    @Override
    public boolean delete(String id) {
        Session session = HbFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE FROM Branch WHERE id = :id");
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
