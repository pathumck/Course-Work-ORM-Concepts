package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.dao.custom.UserDAO;
import lk.ijse.bookworm.dao.custom.impl.UserDAOImpl;
import lk.ijse.bookworm.dto.UserDTO;
import lk.ijse.bookworm.entity.User;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public String generateNextUserId() {
        return userDAO.generateNextId();
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getUserId(),userDTO.getUserName(),userDTO.getUserAddress(),userDTO.getUserTp(),userDTO.getUserBirthDay()));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userDAO.update(new User(userDTO.getUserId(),userDTO.getUserName(),userDTO.getUserAddress(),userDTO.getUserTp(),userDTO.getUserBirthDay()));
    }
}
