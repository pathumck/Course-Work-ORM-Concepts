package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.dao.custom.UserDAO;
import lk.ijse.bookworm.dao.custom.impl.UserDAOImpl;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public String generateNextUserId() {
        return userDAO.generateNextId();
    }
}
