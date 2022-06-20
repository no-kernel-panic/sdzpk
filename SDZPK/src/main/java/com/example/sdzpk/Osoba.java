package com.example.sdzpk;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class
Osoba {

    private String imie;
    private String nazwisko;
    private int wiek;
    private boolean płeć;
    private String narodowość;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public boolean isPłeć() {
        return płeć;
    }

    public void setPłeć(boolean płeć) {
        this.płeć = płeć;
    }

    public String getNarodowość() {
        return narodowość;
    }

    public void setNarodowość(String narodowość) {
        this.narodowość = narodowość;
    }

    public String getStanCiwilny() {
        return stanCiwilny;
    }

    public void setStanCiwilny(String stanCiwilny) {
        this.stanCiwilny = stanCiwilny;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getDowódOsobisty() {
        return dowódOsobisty;
    }

    public void setDowódOsobisty(String dowódOsobistu) {
        this.dowódOsobisty = dowódOsobistu;
    }

    public static List<Osoba> getExtension() {
        return extension;
    }


    private String stanCiwilny;
    private String adres;
    private String email;
    private int telefon;

    @Id
    private String dowódOsobisty;

    @OneToMany()
    protected static List<Osoba> extension = new ArrayList<>();

    /**
     * many to many relation osoba - proces karny
     */
    @OneToMany(mappedBy = "osoba")
    private List<OsobaProces_karny> osobaProces_karnyList = new ArrayList<>();




    public void addOsobaProces_karny(OsobaProces_karny osobaProces_karny) {
        if (!osobaProces_karnyList.contains(osobaProces_karny)) {
            osobaProces_karnyList.add(osobaProces_karny);
        }
    }

    public void removeOsobaProces_karny(OsobaProces_karny osobaProces_karny) {
        if (osobaProces_karnyList.contains(osobaProces_karny)) {
            osobaProces_karnyList.remove(osobaProces_karny);
        }
    }

}
