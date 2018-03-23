package eobrazovanje.tim014.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "nastavnik")
public class Nastavnik extends Korisnik {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nastavnik_id")
    private Integer nastavnikId;


    @Column(name = "zvanje",nullable = false)
    private String zvanje;


    public Nastavnik(){
        super();
    }

    @OneToMany(mappedBy = "nastavnik",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Predaje> predavanja = new ArrayList<Predaje>();


    public Nastavnik(Long jmbg, String korisnickoIme, String lozinka, String ime, String prezime, String brojTelefona, String email, String ulicaBroj, Integer postanskiBroj, String mesto, Integer profesorId, String zvanje) {
        super(jmbg, korisnickoIme, lozinka, ime, prezime, brojTelefona, email, ulicaBroj, postanskiBroj, mesto);
        this.nastavnikId = profesorId;
        this.zvanje = zvanje;
    }

    public Integer getNastavnikId() {
        return nastavnikId;
    }

    public void setNastavnikId(Integer profesorId) {
        this.nastavnikId = profesorId;
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
}
