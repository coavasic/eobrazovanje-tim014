package eobrazovanje.tim014.dto;

import eobrazovanje.tim014.model.Dokument;

public class DokumentDTO {

    private Integer id;
    private String naziv;
    private String tipDokumenta;
    private String downloadUri;
    private String brojIndeksa;

    public DokumentDTO(Dokument dokument){
        this.id = dokument.getDokumentId();
        this.naziv = dokument.getNaziv();
        this.tipDokumenta = dokument.getTipDokumenta();
        this.downloadUri = dokument.getDownloadUri();
        this.brojIndeksa = dokument.getStudent().getBrojIndeksa();
    }

    public DokumentDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }
}
