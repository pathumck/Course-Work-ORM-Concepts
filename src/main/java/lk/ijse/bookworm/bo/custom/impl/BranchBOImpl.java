package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.BranchBO;
import lk.ijse.bookworm.dao.custom.BranchDAO;
import lk.ijse.bookworm.dao.custom.impl.BranchDAOImpl;
import lk.ijse.bookworm.dto.BranchDTO;
import lk.ijse.bookworm.entity.Branch;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<BranchDTO> getAllBranches() {
        List<Branch> branches = branchDAO.getAll();
        List<BranchDTO> dtos = new ArrayList<>();
        for (Branch branch : branches) {
            dtos.add(new BranchDTO(branch.getId(),branch.getName(),branch.getAddress()));
        }
        return dtos;
    }

    @Override
    public boolean deleteBranch(String id) {
        return branchDAO.delete(id);
    }
}
