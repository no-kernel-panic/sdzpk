package com.example.sdzpk;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Prośba_do_sędziego{
    //todo initialization

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum Stan { AresztDomowy, WyjazdZaGranicę, Przepustka }

    @Enumerated
    public Stan getStan() {
        return stan;
    }

    public void setStan(Stan stan) {
        this.stan = stan;
    }

    private Stan stan;

    @Id
    private int id;

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    private String opis;



    /**
     * 1 to many relation prośba do sędziego - oskarżony
     */
    @ManyToOne
    private Oskarżony oskarżony;

    public void setOskarżony(Oskarżony oskarżony) {
        if (this.oskarżony == oskarżony ) {
            return;
        }

        if (this.oskarżony != null) {
            this.oskarżony.removeProśbadoSedziego(this);
        }

        this.oskarżony = oskarżony;
        this.oskarżony.addProśbadoSedziego(this);

    }


    /**
     * 1 to many relation Pracownik -  prośba_do_sędziego
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Pracownik pracownikOtrzyma;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pracownik pracownikWysyła;

    public void setPracownikOtrzyma(Pracownik pracownik) {
        if (this.pracownikOtrzyma == pracownik ) {
            return;
        }

        if (this.pracownikOtrzyma != null) {
            this.pracownikOtrzyma.removeProśbadosędziegoOtrzyma(this);
        }

        this.pracownikOtrzyma = pracownik;
        this.pracownikOtrzyma.addProśbadosędziegoOtrzyma(this);

    }

    public Oskarżony getOskarżony() {
        return oskarżony;
    }

    public Pracownik getPracownikOtrzyma() {
        return pracownikOtrzyma;
    }

    public Pracownik getPracownikWysyła() {
        return pracownikWysyła;
    }

    public void setPracownikWysyła(Pracownik pracownik) {
        if (this.pracownikWysyła == pracownik ) {
            return;
        }

        if (this.pracownikWysyła != null) {
            this.pracownikWysyła.removeProśbadosędziegoWysyła(this);
        }

        this.pracownikWysyła = pracownik;
        this.pracownikWysyła.addProśbadosędziegoWysyła(this);

    }



}
