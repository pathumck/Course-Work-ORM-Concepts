package lk.ijse.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
}
