package com.example.sdzpk;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.hibernate.Session;

import java.util.List;

public class SędziaController {

    private List<Prośba_do_sędziego> Re;

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
    @FXML
    protected ComboBox judgeBox;
*/
    @FXML
    protected void initialize(){
        Session session =  HelloApplication.createSession();
        Re = session.createQuery("select prośba from Prośba_do_sędziego as prośba").list();
        session.close();
        for(Prośba_do_sędziego p : Re) {
            requests.add(p.getOpis());
        }
        Platform.runLater(() -> {
            welcomeText.setText("Welcome: " + sędzia.getImie() + " " + sędzia.getNazwisko());

                });
       /* Session session =  HelloApplication.createSession();
        List<Adwokat> Ad = session.createQuery("select adwokat from Adwokat as adwokat").list();
        List<Sędzia> Se = session.createQuery("select sędzia from Sędzia as sędzia").list();
        session.close();
*/

    }
    @FXML
    private Label welcomeText;



}