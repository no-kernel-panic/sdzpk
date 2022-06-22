package com.example.sdzpk;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Przestępstwo {


    public Przestępstwo() {
        }

    @Id
    private int numer;
    private LocalDate dataRealizacji;
    private String adres;

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public LocalDate getDataRealizacji() {
        return dataRealizacji;
    }

    public void setDataRealizacji(LocalDate dataRealizacji) {
        this.dataRealizacji = dataRealizacji;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    private String opis;

    /**
     * many to many relation between Przestępstwo - Oskarżony
     *
     */

    @OneToMany(mappedBy = "przestępstwo")
    private List<PrzestępstwoOskarżony> przestępstwoOskarżonyList = new ArrayList<>();

    public void addPrzestępstwoOskarżony(PrzestępstwoOskarżony przestępstwoOskarżony) {
        if (!przestępstwoOskarżonyList.contains(przestępstwoOskarżony)) {
            przestępstwoOskarżonyList.add(przestępstwoOskarżony);
        }
    }

    public void removePrzestępstwoOskarżony(PrzestępstwoOskarżony przestępstwoOskarżony) {
        if (przestępstwoOskarżonyList.contains(przestępstwoOskarżony)) {
            przestępstwoOskarżonyList.remove(przestępstwoOskarżony);
        }
    }

    /**
     * many to many relation between Przestępstwo - Proces_Karny
     *
     */
    @OneToMany(mappedBy = "przestępstwo")
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

}
