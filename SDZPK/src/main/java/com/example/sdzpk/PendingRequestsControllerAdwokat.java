package com.example.sdzpk;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;

public class PendingRequestsControllerAdwokat {


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

    public Adwokat adwokat;

        public Oskarżony oskarżony;

        public Sędzia getSędzia() {
            return sędzia;
        }

        public void setSędzia(Sędzia sędzia) {
            this.sędzia = sędzia;
        }

        private Sędzia sędzia;


        private ObservableList<String> requests =
                FXCollections.observableArrayList();

        private Prośba_do_sędziego prośbaDoSędziego;

        @FXML
        protected void initialize(){

            Platform.runLater(() -> {

                for (Prośba_do_sędziego re : adwokat.getProśba_do_sędziegoWysyłaList()) {
                    requests.add(re.getId()+"    "+ re.getOpis().substring(0, Math.min(re.getOpis().length(), 15)));
                }
                requestsBox.setItems(requests);

                for (String request: requests) {
                    System.out.println(request);
                }

            });

        }

        @FXML
        protected ComboBox requestsBox;

        @FXML
        private Label issueDescription;

        @FXML
        private Button withdrawnButton;

        @FXML
        public void nextButtonAction(){
            if(requestsBox.getValue() != null){
                String opis = (String) requestsBox.getValue();
             prośbaDoSędziego = adwokat.getProśba_do_sędziegoWysyłaList().stream()
                                                    .filter(e -> e.getId() == Integer.parseInt(opis.substring(0,4).trim()))
                                                    .findFirst()
                                                    .orElse(null);
                oskarżony = prośbaDoSędziego.getOskarżony();
            issueDescription.setText("Sent to: "+prośbaDoSędziego.getPracownikOtrzyma().getImie()+" "
                    +prośbaDoSędziego.getPracownikOtrzyma().getNazwisko() +"\n"+ "Request id: " + prośbaDoSędziego.getId()+ "\n" +
                            "Request for: "+prośbaDoSędziego.getStan()+ "\n" +
                          "Accused: "+ oskarżony.getImie()+" "
                                     +  oskarżony.getNazwisko()+ "\n" +
                            "In state: "+ oskarżony.getStan() + "\n" +
                              "Sent by: "+ prośbaDoSędziego.getPracownikWysyła().getImie()+ " "+
                                prośbaDoSędziego.getPracownikWysyła().getNazwisko() +"\n" +
                                        "Description: "+ prośbaDoSędziego.getOpis() );
                     withdrawnButton.setVisible(true);
            }
        }

        public void withdrawnButtonAction() throws IOException {
           adwokat.withdrawnRequest(prośbaDoSędziego);
            issueDescription.setText("The request has been withdrawn");

        }



}
