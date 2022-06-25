package com.example.sdzpk;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.List;


public class SendRequestController {

    @FXML
    protected ComboBox stanBox;
    private final ObservableList<Enum> stanList = FXCollections.observableArrayList(Prośba_do_sędziego.Stan.AresztDomowy,
            Prośba_do_sędziego.Stan.Przepustka, Prośba_do_sędziego.Stan.WyjazdZaGranicę);
    private List<Prośba_do_sędziego> Re;
    private Adwokat adwokat;
    private Sędzia sędzia;
    private Oskarżony oskarżony;
    @FXML
    private Label confirmationText;
    @FXML
    private TextArea textArea;
    @FXML
    private Label accusedDescription;

    public Adwokat getAdwokat() {
        return adwokat;
    }

    public void setAdwokat(Adwokat adwokat) {
        this.adwokat = adwokat;
    }

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

    @FXML
    protected void initialize() {
        stanBox.setItems(stanList);

        Platform.runLater(() -> accusedDescription.setText("Request for: " + sędzia.getImie() + " " + sędzia.getNazwisko() + "\n" +
                "Accused: " +
                oskarżony.toString()));
    }

    @FXML
    public void submitRequest() {
        if (stanBox.getValue() != null) {
            Prośba_do_sędziego.Stan stan = (Prośba_do_sędziego.Stan) stanBox.getValue();
            if (adwokat.createRequest(textArea.getText(), stan, oskarżony, sędzia)) {
                confirmationText.setText("The request has been sent to " + getSędzia().getImie() + " " + getSędzia().getNazwisko());
            } else {
                confirmationText.setText("Error. There is another request" +
                        "for the same accused already in the system.");
            }
        }
    }

}