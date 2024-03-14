package lk.ijse.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.ijse.bookworm.bo.custom.UserBO;
import lk.ijse.bookworm.bo.custom.impl.UserBOImpl;

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
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

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
}
