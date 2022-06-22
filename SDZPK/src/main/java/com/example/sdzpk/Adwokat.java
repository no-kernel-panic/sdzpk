package com.example.sdzpk;


import org.hibernate.Session;

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
    
    public void createRequest(Session session){

        Prośba_do_sędziego prośba = new Prośba_do_sędziego();
        prośba.setPracownikWysyła(this);
      //  prośba.setOskarżony();
        // session.beginTransaction();
        // session.save(proces_karny);//save(NEW PROCES_KARNY(...))
        // session.getTransaction().commit();

    }
    


}
