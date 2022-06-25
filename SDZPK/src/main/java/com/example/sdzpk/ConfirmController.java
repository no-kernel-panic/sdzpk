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


    @FXML
    protected void initialize(){

        Platform.runLater(() -> {
            confirmationText.setText(prośbaDoSędziego.getOskarżony().getImie()+" "
                    +  prośbaDoSędziego.getOskarżony().getNazwisko()+ "\n" +
                    "has now status ->"+ prośbaDoSędziego.getStan() + "\n");
                });


    }

    @FXML
    private Label withdrawnText;

    @FXML
    private Label confirmationText;

    @FXML
    private Button ok;

    @FXML
    private Button withdrawn;

    private ComboBox requestsBox;

    @FXML
    protected void confirmRequest() {
           Stage stage = (Stage) ok.getScene().getWindow();
          if(  getSędzia().confirmRequest(prośbaDoSędziego, oskarżony, prośbaDoSędziego.getStan())) {
              ;
              requestsBox.getItems().remove(prośbaDoSędziego.getId() + "    " +
                      prośbaDoSędziego.getOpis().substring(0,
                              Math.min(prośbaDoSędziego.getOpis().length(), 15)));
              stage.close();
          } else {
              confirmationText.setText("Error");
          }
        }
    @FXML
    protected void withdrawnRequest() {
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

    public void setCombobox(ComboBox requestsBox) {
        this.requestsBox = requestsBox;
    }
}



