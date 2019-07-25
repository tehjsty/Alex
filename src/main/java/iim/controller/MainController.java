package iim.controller;

import iim.core.AppData;
import iim.data.ApplicationData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/*
    //TODO for later JavaFX GUI use
 */
public class MainController extends AppData implements ApplicationData {
    @FXML
    private Label eprocField;
    @FXML
    private Label kdsField;


    public void handleButtonAction(MouseEvent event) throws IOException {


        if (event.getSource() == eprocField) {
            primaryStage = (Stage) eprocField.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource(eprocView));
        } else {
            primaryStage = (Stage) kdsField.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource(kdsView));
        }
        primaryStage.setScene(new Scene(root));
        root.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.show();
    }

}
