package com.example.sdzpk;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wyrok implements IExtension<Wyrok>{


    public Wyrok() {
        addToExtension(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    private int id;
    private LocalDate dataRospoczęcia;



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

    public int getOkresWyroku() {
        return okresWyroku;
    }

    public void setOkresWyroku(int okresWyroku) {
        this.okresWyroku = okresWyroku;
    }

    private LocalDate dataZakończenia;
    private float mandatKarny;//todo optional
    private int okresWyroku;//todo pochodny


    /**
     * extension
     */
    @OneToMany
    private List<Wyrok> extension = new ArrayList<>();

    public List<Wyrok> getExtension() {
        return extension;
    }

    @Override
    public void addToExtension(Wyrok object) {
    extension.add(this);
    }

    @Override
    public void removeFromExtension(Wyrok object) {
    extension.remove(this);
    }

    /**
     * one to many relation wyrok - więzenie
     */
    @ManyToOne
    private Więzienie więzienie;
    public void setWięzienie(Więzienie więzienie) {
        if (this.więzienie == więzienie ) {
            return;
        }

        if (this.więzienie != null) {
            this.więzienie.removeWyrok(this);
        }

        this.więzienie = więzienie;
        this.więzienie.addWyrok(this);

    }

    /**
     * one to many relation wyrok - Sędzia
     */
    @ManyToOne
    private Sędzia sędzia;
    public void setSędzia(Sędzia sędzia) {
        if (this.sędzia == sędzia ) {
            return;
        }

        if (this.sędzia != null) {
            this.sędzia.removeWyrok(this);
        }

        this.sędzia = sędzia;
        this.sędzia.addWyrok(this);

    }


    /**
     * one to one relation wyrok - Proces Karny
     */
    @OneToOne
    private Proces_Karny proces_karny;
    public void setProces_karny(Proces_Karny proces_karny) {
        if (this.proces_karny == proces_karny ) {
            return;
        }

        if (this.proces_karny != null) {
            this.proces_karny.removeWyrok();
        }

        this.proces_karny = proces_karny;
        this.proces_karny.setWyrok(this);
    }

    public void removeProces_Karny(){
        this.proces_karny = null;
    }



}
