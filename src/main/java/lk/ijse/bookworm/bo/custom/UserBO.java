package lk.ijse.bookworm.bo.custom;

import lk.ijse.bookworm.dto.UserDTO;

public interface UserBO {
    String generateNextUserId ();
    boolean saveUser(UserDTO userDTO);
    boolean updateUser(UserDTO userDTO);
}
