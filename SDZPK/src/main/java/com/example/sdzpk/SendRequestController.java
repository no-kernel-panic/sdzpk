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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

import static com.example.sdzpk.HelloApplication.webAPI;
import static javafx.application.Application.setUserAgentStylesheet;


public class SendRequestController {


    private ObservableList<Enum> stanList = FXCollections.observableArrayList(Prośba_do_sędziego.Stan.AresztDomowy,
            Prośba_do_sędziego.Stan.Przepustka, Prośba_do_sędziego.Stan.WyjazdZaGranicę);

    private List<Prośba_do_sędziego> Re;

    public Adwokat getAdwokat() {
        return adwokat;
    }

    public void setAdwokat(Adwokat adwokat) {
        this.adwokat = adwokat;
    }

    private Adwokat adwokat;

    public Oskarżony getOskarżony() {
        return oskarżony;
    }

    public Sędzia getSędzia() {
        return sędzia;
    }

    public void setSędzia(Sędzia sędzia) {
        this.sędzia = sędzia;
    }

    private Sędzia sędzia;

    public void setOskarżony(Oskarżony oskarżony) {
        this.oskarżony = oskarżony;
    }

    private Oskarżony oskarżony;

  /*  private ObservableList<String> requests =
            FXCollections.observableArrayList();


    ObservableList<String> accusedNames =
            FXCollections.observableArrayList();*/

  /*  public ComboBox getAccusedBox() {
        return accusedBox;
    }
*/
    @FXML
    protected ComboBox stanBox;

    @FXML
    protected void initialize(){
      stanBox.setItems(stanList);

       // Session session =  HelloApplication.createSession();
     /* //Re = session.createQuery("select prośba from Prośba_do_sędziego as prośba").list();
        Os = session.createQuery("select oskarżony from Oskarżony as oskarżony").list();
        session.close();
        for(Oskarżony o : Os){
        accusedNames.add(o.getImie() + " " + o.getNazwisko());
        }
        accusedBox.setItems(accusedNames);
     *//*   for(Prośba_do_sędziego p : Re) {
            requests.add(p.getOpis());
        }*/
        Platform.runLater(() -> {
            welcomeText.setText( oskarżony.toString() );
        });


    }

    @FXML
    public void submitRequest(){
       Prośba_do_sędziego.Stan stan = (Prośba_do_sędziego.Stan) stanBox.getValue();
       adwokat.createRequest(textArea.getText(), stan, oskarżony, sędzia);

    }
    @FXML
    private Label welcomeText;

    @FXML
    private TextArea textArea;

    @FXML
    private Label selectAccused;

    @FXML
    private Button createrequest;

    @FXML
    private Button next;





}