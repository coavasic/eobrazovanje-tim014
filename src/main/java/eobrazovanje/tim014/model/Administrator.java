package eobrazovanje.tim014.model;

import javax.persistence.*;

@Entity(name = "administrator")
public class Administrator extends Korisnik{



    public Administrator(){
        super("administrator");
    }

    public Administrator(Long jmbg, String korisnickoIme, String lozinka, String ime, String prezime, String brojTelefona, String email, String ulicaBroj, Integer postanskiBroj, String mesto) {
        super(jmbg,"administrator", korisnickoIme, lozinka, ime, prezime, brojTelefona, email, ulicaBroj, postanskiBroj, mesto);

    }

    @Override
    public String toString() {
        return "Administrator{} " + super.toString();
    }
}
