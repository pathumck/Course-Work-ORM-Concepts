package lk.ijse.bookworm.dao.custom;

import lk.ijse.bookworm.entity.User;

public interface UserDAO {
    String generateNextId();
    boolean save(User user);
    boolean update(User user);
}
