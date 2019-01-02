package eobrazovanje.tim014.repository;

import eobrazovanje.tim014.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    public Student findByKorisnickoIme(String korisnickoIme);
    public Student findByBrojIndeksa(String brojIndeksa);
}
