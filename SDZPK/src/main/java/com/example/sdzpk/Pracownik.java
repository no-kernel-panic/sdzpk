package com.example.sdzpk;

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


    /**
     * many to one relation Pracownik prośba do sędziego
     *
     */

    @OneToMany(mappedBy = "pracownik")
    private List<Prośba_do_sędziego> prośba_do_sędziegoList = new ArrayList<>();

    public void addProśbadosędziego(Prośba_do_sędziego prośba_do_sędziego){
        if(!this.prośba_do_sędziegoList.contains(prośba_do_sędziego)) {
            this.prośba_do_sędziegoList.add(prośba_do_sędziego);
            prośba_do_sędziego.setPracownik(this);
        }

    }
    public void removeProśbadosędziego(Prośba_do_sędziego prośba_do_sędziego) {
        if (this.prośba_do_sędziegoList.contains(prośba_do_sędziego)) {
            this.prośba_do_sędziegoList.remove(prośba_do_sędziego);
        }
    }

}
