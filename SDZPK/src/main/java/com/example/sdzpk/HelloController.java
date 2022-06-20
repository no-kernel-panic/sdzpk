package com.example.sdzpk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.hibernate.Session;

import java.util.List;

public class HelloController {

    ObservableList<String> employees =
            FXCollections.observableArrayList();

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected ComboBox idBox;

    @FXML
    protected void initialize(){
        Session session =  HelloApplication.createSession();
        List<Adwokat> Ad = session.createQuery("select adwokat from Adwokat as adwokat").list();
        List<Sędzia> Se = session.createQuery("select sędzia from Sędzia as sędzia").list();

        for(Osoba o : Adwokat.getExtension())
           employees.add(o.getImie()+" "+o.getNazwisko());

        idBox.setItems(employees);
    }

    public ComboBox getIdBox() {
        return idBox;
    }
}