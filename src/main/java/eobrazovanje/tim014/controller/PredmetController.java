package eobrazovanje.tim014.controller;

import com.sun.deploy.nativesandbox.comm.Response;
import eobrazovanje.tim014.model.Predmet;
import eobrazovanje.tim014.repository.PredmetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@RestController
@RequestMapping(value = "/api/predmet")
public class PredmetController {


    @Autowired
    PredmetRepo predmetRepo;

    @GetMapping("/kreiraj/predmete")
    public ResponseEntity<?> cratePredmets(){

        List<Predmet> predmeti = Arrays.asList(
                new Predmet("Matematika1","Algebra"),
                new Predmet("Matematika2","Algebra"),
                new Predmet("Matematika3","Algebra"),
                new Predmet("Matematika4","Algebra"),
                new Predmet("Matematika5","Algebra"),
                new Predmet("Matematika6","Algebra"),
                new Predmet("Matematika7","Algebra"),
                new Predmet("Matematika8","Algebra"),
                new Predmet("Matematika9","Algebra"),
                new Predmet("Matematika22","Algebra"),
                new Predmet("Matematika33","Algebra"),
                new Predmet("Matematika44","Algebra"),
                new Predmet("Matematika55","Algebra"),
                new Predmet("Matematika66","Algebra")
        );

        predmetRepo.saveAll(predmeti);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/all")
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



    @PostMapping()
    public ResponseEntity<Predmet> dodajPredmet(@RequestBody Predmet predmet){

        return new ResponseEntity<Predmet>(predmetRepo.save(predmet),HttpStatus.OK);

    }





}
