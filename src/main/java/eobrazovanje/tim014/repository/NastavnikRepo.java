package eobrazovanje.tim014.repository;

import eobrazovanje.tim014.model.Nastavnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NastavnikRepo  extends JpaRepository<Nastavnik, Long> {
    void deleteByJmbg(Long jmbg);
}
