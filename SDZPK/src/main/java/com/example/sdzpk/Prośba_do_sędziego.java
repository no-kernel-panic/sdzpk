package com.example.sdzpk;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
public class Prośba_do_sędziego {


    private Stan stan;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy
            = "increment")
    private int id;
    private String opis;
    /*
     * 1 to many relation prośba do sędziego - oskarżony
     */
    @ManyToOne
    private Oskarżony oskarżony;
    /*
     * 1 to many relation Pracownik -  prośba_do_sędziego
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Pracownik pracownikOtrzyma;
    @ManyToOne(fetch = FetchType.EAGER)
    private Pracownik pracownikWysyła;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Enumerated
    public Stan getStan() {
        return stan;
    }

    public void setStan(Stan stan) {
        this.stan = stan;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Oskarżony getOskarżony() {
        return oskarżony;
    }

    public void setOskarżony(Oskarżony oskarżony) {
        if (this.oskarżony == oskarżony) {
            return;
        }

        if (this.oskarżony != null) {
            this.oskarżony.removeProśbadoSedziego(this);
        }

        this.oskarżony = oskarżony;
        this.oskarżony.addProśbadoSedziego(this);

    }

    public Pracownik getPracownikOtrzyma() {
        return pracownikOtrzyma;
    }

    public void setPracownikOtrzyma(Pracownik pracownik) {
        if (this.pracownikOtrzyma == pracownik) {
            return;
        }

        if (this.pracownikOtrzyma != null) {
            this.pracownikOtrzyma.removeProśbadosędziegoOtrzyma(this);
        }

        this.pracownikOtrzyma = pracownik;
        this.pracownikOtrzyma.addProśbadosędziegoOtrzyma(this);

    }

    public Pracownik getPracownikWysyła() {
        return pracownikWysyła;
    }

    public void setPracownikWysyła(Pracownik pracownik) {
        if (this.pracownikWysyła == pracownik) {
            return;
        }

        if (this.pracownikWysyła != null) {
            this.pracownikWysyła.removeProśbadosędziegoWysyła(this);
        }

        this.pracownikWysyła = pracownik;
        this.pracownikWysyła.addProśbadosędziegoWysyła(this);

    }

    public enum Stan {AresztDomowy, WyjazdZaGranicę, Przepustka}


}
