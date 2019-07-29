package iim.controller;

import iim.data.RootLayout;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Close extends RootLayout {



    @FXML
    private Label closeWindow;
    @FXML
    public void closeButtonAction() {
        primaryStage = (Stage) closeWindow.getScene().getWindow();
        primaryStage.close();
    }
}
