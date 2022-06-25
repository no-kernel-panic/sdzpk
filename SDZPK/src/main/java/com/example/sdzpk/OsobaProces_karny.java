package com.example.sdzpk;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OsobaProces_karny {
    @Id
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Osoba osoba;
    @ManyToOne(fetch = FetchType.EAGER)
    private Proces_Karny proces_karny;

    public OsobaProces_karny() {
    }

    public OsobaProces_karny(Proces_Karny proces_karny, Osoba osoba) {
        this.osoba = osoba;
        this.proces_karny = proces_karny;
    }

    private void setProces_karny(Proces_Karny proces_karny) {
        this.proces_karny = proces_karny;
        this.proces_karny.addOsobaProces_karny(this);
    }

    private void setOsoba(Osoba osoba) {
        this.osoba = osoba;
        this.osoba.addOsobaProces_karny(this);
    }
}
