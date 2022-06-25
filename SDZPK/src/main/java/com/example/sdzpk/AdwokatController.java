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


public class AdwokatController {

    private List<Oskarżony> Os;

    public void setSe(List<Sędzia> se) {
        Se = se;
    }

    private List<Sędzia> Se;

    public Oskarżony getOskarżony() {
        return oskarżony;
    }

    public void setOskarżony(Oskarżony oskarżony) {
        this.oskarżony = oskarżony;
    }

    private  Oskarżony oskarżony;

    public Adwokat getAdwokat() {
        return adwokat;
    }

    public void setAdwokat(Adwokat adwokat) {
        this.adwokat = adwokat;
    }

    private Adwokat adwokat;

    ObservableList<String> accusedNames =
            FXCollections.observableArrayList();

    public void setJudgeNames(ObservableList<String> judgeNames) {
        this.judgeNames = judgeNames;
    }

    ObservableList<String> judgeNames =
            FXCollections.observableArrayList();

    public ComboBox getAccusedBox() {
        return accusedBox;
    }

    @FXML
    protected ComboBox accusedBox;

    public ComboBox getJudgeBox() {
        return judgeBox;
    }

    @FXML
    protected ComboBox judgeBox;

    @FXML
    protected void initialize(){
        Session session =  HelloApplication.createSession();
        Os = session.createQuery("select oskarżony from Oskarżony as oskarżony").list();
        session.close();
        for(Oskarżony o : Os){
            if(!accusedNames.contains(o)) {
                accusedNames.add(o.getImie() + " " + o.getNazwisko());
            }
        }
        accusedBox.setItems(accusedNames);

        Platform.runLater(() -> {
            String title = adwokat.isPłeć() ? "Mr" : "Mrs";

            welcomeText.setText("Welcome \n" +title+ " "+ adwokat.getImie() + " " + adwokat.getNazwisko());
            judgeBox.setItems(judgeNames);

        });


    }
    @FXML
    private Label welcomeText;

    @FXML
    private Label selectAccused;

    @FXML
    private Button createrequest;

    @FXML
    private Button checkRequests;

    @FXML
    private Button nextToJudgeSelection;

    @FXML
    private Button sendRequest;


    @FXML
    protected void accusedSelection() throws IOException {
        if (getAccusedBox().getValue() != null) {
            String accusedName = String.valueOf(getAccusedBox().getValue());
            oskarżony = Os.stream().filter(e -> (e.getImie()+" "+e.getNazwisko()).equals(accusedName)).findFirst().orElse(null);
            nextToJudgeSelection.setVisible(false);
            accusedBox.setVisible(false);
            selectAccused.setText("Select judge");
            judgeBox.setVisible(true);
            sendRequest.setVisible(true);

        }
    }

    @FXML
    protected void comboJudgeSelected() throws IOException {
        if (getJudgeBox().getValue() != null) {
            String judgeName = String.valueOf(getJudgeBox().getValue());
            Sędzia sędzia = Se.stream().filter(e -> (e.getImie() + " " + e.getNazwisko()).equals(judgeName)).findFirst().orElse(null);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sendrequest-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("New request");
            SendRequestController controller = fxmlLoader.getController();
            controller.setSędzia(sędzia);
            controller.setOskarżony(oskarżony);
            controller.setAdwokat(adwokat);
            stage.setScene(new Scene(root, 450, 450));
            //webAPI.openStageAsPopup(stage);
            stage.show();
        }
    }
    @FXML
    protected void createRequestButton() {
        createrequest.setVisible(false);
        selectAccused.setText("Select accused");
        accusedBox.setVisible(true);
        nextToJudgeSelection.setVisible(true);
    }



    @FXML
    protected void checkRequestButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkRequestsAdwokat-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Check requests");
        PendingRequestsControllerAdwokat controller = fxmlLoader.getController();
        controller.setAdwokat(adwokat);
        stage.setScene(new Scene(root, 450, 450));
        //webAPI.openStageAsPopup(stage);
        stage.show();
    }

}