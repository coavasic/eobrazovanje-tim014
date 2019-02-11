package eobrazovanje.tim014.repository;

import eobrazovanje.tim014.model.Predaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredajeRepo extends JpaRepository<Predaje,Long> {
    List<Predaje> findAllByNastavnik_Jmbg(Long jmbg);
    List<Predaje> findAllByPredmet_PredmetId(Integer id);
    void deleteByPredmet_PredmetIdAndNastavnik_Jmbg(Integer predmetId,Long jmbg);
}
