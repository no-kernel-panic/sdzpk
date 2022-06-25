package com.example.sdzpk;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class PendingRequestsControllerAdwokat {


    private final ObservableList<String> requests =
            FXCollections.observableArrayList();
    public Adwokat adwokat;
    public Oskarżony oskarżony;
    @FXML
    protected ComboBox requestsBox;
    private Sędzia sędzia;
    private Prośba_do_sędziego prośbaDoSędziego;
    @FXML
    private Label issueDescription;
    @FXML
    private Button withdrawnButton;

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

        Platform.runLater(() -> {

            for (Prośba_do_sędziego re : adwokat.getProśba_do_sędziegoWysyłaList()) {
                requests.add(re.getId() + "    " + re.getOpis().substring(0, Math.min(re.getOpis().length(), 15)));
            }
            requestsBox.setItems(requests);

            for (String request : requests) {
                System.out.println(request);
            }

        });

    }

    @FXML
    public void nextButtonAction() {
        if (requestsBox.getValue() != null) {
            String opis = (String) requestsBox.getValue();
            prośbaDoSędziego = adwokat.getProśba_do_sędziegoWysyłaList().stream()
                    .filter(e -> e.getId() == Integer.parseInt(opis.substring(0, 4).trim()))
                    .findFirst()
                    .orElse(null);
            oskarżony = prośbaDoSędziego.getOskarżony();
            issueDescription.setText("Sent to: " + prośbaDoSędziego.getPracownikOtrzyma().getImie() + " "
                    + prośbaDoSędziego.getPracownikOtrzyma().getNazwisko() + "\n" + "Request id: " + prośbaDoSędziego.getId() + "\n" +
                    "Request for: " + prośbaDoSędziego.getStan() + "\n" +
                    "Accused: " + oskarżony.getImie() + " "
                    + oskarżony.getNazwisko() + "\n" +
                    "In state: " + oskarżony.getStan() + "\n" +
                    "Sent by: " + prośbaDoSędziego.getPracownikWysyła().getImie() + " " +
                    prośbaDoSędziego.getPracownikWysyła().getNazwisko() + "\n" +
                    "Description: " + prośbaDoSędziego.getOpis());
            withdrawnButton.setVisible(true);
        }
    }

    public void withdrawnButtonAction() {
        if (requestsBox.getValue() != null) {
            requestsBox.getItems().remove(prośbaDoSędziego.getId() + "    " +
                    prośbaDoSędziego.getOpis().substring(0,
                            Math.min(prośbaDoSędziego.getOpis().length(), 15)));
            adwokat.withdrawnRequest(prośbaDoSędziego);
            issueDescription.setText("The request has been withdrawn");

        }
    }

}
