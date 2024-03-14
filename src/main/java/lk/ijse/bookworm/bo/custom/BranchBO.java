package lk.ijse.bookworm.bo.custom;

import lk.ijse.bookworm.dto.BranchDTO;

public interface BranchBO {
    String generateNextBranchId ();
    boolean saveBranch (BranchDTO dto);
    boolean updateBranch (BranchDTO dto);
}
