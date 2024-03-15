package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.dao.custom.UserDAO;
import lk.ijse.bookworm.dao.custom.impl.UserDAOImpl;
import lk.ijse.bookworm.dto.BookDTO;
import lk.ijse.bookworm.dto.UserDTO;
import lk.ijse.bookworm.entity.Book;
import lk.ijse.bookworm.entity.User;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userDAO.getAll();
        List<UserDTO> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(new UserDTO(user.getId(),user.getName(),user.getAddress(),user.getbDay(),user.getTp()));
        }
        return dtos;
    }

    @Override
    public boolean deleteUser(String id) throws HibernateException {
        return userDAO.delete(id);
    }
}
