package com.example.sdzpk;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

enum Stan { AresztDomowy, WyjazdZaGranicę, Przepustka } //popełnione zostało z użyciem przemocy, i przeciw własności

@Entity
public class Prośba_do_sędziego implements IExtension<Prośba_do_sędziego>{
    //todo initialization

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    private int id;

    public Prośba_do_sędziego() {
        addToExtension(this);
    }

    private EnumSet<Stan> crimeType = EnumSet.noneOf(Stan.class);


    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    private String opis;

    /**
     *extension
     */
    @OneToMany
    private List<Prośba_do_sędziego> extension = new ArrayList<>();


    public List<Prośba_do_sędziego> getExtension() {
        return extension;
    }


    @Override
    public void addToExtension(Prośba_do_sędziego object) {
        extension.add(object);

    }

    @Override
    public void removeFromExtension(Prośba_do_sędziego object) {
        extension.remove(object);

    }

    /**
     * 1 to many relation prośba do sędziego - oskarżony
     */
    @ManyToOne
    private Oskarżony oskarżony;

    public void setOskarżony(Oskarżony oskarżony) {
        if (this.oskarżony == oskarżony ) {
            return;
        }

        if (this.oskarżony != null) {
            this.oskarżony.removeProśbadoSedziego(this);
        }

        this.oskarżony = oskarżony;
        this.oskarżony.addProśbadoSedziego(this);

    }


    /**
     * 1 to many relation Pracownik -  prośba_do_sędziego
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Pracownik pracownik;

    public void setPracownik(Pracownik pracownik) {
        if (this.pracownik == pracownik ) {
            return;
        }

        if (this.pracownik != null) {
            this.pracownik.removeProśbadosędziego(this);
        }

        this.pracownik = pracownik;
        this.pracownik.addProśbadosędziego(this);

    }

}
