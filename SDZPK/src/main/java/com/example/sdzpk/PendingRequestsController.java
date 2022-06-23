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

import java.io.IOException;
import java.util.List;

public class PendingRequestsController {


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
//                welcomeText.setText("Request for : " + sędzia.getProśba_do_sędziegoOtrzymaList().get(0).getOpis() + " " + sędzia.getNazwisko());
                for (Prośba_do_sędziego re : sędzia.getProśba_do_sędziegoOtrzymaList()) {
                    requests.add(re.getId()+"    "+ re.getOpis().substring(0, Math.min(re.getOpis().length(), 15)));
                }
                requestsBox.setItems(requests);
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
        private Label issueDescription;

        @FXML
        private Button nextButton;

        @FXML
        private Button confirmButton;

        @FXML
        private Button rejectButton;

        @FXML
        public void nextButtonAction(){
            if(requestsBox.getValue() != null){
                String opis = (String) requestsBox.getValue();
            Prośba_do_sędziego prośbaDoSędziego = sędzia.getProśba_do_sędziegoOtrzymaList().stream()
                                                    .filter(e -> e.getId() == Integer.parseInt(opis.substring(0,4).trim()))
                                                    .findFirst()
                                                    .orElse(null);
            nextButton.setVisible(false);
            issueDescription.setText("Request id: " + prośbaDoSędziego.getId()+ "\n" +
                            "Request for: "+prośbaDoSędziego.getStan()+ "\n" +
                          "Accused: "+ prośbaDoSędziego.getOskarżony().getImie()+" "
                                     +  prośbaDoSędziego.getOskarżony().getNazwisko()+ "\n" +
                            "In state: "+ prośbaDoSędziego.getOskarżony().getStan() + "\n" +
                              "Sent by: "+ prośbaDoSędziego.getPracownikWysyła().getImie()+ " "+
                                prośbaDoSędziego.getPracownikWysyła().getNazwisko() +"\n" +
                                        "Description: "+ prośbaDoSędziego.getOpis() );
            confirmButton.setVisible(true);
            rejectButton.setVisible(true);
            }
        }



}
