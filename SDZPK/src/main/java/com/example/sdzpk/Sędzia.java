package com.example.sdzpk;

import org.hibernate.Session;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sędzia extends Pracownik {


    /*
     * many to one relation sędzia - wyrok
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "sędzia")

    private List<Wyrok> wyrokList = new ArrayList<>();


    public Sędzia() {
    }

    public void addWyrok(Wyrok wyrok) {
        if (!this.wyrokList.contains(wyrok)) {
            this.wyrokList.add(wyrok);
            wyrok.setSędzia(this);
        }

    }

    public void removeWyrok(Wyrok wyrok) {
        this.wyrokList.remove(wyrok);
    }

    public boolean confirmRequest(Prośba_do_sędziego prośbadoSędziego, Oskarżony oskarżony, Prośba_do_sędziego.Stan stan) {


        Oskarżony _oskarżony = oskarżony;
        Prośba_do_sędziego prośba = prośbadoSędziego;
        try {
            _oskarżony.setStan(Oskarżony.Stan.values()[stan.ordinal()]);
            Session session = HelloApplication.createSession();
            session.beginTransaction();
            session.update(oskarżony);
            session.delete(prośba);
            session.getTransaction().commit();
            session.close();
        } catch (Exception exception) {
            return false;
        }
        prośbadoSędziego.getPracownikWysyła().removeProśbadosędziegoWysyła(prośbadoSędziego);
        removeProśbadosędziegoOtrzyma(prośbadoSędziego);
        return true;
    }

    public void withdrawnRequest(Prośba_do_sędziego prośbadoSędziego) {

        Session session = HelloApplication.createSession();
        session.beginTransaction();
        session.delete(prośbadoSędziego);
        session.getTransaction().commit();
        session.close();
        removeProśbadosędziegoOtrzyma(prośbadoSędziego);
        prośbadoSędziego.getPracownikWysyła().removeProśbadosędziegoWysyła(prośbadoSędziego);

    }

}