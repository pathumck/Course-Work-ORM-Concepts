package lk.ijse.bookworm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static AnchorPane rootNode;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        rootNode = FXMLLoader.load(this.getClass().getResource("/view/login-form.fxml"));
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
        stage.setTitle("BookWorm");
        stage.centerOnScreen();
        stage.show();
    }
}
