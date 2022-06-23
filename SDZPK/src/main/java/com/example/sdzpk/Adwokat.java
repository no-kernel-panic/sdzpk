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


    public Adwokat(){

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
    
    public void createRequest(String opis, Prośba_do_sędziego.Stan stan, Oskarżony oskarżony, Sędzia sędzia)  {
        Session session =  HelloApplication.createSession();
        Prośba_do_sędziego prośba = new Prośba_do_sędziego();
        prośba.setStan(stan);
        prośba.setOpis(opis);
        prośba.setPracownikWysyła(this);
        prośba.setOskarżony(oskarżony);
        prośba.setPracownikOtrzyma(sędzia);
         session.beginTransaction();
         session.save(prośba);//save(NEW PROCES_KARNY(...))
         session.getTransaction().commit();
         session.close();

    }
    


}


