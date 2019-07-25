package iim.controller;

import iim.connection.NetworkCheck;
import iim.connection.SSHConnection;
import iim.core.AppData;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;

/*
    Controller, to handle the network scan and add events to the GUI controls

    @author jplennis

 */

public class ConfigController extends AppData {


    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();


    public void buttonAction(ActionEvent event) {

        String url = "https://eclipse.org";

//        primaryStage.setScene(new Scene(webView));
        // Create the VBox
        VBox root = new VBox();
        // Add the WebView to the VBox
        root.getChildren().add(webView);
        webEngine.load(url);

    }

    public void handleButtonAction(MouseEvent event) throws IOException, InterruptedException {
        NetworkCheck networkCheck = new NetworkCheck();
        SSHConnection sshConnection = new SSHConnection(networkCheck.getIpList());
        sshConnection.connectionToSsh("cd workspace/art/ ; ls\n");
        sshConnection.readOutput();
        sshConnection.closeConnection();


    }

}
