package lk.ijse.bookworm.bo.custom;

import lk.ijse.bookworm.dto.BranchDTO;

import java.util.List;

public interface BranchBO {
    String generateNextBranchId ();
    boolean saveBranch (BranchDTO dto);
    boolean updateBranch (BranchDTO dto);
    List<BranchDTO> getAllBranches ();
    boolean deleteBranch (String id);
}
