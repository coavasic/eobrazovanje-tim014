package eobrazovanje.tim014.model;

import javax.persistence.*;

@Entity(name = "predaje")
public class Predaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "predaje_id")
    private Integer predajeId;

    @ManyToOne
    @JoinColumn(name = "jmbg",referencedColumnName = "jmbg",nullable = false)
    private Nastavnik nastavnik;

    @ManyToOne
    @JoinColumn(name = "predmet_id",referencedColumnName = "predmet_id",nullable = false)
    private Predmet predmet;


    public Predaje(){

    }

    public Integer getPredajeId() {
        return predajeId;
    }


    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public void setPredajeId(Integer predajeId) {
        this.predajeId = predajeId;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }
}
