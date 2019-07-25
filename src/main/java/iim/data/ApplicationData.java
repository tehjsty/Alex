package iim.data;

/*
    Basic settings for debugging or later use.
    Approved settings will be stored in a configuration file.
 */
public interface ApplicationData {


    //SSH
    //String user = "integra";
    String password = "integra";

    //SSH Commands
    String command = "lsb_release-a";
    String update = "git up";

    //VMWare Debug Messages

    String request = "Checking for VMs at: ";


    //FXML Views
    String mainView = "/view/mainView.fxml";
    String eprocView = "/view/configurationViewEproc.fxml";
    String kdsView = "/view/configurationViewKds.fxml";
    String updateView = "/view/updateView.fxml";
    String finishView = "/view/finishView.fxml";
    String bootstrapCSS = "org/kordamp/bootstrapfx/bootstrapfx.css";
    String programTitle = "Projekt Manager";

}
