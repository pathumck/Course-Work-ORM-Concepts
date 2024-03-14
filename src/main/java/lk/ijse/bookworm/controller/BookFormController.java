package lk.ijse.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lk.ijse.bookworm.bo.custom.BookBO;
import lk.ijse.bookworm.bo.custom.impl.BookBOImpl;
import lk.ijse.bookworm.dto.BookDTO;
import lk.ijse.bookworm.dto.BranchDTO;
import lk.ijse.bookworm.entity.Branch;

import java.util.Arrays;
import java.util.Optional;

public class BookFormController {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<String> cmbBranch;

    @FXML
    private JFXComboBox<String> cmbGenre;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private Label lblId;

    @FXML
    private Label lblStatus;

    @FXML
    private TableView<?> tblBook;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtTitle;

    BookBO bookBO = new BookBOImpl();

    public void initialize() {
        generateNextID();
        btnSaveAction();
        cmbGenre.getItems().addAll(Arrays.asList("Mystery", "Romance", "Science Fiction", "Fantasy", "Thriller", "Horror", "Historical Fiction", "Biography", "Self-Help", "Poetry"));
        cmbBranch.getItems().addAll(Arrays.asList("B001", "Romance", "Science Fiction", "Fantasy", "Thriller", "Horror", "Historical Fiction", "Biography", "Self-Help", "Poetry"));
    }

    private void generateNextID() {
        String bookId = splitBookId(bookBO.generateNextBookId());
        lblId.setText(bookId);
    }

    private String splitBookId(String bookId) {
        if (bookId == null || bookId.isEmpty() || !bookId.matches("^b\\d+$")) {
            return "b001";
        } else {
            String numericPart = bookId.substring(3);
            int numericValue = Integer.parseInt(numericPart);

            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);

            return "b00" + nextNumericPart;
        }
    }

    public void btnSaveAction() {
        btnAdd.setOnAction((e) -> {
            int index = tblBook.getSelectionModel().getSelectedIndex();
            if (index > -1) {
                new Alert(Alert.AlertType.ERROR,"Book "+lblId.getText()+" already exists").show();
                clearAllFields();
                return;
            }

            if (txtAuthor.getText().isEmpty()||txtTitle.getText().isEmpty()||cmbGenre.getValue()==null||cmbBranch.getValue()==null){
                new Alert(Alert.AlertType.ERROR,"Text Fields Empty!").show();
                return;
            }

            //validation
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to add new Book \""+lblId.getText()+"\" ?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                Boolean flag = bookBO.saveBook(new BookDTO(lblId.getText(),txtTitle.getText(),txtAuthor.getText(),cmbGenre.getValue(),cmbBranch.getValue(),"yes"));
                if (flag) {
                    clearAllFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Book Saved!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }
        });
    }

    private void clearAllFields() {
        txtTitle.clear();
        txtAuthor.clear();
        cmbGenre.getItems().clear();
        cmbBranch.getItems().clear();
        txtSearch.clear();
        initialize();
    }

    @FXML
    void tblBookMouseClickOnAction(MouseEvent event) {

    }


}
