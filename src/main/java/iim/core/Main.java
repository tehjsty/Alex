package iim.core;

import iim.connection.NetworkCheck;
import iim.connection.SSHConnection;
import iim.controller.ConfigController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static iim.data.RootLayout.*;

/**
 * Tool to analyze projects
 * features can be added and/or removed via FXML and in the correct class
 * This class will load the main JavaFX window and provide the basic functions
 * <p>
 * In the current state (summer 2019) it is required
 * to use a command in the vm-options of IntelliJ to use JavaFX properly:
 * https://openjfx.io/openjfx-docs/#IDE-Intellij
 *
 * @author jplennis
 * @version 0.1
 */


public class Main extends Application {
    private double x;
    private double y;

    public static void main(String[] args) {



        launch(args);
    }

    /**
     * The properties and behavior of the main window and the stage are defined here
     *
     * @param primaryStage
     * @throws Exception
     */

    @Override
    public void start(Stage primaryStage) throws Exception {


        AnchorPane root = FXMLLoader.load(getClass().getResource(NEW_TEST));
        //Lambda mouse event handler
        root.setOnMousePressed(event -> {
            x = primaryStage.getX() - event.getScreenX();
            y = primaryStage.getY() - event.getScreenY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + x);
            primaryStage.setY(event.getScreenY() + y);
        });


        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle(PROGRAM_TITLE);
        primaryStage.setResizable(false);
        // primaryStage.getIcons().add(new Image("/images/logo.jpg"));
        primaryStage.setScene(new Scene(root));
        root.getStylesheets().add(BOOTSTRAP_CSS);
        primaryStage.show();


    }

}
