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

import static com.example.sdzpk.HelloApplication.webAPI;


public class AdwokatController {

    @FXML
    protected ComboBox accusedBox;
    @FXML
    protected ComboBox judgeBox;
    ObservableList<String> accusedNames =
            FXCollections.observableArrayList();
    ObservableList<String> judgeNames =
            FXCollections.observableArrayList();
    private List<Oskarżony> Os;
    private List<Sędzia> Se;
    private Oskarżony oskarżony;
    private Adwokat adwokat;
    @FXML
    private Label welcomeText;
    @FXML
    private Label selectAccused;
    @FXML
    private Button createrequest;
    @FXML
    private Button nextToJudgeSelection;
    @FXML
    private Button sendRequest;

    public void setSe(List<Sędzia> se) {
        Se = se;
    }

    public Oskarżony getOskarżony() {
        return oskarżony;
    }

    public void setOskarżony(Oskarżony oskarżony) {
        this.oskarżony = oskarżony;
    }

    public Adwokat getAdwokat() {
        return adwokat;
    }

    public void setAdwokat(Adwokat adwokat) {
        this.adwokat = adwokat;
    }

    public void setJudgeNames(ObservableList<String> judgeNames) {
        this.judgeNames = judgeNames;
    }

    public ComboBox getAccusedBox() {
        return accusedBox;
    }

    public ComboBox getJudgeBox() {
        return judgeBox;
    }

    /**
     * Fetches all the accused from the system by the help of a hibernate query
     *
     */
    @FXML
    protected void initialize() {
        Session session = HelloApplication.createSession();
        Os = session.createQuery("select oskarżony from Oskarżony as oskarżony").list();
        session.close();
        for (Oskarżony o : Os) {
            if (!accusedNames.contains(o)) {
                accusedNames.add(o.getImie() + " " + o.getNazwisko());
            }
        }
        accusedBox.setItems(accusedNames);

        Platform.runLater(() -> {
            String title = adwokat.isPłeć() ? "Mr" : "Mrs";

            welcomeText.setText("Welcome \n" + title + " " + adwokat.getImie() + " " + adwokat.getNazwisko());
            judgeBox.setItems(judgeNames);

        });


    }
    /**
     * Selects from the combobox the accused for which the request is going to be
     *
     */
    @FXML
    protected void accusedSelection() {
        if (getAccusedBox().getValue() != null) {
            String accusedName = String.valueOf(getAccusedBox().getValue());
            oskarżony = Os.stream().filter(e -> (e.getImie() + " " + e.getNazwisko()).equals(accusedName)).findFirst().orElse(null);
            nextToJudgeSelection.setVisible(false);
            accusedBox.setVisible(false);
            selectAccused.setText("Select judge");
            judgeBox.setVisible(true);
            sendRequest.setVisible(true);

        }
    }
    /**
     * Selects from the combobox the judge to which the request is going to be sent
     * and opens up a new scene where the request can be sent
     *
     */
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

    /**
     * Opens up a new scene with the requests done by the lawyer which are not processed by a judge yet
     *
     * @throws IOException
     */
    @FXML
    protected void checkRequestButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkRequestsAdwokat-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Check requests");
        PendingRequestsControllerAdwokat controller = fxmlLoader.getController();
        controller.setAdwokat(adwokat);
        stage.setScene(new Scene(root, 450, 450));

        stage.show();
    }

}