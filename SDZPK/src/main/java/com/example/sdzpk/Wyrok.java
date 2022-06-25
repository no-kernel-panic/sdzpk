package com.example.sdzpk;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Wyrok {


    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy
            = "increment")
    private int id;
    private LocalDate dataRospoczęcia;
    private LocalDate dataZakończenia;
    private float mandatKarny;
    private int okresWyroku;
    /*
     * one to many relation wyrok - więzenie
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Więzienie więzienie;
    /*
     * one to many relation wyrok - Sędzia
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Sędzia sędzia;
    /*
     * one to one relation wyrok - Proces Karny
     */
    @OneToOne
    private Proces_Karny proces_karny;

    public Wyrok() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataRospoczęcia() {
        return dataRospoczęcia;
    }

    public void setDataRospoczęcia(LocalDate dataRospoczęcia) {
        this.dataRospoczęcia = dataRospoczęcia;
    }

    public LocalDate getDataZakończenia() {
        return dataZakończenia;
    }

    public void setDataZakończenia(LocalDate dataZakończenia) {
        this.dataZakończenia = dataZakończenia;
    }

    public float getMandatKarny() {
        return mandatKarny;
    }

    public void setMandatKarny(float mandatKarny) {
        this.mandatKarny = mandatKarny;
    }

    @Transient
    public int getOkresWyroku() {
        return (int) dataRospoczęcia.until(dataZakończenia, ChronoUnit.DAYS);
    }

    public void setOkresWyroku(int okresWyroku) {
        this.okresWyroku = okresWyroku;
    }

    public void setWięzienie(Więzienie więzienie) {
        if (this.więzienie == więzienie) {
            return;
        }

        if (this.więzienie != null) {
            this.więzienie.removeWyrok(this);
        }

        this.więzienie = więzienie;
        this.więzienie.addWyrok(this);

    }

    public void setSędzia(Sędzia sędzia) {
        if (this.sędzia == sędzia) {
            return;
        }

        if (this.sędzia != null) {
            this.sędzia.removeWyrok(this);
        }

        this.sędzia = sędzia;
        this.sędzia.addWyrok(this);

    }

    public void setProces_karny(Proces_Karny proces_karny) {
        if (this.proces_karny == proces_karny) {
            return;
        }

        if (this.proces_karny != null) {
            this.proces_karny.removeWyrok();
        }

        this.proces_karny = proces_karny;
        this.proces_karny.setWyrok(this);
    }

    public void removeProces_Karny() {
        this.proces_karny = null;
    }


}
