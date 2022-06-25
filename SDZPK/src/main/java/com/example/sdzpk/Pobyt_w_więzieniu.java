package com.example.sdzpk;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

/*
 *Association class for many to many relation between oskarżony and więzienie
 */
@Entity
public class Pobyt_w_więzieniu {

    //todo relations
    private String powód;
    private LocalDate odKiedy;
    private LocalDate doKiedy;


    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy
            = "increment")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Oskarżony oskarżony;

    @ManyToOne(fetch = FetchType.EAGER)
    private Więzienie więzienie; //< przebywa w

    public Pobyt_w_więzieniu(Oskarżony oskarżony, Więzienie więzienie) {
        this.oskarżony = oskarżony;
        this.więzienie = więzienie;
    }

    public Pobyt_w_więzieniu() {
    }

    private void setOskarżony(Oskarżony oskarżony) {
        this.oskarżony = oskarżony;
        this.oskarżony.addPobytwWięzeniu(this);
    }

    private void setWięzienie(Więzienie więzienie) {
        this.więzienie = więzienie;
        this.więzienie.addPobytwWięzeniu(this);
    }
}
