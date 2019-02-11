package eobrazovanje.tim014.service;

import eobrazovanje.tim014.model.Pohadja;
import eobrazovanje.tim014.model.Student;
import eobrazovanje.tim014.repository.PohadjaRepo;
import eobrazovanje.tim014.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    PohadjaRepo pohadjaRepo;

    public Student getOne(Long id){
        return studentRepo.getOne(id);
    }

    public List<Student> getAll(){return studentRepo.findAll();}
    public Student add(Student student){return studentRepo.save(student);}
    public Student findByBrIndeksa(String brojIndeksa){return studentRepo.findByBrojIndeksa(brojIndeksa);}

    public List<Student> findByPredmetKojiPohadja(Integer predmetId){
        List<Student> students = pohadjaRepo.findAllByPredmet_PredmetId(predmetId).stream().map(
                pohadja -> {
                    return studentRepo.getOne(pohadja.getStudent().getJmbg());
                }
        ).collect(Collectors.toList());

        return students;
    }

    public List<Student> studentiKojiNePohadjajuPredmet(Integer predmetId){
        List<Student> students = studentRepo.findAll().stream().filter(
                student -> !findByPredmetKojiPohadja(predmetId).contains(student)
        ).collect(Collectors.toList());

        return students;
    }

}
