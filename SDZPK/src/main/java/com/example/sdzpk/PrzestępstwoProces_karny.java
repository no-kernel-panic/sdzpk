package com.example.sdzpk;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PrzestępstwoProces_karny {


    @Id
    private int id;


    @ManyToOne(fetch = FetchType.EAGER)
    private Przestępstwo przestępstwo;//< oskarżony o


    @ManyToOne(fetch = FetchType.EAGER)
    private Proces_Karny proces_karny;

    public PrzestępstwoProces_karny(Proces_Karny proces_karny, Przestępstwo przestępstwo) {
        this.przestępstwo = przestępstwo;
        this.proces_karny = proces_karny;
    }

    public PrzestępstwoProces_karny() {
    }

    private void setProces_karny(Proces_Karny proces_karny) {
        this.proces_karny = proces_karny;
        this.proces_karny.addPrzestępstwoProces_karny(this);
    }

    private void setPrzestępstwo(Przestępstwo przestępstwo) {
        this.przestępstwo = przestępstwo;
        this.przestępstwo.addPrzestępstwoProces_karny(this);
    }
}
