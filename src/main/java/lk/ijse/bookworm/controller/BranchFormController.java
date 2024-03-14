package lk.ijse.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bookworm.bo.custom.BranchBO;
import lk.ijse.bookworm.bo.custom.impl.BranchBOImpl;
import lk.ijse.bookworm.dto.BranchDTO;
import lk.ijse.bookworm.dto.tm.BranchTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BranchFormController {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<BranchTM, Button> colAction;

    @FXML
    private TableColumn<BranchTM, String> colAddress;

    @FXML
    private TableColumn<BranchTM, String> colId;

    @FXML
    private TableColumn<BranchTM, String> colName;

    @FXML
    private Label lblId;

    @FXML
    private TableView<BranchTM> tblBranch;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSearch;

    BranchBO branchBO = new BranchBOImpl();

    public void initialize() {
        generateNextId();
        setTabelBranch();
        vitualize();
        btnSaveAction();
    }

    public void btnSaveAction() {
        btnAdd.setOnAction((e) -> {
            String name = txtName.getText();
            String address = txtAddress.getText();

            if (name.isEmpty()||address.isEmpty()){
                new Alert(Alert.AlertType.ERROR,"Text Fields Empty!").show();
                return;
            }

            //validation
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to add new Branch \""+lblId.getText()+"\" ?", yes, no).showAndWait();

                if (type.orElse(no) == yes) {
                        Boolean flag = branchBO.saveBranch(new BranchDTO(lblId.getText(),txtName.getText(),txtAddress.getText()));
                        if (flag) {
                            clearAllFields();
                            new Alert(Alert.AlertType.CONFIRMATION, "Branch Saved!").show();
                        }else {
                            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                        }
                }
        });
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

    private void setTabelBranch() {
        List<BranchDTO> branchDTOS = branchBO.getAllBranches();
        List<BranchTM> tms = new ArrayList<>();

        for (BranchDTO branchDTO : branchDTOS) {
            Button deleteButton = new Button("Delete");
            deleteButton.setCursor(Cursor.HAND);
            deleteButton.setStyle("-fx-background-color: #e84118; -fx-text-fill: #ffffff;");
            setRemoveBtnAction(deleteButton);
            tms.add(new BranchTM(branchDTO.getBranchId(),branchDTO.getBranchName(),branchDTO.getBranchAddress(),deleteButton));
        }

        ObservableList<BranchTM> toTable = FXCollections.observableArrayList(tms);
        tblBranch.setItems(toTable);
    }

    private void setRemoveBtnAction(Button deleteButton) {
        deleteButton.setOnAction((e) -> {
            Integer index = tblBranch.getSelectionModel().getSelectedIndex();
            if (index <= -1) {
                new Alert(Alert.AlertType.ERROR, "Please select a Branch table row to delete a Branch!").show();
                return;
            }
            String id = colId.getCellData(index).toString();
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete Branch \"" + id + "\" ?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                    Boolean flag = branchBO.deleteBranch(id);
                    if (flag) {
                        clearAllFields();
                        new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
                    }
            }
        });
    }

    private void vitualize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
    }

    private void clearAllFields() {
        initialize();
        txtName.clear();
        txtAddress.clear();
        txtSearch.clear();
    }

}
