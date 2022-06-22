package com.example.sdzpk;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sędzia extends Pracownik {


    public Sędzia() {
    }


    /**
     * many to one relation sędzia - wyrok
     */
    @OneToMany(mappedBy = "sędzia")

    private List<Wyrok> wyrokList = new ArrayList<>();
    public void addWyrok(Wyrok wyrok){
        if(!this.wyrokList.contains(wyrok)) {
            this.wyrokList.add(wyrok);
            wyrok.setSędzia(this);
        }

    }
    public void removeWyrok(Wyrok wyrok) {
        if (this.wyrokList.contains(wyrok)) {
            this.wyrokList.remove(wyrok);
        }
    }

    //todo metody
}