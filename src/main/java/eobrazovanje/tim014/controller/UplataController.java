package eobrazovanje.tim014.controller;

import eobrazovanje.tim014.dto.UplataDTO;
import eobrazovanje.tim014.model.Korisnik;
import eobrazovanje.tim014.model.Uplata;
import eobrazovanje.tim014.repository.KorisnikRepo;
import eobrazovanje.tim014.repository.StudentRepo;
import eobrazovanje.tim014.service.UplataService;
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
public class UplataController {


    @Autowired
    UplataService uplataService;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    KorisnikRepo korisnikRepo;

    @GetMapping("uplata/{id}")
    public ResponseEntity<UplataDTO> getById(@PathVariable Integer id){
        Uplata uplata = uplataService.getOne(id);
        if(uplata == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UplataDTO>(new UplataDTO(uplata),HttpStatus.OK);
    }

    @GetMapping("api/uplate")
    public ResponseEntity<List<UplataDTO>> getAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(korisnik.getTipkorisnika().equals("nastavnik")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Uplata> uplate = new ArrayList<>();
        if(korisnik.getTipkorisnika().equals("student")){
            uplate = uplataService.getByStudentJmbg(korisnik.getJmbg());
        }
        if(korisnik.getTipkorisnika().equals("administrator")){
            uplate = uplataService.getAll();
        }
        List<UplataDTO> uplataDTOS = uplate.stream().map(
                uplata -> new UplataDTO(uplata)
        ).collect(Collectors.toList());

        return new ResponseEntity<List<UplataDTO>>(uplataDTOS,HttpStatus.OK);

    }

    @PostMapping("api/uplata")
    public ResponseEntity<?> add(@Valid @RequestBody UplataDTO uplataDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Uplata uplata = new Uplata();
        uplata.setDatumUplate(new Date());
        uplata.setSvrhaUplate(uplataDTO.getSvrhaUplate());
        uplata.setIznos(uplata.getIznos());
        uplata.setStudent(studentRepo.findByBrojIndeksa(uplataDTO.getBrojIndeksa()));
        System.out.println(uplata.toString());

        return new ResponseEntity<UplataDTO>(new UplataDTO(uplataService.save(uplata)),HttpStatus.OK);

    }

    @PutMapping("api/uplata")
    public ResponseEntity<?> edit(@RequestBody UplataDTO uplataDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Uplata uplata = new Uplata();
        uplata.setUplata(uplataDTO.getId());
        uplata.setSvrhaUplate(uplataDTO.getSvrhaUplate());
        uplata.setIznos(uplataDTO.getIznos());
        uplata.setDatumUplate(uplataDTO.getDatumUplate());
        uplata.setStudent(studentRepo.findByBrojIndeksa(uplataDTO.getBrojIndeksa()));

        return new ResponseEntity<UplataDTO>(new UplataDTO(uplataService.save(uplata)),HttpStatus.OK);
    }

    @DeleteMapping("api/uplata/{id}")
    public ResponseEntity<?> deleteUplata(@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        if(!korisnik.getTipkorisnika().equals("administrator")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        uplataService.remove(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
