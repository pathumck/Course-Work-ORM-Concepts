package lk.ijse.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.bo.custom.impl.UserBOImpl;
import lk.ijse.bookworm.dto.BookDTO;
import lk.ijse.bookworm.dto.UserDTO;

import java.util.Optional;

public class UserFormController {@FXML
private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colBirthDay;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private DatePicker dPickBD;

    @FXML
    private Label lblId;

    @FXML
    private TableView<?> tblUser;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtTp;

    UserBO userBO = new UserBOImpl();

    public void initialize() {
        generateNextID();
        btnSaveAction();
        updateBtnAction();
    }

    private void generateNextID() {
        String userId = splitUserId(userBO.generateNextUserId());
        lblId.setText(userId);
    }

    private String splitUserId(String userId) {
        if (userId == null || userId.isEmpty() || !userId.matches("^u\\d+$")) {
            return "u001";
        } else {
            String numericPart = userId.substring(3);
            int numericValue = Integer.parseInt(numericPart);

            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);

            return "u00" + nextNumericPart;
        }
    }

    @FXML
    void tblUserMouseClickedOnAction(MouseEvent event) {

    }

    public void btnSaveAction() {
        btnAdd.setOnAction((e) -> {
            int index = tblUser.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                new Alert(Alert.AlertType.ERROR,"User "+lblId.getText()+" already exists").show();
                clearAllFields();
                return;
            }

            if (txtName.getText().isEmpty()||txtAddress.getText().isEmpty()||txtTp.getText().isEmpty()||dPickBD.getValue()==null){
                new Alert(Alert.AlertType.ERROR,"Text Fields Empty!").show();
                return;
            }

            //validation
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to add new User \""+lblId.getText()+"\" ?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                Boolean flag = userBO.saveUser(new UserDTO(lblId.getText(),txtName.getText(),txtAddress.getText(),dPickBD.getValue().toString(),txtTp.getText()));
                if (flag) {
                    clearAllFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "User Saved!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }
        });
    }

    private void clearAllFields() {
        txtName.clear();
        txtAddress.clear();
        txtTp.clear();
        txtSearch.clear();
        dPickBD.setValue(null);
        initialize();
    }

    public void updateBtnAction() {
        btnUpdate.setOnAction((e) -> {
            if (!tblUser.getSelectionModel().isEmpty()) {
                if (txtName.getText().isEmpty()||txtAddress.getText().isEmpty()||txtTp.getText().isEmpty()||dPickBD.getValue()==null){
                    new Alert(Alert.AlertType.ERROR,"Check empty fields!").show();
                    return;
                }
                //validation
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to update User \"" + lblId.getText() + "\" ?", yes, no).showAndWait();

                if (type.orElse(no) == yes) {
                    boolean flag = userBO.updateUser(new UserDTO(lblId.getText(),txtName.getText(),txtAddress.getText(),dPickBD.getValue().toString(),txtTp.getText()));
                    if (flag) {
                        new Alert(Alert.AlertType.CONFIRMATION, "User Updated").show();
                        clearAllFields();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Error!").show();
                    }
                }
            }else {
                new Alert(Alert.AlertType.INFORMATION, "Select a row in User Table!").show();
            }
        });
    }


}
