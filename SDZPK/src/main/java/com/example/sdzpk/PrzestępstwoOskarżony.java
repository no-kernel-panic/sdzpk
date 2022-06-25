package com.example.sdzpk;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PrzestępstwoOskarżony {

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Przestępstwo przestępstwo;//< oskarżony o


    @ManyToOne(fetch = FetchType.EAGER)
    private Oskarżony oskarżony;

    public PrzestępstwoOskarżony(Oskarżony oskarżony, Przestępstwo przestępstwo) {
        this.przestępstwo = przestępstwo;
        this.oskarżony = oskarżony;
    }

    public PrzestępstwoOskarżony() {
    }

    private void setOskarżony(Oskarżony oskarżony) {
        this.oskarżony = oskarżony;
        this.oskarżony.addPrzestępstwoOskarżony(this);
    }

    private void setPrzestępstwo(Przestępstwo przestępstwo) {
        this.przestępstwo = przestępstwo;
        this.przestępstwo.addPrzestępstwoOskarżony(this);
    }
}
