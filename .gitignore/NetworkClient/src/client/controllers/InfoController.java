package client.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class InfoController {

    private Stage infoStage;

    public void setInfoStage(Stage infoStage)
    {
        this.infoStage = infoStage;
    }

    @FXML
    private void handleOk()
    {
        infoStage.close();
    }
}
