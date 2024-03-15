package lk.ijse.bookworm.bo.custom;

import lk.ijse.bookworm.dto.BranchDTO;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.List;

public interface BranchBO {
    String generateNextBranchId ();
    boolean saveBranch (BranchDTO dto);
    boolean updateBranch (BranchDTO dto);
    List<BranchDTO> getAllBranches ();
    boolean deleteBranch (String id) throws HibernateException;
}
