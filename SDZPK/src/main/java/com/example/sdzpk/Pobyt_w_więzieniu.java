package com.example.sdzpk;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
/**
 *Association class for many to many relation between oskarżony and więzienie
 */
@Entity
public class Pobyt_w_więzieniu  {

    //todo relations
    private String powód;
    private LocalDate odKiedy;
    private LocalDate doKiedy;


    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Oskarżony oskarżony;

    @ManyToOne(fetch = FetchType.LAZY)
    private Więzienie więzienie; //< przebywa w

    public Pobyt_w_więzieniu(Oskarżony oskarżony, Więzienie więzienie){
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
