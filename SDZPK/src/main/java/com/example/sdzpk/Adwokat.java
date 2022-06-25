package com.example.sdzpk;

import org.hibernate.Session;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Adwokat is the main entity used for representing a lawyer in our system
 * Please see the {@link com.example.sdzpk.Pracownik} for true identity
 */
@Entity

public class Adwokat extends Pracownik {


    /*
     * many to many relation between adwokat and wizyta
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "adwokat")
    private List<AdwokatWizyta> adwokatWizytaList = new ArrayList<>();

    public Adwokat() {

    }

    public void removeAdwokatWizyta(AdwokatWizyta adwokatWizyta) {
        if (!adwokatWizytaList.contains(adwokatWizyta)) {
            adwokatWizytaList.remove(adwokatWizyta);
        }
    }

    public void addAdwokatWizyta(AdwokatWizyta adwokatWizyta) {
        if (!adwokatWizytaList.contains(adwokatWizyta)) {
            adwokatWizytaList.add(adwokatWizyta);
        }
    }

    /**
     *
     * Creates a new request and persists it to the db.
     *
     * Creates then the right associations by calling the following methods
     *  {@link com.example.sdzpk.Prośba_do_sędziego#setOskarżony(Oskarżony)} and
     * {@link com.example.sdzpk.Prośba_do_sędziego#setPracownikOtrzyma(Pracownik)} and
     * {@link com.example.sdzpk.Prośba_do_sędziego#setPracownikOtrzyma(Pracownik)}
     *
     * @param opis the description of the request inserted in the front-end by the user
     * @param oskarżony the accused for which the request applies selected from the accused combobox
     * @param sędzia the judge to which the request is sent selected from the judge combobox
     * @param stan the accused state which is requested
     *
     */
    public boolean createRequest(String opis, Prośba_do_sędziego.Stan stan, Oskarżony oskarżony, Sędzia sędzia) {
        Prośba_do_sędziego prośba = new Prośba_do_sędziego();
        try {
            prośba.setStan(stan);
            prośba.setOpis(opis);
            prośba.setPracownikWysyła(this);
            prośba.setOskarżony(oskarżony);
            prośba.setPracownikOtrzyma(sędzia);
            Session session = HelloApplication.createSession();
            session.beginTransaction();
            session.save(prośba);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            prośba.getPracownikWysyła().removeProśbadosędziegoWysyła(prośba);
            prośba.getPracownikOtrzyma().removeProśbadosędziegoOtrzyma(prośba);
            prośba.getOskarżony().removeProśbadoSedziego(prośba);
            return false;
        }

        return true;
    }

    public void withdrawnRequest(Prośba_do_sędziego prośbadoSędziego) {

        Session session = HelloApplication.createSession();
        session.beginTransaction();
        session.delete(prośbadoSędziego);//save(NEW PROCES_KARNY(...))
        session.getTransaction().commit();
        session.close();
        removeProśbadosędziegoWysyła(prośbadoSędziego);
        prośbadoSędziego.getPracownikOtrzyma().removeProśbadosędziegoOtrzyma(prośbadoSędziego);

    }


}