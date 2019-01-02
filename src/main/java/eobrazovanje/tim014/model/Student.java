package eobrazovanje.tim014.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "student")
@DiscriminatorValue("student")
public class Student extends Korisnik {



    @Column(name = "broj_indeksa",unique = true)
    private String brojIndeksa;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Dokument> dokumenti = new ArrayList<Dokument>();

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Pohadja> pohadjanja = new ArrayList<Pohadja>();

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Obaveza> obaveze = new ArrayList<Obaveza>();




    public Student(){
        super("student");
    }

    public Student(Long jmbg, String korisnickoIme, String lozinka, String ime, String prezime, String brojTelefona, String email, String ulicaBroj, Integer postanskiBroj, String mesto,String brojIndeksa) {
        super(jmbg,"nastavnik", korisnickoIme, lozinka, ime, prezime, brojTelefona, email, ulicaBroj, postanskiBroj, mesto);
        this.brojIndeksa = brojIndeksa;
    }


    public List<Dokument> getDokumenti() {
        return dokumenti;
    }

    public void setDokumenti(List<Dokument> dokumenti) {
        this.dokumenti = dokumenti;
    }

    public List<Pohadja> getPohadjanja() {
        return pohadjanja;
    }

    public void setPohadjanja(List<Pohadja> pohadjanja) {
        this.pohadjanja = pohadjanja;
    }

    public List<Obaveza> getObaveze() {
        return obaveze;
    }

    public void setObaveze(List<Obaveza> obaveze) {
        this.obaveze = obaveze;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "brojIndeksa='" + brojIndeksa + '\'' +
                ", dokumenti=" + dokumenti +
                ", pohadjanja=" + pohadjanja +
                ", obaveze=" + obaveze +
                "} " + super.toString();
    }
}
