package ru.johnnygomezzz.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthDialogController implements Initializable {
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;

    private Socket socket;
    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8189;

    private DataInputStream in;
    private DataOutputStream out;

    @FXML
    public void checkAuth() {
        String login = loginField.getText();
        String password = passwordField.getText();

        if (login.isEmpty() || password.isEmpty()) {
            showErrorMessage("Введите логин и пароль", "Поля не должны быть пустыми");
            return;
        }
        if (socket == null || socket.isClosed()) {
            connect();
        }

        ///==============///
        login = loginField.getText().trim();
        ///==============///

        String msg = String.format("/auth %s %s", loginField.getText().trim(), passwordField.getText().trim());
        try {
            out.writeUTF(msg);
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        String authErrorMessage = network.sendAuthCommand(login, password);
//        if (authErrorMessage == null){
//            openChat();
//        }
//        else {
//            showErrorMessage(authErrorMessage, "Ошибка авторизации");
//        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        createRegWindow();
//        Platform.runLater(() -> {
//            stage = (Stage) textField.getScene().getWindow();
//            stage.setOnCloseRequest(event -> {
//                System.out.println("bye");
//                if (socket != null && !socket.isClosed()) {
//                    try {
//                        out.writeUTF("/end");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        });
//        setAuthenticated(false);
    }

    @FXML
    public static void showErrorMessage(String message, String errorMessage)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(errorMessage);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void connect() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void openChat(){
//        authStage.close();
//        primaryStage.show();
//        primaryStage.setTitle("CoolChat! - " + network.getUsername());
//        network.waitMessage(chatMainController);
//    }
}
