package eobrazovanje.tim014.repository;

import eobrazovanje.tim014.model.Uplata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UplataRepo extends JpaRepository<Uplata,Integer> {
    List<Uplata> findAllByStudent_Jmbg(Long jmbg);
}
