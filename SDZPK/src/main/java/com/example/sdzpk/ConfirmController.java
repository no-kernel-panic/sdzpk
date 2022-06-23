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

public class ConfirmController {


    public Oskarżony getOskarżony() {
        return oskarżony;
    }

    public void setOskarżony(Oskarżony oskarżony) {
        this.oskarżony = oskarżony;
    }

    private Oskarżony oskarżony;

    public Sędzia getSędzia() {
        return sędzia;
    }

    public void setSędzia(Sędzia sędzia) {
        this.sędzia = sędzia;
    }

    private Sędzia sędzia;

    private Prośba_do_sędziego prośbaDoSędziego;

    public Prośba_do_sędziego getProśbaDoSędziego() {
        return prośbaDoSędziego;
    }

    public void setProśbaDoSędziego(Prośba_do_sędziego prośbaDoSędziego) {
        this.prośbaDoSędziego = prośbaDoSędziego;
    }
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
            confirmationText.setText("Request id " + prośbaDoSędziego.getId()+ "\n" +
                    " for: "+ prośbaDoSędziego.getOskarżony().getImie()+" "
                    +  prośbaDoSędziego.getOskarżony().getNazwisko()+ "\n" +
                    "has been granted and the accused is now in status: "+ prośbaDoSędziego.getStan() + "\n");
                });
       /* Session session =  HelloApplication.createSession();
        List<Adwokat> Ad = session.createQuery("select adwokat from Adwokat as adwokat").list();
        List<Sędzia> Se = session.createQuery("select sędzia from Sędzia as sędzia").list();
        session.close();
*/

    }

    @FXML
    private Label withdrawnText;

    @FXML
    private Label confirmationText;

    @FXML
    private Button ok;

    @FXML
    private Button withdrawn;

    @FXML
    protected void confirmRequest() {
           Stage stage = (Stage) ok.getScene().getWindow();
            getSędzia().confirmRequest(prośbaDoSędziego, oskarżony, prośbaDoSędziego.getStan());
            stage.close();
        }
    @FXML
    protected void withdrawnRequest() {
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }
    }



