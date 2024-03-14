package lk.ijse.bookworm.dao.custom;

import lk.ijse.bookworm.entity.Branch;

public interface BranchDAO {
    String generateNextId();
    boolean save(Branch branch);
    boolean update(Branch branch);
}
