package eobrazovanje.tim014.service;

import eobrazovanje.tim014.model.Student;
import eobrazovanje.tim014.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public Student getOne(Integer id){
        return studentRepo.getOne(id);
    }

}
