package eobrazovanje.tim014.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(nullable = false,name = "download_uri")
    private String downloadUri;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "jmbg",referencedColumnName = "jmbg",nullable = true)
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

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    @Override
    public String toString() {
        return "Dokument{" +
                "dokumentId=" + dokumentId +
                ", naziv='" + naziv + '\'' +
                ", tipDokumenta='" + tipDokumenta + '\'' +
                ", downloadUri= '" + downloadUri +'\''+
                ", student=" + student +
                '}';
    }
}
