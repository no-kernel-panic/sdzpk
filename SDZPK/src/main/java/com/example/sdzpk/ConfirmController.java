package com.example.sdzpk;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmController {


    private Oskarżony oskarżony;
    private Sędzia sędzia;
    private Prośba_do_sędziego prośbaDoSędziego;
    @FXML
    private Label confirmationText;
    @FXML
    private Button ok;

    private ComboBox requestsBox;

    public Oskarżony getOskarżony() {
        return oskarżony;
    }

    public void setOskarżony(Oskarżony oskarżony) {
        this.oskarżony = oskarżony;
    }

    public Sędzia getSędzia() {
        return sędzia;
    }

    public void setSędzia(Sędzia sędzia) {
        this.sędzia = sędzia;
    }

    public Prośba_do_sędziego getProśbaDoSędziego() {
        return prośbaDoSędziego;
    }

    public void setProśbaDoSędziego(Prośba_do_sędziego prośbaDoSędziego) {
        this.prośbaDoSędziego = prośbaDoSędziego;
    }


    @FXML
    protected void initialize() {

        Platform.runLater(() -> {
            confirmationText.setText(prośbaDoSędziego.getOskarżony().getImie() + " "
                    + prośbaDoSędziego.getOskarżony().getNazwisko() + "\n" +
                    "has now status ->" + prośbaDoSędziego.getStan() + "\n");
        });


    }
    /**
     * Accepts a request and persists it in the system by calling the following method
     *
     * {@link com.example.sdzpk.Sędzia#confirmRequest(Prośba_do_sędziego)}
     *
     */
    @FXML
    protected void confirmRequest() {
        Stage stage = (Stage) ok.getScene().getWindow();
        if (getSędzia().confirmRequest(prośbaDoSędziego)) {
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



