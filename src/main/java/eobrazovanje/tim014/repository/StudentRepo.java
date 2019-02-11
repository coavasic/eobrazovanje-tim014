package eobrazovanje.tim014.repository;

import eobrazovanje.tim014.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    public Student findByKorisnickoIme(String korisnickoIme);
    public Student findByBrojIndeksa(String brojIndeksa);
    public void deleteByJmbg(Long jmbg);
}
