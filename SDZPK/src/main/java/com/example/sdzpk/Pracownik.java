package com.example.sdzpk;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pracownik extends Osoba{

    private int numerIdentyfikacyjny;
    private int pensja;
    private int stopień; //todo od 1 do 5

    public int getNumerIdentyfikacyjny() {
        return numerIdentyfikacyjny;
    }

    public void setNumerIdentyfikacyjny(int numerIdentyfikacyjny) {
        this.numerIdentyfikacyjny = numerIdentyfikacyjny;
    }

    public int getPensja() {
        return pensja;
    }

    public void setPensja(int pensja) {
        this.pensja = pensja;
    }

    public int getStopień() {
        return stopień;
    }

    public void setStopień(int stopień) {
        this.stopień = stopień;
    }

    public int getStaż() {
        return staż;
    }

    public void setStaż(int staż) {
        this.staż = staż;
    }

    private int staż;


    public List<Prośba_do_sędziego> getProśba_do_sędziegoOtrzymaList() {
        return prośba_do_sędziegoOtrzymaList;
    }

    public List<Prośba_do_sędziego> getProśba_do_sędziegoWysyłaList() {
        return prośba_do_sędziegoWysyłaList;
    }

    /**
     * many to one relation Pracownik prośba do sędziego
     *
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "pracownikOtrzyma")
    private List<Prośba_do_sędziego> prośba_do_sędziegoOtrzymaList = new ArrayList<>();

    public void addProśbadosędziego(Prośba_do_sędziego prośba_do_sędziego){
        if(!this.prośba_do_sędziegoOtrzymaList.contains(prośba_do_sędziego)) {
            this.prośba_do_sędziegoOtrzymaList.add(prośba_do_sędziego);
            prośba_do_sędziego.setPracownikOtrzyma(this);
        }

    }
    public void removeProśbadosędziegoOtrzyma(Prośba_do_sędziego prośba_do_sędziego) {
        if (this.prośba_do_sędziegoOtrzymaList.contains(prośba_do_sędziego)) {
            this.prośba_do_sędziegoOtrzymaList.remove(prośba_do_sędziego);
        }
    }
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "pracownikWysyła")
    private List<Prośba_do_sędziego> prośba_do_sędziegoWysyłaList = new ArrayList<>();

    public void addProśbadosędziegoWysyła(Prośba_do_sędziego prośba_do_sędziego){
        if(!this.prośba_do_sędziegoWysyłaList.contains(prośba_do_sędziego)) {
            this.prośba_do_sędziegoWysyłaList.add(prośba_do_sędziego);
            prośba_do_sędziego.setPracownikWysyła(this);
        }

    }
    public void removeProśbadosędziegoWysyła(Prośba_do_sędziego prośba_do_sędziego) {
        if (this.prośba_do_sędziegoWysyłaList.contains(prośba_do_sędziego)) {
            this.prośba_do_sędziegoWysyłaList.remove(prośba_do_sędziego);
        }
    }

}
