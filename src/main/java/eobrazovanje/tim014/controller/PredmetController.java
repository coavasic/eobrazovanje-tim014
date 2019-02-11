package eobrazovanje.tim014.controller;

import com.sun.deploy.nativesandbox.comm.Response;
import eobrazovanje.tim014.model.Korisnik;
import eobrazovanje.tim014.model.Predmet;
import eobrazovanje.tim014.repository.KorisnikRepo;
import eobrazovanje.tim014.repository.PredmetRepo;
import eobrazovanje.tim014.repository.StudentRepo;
import eobrazovanje.tim014.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PredmetController {


    @Autowired
    PredmetRepo predmetRepo;

    @Autowired
    PredmetService predmetService;

    @Autowired
    KorisnikRepo korisnikRepo;

    @Autowired
    StudentRepo studentRepo;


    @GetMapping("api/paged")
    public ResponseEntity<Page<Predmet>> getAllPagable(@RequestParam("page") Integer page,
                                                       @RequestParam("direction") String direction,
                                                       @RequestParam("order") String order,
                                                       @RequestParam("size") Integer size){

        Sort.Direction directionSort;
        if(direction.equals("ASC")){
            directionSort  = Sort.Direction.ASC;
        } else {
            directionSort = Sort.Direction.DESC;
        }

        PageRequest request = PageRequest.of(page -1,size,directionSort,order);

        return new ResponseEntity<Page<Predmet>>(predmetRepo.findAll(request),HttpStatus.OK);


    }



    @PostMapping("api/predmet")
    public ResponseEntity<Predmet> dodajPredmet(@RequestBody Predmet predmet){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Predmet>(predmetRepo.save(predmet),HttpStatus.OK);
    }

    @PutMapping("api/predmet/{id}")
    public ResponseEntity<Predmet> editPredmet(@RequestBody Predmet predmet,@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!predmetRepo.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Predmet>(predmetRepo.save(predmet),HttpStatus.OK);
    }

    @DeleteMapping("api/predmet/{id}")
    public ResponseEntity<?> deletePredmet(@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        predmetRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




    @GetMapping("/api/predmeti")
    public ResponseEntity<?> getAll(){

        return new ResponseEntity<List<Predmet>>(predmetService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/api/predmeti/pohadja")
    public ResponseEntity<?> getAllPohadhja(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("student")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Predmet>>(this.predmetService.sviPredmetiKojeStudentPohadja(this.studentRepo.getOne(korisnik.getJmbg()).getBrojIndeksa()),HttpStatus.OK);
    }

    @GetMapping("/api/predmeti/predaje")
    public ResponseEntity<?> getAllPredaje(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("nastavnik")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Predmet>>(this.predmetService.sviPredmetiKojeNastavnikPredaje(korisnik.getJmbg()),HttpStatus.OK);
    }






}
