package com.example.sdzpk;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wizyta {


    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy
            = "increment")
    private int id;
    /*
     * many to many relation adwokat - wizyta
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "wizyta")
    private List<AdwokatWizyta> adwokatWizytaList = new ArrayList<>();
    /*
     * 1 to many relation wizyta oskarżony
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Oskarżony oskarżony;
    /*
     * many to many relation Wizyta - wiezienie
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "wizyta")
    private List<Wizita_w_więzieniu> wizyta_w_więzieniuList = new ArrayList<>();

    public Wizyta() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addAdwokatWizyta(AdwokatWizyta adwokatWizyta) {
        if (!adwokatWizytaList.contains(adwokatWizyta)) {
            adwokatWizytaList.add(adwokatWizyta);
        }
    }

    public void removeAdwokatWizyta(AdwokatWizyta adwokatWizyta) {
        if (!adwokatWizytaList.contains(adwokatWizyta)) {
            adwokatWizytaList.remove(adwokatWizyta);
        }
    }

    public void setOskarżony(Oskarżony oskarżony) {
        if (this.oskarżony == oskarżony) {
            return;
        }

        if (this.oskarżony != null) {
            this.oskarżony.removeWizyta(this);
        }

        this.oskarżony = oskarżony;
        this.oskarżony.addWizyta(this);

    }

    public void addWizytawWięzeniu(Wizita_w_więzieniu wizita_w_więzieniu) {
        if (!wizyta_w_więzieniuList.contains(wizita_w_więzieniu)) {
            wizyta_w_więzieniuList.add(wizita_w_więzieniu);
        }
    }

    public void removeWizytawWięzeniu(Wizita_w_więzieniu wizita_w_więzieniu) {
        if (!wizyta_w_więzieniuList.contains(wizita_w_więzieniu)) {
            wizyta_w_więzieniuList.remove(wizita_w_więzieniu);
        }
    }

}
