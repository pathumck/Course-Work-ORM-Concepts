package lk.ijse.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bookworm.bo.custom.BranchBO;
import lk.ijse.bookworm.bo.custom.impl.BranchBOImpl;
import lk.ijse.bookworm.dto.BranchDTO;
import lk.ijse.bookworm.dto.tm.BranchTM;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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

    private ObservableList<BranchTM> toTable;

    BranchBO branchBO = new BranchBOImpl();

    public void initialize() {
        generateNextId();
        setTabelBranch();
        vitualize();
        btnSaveAction();
        updateBtnAction();
        searchFilter();
    }

    public void btnSaveAction() {
        btnAdd.setOnAction((e) -> {
            int index = tblBranch.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                new Alert(Alert.AlertType.ERROR,"Branch "+lblId.getText()+"already exists").show();
                clearAllFields();
                return;
            }
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

    public void updateBtnAction() {
        btnUpdate.setOnAction((e) -> {
            if (!tblBranch.getSelectionModel().isEmpty()) {
                if (txtName.getText().isEmpty()||txtAddress.getText().isEmpty()){
                    new Alert(Alert.AlertType.ERROR,"Check empty fields!").show();
                    return;
                }
                //validation
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to update Branch \"" + lblId.getText() + "\" ?", yes, no).showAndWait();

                if (type.orElse(no) == yes) {
                    boolean flag = branchBO.updateBranch(new BranchDTO(lblId.getText(),txtName.getText(),txtAddress.getText()));
                    if (flag) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Branch Updated").show();
                        clearAllFields();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Error!").show();
                    }
                }
            }else {
                new Alert(Alert.AlertType.INFORMATION, "Select a row in Branch Table!").show();
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

        toTable = FXCollections.observableArrayList(tms);
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

    @FXML
    void tblBranchMouseClickOnAction(MouseEvent event) {
        int index = tblBranch.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        lblId.setText(colId.getCellData(index).toString());
        txtName.setText(colName.getCellData(index).toString());
        txtAddress.setText(colAddress.getCellData(index).toString());
    }

    private void searchFilter() {
        FilteredList<BranchTM> filterData= new FilteredList<>(toTable, e->true);
        txtSearch.setOnKeyReleased(e->{
            txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.setPredicate((Predicate<? super BranchTM >) cust->{
                    if(newValue==null){
                        return true;
                    }
                    String toLowerCaseFilter = newValue.toLowerCase();
                    if(cust.getId().contains(newValue)){
                        return true;
                    }else  if(cust.getName().toLowerCase().contains(toLowerCaseFilter)){
                        return true;
                    }else  if(cust.getAddress().toLowerCase().contains(toLowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });

            final SortedList<BranchTM> branchTMS = new SortedList<>(filterData);
            branchTMS.comparatorProperty().bind(tblBranch.comparatorProperty());
            tblBranch.setItems(branchTMS);
        });
    }


}
