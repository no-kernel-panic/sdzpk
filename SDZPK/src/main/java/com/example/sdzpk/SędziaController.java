package com.example.sdzpk;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SędziaController {


    public Sędzia getSędzia() {
        return sędzia;
    }

    public void setSędzia(Sędzia sędzia) {
        this.sędzia = sędzia;
    }

    private Sędzia sędzia;




    /*ObservableList<String> judges =
            FXCollections.observableArrayList();
*/

   /* @FXML
    private Label errorText;

    @FXML
    protected ComboBox lawyerBox;

*/
    @FXML
    protected void initialize(){

        Platform.runLater(() -> {
            String title = sędzia.isPłeć() ? "Mr" : "Mrs";
            welcomeText.setText("Welcome \n "+title+" " + sędzia.getImie()+" "+sędzia.getNazwisko());

                });


    }

    @FXML
    protected ComboBox requestsBox;

    @FXML
    private Label welcomeText;

    @FXML
    private Button checkPendingRequests;

    @FXML
    protected void checkPendingRequests() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkRequestsSędzia-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Check requests");
            PendingRequestsControllerSędzia controller = fxmlLoader.getController();
            controller.setSędzia(sędzia);
            stage.setScene(new Scene(root, 450, 450));
            //webAPI.openStageAsPopup(stage);
            stage.show();
        }
    }



