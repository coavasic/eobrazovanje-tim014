package eobrazovanje.tim014.model;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "korisnik")
public abstract class Korisnik {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jmbg;

    @Column(name="tipkorisnika")
    private String tipkorisnika;

    @Column(name = "korisnicko_ime")
    private String korisnickoIme;

    @Column
    private String lozinka;

    @Column
    private String ime;

    @Column
    private String prezime;

    @Column(name = "broj_telefona")
    private String brojTelefona;

    @Column
    private String email;

    @Column(name = "ulica_broj")
    private String ulicaBroj;

    @Column(name = "postanski_broj")
    private Integer postanskiBroj;

    @Column
    private String mesto;


    public Korisnik(){

    }

    public Korisnik(Long jmbg, String tip, String korisnickoIme, String lozinka, String ime, String prezime, String brojTelefona, String email, String ulicaBroj, Integer postanskiBroj, String mesto) {
        this.jmbg = jmbg;
        this.tipkorisnika = tip;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
        this.ulicaBroj = ulicaBroj;
        this.postanskiBroj = postanskiBroj;
        this.mesto = mesto;
    }

    public Korisnik(String tip){
        this.tipkorisnika = tip;
    }

    public Long getJmbg() {
        return jmbg;
    }

    public void setJmbg(Long jmbg) {
        this.jmbg = jmbg;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUlicaBroj() {
        return ulicaBroj;
    }

    public void setUlicaBroj(String ulicaBroj) {
        this.ulicaBroj = ulicaBroj;
    }

    public Integer getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(Integer postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getTipkorisnika() {
        return tipkorisnika;
    }

    public void setTipkorisnika(String tipkorisnika) {
        this.tipkorisnika = tipkorisnika;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "jmbg=" + jmbg +
                ", tipkorisnika='" + tipkorisnika + '\'' +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", brojTelefona='" + brojTelefona + '\'' +
                ", email='" + email + '\'' +
                ", ulicaBroj='" + ulicaBroj + '\'' +
                ", postanskiBroj=" + postanskiBroj +
                ", mesto='" + mesto + '\'' +
                '}';
    }
}
