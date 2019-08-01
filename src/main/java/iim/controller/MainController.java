package iim.controller;

import iim.core.AppData;
import iim.data.RootLayout;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

/*
    //TODO for later JavaFX GUI use
 */
public class MainController extends RootLayout {
    @FXML
    private Label eprocField;
    @FXML
    private Label kdsField;


    public void handleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == eprocField) {
            primaryStage = (Stage) eprocField.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource(CONFIG_VIEW));
        } else {
            primaryStage = (Stage) kdsField.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource(CONFIG_VIEW));
        }
        primaryStage.setScene(new Scene(root));
        root.getStylesheets().add(BOOTSTRAP_CSS);
        primaryStage.show();
    }

}
