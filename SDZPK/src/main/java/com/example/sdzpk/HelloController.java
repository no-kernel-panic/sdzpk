package com.example.sdzpk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.example.sdzpk.HelloApplication.webAPI;

public class HelloController {

    private List<Adwokat> Ad;
    private List<Sędzia> Se;

    private ObservableList<String> lawyers =
            FXCollections.observableArrayList();


    private ObservableList<String> judges =
            FXCollections.observableArrayList();

    @FXML
    private Label errorText;

    @FXML
    protected ComboBox lawyerBox;
    @FXML
    protected ComboBox judgeBox;

    @FXML
    protected void initialize(){
        Session session =  HelloApplication.createSession();
         Ad = session.createQuery("select adwokat from Adwokat as adwokat").list();
         Se = session.createQuery("select sędzia from Sędzia as sędzia").list();
        session.close();
        for(Adwokat a : Ad) {
            lawyers.add(a.getImie() + " " + a.getNazwisko());
        }
        for(Sędzia s : Se) {
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

    @FXML
    protected void comboLawyerSelected() throws IOException {
        if (getLawyerBox().getValue() != null) {
            String lawyerName = String.valueOf(getLawyerBox().getValue());
            Adwokat adwokat = Ad.stream().filter(e -> (e.getImie()+" "+e.getNazwisko()).equals(lawyerName)).findFirst().orElse(null);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adwokat-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Lawyer Stage");
            AdwokatController controller = fxmlLoader.getController();
            controller.setAdwokat(adwokat);
            controller.setJudgeNames(judges);
            controller.setSe(Se);
            stage.setScene(new Scene(root, 450, 450));
            //webAPI.openStageAsPopup(stage);
            stage.show();
        }
    }
    @FXML
    protected void comboJudgeSelected() throws IOException {
        if (getJudgeBox().getValue() != null) {
            String judgeName = String.valueOf(getJudgeBox().getValue());
            Sędzia sędzia = Se.stream().filter(e -> (e.getImie()+" "+e.getNazwisko()).equals(judgeName)).findFirst().orElse(null);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sędzia-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Judge Stage");
            SędziaController controller = fxmlLoader.getController();
            controller.setSędzia(sędzia);
            stage.setScene(new Scene(root, 450, 450));
            webAPI.openStageAsPopup(stage);
        }

    }

}