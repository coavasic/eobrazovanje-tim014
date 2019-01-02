package eobrazovanje.tim014.repository;

import eobrazovanje.tim014.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KorisnikRepo  extends JpaRepository<Korisnik,Long> {

    @Query(value = "select * from korisnik",nativeQuery = true)
    public List<Korisnik> nadjiSve();
    public Korisnik findByKorisnickoIme(String username);
}
