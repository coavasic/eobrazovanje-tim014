package eobrazovanje.tim014.controller;

import eobrazovanje.tim014.dto.PohadjaDTO;
import eobrazovanje.tim014.model.Korisnik;
import eobrazovanje.tim014.model.Pohadja;
import eobrazovanje.tim014.model.Predmet;
import eobrazovanje.tim014.model.Student;
import eobrazovanje.tim014.repository.KorisnikRepo;
import eobrazovanje.tim014.repository.PohadjaRepo;
import eobrazovanje.tim014.repository.PredmetRepo;
import eobrazovanje.tim014.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class PohadjaController {


    @Autowired
    PohadjaRepo pohadjaRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    PredmetRepo predmetRepo;

    @Autowired
    KorisnikRepo korisnikRepo;

    @PostMapping("/api/pohadja")
    public ResponseEntity<?> dodajPohadjanje(@RequestBody PohadjaDTO pohadjaDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Predmet predmet = predmetRepo.getOne(pohadjaDTO.getPredmetId());
        Student student = studentRepo.getOne(pohadjaDTO.getJmbg());

        Pohadja pohadja = new Pohadja();
        pohadja.setPredmet(predmet);
        pohadja.setStudent(student);

        pohadjaRepo.save(pohadja);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Transactional
    @DeleteMapping("/api/pohadja/{predmetId}/{jmbg}")
    public ResponseEntity<?> deletePredaje(@PathVariable Integer predmetId, @PathVariable Long jmbg){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        pohadjaRepo.deleteByPredmet_PredmetIdAndStudent_Jmbg(predmetId,jmbg);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
