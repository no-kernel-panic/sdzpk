package com.example.sdzpk;

import com.jpro.webapi.WebAPI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

import static com.example.sdzpk.HelloApplication.webAPI;

public class HelloController {

    ObservableList<String> employees =
            FXCollections.observableArrayList();

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 450, 450));
        webAPI.openStageAsPopup(stage);
    }

    @FXML
    protected ComboBox idBox;

    @FXML
    protected void initialize(){
        Session session =  HelloApplication.createSession();
        List<Adwokat> Ad = session.createQuery("select adwokat from Adwokat as adwokat").list();
        List<Sędzia> Se = session.createQuery("select sędzia from Sędzia as sędzia").list();

        for(Osoba o : Adwokat.getExtension()){
           if(!employees.contains(o.getImie()+" "+o.getNazwisko())) {
            employees.add(o.getImie() + " " + o.getNazwisko());
         }
        }

        idBox.setItems(employees);
    }

    public ComboBox getIdBox() {
        return idBox;
    }
}