package eobrazovanje.tim014.Model;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "uplata")
public class Uplata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uplata_id")
    private Integer uplata;

    @Column(name = "datum_uplate",nullable = false)
    private Date datumUplate;

    @Column(name = "svrha_uplate",nullable = false)
    private String svrhaUplate;

    @Column(nullable = false)
    private Float iznos;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "student_id",nullable = false)
    private Student student;


    public Uplata(){

    }

    public Integer getUplata() {
        return uplata;
    }

    public void setUplata(Integer uplata) {
        this.uplata = uplata;
    }

    public Date getDatumUplate() {
        return datumUplate;
    }

    public void setDatumUplate(Date datumUplate) {
        this.datumUplate = datumUplate;
    }

    public String getSvrhaUplate() {
        return svrhaUplate;
    }

    public void setSvrhaUplate(String svrhaUplate) {
        this.svrhaUplate = svrhaUplate;
    }

    public Float getIznos() {
        return iznos;
    }

    public void setIznos(Float iznos) {
        this.iznos = iznos;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
