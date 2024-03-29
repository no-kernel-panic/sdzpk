package com.example.sdzpk;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


//popełnione zostało z użyciem przemocy, i przeciw własności
@Entity
public class Oskarżony extends Osoba {

    private Stan stan;

    private int numerIdentyfikacjyny;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "oskarżony")
    private List<Wizyta> wizytaList = new ArrayList<>();
    /*
     * many to many relation oskarżony wiezienie
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "oskarżony")
    private List<Pobyt_w_więzieniu> pobyt_w_więzieniuList = new ArrayList<>();
    /*
     * many to many relation between Przestępstwo - Oskarżony
     *
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "oskarżony")
    private List<PrzestępstwoOskarżony> przestępstwoOskarżonyList = new ArrayList<>();
    /*
     * many to one relation oskarżóny - prośba do sędziego
     *
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "oskarżony")
    private List<Prośba_do_sędziego> prośba_do_sędziegoList = new ArrayList<>();

    @Enumerated
    public Stan getStan() {
        return stan;
    }

    public void setStan(Stan stan) {
        this.stan = stan;
    }

    public int getNumerIdentyfikacjyny() {
        return numerIdentyfikacjyny;
    }

    public void setNumerIdentyfikacjyny(int numerIdentyfikacjyny) {
        this.numerIdentyfikacjyny = numerIdentyfikacjyny;
    }

    public void addWizyta(Wizyta wizyta) {
        if (!this.wizytaList.contains(wizyta)) {
            this.wizytaList.add(wizyta);
            wizyta.setOskarżony(this);
        }

    }

    public void removeWizyta(Wizyta wizyta) {
        this.wizytaList.remove(wizyta);
    }

    public void addPobytwWięzeniu(Pobyt_w_więzieniu pobyt_w_więzieniu) {
        if (!pobyt_w_więzieniuList.contains(pobyt_w_więzieniu)) {
            pobyt_w_więzieniuList.add(pobyt_w_więzieniu);
        }
    }

    public void removePobytwWięzeniu(Pobyt_w_więzieniu pobyt_w_więzieniu) {
        pobyt_w_więzieniuList.remove(pobyt_w_więzieniu);
    }

    public void addPrzestępstwoOskarżony(PrzestępstwoOskarżony przestępstwoOskarżony) {
        if (!przestępstwoOskarżonyList.contains(przestępstwoOskarżony)) {
            przestępstwoOskarżonyList.add(przestępstwoOskarżony);
        }
    }

    public void removePrzestępstwoOskarżony(PrzestępstwoOskarżony przestępstwoOskarżony) {
        przestępstwoOskarżonyList.remove(przestępstwoOskarżony);
    }

    public void addProśbadoSedziego(Prośba_do_sędziego prośba_do_sędziego) {
        if (!this.prośba_do_sędziegoList.contains(prośba_do_sędziego)) {
            this.prośba_do_sędziegoList.add(prośba_do_sędziego);
            prośba_do_sędziego.setOskarżony(this);
        }

    }

    public void removeProśbadoSedziego(Prośba_do_sędziego prośba_do_sędziego) {
        this.prośba_do_sędziegoList.remove(prośba_do_sędziego);
    }

    @Override
    public String toString() {
        return getImie() + " " + getNazwisko() +
                " having ID: " + numerIdentyfikacjyny + "\n" + "Current state: " + stan;


    }


    public enum Stan {AresztDomowy, WyjazdZaGranicę, Przepustka, Wwięzieniu}
}
