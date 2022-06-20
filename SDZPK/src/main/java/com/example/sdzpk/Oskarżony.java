package com.example.sdzpk;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


enum StanO { Wwięzieniu, AresztDomowy, WyjazdZaGranicę, Przepustka } //popełnione zostało z użyciem przemocy, i przeciw własności
@Entity
public class Oskarżony extends Osoba implements IExtension<Oskarżony>{

    //todo initialization
    private EnumSet<StanO> stan = EnumSet.noneOf(StanO.class);

    public int getNumerIdentyfikacjyny() {
        return numerIdentyfikacjyny;
    }

    public void setNumerIdentyfikacjyny(int numerIdentyfikacjyny) {
        this.numerIdentyfikacjyny = numerIdentyfikacjyny;
    }

    private int numerIdentyfikacjyny;


    /**
     * Extension (list in parent class)
     */

    public Oskarżony() {
        addToExtension(this);
    }

    @Override
    public void addToExtension(Oskarżony object) {
    extension.add(object);
    }

    @Override
    public void removeFromExtension(Oskarżony object) {
    extension.remove(object);
    }


    /**
     * many to one relation oskarżóny wizyta
     *
     */

    @OneToMany(mappedBy = "oskarżony")
    private List<Wizyta> wizytaList = new ArrayList<>();

    public void addWizyta(Wizyta wizyta){
        if(!this.wizytaList.contains(wizyta)) {
            this.wizytaList.add(wizyta);
            wizyta.setOskarżony(this);
        }

    }
    public void removeWizyta(Wizyta wizyta) {
        if (this.wizytaList.contains(wizyta)) {
            this.wizytaList.remove(wizyta);
        }
    }

    /**
     * many to many relation oskarżony wiezienie
     */

    @OneToMany(mappedBy = "oskarżony")
    private List<Pobyt_w_więzieniu> pobyt_w_więzieniuList = new ArrayList<>();

    public void addPobytwWięzeniu(Pobyt_w_więzieniu pobyt_w_więzieniu) {
        if (!pobyt_w_więzieniuList.contains(pobyt_w_więzieniu)) {
            pobyt_w_więzieniuList.add(pobyt_w_więzieniu);
        }
    }

    public void removePobytwWięzeniu(Pobyt_w_więzieniu pobyt_w_więzieniu) {
        if (pobyt_w_więzieniuList.contains(pobyt_w_więzieniu)) {
            pobyt_w_więzieniuList.remove(pobyt_w_więzieniu);
        }
    }

    /**
     * many to many relation between Przestępstwo - Oskarżony
     *
     */
    @OneToMany(mappedBy = "oskarżony")
    private List<PrzestępstwoOskarżony> przestępstwoOskarżonyList = new ArrayList<>();

    public void addPrzestępstwoOskarżony(PrzestępstwoOskarżony przestępstwoOskarżony) {
        if (!przestępstwoOskarżonyList.contains(przestępstwoOskarżony)) {
            przestępstwoOskarżonyList.add(przestępstwoOskarżony);
        }
    }

    public void removePrzestępstwoOskarżony(PrzestępstwoOskarżony przestępstwoOskarżony) {
        if (przestępstwoOskarżonyList.contains(przestępstwoOskarżony)) {
            przestępstwoOskarżonyList.remove(przestępstwoOskarżony);
        }
    }

    /**
     * many to one relation oskarżóny - prośba do sędziego
     *
     */
    @OneToMany(mappedBy = "oskarżony")
    private List<Prośba_do_sędziego> prośba_do_sędziegoList = new ArrayList<>();

    public void addProśbadoSedziego(Prośba_do_sędziego prośba_do_sędziego){
        if(!this.prośba_do_sędziegoList.contains(prośba_do_sędziego)) {
            this.prośba_do_sędziegoList.add(prośba_do_sędziego);
            prośba_do_sędziego.setOskarżony(this);
        }

    }
    public void removeProśbadoSedziego(Prośba_do_sędziego prośba_do_sędziego) {
        if (this.prośba_do_sędziegoList.contains(prośba_do_sędziego)) {
            this.prośba_do_sędziegoList.remove(prośba_do_sędziego);
        }
    }
}
