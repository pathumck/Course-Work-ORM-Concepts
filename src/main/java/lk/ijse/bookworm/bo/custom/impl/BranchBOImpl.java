package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.BranchBO;
import lk.ijse.bookworm.dao.custom.BranchDAO;
import lk.ijse.bookworm.dao.custom.impl.BranchDAOImpl;
import lk.ijse.bookworm.dto.BranchDTO;
import lk.ijse.bookworm.entity.Branch;

public class BranchBOImpl implements BranchBO {
    BranchDAO branchDAO = new BranchDAOImpl();

    @Override
    public String generateNextBranchId() {
        return branchDAO.generateNextId();
    }

    @Override
    public boolean saveBranch(BranchDTO dto) {
        return branchDAO.save(new Branch(dto.getBranchId(),dto.getBranchName(),dto.getBranchAddress()));
    }

    @Override
    public boolean updateBranch(BranchDTO dto) {
        return branchDAO.update(new Branch(dto.getBranchId(),dto.getBranchName(),dto.getBranchAddress()));
    }
}
