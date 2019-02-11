package eobrazovanje.tim014.controller;

import eobrazovanje.tim014.model.Korisnik;
import eobrazovanje.tim014.model.Student;
import eobrazovanje.tim014.repository.KorisnikRepo;
import eobrazovanje.tim014.repository.StudentRepo;
import eobrazovanje.tim014.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    StudentService studentService;

    @Autowired
    KorisnikRepo korisnikRepo;

    @PostMapping("/api/student")
    public ResponseEntity<?> noviStudent(@Valid  @RequestBody Student student){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(student.toString());
        return new ResponseEntity<Student>(studentRepo.save(student),HttpStatus.OK);
    }

    @PutMapping("api/student")
    public ResponseEntity<?> editStudent(@Valid @RequestBody Student student){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Student>(
                studentRepo.save(student),
                HttpStatus.OK
        );
    }

    @GetMapping("/api/students")
    public ResponseEntity<?> getStudents(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Student>>(studentRepo.findAll(),HttpStatus.OK);
    }

    @DeleteMapping("/api/student/{jmbg}")
    public ResponseEntity<?> delete(@PathVariable Long jmbg){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.studentRepo.deleteByJmbg(jmbg);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/students/{id}")
    public ResponseEntity<?> getStudentsByPredmetId(@PathVariable Integer id){
        return new ResponseEntity<List<Student>>(studentService.findByPredmetKojiPohadja(id),HttpStatus.OK);
    }

    @GetMapping("/api/students/not/{id}")
    public ResponseEntity<?> getStudentsAsdasd(@PathVariable Integer id){
        return new ResponseEntity<List<Student>>(studentService.studentiKojiNePohadjajuPredmet(id),HttpStatus.OK);
    }

}
