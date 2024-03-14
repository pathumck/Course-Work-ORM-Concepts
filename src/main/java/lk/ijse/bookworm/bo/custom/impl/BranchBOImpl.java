package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.BranchBO;
import lk.ijse.bookworm.dao.custom.BranchDAO;
import lk.ijse.bookworm.dao.custom.impl.BranchDAOImpl;

public class BranchBOImpl implements BranchBO {
    BranchDAO branchDAO = new BranchDAOImpl();

    @Override
    public String generateNextBranchId() {
        return branchDAO.generateNextId();
    }
}
