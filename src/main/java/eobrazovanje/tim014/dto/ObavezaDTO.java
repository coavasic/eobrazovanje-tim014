package eobrazovanje.tim014.dto;

import eobrazovanje.tim014.model.Obaveza;

import java.util.Date;

public class ObavezaDTO {
    private Integer id;
    private String brojIndeksa;
    private Integer predmetId;
    private Date datumObaveze;
    private boolean polozen;
    private String tipObaveze;
    private String predmetNaziv;
    private String imePrezime;

    public ObavezaDTO(){}

    public ObavezaDTO(Obaveza obaveza){
        this.id = obaveza.getObavezaId();
        this.brojIndeksa = obaveza.getStudent().getBrojIndeksa();
        this.predmetId = obaveza.getPredmet().getPredmetId();
        this.datumObaveze = obaveza.getDatumObaveze();
        this.polozen = obaveza.getPolozen();
        this.tipObaveze = obaveza.getTipObaveze();
        this.predmetNaziv = obaveza.getPredmet().getNaziv();
        this.imePrezime = obaveza.getStudent().getIme() + " " + obaveza.getStudent().getPrezime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public Integer getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Integer predmetId) {
        this.predmetId = predmetId;
    }

    public Date getDatumObaveze() {
        return datumObaveze;
    }

    public void setDatumObaveze(Date datumObaveze) {
        this.datumObaveze = datumObaveze;
    }

    public boolean isPolozen() {
        return polozen;
    }

    public void setPolozen(boolean polozen) {
        this.polozen = polozen;
    }

    public String getTipObaveze() {
        return tipObaveze;
    }

    public void setTipObaveze(String tipObaveze) {
        this.tipObaveze = tipObaveze;
    }

    public String getPredmetNaziv() {
        return predmetNaziv;
    }

    public void setPredmetNaziv(String predmetNaziv) {
        this.predmetNaziv = predmetNaziv;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }
}
