package eobrazovanje.tim014.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity(name = "nastavnik")
public class Nastavnik extends Korisnik  {




    @Column(name = "zvanje")
    private String zvanje;


    public Nastavnik(){
        super("nastavnik");
    }

    @JsonIgnore
    @OneToMany(mappedBy = "nastavnik",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Predaje> predavanja = new ArrayList<Predaje>();


    public Nastavnik(Long jmbg, String korisnickoIme, String lozinka, String ime, String prezime, String brojTelefona, String email, String ulicaBroj, Integer postanskiBroj, String mesto, String zvanje) {
        super(jmbg,"nastavnik", korisnickoIme, lozinka, ime, prezime, brojTelefona, email, ulicaBroj, postanskiBroj, mesto);
        this.zvanje = zvanje;
    }


    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }

    public List<Predaje> getPredavanja() {
        return predavanja;
    }

    public void setPredavanja(List<Predaje> predavanja) {
        this.predavanja = predavanja;
    }

    @Override
    public String toString() {
        return "Nastavnik{" +
                "zvanje='" + zvanje + '\'' +
                ", predavanja=" + predavanja +
                "} " + super.toString();
    }
}
