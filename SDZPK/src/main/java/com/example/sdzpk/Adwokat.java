package com.example.sdzpk;

import org.hibernate.Session;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@Entity

public class Adwokat extends Pracownik {


    public Adwokat() {

    }

    /**
     * many to many relation between adwokat and wizyta
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "adwokat")
    private List<AdwokatWizyta> adwokatWizytaList = new ArrayList<>();


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