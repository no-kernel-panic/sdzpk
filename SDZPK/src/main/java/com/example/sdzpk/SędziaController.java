package com.example.sdzpk;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.sdzpk.HelloApplication.webAPI;

public class SędziaController {


    private Sędzia sędzia;
    @FXML
    private Label welcomeText;

    public Sędzia getSędzia() {
        return sędzia;
    }

    public void setSędzia(Sędzia sędzia) {
        this.sędzia = sędzia;
    }


    @FXML
    protected void initialize() {

        Platform.runLater(() -> {
            String title = sędzia.isPłeć() ? "Mr" : "Mrs";
            welcomeText.setText("Welcome \n " + title + " " + sędzia.getImie() + " " + sędzia.getNazwisko());

        });


    }

    /**
     *
     * Opens up a new scene where the judge can see the requests he has assigned.
     *
     * @throws IOException
     */

    @FXML
    protected void checkPendingRequests() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkRequestsSędzia-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Check requests");
        PendingRequestsControllerSędzia controller = fxmlLoader.getController();
        controller.setSędzia(sędzia);
        stage.setScene(new Scene(root, 450, 450));
        stage.show();
    }
}



