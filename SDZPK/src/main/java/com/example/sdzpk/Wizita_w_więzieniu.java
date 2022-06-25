package com.example.sdzpk;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Wizita_w_więzieniu {
    private LocalDate dataWizyty;


    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy
            = "increment")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Wizyta wizyta;

    @ManyToOne(fetch = FetchType.EAGER)
    private Więzienie więzienie; //< przebywa w

    public Wizita_w_więzieniu(Oskarżony oskarżony, Wizyta wizyta) {
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
