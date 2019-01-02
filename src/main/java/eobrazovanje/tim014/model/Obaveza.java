package eobrazovanje.tim014.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "obaveza")
public class Obaveza  {

    @Id
    @GeneratedValue
    @Column(name = "obaveza_id")
    private Integer obavezaId;

    @ManyToOne
    @JoinColumn(name = "jmbg",referencedColumnName = "jmbg",nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "predmet_id", referencedColumnName = "predmet_id",nullable = false)
    private Predmet predmet;

    @Column(name = "datum_obaveze")
    private Date datumObaveze;

    @Column(nullable = false)
    private Boolean polozen;

    @Column(name = "tip_obaveze",nullable = false)
    private String tipObaveze;


    public Integer getObavezaId() {
        return obavezaId;
    }

    public void setObavezaId(Integer obavezaId) {
        this.obavezaId = obavezaId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Date getDatumObaveze() {
        return datumObaveze;
    }

    public void setDatumObaveze(Date datumObaveze) {
        this.datumObaveze = datumObaveze;
    }

    public Boolean getPolozen() {
        return polozen;
    }

    public void setPolozen(Boolean polozen) {
        this.polozen = polozen;
    }

    public String getTipObaveze() {
        return tipObaveze;
    }

    public void setTipObaveze(String tipObaveze) {
        this.tipObaveze = tipObaveze;
    }

    @Override
    public String toString() {
        return "Obaveza{" +
                "obavezaId=" + obavezaId +
                ", student=" + student +
                ", predmet=" + predmet +
                ", datumObaveze=" + datumObaveze +
                ", polozen=" + polozen +
                ", tipObaveze='" + tipObaveze + '\'' +
                '}';
    }
}
