package com.example.sdzpk;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Wizita_w_więzieniu {
    private LocalDate dataWizyty;



    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Wizyta wizyta;

    @ManyToOne(fetch = FetchType.LAZY)
    private Więzienie więzienie; //< przebywa w

    public Wizita_w_więzieniu(Oskarżony oskarżony, Wizyta wizyta){
        this.wizyta = wizyta;
        this.więzienie = więzienie;
    }

    public Wizita_w_więzieniu() {
    }

    private void setWizyta(Wizyta wizyta) {
        this.wizyta = wizyta;
        this.wizyta.addWizytawWięzeniu(this);
    }

    private void setWięzienie(Więzienie więzienie) {
        this.więzienie = więzienie;
        this.więzienie.addWizytawWięzeniu(this);
    }

}
