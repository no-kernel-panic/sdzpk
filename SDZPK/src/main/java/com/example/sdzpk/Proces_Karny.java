package com.example.sdzpk;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Proces_Karny {

    public Proces_Karny() {

    }

    @Id
    private int numer;

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public LocalDate getDataRospoczęcia() {
        return dataRospoczęcia;
    }

    public void setDataRozpoczęcia(LocalDate dataRozpoczęcia) {
        this.dataRospoczęcia = dataRozpoczęcia;
    }

    public void setDataZakończenia(LocalDate dataZakończenia) {
        this.dataZakończenia = dataZakończenia;
    }

    public LocalDate getDataZakończenia() {
        return dataZakończenia;
    }

    private LocalDate dataRospoczęcia;
    private LocalDate dataZakończenia; //todo nullable (optional)




    /**
     * one to one relation wyrok - Proces Karny
     */
    @OneToOne
    private Wyrok wyrok;
    public void setWyrok(Wyrok wyrok) {
        if (this.wyrok == wyrok ) {
            return;
        }

        if (this.wyrok != null) {
            this.wyrok.removeProces_Karny();
        }

        this.wyrok = wyrok;
        this.wyrok.setProces_karny(this);
    }

    public void removeWyrok(){
        this.wyrok = null;
    }

    /**
     * many to many relation przestepstwo - proces karny
     */
    @OneToMany(mappedBy = "proces_karny")
    private List<PrzestępstwoProces_karny> przestępstwoProces_karnyList = new ArrayList<>();

    public void addPrzestępstwoProces_karny(PrzestępstwoProces_karny przestępstwoProces_karny) {
        if (!przestępstwoProces_karnyList.contains(przestępstwoProces_karny)) {
            przestępstwoProces_karnyList.add(przestępstwoProces_karny);
        }
    }

    public void removePrzestępstwoProces_karny(PrzestępstwoProces_karny przestępstwoProces_karny) {
        if (przestępstwoProces_karnyList.contains(przestępstwoProces_karny)) {
            przestępstwoProces_karnyList.remove(przestępstwoProces_karny);
        }
    }




    /**
     * many to many relation osoba - proces karny
     */
    @OneToMany(mappedBy = "proces_karny")
    private List<OsobaProces_karny> osobaProces_karnyList = new ArrayList<>();

    public List<OsobaProces_karny> getOsobaProces_karnyList() {
        return osobaProces_karnyList;
    }
    public void addOsobaProces_karny(OsobaProces_karny osobaProces_karny) {
        if (!osobaProces_karnyList.contains(osobaProces_karny)) {
            osobaProces_karnyList.add(osobaProces_karny);
        }
    }

    public void removeOsobaProces_karny(OsobaProces_karny osobaProces_karny) {
        if (osobaProces_karnyList.contains(osobaProces_karny)) {
            osobaProces_karnyList.remove(osobaProces_karny);
        }
    }

}
