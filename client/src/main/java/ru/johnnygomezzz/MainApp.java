package ru.johnnygomezzz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/auth-dialog.fxml"));
        primaryStage.setTitle("CoolChat! - авторизация");
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.getIcons().add(new Image("/images/coolchat_logo_32.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
