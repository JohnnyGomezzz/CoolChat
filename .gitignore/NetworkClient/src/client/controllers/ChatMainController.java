package client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import client.NetworkClient;
import client.models.Network;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ChatMainController {

    @FXML
    public ListView<String> contacts;
    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatWindow;
    @FXML
    private TextField messageWindow;

    private Network network;
    private NetworkClient mainApp;
    private String selectedRecipient;

    public void setMainApp(NetworkClient mainApp)
    {
        this.mainApp = mainApp;
    }

    public void initialize()
    {
        sendButton.setOnAction(event -> ChatMainController.this.sendMessage());
        messageWindow.setOnAction(event -> ChatMainController.this.sendMessage());

        contacts.setCellFactory(lv -> {
            MultipleSelectionModel<String> selectionModel = contacts.getSelectionModel();
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                contacts.requestFocus();
                if (! cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (selectionModel.getSelectedIndices().contains(index)) {
                        selectionModel.clearSelection(index);
                        selectedRecipient = null;
                    } else {
                        selectionModel.select(index);
                        selectedRecipient = cell.getItem();
                    }
                    event.consume();
                }
            });
            return cell ;
        });
    }

    private void sendMessage() {
        String message = messageWindow.getText();
        messageWindow.clear();

        if(message.isBlank()) {
            return;
        }

        appendMessage("Я: " + message);
        messageWindow.clear();

        try {
            if (selectedRecipient != null) {
                network.sendPrivateMessage(message, selectedRecipient);
            }
            else {
                network.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "Ошибка при отправке сообщения";
            NetworkClient.showErrorMessage(e.getMessage(), errorMessage);
        }

    }

    public void setNetwork(Network network)
    {
        this.network = network;
    }

    public void appendMessage(String message)
    {
        String timestamp = DateFormat.getInstance().format(new Date());
        chatWindow.appendText(timestamp);
        chatWindow.appendText(System.lineSeparator());
        chatWindow.appendText(message);
        chatWindow.appendText(System.lineSeparator());
        chatWindow.appendText(System.lineSeparator());
    }

    public void showError(String title, String message) {
        NetworkClient.showErrorMessage(message, title);
    }

    @FXML
    private void handleAbout() {
        mainApp.showInfo();
    }

    @FXML
    private void handleClear()
    {
        chatWindow.clear();
    }

    @FXML
    private void handleExit()
    {
        System.exit(0);
    }

    public void updateUsers(List<String> users) {
        contacts.setItems(FXCollections.observableArrayList(users));
    }
}
