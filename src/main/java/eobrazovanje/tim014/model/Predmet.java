package eobrazovanje.tim014.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "predmet")
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "predmet_id")
    private Integer predmetId;

    @NotBlank(message = "Morate uneti naziv predmea")
    @Column(nullable = false)
    private String naziv;

    @Column
    private String opis;


    public Predmet(){

    }

    public Predmet(String naziv, String opis) {
        this.naziv = naziv;
        this.opis = opis;
    }

    public Integer getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Integer predmetId) {
        this.predmetId = predmetId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Predmet{" +
                "predmetId=" + predmetId +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }
}
