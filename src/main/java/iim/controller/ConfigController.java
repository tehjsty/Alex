package iim.controller;

import iim.connection.NetworkCheck;
import iim.connection.SSHConnection;
import iim.data.RootLayout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;

/*
    Controller, to handle the network scan and add events to the GUI controls

    @author jplennis

 */

public class ConfigController extends RootLayout {


    @FXML
    protected Button scanButton;
    private WebView webView = new WebView();
    private WebEngine webEngine = webView.getEngine();

    public Thread newThread() {
        return new Thread(() -> {
            NetworkCheck networkCheck = new NetworkCheck();
            networkCheck.scanNetwork();
            SSHConnection sshConnection = new SSHConnection(networkCheck.getIpList());
            try {
                sshConnection.connectionToSsh("cd workspace/ ; ls -d */\n");
                sshConnection.readOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void buttonAction(ActionEvent event) {

        String url = "https://eclipse.org";

//        primaryStage.setScene(new Scene(webView));
        // Create the VBox
        VBox root = new VBox();
        // Add the WebView to the VBox
        root.getChildren().add(webView);
        webEngine.load(url);

    }

    public void handleButtonAction(MouseEvent event) throws IOException {

        scanButton.getScene().getWindow();
        FXMLLoader.load(getClass().getResource(TEST_VIEW));

        this.newThread().start();


//        sshConnection.closeConnection();

    }


}
