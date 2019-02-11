package eobrazovanje.tim014.controller;

import eobrazovanje.tim014.model.Korisnik;
import eobrazovanje.tim014.model.Nastavnik;
import eobrazovanje.tim014.model.Student;
import eobrazovanje.tim014.repository.KorisnikRepo;
import eobrazovanje.tim014.service.NastavnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
public class NastavnikController {

    @Autowired
    NastavnikService nastavnikService;

    @Autowired
    KorisnikRepo korisnikRepo;


    @GetMapping("/api/nastavnici")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<List<Nastavnik>>(nastavnikService.getAll(),HttpStatus.OK);
    }

    @PostMapping("/api/nastavnik")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Nastavnik nastavnik){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Nastavnik>(nastavnikService.add(nastavnik),HttpStatus.OK);
    }


    @Transactional
    @DeleteMapping("/api/nastavnik/{jmbg}")
    public ResponseEntity<?> deleteNastavnik(@PathVariable Long jmbg){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        nastavnikService.delete(jmbg);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/nastavnik")
    public ResponseEntity<?> editStudent(@Valid @RequestBody Nastavnik nastavnik){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Nastavnik>(
                nastavnikService.add(nastavnik),
                HttpStatus.OK
        );
    }

    @GetMapping("/api/nastavnik/{id}")
    public ResponseEntity<?> getStudentsByPredmetId(@PathVariable Integer id){
        return new ResponseEntity<List<Nastavnik>>(nastavnikService.findByPredmetKojiPredaje(id),HttpStatus.OK);
    }

    @GetMapping("/api/nastavnik/not/{id}")
    public ResponseEntity<?> getStudentsAsdasd(@PathVariable Integer id){
        return new ResponseEntity<List<Nastavnik>>(nastavnikService.nastavnikKojiNePredajePredmet(id),HttpStatus.OK);
    }



}
