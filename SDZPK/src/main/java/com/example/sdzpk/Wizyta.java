package com.example.sdzpk;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wizyta implements IExtension<Wizyta>{


    public Wizyta() {
        addToExtension(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    private int id;

    /**
     * many to many relation adwokat - wizyta
     */
    @OneToMany(mappedBy = "wizyta")
    private List<AdwokatWizyta> adwokatWizytaList = new ArrayList<>();

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

    /**
     * 1 to many relation wizyta oskarżony
     */
    @ManyToOne
    private Oskarżony oskarżony;

    public void setOskarżony(Oskarżony oskarżony) {
        if (this.oskarżony == oskarżony ) {
            return;
        }

        if (this.oskarżony != null) {
            this.oskarżony.removeWizyta(this);
        }

        this.oskarżony = oskarżony;
        this.oskarżony.addWizyta(this);

    }

    /**
     * Extension
     *
     */

    @OneToMany
    private List<Wizyta> extension = new ArrayList<>();

    public List<Wizyta> getExtension() {
        return extension;
    }

    @Override
    public void addToExtension(Wizyta object) {
        extension.add(object);
    }

    @Override
    public void removeFromExtension(Wizyta object) {
        extension.remove(object);
    }



    /**
     * many to many relation Wizyta - wiezienie
     */
    @OneToMany(mappedBy = "wizyta")
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
