package com.example.sdzpk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

import static com.example.sdzpk.HelloApplication.webAPI;

public class HelloController {

    private final ObservableList<String> lawyers =
            FXCollections.observableArrayList();
    private final ObservableList<String> judges =
            FXCollections.observableArrayList();
    @FXML
    protected ComboBox lawyerBox;
    @FXML
    protected ComboBox judgeBox;
    private List<Adwokat> Ad;
    private List<Sędzia> Se;


    /**
     *
     * Fetches all the lawyers and judges from the db using a hibernate query
     *
     */
    @FXML
    protected void initialize() {
        Session session = HelloApplication.createSession();
        Ad = session.createQuery("select adwokat from Adwokat as adwokat").list();
        Se = session.createQuery("select sędzia from Sędzia as sędzia").list();
        session.close();
        for (Adwokat a : Ad) {
            lawyers.add(a.getImie() + " " + a.getNazwisko());
        }
        for (Sędzia s : Se) {
            judges.add(s.getImie() + " " + s.getNazwisko());
        }


        lawyerBox.setItems(lawyers);
        judgeBox.setItems(judges);
    }

    public ComboBox getLawyerBox() {
        return lawyerBox;
    }

    public ComboBox getJudgeBox() {
        return judgeBox;
    }

    /**
     * Opens up a new scene showing the main menu for the lawyer selected from the combobox
     *
     * @throws IOException
     */
    @FXML
    protected void comboLawyerSelected() throws IOException {
        if (getLawyerBox().getValue() != null) {
            String lawyerName = String.valueOf(getLawyerBox().getValue());
            Adwokat adwokat = Ad.stream().filter(e -> (e.getImie() + " " + e.getNazwisko()).equals(lawyerName)).findFirst().orElse(null);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adwokat-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Lawyer Home");
            AdwokatController controller = fxmlLoader.getController();
            controller.setAdwokat(adwokat);
            controller.setJudgeNames(judges);
            controller.setSe(Se);
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
        }
    }

    /**
     * Opens up a new scene showing the main menu for the judge selected from the combobox
     *
     * @throws IOException
     */
    @FXML
    protected void comboJudgeSelected() throws IOException {
        if (getJudgeBox().getValue() != null) {
            String judgeName = String.valueOf(getJudgeBox().getValue());
            Sędzia sędzia = Se.stream().filter(e -> (e.getImie() + " " + e.getNazwisko()).equals(judgeName)).findFirst().orElse(null);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sędzia-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Judge home");
            SędziaController controller = fxmlLoader.getController();
            controller.setSędzia(sędzia);
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
        }

    }

}