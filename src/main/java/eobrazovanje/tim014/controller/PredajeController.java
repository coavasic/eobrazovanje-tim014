package eobrazovanje.tim014.controller;

import eobrazovanje.tim014.dto.PohadjaDTO;
import eobrazovanje.tim014.model.*;
import eobrazovanje.tim014.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.xml.ws.Response;

@RestController
public class PredajeController {


    @Autowired
    PredajeRepo predajeRepo;

    @Autowired
    NastavnikRepo nastavnikRepo;

    @Autowired
    PredmetRepo predmetRepo;

    @Autowired
    KorisnikRepo korisnikRepo;

    @PostMapping("/api/predaje")
    public ResponseEntity<?> dodajPohadjanje(@RequestBody PohadjaDTO pohadjaDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Predmet predmet = predmetRepo.getOne(pohadjaDTO.getPredmetId());
        Nastavnik nastavnik = nastavnikRepo.getOne(pohadjaDTO.getJmbg());

        Predaje predaje = new Predaje();
        predaje.setPredmet(predmet);
        predaje.setNastavnik(nastavnik);

        predajeRepo.save(predaje);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Transactional
    @DeleteMapping("/api/predaje/{predmetId}/{jmbg}")
    public ResponseEntity<?> deletePredaje(@PathVariable Integer predmetId,@PathVariable Long jmbg){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        predajeRepo.deleteByPredmet_PredmetIdAndNastavnik_Jmbg(predmetId,jmbg);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
