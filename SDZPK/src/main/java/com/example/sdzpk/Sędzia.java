package com.example.sdzpk;

import org.hibernate.Session;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sędzia extends Pracownik {


    public Sędzia() {
    }


    /**
     * many to one relation sędzia - wyrok
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "sędzia")

    private List<Wyrok> wyrokList = new ArrayList<>();
    public void addWyrok(Wyrok wyrok){
        if(!this.wyrokList.contains(wyrok)) {
            this.wyrokList.add(wyrok);
            wyrok.setSędzia(this);
        }

    }
    public void removeWyrok(Wyrok wyrok) {
        if (this.wyrokList.contains(wyrok)) {
            this.wyrokList.remove(wyrok);
        }
    }

    public void confirmRequest(Prośba_do_sędziego prośbadoSędziego, Oskarżony oskarżony, Prośba_do_sędziego.Stan stan) {


        Oskarżony _oskarżony = oskarżony;
        Prośba_do_sędziego prośba = prośbadoSędziego;
        _oskarżony.setStan(Oskarżony.Stan.values()[stan.ordinal()]);
        Session session =  HelloApplication.createSession();
        session.beginTransaction();
        session.update(oskarżony);
        session.delete(prośba);//save(NEW PROCES_KARNY(...))
        session.getTransaction().commit();
        session.close();
    }

    //todo metody
}