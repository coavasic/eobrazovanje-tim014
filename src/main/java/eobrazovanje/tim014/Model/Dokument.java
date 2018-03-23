package eobrazovanje.tim014.Model;

import javax.persistence.*;

@Entity(name = "dokument")
public class Dokument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dokument_id")
    private Integer dokumentId;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false,name = "tip_dokumenta")
    private String tipDokumenta;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "student_id",nullable = false)
    private Student student;


    public Dokument(){

    }

    public Integer getDokumentId() {
        return dokumentId;
    }

    public void setDokumentId(Integer dokumentId) {
        this.dokumentId = dokumentId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTipDokumenta() {
        return tipDokumenta;
    }

    public void setTipDokumenta(String tipDokumenta) {
        this.tipDokumenta = tipDokumenta;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
