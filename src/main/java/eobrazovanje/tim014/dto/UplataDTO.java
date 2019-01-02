package eobrazovanje.tim014.dto;

import eobrazovanje.tim014.model.Uplata;

import java.util.Date;

public class UplataDTO {

    private Integer id;
    private Date datumUplate;
    private String svrhaUplate;
    private Float iznos;
    private String brojIndeksa;


    public UplataDTO(){}

    public UplataDTO(Uplata uplata){
        this.id = uplata.getUplata();
        this.datumUplate = uplata.getDatumUplate();
        this.svrhaUplate = uplata.getSvrhaUplate();
        this.iznos = uplata.getIznos();
        this.brojIndeksa = uplata.getStudent().getBrojIndeksa();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }
}
