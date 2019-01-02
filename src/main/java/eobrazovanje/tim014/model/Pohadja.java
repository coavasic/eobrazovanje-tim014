package eobrazovanje.tim014.model;

import javax.persistence.*;

@Entity(name = "pohadja")
public class Pohadja {

    @Id
    @GeneratedValue
    @Column(name = "pohadja_id")
    private Integer pohadjaId;


    @ManyToOne
    @JoinColumn(name = "jmbg",referencedColumnName = "jmbg",nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "predmet_id", referencedColumnName = "predmet_id",nullable = false)
    private Predmet predmet;

    public Pohadja() {}

    public Integer getPohadjaId() {
        return pohadjaId;
    }

    public void setPohadjaId(Integer pohadjaId) {
        this.pohadjaId = pohadjaId;
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


}
