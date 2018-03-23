package eobrazovanje.tim014.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "student")
public class Student extends Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "broj_indeksa",nullable = false,unique = true)
    private String brojIndeksa;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Dokument> dokumenti = new ArrayList<Dokument>();

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Pohadja> pohadjanja = new ArrayList<Pohadja>();

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Obaveza> obaveze = new ArrayList<Obaveza>();




    public Student(){
        super();
    }

    public Student(Long jmbg, String korisnickoIme, String lozinka, String ime, String prezime, String brojTelefona, String email, String ulicaBroj, Integer postanskiBroj, String mesto, Integer studentId,String brojIndeksa) {
        super(jmbg, korisnickoIme, lozinka, ime, prezime, brojTelefona, email, ulicaBroj, postanskiBroj, mesto);
        this.studentId = studentId;
        this.brojIndeksa = brojIndeksa;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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
}
