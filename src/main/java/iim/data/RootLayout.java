package iim.data;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public abstract class RootLayout {
    //FXML Views
    public static final String MAIN_VIEW = "/view/mainView.fxml";
    public static final String REQUEST = "Checking for VMs at: ";
    public static final String CONFIG_VIEW = "/view/configurationViewEproc.fxml";
    public static final String UPDATE_VIEW = "/view/updateView.fxml";
    public static final String FINISH_VIEW = "/view/finishView.fxml";
    public static final String BOOTSTRAP_CSS = "org/kordamp/bootstrapfx/bootstrapfx.css";
    public static final String PROGRAM_TITLE = "Projekt Manager";
    @FXML
    protected AnchorPane root;
    @FXML
    protected Stage primaryStage;

    @FXML
    protected Button scanButton;
    @FXML
    protected Button webButtonHelp;
    //SSH
    //String user = "integra";
    public static final String PASSWORD = "integra";
    //SSH Commands
    public static final String COMMAND = "lsb_release-a";
    public static final String UPDATE = "git up";
    public RootLayout() {

    }

    public AnchorPane getRoot() {
        return root;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


}
