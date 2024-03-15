package lk.ijse.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.bo.custom.impl.UserBOImpl;
import lk.ijse.bookworm.dto.BookDTO;
import lk.ijse.bookworm.dto.UserDTO;
import lk.ijse.bookworm.dto.tm.BookTM;
import lk.ijse.bookworm.dto.tm.UserTM;
import org.hibernate.HibernateException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserFormController {@FXML
private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<UserTM, Button> colAction;

    @FXML
    private TableColumn<UserTM, String> colAddress;

    @FXML
    private TableColumn<UserTM, String> colBirthDay;

    @FXML
    private TableColumn<UserTM, String> colId;

    @FXML
    private TableColumn<UserTM, String> colName;

    @FXML
    private TableColumn<UserTM, String> colTp;

    @FXML
    private DatePicker dPickBD;

    @FXML
    private Label lblId;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtTp;

    private ObservableList<UserTM> toTable;

    UserBO userBO = new UserBOImpl();

    public void initialize() {
        generateNextID();
        btnSaveAction();
        updateBtnAction();
        vitualize();
        setTabelBook();
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
        int index = tblUser.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        lblId.setText(colId.getCellData(index).toString());
        txtName.setText(colName.getCellData(index).toString());
        txtAddress.setText(colAddress.getCellData(index).toString());
        txtTp.setText(colTp.getCellData(index).toString());
        dPickBD.setValue(LocalDate.parse(colBirthDay.getCellData(index).toString()));
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

    private void setTabelBook() {
        List<UserDTO> userDTOS = userBO.getAllUsers();
        List<UserTM> tms = new ArrayList<>();

        for (UserDTO userDTO : userDTOS) {
            Button deleteButton = new Button("Delete");
            deleteButton.setCursor(Cursor.HAND);
            deleteButton.setStyle("-fx-background-color: #e84118; -fx-text-fill: #ffffff;");
            setRemoveBtnAction(deleteButton);
            tms.add(new UserTM(userDTO.getUserId(),userDTO.getUserName(),userDTO.getUserAddress(),userDTO.getUserBirthDay(),userDTO.getUserTp(),deleteButton));
        }

        toTable = FXCollections.observableArrayList(tms);
        tblUser.setItems(toTable);
    }

    private void setRemoveBtnAction(Button deleteButton) {
        deleteButton.setOnAction((e) -> {
            Integer index = tblUser.getSelectionModel().getSelectedIndex();
            if (index <= -1) {
                new Alert(Alert.AlertType.ERROR, "Please select a User table row to delete a Book!").show();
                return;
            }
            String id = colId.getCellData(index).toString();
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete User \"" + id + "\" ?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                Boolean flag = null;
                try {
                    flag = userBO.deleteUser(id);
                }catch (HibernateException ex) {
                    new Alert(Alert.AlertType.ERROR, "Error!").show();
                }
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
        colBirthDay.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
        colTp.setCellValueFactory(new PropertyValueFactory<>("tp"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));

    }



}
