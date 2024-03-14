package lk.ijse.bookworm.dao.custom;

import lk.ijse.bookworm.entity.Branch;

import java.util.List;

public interface BranchDAO {
    String generateNextId();
    boolean save(Branch branch);
    boolean update(Branch branch);
    List<Branch> getAll();
    boolean delete(String id);
    Branch search(String id);
}
