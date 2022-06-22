package com.example.sdzpk;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Więzienie {


    public Więzienie() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    private int id;



//todo metody?


    /**
     * many to many relation oskarżony wiezienie
     */

    @OneToMany(mappedBy = "więzienie")
    private List<Pobyt_w_więzieniu> pobyt_w_więzieniuList = new ArrayList<>();

    public void addPobytwWięzeniu(Pobyt_w_więzieniu pobyt_w_więzieniu) {
        if (!pobyt_w_więzieniuList.contains(pobyt_w_więzieniu)) {
            pobyt_w_więzieniuList.add(pobyt_w_więzieniu);
        }
    }

    public void removePobytwWięzeniu(Pobyt_w_więzieniu pobyt_w_więzieniu) {
        if (!pobyt_w_więzieniuList.contains(pobyt_w_więzieniu)) {
            pobyt_w_więzieniuList.remove(pobyt_w_więzieniu);
        }
    }


    /**
     * many to one relation Więzenie - wyrok
     *
     */

    @OneToMany(mappedBy = "więzienie")
    private List<Wyrok> wyrokList = new ArrayList<>();

    public void addWyrok(Wyrok wyrok){
        if(!this.wyrokList.contains(wyrok)) {
            this.wyrokList.add(wyrok);
            wyrok.setWięzienie(this);
        }

    }
    public void removeWyrok(Wyrok wyrok) {
        if (this.wyrokList.contains(wyrok)) {
            this.wyrokList.remove(wyrok);
        }
    }


    /**
     * many to many relation Wizyta - wiezienie
     */
    @OneToMany(mappedBy = "więzienie")
    private List<Wizita_w_więzieniu> wizyta_w_więzieniuList = new ArrayList<>();

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


