package lk.ijse.bookworm.dao.custom;

import lk.ijse.bookworm.entity.User;
import org.hibernate.HibernateException;

import java.util.List;

public interface UserDAO {
    String generateNextId();
    boolean save(User user);
    boolean update(User user);
    List<User> getAll();
    boolean delete(String id) throws HibernateException;
}
