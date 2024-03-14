package lk.ijse.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.ijse.bookworm.bo.custom.BranchBO;
import lk.ijse.bookworm.bo.custom.impl.BranchBOImpl;
import lk.ijse.bookworm.dto.BranchDTO;

public class BranchFormController {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private Label lblId;

    @FXML
    private TableView<?> tblBranch;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSearch;

    BranchBO branchBO = new BranchBOImpl();

    public void initialize() {
        generateNextId();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        boolean flag = branchBO.saveBranch(new BranchDTO(lblId.getText(),txtName.getText(),txtAddress.getText()));
        if (flag) {
            new Alert(Alert.AlertType.CONFIRMATION,"Branch added successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private void generateNextId() {
        String branchId = splitBranchId(branchBO.generateNextBranchId());
        lblId.setText(branchId);
    }

    private String splitBranchId(String branchId) {
        if (branchId == null || branchId.isEmpty() || !branchId.matches("^B\\d+$")) {
            return "B001";
        } else {
            String numericPart = branchId.substring(3);
            int numericValue = Integer.parseInt(numericPart);

            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);

            return "B00" + nextNumericPart;
        }
    }
}
