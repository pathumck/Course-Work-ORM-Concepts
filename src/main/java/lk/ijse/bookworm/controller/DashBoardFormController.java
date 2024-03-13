package lk.ijse.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

import java.io.IOException;

public class DashBoardFormController {
    @FXML
    private JFXButton btnBook;

    @FXML
    private JFXButton btnBorrow;

    @FXML
    private JFXButton btnBranch;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnReturn;

    @FXML
    private JFXButton btnUser;

    @FXML
    private AnchorPane subAnchorpane;

    @FXML
    void btnBookOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/book-form.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.subAnchorpane.getChildren().clear();
        this.subAnchorpane.getChildren().add(rootNode);
    }

    @FXML
    void btnBorrowOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/borrow-form.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.subAnchorpane.getChildren().clear();
        this.subAnchorpane.getChildren().add(rootNode);
    }

    @FXML
    void btnBranchOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/branch-form.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.subAnchorpane.getChildren().clear();
        this.subAnchorpane.getChildren().add(rootNode);
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/home-form.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.subAnchorpane.getChildren().clear();
        this.subAnchorpane.getChildren().add(rootNode);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/return-form.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.subAnchorpane.getChildren().clear();
        this.subAnchorpane.getChildren().add(rootNode);
    }

    @FXML
    void btnUserOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/user-form.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.subAnchorpane.getChildren().clear();
        this.subAnchorpane.getChildren().add(rootNode);
    }

}
