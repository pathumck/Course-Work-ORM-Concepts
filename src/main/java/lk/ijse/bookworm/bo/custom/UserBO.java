package lk.ijse.bookworm.bo.custom;

import lk.ijse.bookworm.dto.UserDTO;
import org.hibernate.HibernateException;

import java.util.List;

public interface UserBO {
    String generateNextUserId ();
    boolean saveUser(UserDTO userDTO);
    boolean updateUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    boolean deleteUser(String id) throws HibernateException;
}
