module com.example.sdzpk {
    requires javafx.controls;
    requires javafx.fxml;
    requires hibernate.core;
    requires hibernate.jpa;
    requires java.naming;
    requires java.sql;
    requires jdk.unsupported;
    requires javassist;
    requires org.controlsfx.controls;
    requires jpro.webapi;

    opens com.example.sdzpk;
    exports com.example.sdzpk;
}