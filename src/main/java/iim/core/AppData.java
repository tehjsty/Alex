package iim.core;

import iim.connection.NetworkCheck;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AppData {
    public Stage primaryStage;
    public AnchorPane root;

    //Fields for all scenes
    private static final ArrayList <Node> nodes = new ArrayList <>();


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

