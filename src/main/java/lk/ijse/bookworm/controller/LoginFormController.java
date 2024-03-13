package lk.ijse.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static lk.ijse.bookworm.Launcher.rootNode;

public class LoginFormController {
    @FXML
    private JFXButton btnLogin;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard-form.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();
        stage.show();

        // Locate the AnchorPane within dashboard_form.fxml
        AnchorPane targetAnchorPane = (AnchorPane) rootNode.lookup("#subAnchorpane");

        // Load and set the content from another FXML file
        FXMLLoader subLoader = new FXMLLoader(getClass().getResource("/view/home-form.fxml"));
        AnchorPane subContent = null;
        try {
            subContent = subLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the subContent to the targetAnchorPane
        targetAnchorPane.getChildren().setAll(subContent);
    }
}
