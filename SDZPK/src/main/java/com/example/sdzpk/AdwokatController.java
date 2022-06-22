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

    private List<Prośba_do_sędziego> Re;
    private List<Oskarżony> Os;

    public Adwokat getAdwokat() {
        return adwokat;
    }

    public void setAdwokat(Adwokat adwokat) {
        this.adwokat = adwokat;
    }

    private Adwokat adwokat;


    private ObservableList<String> requests =
            FXCollections.observableArrayList();


    ObservableList<String> accusedNames =
            FXCollections.observableArrayList();

    public ComboBox getAccusedBox() {
        return accusedBox;
    }

    @FXML
    protected ComboBox accusedBox;

    @FXML
    protected void initialize(){
        Session session =  HelloApplication.createSession();
      //Re = session.createQuery("select prośba from Prośba_do_sędziego as prośba").list();
        Os = session.createQuery("select oskarżony from Oskarżony as oskarżony").list();
        session.close();
        for(Oskarżony o : Os){
        accusedNames.add(o.getImie() + " " + o.getNazwisko());
        }
        accusedBox.setItems(accusedNames);
     /*   for(Prośba_do_sędziego p : Re) {
            requests.add(p.getOpis());
        }*/
        Platform.runLater(() -> {
            welcomeText.setText("Welcome: " + adwokat.getImie() + " " + adwokat.getNazwisko());
        });


    }
    @FXML
    private Label welcomeText;

    @FXML
    private Label selectAccused;

    @FXML
    private Button createrequest;

    @FXML
    private Button next;


    @FXML
    protected void requestPane() throws IOException {
        if (getAccusedBox().getValue() != null) {
            String accusedName = String.valueOf(getAccusedBox().getValue());
            Oskarżony oskarżony = Os.stream().filter(e -> (e.getImie()+" "+e.getNazwisko()).equals(accusedName)).findFirst().orElse(null);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sendrequest-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Accused Stage");
            SendRequestController controller = fxmlLoader.getController();
            controller.setAdwokat(adwokat);
            controller.setOskarżony(oskarżony);
            stage.setScene(new Scene(root, 450, 450));
            //webAPI.openStageAsPopup(stage);
            stage.show();
        }
    }

    @FXML
    protected void createRequestButton() throws IOException {
       prepareWidgets();

    }


        protected void prepareWidgets(){
            createrequest.setVisible(false);
            selectAccused.setVisible(true);
            accusedBox.setVisible(true);
            next.setVisible(true);
        }
}