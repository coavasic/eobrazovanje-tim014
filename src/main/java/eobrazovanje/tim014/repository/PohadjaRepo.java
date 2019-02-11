package eobrazovanje.tim014.repository;

import eobrazovanje.tim014.model.Pohadja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PohadjaRepo extends JpaRepository<Pohadja,Integer> {
    List<Pohadja> findAllByStudentBrojIndeksa(String brojIndeksa);
    List<Pohadja> findAllByStudent_Jmbg(Long jmbg);
    List<Pohadja> findAllByPredmet_PredmetId(Integer id);
    List<Pohadja> findAllByStudent_BrojIndeksa(String brojIndeksa);
    void deleteByPredmet_PredmetIdAndStudent_Jmbg(Integer predmetId,Long jmbg);

}
