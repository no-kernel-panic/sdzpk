package com.example.sdzpk;

import com.jpro.webapi.WebAPI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class HelloApplication extends Application {
    public static WebAPI webAPI;

    @Override
    public void start(Stage stage) throws IOException {
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        webAPI = WebAPI.getWebAPI(stage);
        }


    public static Session createSession(){

        Configuration configuration = new Configuration();
        configuration.configure(HelloApplication.class.getResource("hibernate.cfg.xml"));
        configuration.addAnnotatedClass(Proces_Karny.class);
        configuration.addAnnotatedClass(Adwokat.class);
        configuration.addAnnotatedClass(AdwokatWizyta.class);
        configuration.addAnnotatedClass(Oskarżony.class);
        configuration.addAnnotatedClass(Osoba.class);
        configuration.addAnnotatedClass(OsobaProces_karny.class);
        configuration.addAnnotatedClass(Pobyt_w_więzieniu.class);
        configuration.addAnnotatedClass(Pracownik.class);
        configuration.addAnnotatedClass(Prośba_do_sędziego.class);
        configuration.addAnnotatedClass(Przestępstwo.class);
        configuration.addAnnotatedClass(PrzestępstwoOskarżony.class);
        configuration.addAnnotatedClass(PrzestępstwoProces_karny.class);
        configuration.addAnnotatedClass(Sędzia.class);
        configuration.addAnnotatedClass(Wizita_w_więzieniu.class);
        configuration.addAnnotatedClass(Wizyta.class);
        configuration.addAnnotatedClass(Więzienie.class);
        configuration.addAnnotatedClass(Wyrok.class);
        ///create session factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory.openSession();
    }
    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd, MMM, yyyy", Locale.ENGLISH);
        //create configuration






        Runnable myThread = () ->
        {
            launch();
        };



        Thread run = new Thread(myThread);

        run.start();



    /*    for(Osoba o : Adwokat.getExtension())
            System.out.println(o.getClass());*/

        // List<Proces_Karny> processes = session.createQuery("select proces from com.example.sdzpk.Proces_Karny as proces").list();



        // Proces_Karny proces_karny = new Proces_Karny();
        // proces_karny.setDataRozpoczęcia(LocalDate.parse("06, Jan, 2015", formatter));

        // session.beginTransaction();
        // session.save(proces_karny);//save(NEW PROCES_KARNY(...))
        // session.getTransaction().commit();


    }
}