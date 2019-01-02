package eobrazovanje.tim014.repository;

import eobrazovanje.tim014.model.Predmet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredmetRepo extends JpaRepository<Predmet,Long> {
    Page<Predmet> findAll(Pageable pageable);
}
