package com.example.sdzpk;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PrzestępstwoOskarżony {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy
            = "increment")
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
