package com.example.sdzpk;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

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
            welcomeText.setText("Welcome : " + sędzia.getImie()+" "+sędzia.getNazwisko());

                });
       /* Session session =  HelloApplication.createSession();
        List<Adwokat> Ad = session.createQuery("select adwokat from Adwokat as adwokat").list();
        List<Sędzia> Se = session.createQuery("select sędzia from Sędzia as sędzia").list();
        session.close();
*/

    }

    @FXML
    protected ComboBox requestsBox;

    @FXML
    private Label welcomeText;

    @FXML
    private Button checkPendingRequests;

    @FXML
    protected void checkPendingRequests() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkRequests-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Requests Stage");
            PendingRequestsController controller = fxmlLoader.getController();
            controller.setSędzia(sędzia);
            stage.setScene(new Scene(root, 450, 450));
            //webAPI.openStageAsPopup(stage);
            stage.show();
        }
    }



