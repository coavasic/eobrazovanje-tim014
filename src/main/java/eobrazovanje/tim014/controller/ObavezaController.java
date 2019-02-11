package eobrazovanje.tim014.controller;

import eobrazovanje.tim014.dto.ObavezaDTO;
import eobrazovanje.tim014.model.Korisnik;
import eobrazovanje.tim014.model.Obaveza;
import eobrazovanje.tim014.repository.KorisnikRepo;
import eobrazovanje.tim014.repository.ObavezaRepo;
import eobrazovanje.tim014.service.NastavnikService;
import eobrazovanje.tim014.service.ObavezaService;
import eobrazovanje.tim014.service.PredmetService;
import eobrazovanje.tim014.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ObavezaController {


    @Autowired
    ObavezaService obavezaService;

    @Autowired
    KorisnikRepo korisnikRepo;

    @Autowired
    NastavnikService nastavnikService;

    @Autowired
    StudentService studentService;

    @Autowired
    PredmetService predmetService;

    @Autowired
    ObavezaRepo obavezaRepo;


    @GetMapping("/api/obaveze")
    public ResponseEntity<?> getAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        List<Obaveza> obaveze = new ArrayList<>();
        if(korisnik.getTipkorisnika().equals("student")){
            obaveze = obavezaService.getAll().stream().filter(
                    obaveza -> {
                        if(obaveza.getStudent().getJmbg() == korisnik.getJmbg());
                        return true;
                    }
            ).collect(Collectors.toList());
        } else if(korisnik.getTipkorisnika().equals("nastavnik")){

            obaveze = obavezaService.getAllByNastavnik(korisnik.getJmbg());

        } else {
            obaveze = obavezaService.getAll();
        }

        List<ObavezaDTO> obavezeDTO = obaveze.stream().map(
                obaveza ->  new ObavezaDTO(obaveza)
        ).collect(Collectors.toList());

        return new ResponseEntity<List<ObavezaDTO>>(obavezeDTO,HttpStatus.OK);

    }


    @PostMapping("/api/obaveza")
    public ResponseEntity<?> dodaj(@Valid @RequestBody ObavezaDTO obavezaDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!obavezaDTO.getDatumObaveze().after(new Date())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(korisnik.getTipkorisnika().equals("administrator")){
            if(!predmetService.pohadjaLiPredmet(obavezaDTO.getPredmetId(),obavezaDTO.getBrojIndeksa())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Obaveza obaveza = new Obaveza();
            obaveza.setDatumObaveze(obavezaDTO.getDatumObaveze());
            obaveza.setPolozen(obavezaDTO.isPolozen());
            obaveza.setStudent(studentService.findByBrIndeksa(obavezaDTO.getBrojIndeksa()));
            obaveza.setPredmet(predmetService.getOne(obavezaDTO.getPredmetId()));
            obaveza.setTipObaveze(obavezaDTO.getTipObaveze());

            obavezaService.save(obaveza);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/obaveza/polozen/{id}")
    public ResponseEntity<?> polozenIspit(@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        Obaveza obaveza = obavezaService.getOnt(id);
        if(korisnik==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(korisnik.getTipkorisnika().equals("student")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(korisnik.getTipkorisnika().equals("nastavnik") && !predmetService.predajeLiPredmet(obaveza.getPredmet().getPredmetId(),korisnik.getJmbg())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(obaveza.getPolozen()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        obaveza.setPolozen(true);
        obavezaService.save(obaveza);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("api/obaveza/{id}")
    public ResponseEntity<?> deleteObaveza(@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        obavezaRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
