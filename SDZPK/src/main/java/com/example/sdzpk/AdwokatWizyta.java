package com.example.sdzpk;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/*
 *class representing many to many relation (not in diagram) between adwokat and wizyta
 */
@Entity
public class AdwokatWizyta {
    //todo at least one!

    @Id

    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Adwokat adwokat;

    @ManyToOne(fetch = FetchType.EAGER)
    private Wizyta wizyta; //< umówi się na

    public AdwokatWizyta(Adwokat adwokat, Wizyta wizyta) {
        this.adwokat = adwokat;
        this.wizyta = wizyta;
    }

    public AdwokatWizyta() {
    }

    private void setWizyta(Wizyta wizyta) {
        this.wizyta = wizyta;
        this.wizyta.addAdwokatWizyta(this);
    }

    private void setAdwokat(Adwokat adwokat) {
        this.adwokat = adwokat;
        this.adwokat.addAdwokatWizyta(this);
    }

}
