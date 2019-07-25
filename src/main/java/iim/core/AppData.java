package iim.core;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AppData {

    //Fields for all scenes
    private final static ArrayList <Node> nodes = new ArrayList <>();
    @FXML
    protected AnchorPane root;
    @FXML
    protected Stage primaryStage;
    @FXML
    protected Label closeWindow;
    @FXML
    protected Button scanButton;
    @FXML
    protected Button webButtonHelp;
    double x;
    double y;

    //method to save the state of the Checkboxes
    protected static List <Node> getAllNodes(Parent root) {

        addAllDescendents(root, nodes);
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList <Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent)
                addAllDescendents((Parent) node, nodes);
        }
    }

    @FXML
    public void closeButtonAction() {
        primaryStage = (Stage) closeWindow.getScene().getWindow();
        primaryStage.close();
    }
    //Test als allgemeine Variable wie closeButtonAction();

//    public void moveWindow() {
//        root.getParent().setOnMousePressed(event -> {
//            x = primaryStage.getX() - event.getScreenX();
//            y = primaryStage.getY() - event.getScreenY();
//        });
//        //Lambda mouse event handler
//        root.setOnMouseDragged(event -> {
//            primaryStage.setX(event.getScreenX() + x);
//            primaryStage.setY(event.getScreenY() + y);
//        });
//    }

}

